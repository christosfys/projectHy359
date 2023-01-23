/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.servlet.http.HttpSession;
import com.google.gson.JsonObject;
import database.tables.EditStudentsTable;
import java.sql.SQLException;
import mainClasses.JSON_Converter;
import mainClasses.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import database.tables.EditBorrowingTable;
import java.util.ArrayList;
import mainClasses.Book;
import mainClasses.Borrowing;
import database.tables.GeneralQueries;
/**
/**
 *
 * @author Christos
 */
public class SeeBorrowings extends HttpServlet {

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
         JSON_Converter conv = new JSON_Converter();
        String json1 = conv.getJSONFromAjax(request.getReader());
        HttpSession session = request.getSession();
         try {
                  String username = (String) session.getAttribute("loggedIn");
                  EditStudentsTable st = new EditStudentsTable();
                  GeneralQueries  ebt=new GeneralQueries();
                  
               
                  String json=st.getid(username);
                  
                    Gson g = new Gson();
                Student s = g.fromJson(json, Student.class); 
                    System.out.println(s.getUser_id());
            JsonArray bor = ebt.databaseBorrowing(s.getUser_id());
                  
            Gson gson = new Gson();
            JsonArray jsonlibs = gson.toJsonTree(bor).getAsJsonArray();
            
            
            
         

            response.getWriter().write(jsonlibs.toString());
            response.setStatus(200);
                  
             
         }catch (Exception e) {

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
