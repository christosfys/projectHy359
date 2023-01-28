/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servelts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.tables.EditStudentsTable;
import database.tables.EditBorrowingTable;
import database.tables.EditBooksInLibraryTable;
import java.util.Date;
import java.time.Month;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import database.tables.EditLibrarianTable;
import javax.servlet.http.HttpSession;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import mainClasses.Student;
import mainClasses.BookInLibrary;
import mainClasses.Borrowing;

import mainClasses.Librarian;
import com.google.gson.*;

/**
 *
 * @author christosfysarakis
 */
public class Request_Data extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Request_Data</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Request_Data at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("loggedIn");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject ja = new Gson().fromJson(request.getReader().readLine(), JsonObject.class);

        try {
            EditStudentsTable est = new EditStudentsTable();
            String json = est.getid(username);
      
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
            json = jsonObject.get("user_id").getAsString();
            EditBorrowingTable ebt = new EditBorrowingTable();
            JsonArray jsonlibs = ebt.threedaysexception(json);
            JsonArray finaljson= new JsonArray();
            System.out.println(jsonlibs.size());
           
            for (int i = 0; i < jsonlibs.size(); i++) {    
                
                 JsonElement element = jsonlibs.get(i);
            String todate = element.getAsJsonObject().get("todate").getAsString();
                
                
                
                
             
                System.out.println(todate);
             
		
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String calendarString = dateFormat.format(cal.getTime());
           
            
            LocalDate dateBefore = LocalDate.parse(calendarString);
	LocalDate dateAfter = LocalDate.parse(todate);
		
	//calculating number of days in between
	long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		
	//displaying the number of days
	System.out.println("EINAI OI APOSTASEI "+noOfDaysBetween);
        
            if(noOfDaysBetween<=3){
                String results=ebt.sendwarnings(json,todate);
                System.out.println("Einai to "+results);
                   JsonObject finaljsonobject= new JsonParser().parse(results).getAsJsonObject();
                   finaljson.add(finaljsonobject);
                   
                   
            
            
            }
       
        
        
        
        
            }

            System.out.println(jsonlibs.toString());

            response.getWriter().write(jsonlibs.toString());
            response.setStatus(200);

        } catch (Exception e) {
        }

        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("loggedIn");
        //System.out.println("EImai ego"+username);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject ja = new Gson().fromJson(request.getReader().readLine(), JsonObject.class);
        String isbn = ja.get("isbn").getAsString();

        try {
            EditBooksInLibraryTable et = new EditBooksInLibraryTable();
            EditStudentsTable est = new EditStudentsTable();

            String id_book = et.getid(isbn);
            String json = est.getid(username);

            Gson g = new Gson();
            BookInLibrary b = g.fromJson(id_book, BookInLibrary.class);
            Student s = g.fromJson(json, Student.class);
            System.out.println(s.getUser_id());
            JsonElement jsonElement = g.toJsonTree(s);
            jsonElement.getAsJsonObject().addProperty("user_id", s.getUser_id());
            jsonElement.getAsJsonObject().addProperty("bookcopy_id", b.getBookcopy_id());
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String calendarString = dateFormat.format(cal.getTime());
            jsonElement.getAsJsonObject().addProperty("fromDate", calendarString);

            cal.add(Calendar.MONTH, 1);
            calendarString = dateFormat.format(cal.getTime());

            jsonElement.getAsJsonObject().addProperty("toDate", calendarString);

//                Date date=new Date();
            jsonElement.getAsJsonObject().addProperty("status", "requested");
            String finalresult = g.toJson(jsonElement);
            System.out.println(finalresult);
            EditBorrowingTable ebt = new EditBorrowingTable();
            ebt.addBorrowingFromJSON(finalresult);

            System.out.println(finalresult);

            System.out.println(id_book);

        } catch (Exception e) {

        }

        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
