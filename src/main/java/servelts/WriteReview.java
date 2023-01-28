/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servelts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.tables.EditStudentsTable;
import database.tables.EditBorrowingTable;
import database.tables.EditBooksInLibraryTable;
import database.tables.GeneralQueries;

import database.tables.EditReviewsTable;
import javax.servlet.http.HttpSession;
import com.google.gson.JsonObject;
import mainClasses.Student;
import mainClasses.BookInLibrary;

import mainClasses.Review;
import com.google.gson.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author christosfysarakis
 */
public class WriteReview extends HttpServlet {

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
            out.println("<title>Servlet WriteReview</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WriteReview at " + request.getContextPath() + "</h1>");
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
        System.out.println("ila");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject ja = new Gson().fromJson(request.getReader().readLine(), JsonObject.class);
     response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
//        JsonObject ja = new Gson().fromJson(request.getReader().readLine(), JsonObject.class);
        HttpSession session = request.getSession(true);
        String reviewText = ja.get("reviewText").getAsString();
        String reviewScore = ja.get("reviewScore").getAsString();
                String isbn = ja.get("isbn").getAsString();
                System.out.println(" "+ reviewText +" "+reviewScore+" "+isbn);
        
        
          try {
                  String username = (String) session.getAttribute("loggedIn");
                  EditStudentsTable st = new EditStudentsTable();
                  Review  rev=new Review();
                  
               
                  String json=st.getid(username);
                       JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                  json = jsonObject.get("user_id").getAsString();
                  int id_user=Integer.parseInt(json);
                  System.out.println("OLa kala me ton kollito ton "+ id_user);
                  rev.setUser_id(id_user);
                  rev.setIsbn(isbn);
                  rev.setReviewText(reviewText);
                  rev.setReviewScore(reviewScore);
                  EditReviewsTable ert=new EditReviewsTable();
                  ert.createNewReview(rev);
                          
                  
            
            
            
            
            
        }catch (Exception e){
        
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
