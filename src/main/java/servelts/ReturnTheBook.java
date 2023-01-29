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
import com.itextpdf.text.pdf.PdfWriter;
/**
 *
 * @author christosfysarakis
 */
public class ReturnTheBook extends HttpServlet {

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
         response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject ja = new Gson().fromJson(request.getReader().readLine(), JsonObject.class);
        HttpSession session = request.getSession(true);
        String borrowing_id = ja.get("borrowing_id").getAsString();
        try{
            EditBorrowingTable ebt=new EditBorrowingTable();
            int parse=Integer.parseInt(borrowing_id);
            ebt.updateBorrowing(parse,"succesEnd");
            String bookcopy=ebt.getbook(parse);
            EditBooksInLibraryTable ebit=new EditBooksInLibraryTable();
             JsonObject jsonObject = new JsonParser().parse(bookcopy).getAsJsonObject();
            bookcopy = jsonObject.get("bookcopy_id").getAsString();
            ebit.updateBookInLibrary(bookcopy,"true");
            
            
            
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
