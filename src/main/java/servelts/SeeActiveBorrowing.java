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
import database.tables.GeneralQueries;
import javax.servlet.http.HttpSession;
import com.google.gson.JsonObject;
import database.tables.EditStudentsTable;
import database.tables.EditLibrarianTable;
import java.sql.SQLException;
import mainClasses.JSON_Converter;
import mainClasses.Student;
import mainClasses.Librarian;
import mainClasses.User;
import com.google.gson.JsonArray;





import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


/**
 *
 * @author christosfysarakis
 */
public class SeeActiveBorrowing extends HttpServlet {

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
          response.setContentType("text/JSON;charset=UTF-8");
        JSON_Converter conv = new JSON_Converter();
        String json1 = conv.getJSONFromAjax(request.getReader());
        HttpSession session = request.getSession();
       String username = (String) session.getAttribute("loggedIn");
       try{
           GeneralQueries q=new GeneralQueries();
           //select book.isbn book.title borrowing.status, students.Firstname students.LastName, students.telephone students.Univeristy
           EditLibrarianTable l =new EditLibrarianTable();
           String json=l.getid(username);
             Gson g = new Gson();
             Librarian lt = g.fromJson(json, Librarian.class);          
            JsonArray bor = q.allLibrs(lt.getLibrary_id());
                  
            
          //   JsonArray bor = q.allLibrs(lt.getLibrary_id());
            JsonArray jsonlibs = g.toJsonTree(bor).getAsJsonArray();
            
            //   System.out.println(jsonlibs.to())
            
         

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
