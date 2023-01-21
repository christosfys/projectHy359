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


import database.tables.EditLibrarianTable;
import javax.servlet.http.HttpSession;
import com.google.gson.JsonObject;
import mainClasses.Student ;
import mainClasses.BookInLibrary;

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
        System.out.println("EImai ego"+username);
         response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject ja = new Gson().fromJson(request.getReader().readLine(), JsonObject.class);
    //    HttpSession session = request.getSession(true);
        String isbn = ja.get("isbn").getAsString();
        
        try{
        EditBooksInLibraryTable et=new EditBooksInLibraryTable(); 
        EditStudentsTable est=new EditStudentsTable();
        
        String id_book=et.getid(isbn);
        String json=est.getid(username);
        
          Gson g = new Gson();
          BookInLibrary b =g.fromJson(id_book, BookInLibrary.class);
          Student s = g.fromJson(json, Student.class);
          System.out.println(s.getUser_id());
                JsonElement jsonElement = g.toJsonTree(s);
                 jsonElement.getAsJsonObject().addProperty("user_id",s.getUser_id());
                jsonElement.getAsJsonObject().addProperty("bookcopy_id",b.getBookcopy_id());

                jsonElement.getAsJsonObject().addProperty("fromDate","1900-01-01");
                jsonElement.getAsJsonObject().addProperty("toDate","1900-01-01");
                jsonElement.getAsJsonObject().addProperty("status","requested");
              
                String finalresult=  g.toJson(jsonElement);
                  System.out.println(finalresult);
                 EditBorrowingTable ebt=new EditBorrowingTable();
                 ebt.addBorrowingFromJSON(finalresult);

                System.out.println(finalresult);
                
                                


        
    
        System.out.println(id_book);
       
        }catch (Exception e) {

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
