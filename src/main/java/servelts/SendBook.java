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
import database.tables.GeneralQueries;

import database.tables.EditLibrarianTable;
import javax.servlet.http.HttpSession;
import com.google.gson.JsonObject;
import mainClasses.Student;
import mainClasses.BookInLibrary;

import mainClasses.Librarian;
import com.google.gson.*;
/**
 *
 * @author christosfysarakis
 */
public class SendBook extends HttpServlet {

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
    
             System.out.println("ila");
      
     JsonObject ja = new Gson().fromJson(request.getReader().readLine(), JsonObject.class);
     response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
//        JsonObject ja = new Gson().fromJson(request.getReader().readLine(), JsonObject.class);
        HttpSession session = request.getSession(true);
        String isbn = ja.get("isbn").getAsString();
        System.out.println(isbn);
        
        
          try {
                  String username = (String) session.getAttribute("loggedIn");
                  EditStudentsTable st = new EditStudentsTable();
                  EditBooksInLibraryTable ebit=new EditBooksInLibraryTable();
                  String json=st.getid(username);
                  JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                  json = jsonObject.get("user_id").getAsString();
                  JsonArray arraywithlbs=ebit.selectealllibswithbook(isbn);
                  EditBorrowingTable ebt=new EditBorrowingTable();
                  System.out.println(arraywithlbs.toString());
               for (JsonElement element : arraywithlbs) {
                   System.out.println("ola kala me ta alania");
                        String name = element.getAsJsonObject().get("bookcopy_id").getAsString();
                        System.out.println(name +" "+ json);
                        ebt.returnbook(name,json);
                       
                        
                        
                        
                
            }
                  
                  
                  
          }catch (Exception e){
              response.setStatus(403);
    }

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
