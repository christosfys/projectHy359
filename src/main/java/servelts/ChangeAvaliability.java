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

import javax.servlet.http.HttpSession;
import com.google.gson.JsonObject;
import mainClasses.*;
import database.tables.*;

import com.google.gson.*;
/**
 *
 * @author christosfysarakis
 */
public class ChangeAvaliability extends HttpServlet {

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
        System.out.println(username);
          JsonObject ja = new Gson().fromJson(request.getReader().readLine(), JsonObject.class);
        try{
             String isbn = ja.get("isbn").getAsString();
             EditBooksTable ebt=new EditBooksTable();
             String exist=ebt.searchbook(isbn);
             if(exist!=null){
             EditLibrarianTable edit = new EditLibrarianTable();
             String library_id=edit.getid(username);
              Gson g = new Gson();
             Librarian l=g.fromJson(library_id, Librarian.class);
             JsonElement jsonElement = g.toJsonTree(l);
              jsonElement.getAsJsonObject().addProperty("library_id",l.getLibrary_id());
              jsonElement.getAsJsonObject().addProperty("isbn",isbn);
             jsonElement.getAsJsonObject().addProperty("available","true");
              String finalresult=  g.toJson(jsonElement);
              System.out.println(finalresult);
                EditBooksInLibraryTable eblt=new EditBooksInLibraryTable();
             eblt.addBookInLibraryFromJSON(finalresult);
             response.setStatus(200);
             }else{ response.setStatus(403);
            JsonObject jo = new JsonObject();
            jo.addProperty("error", "Yparxei diathesimo");
             }
        
        
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
