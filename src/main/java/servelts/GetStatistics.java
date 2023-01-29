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
import mainClasses.Student;
import mainClasses.Librarian;
import mainClasses.User;
import database.tables.EditStudentsTable;
import database.tables.EditBooksTable;
import java.sql.SQLException;
import mainClasses.JSON_Converter;
import com.google.gson.JsonArray;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


/**
 *
 * @author christosfysarakis
 */
public class GetStatistics extends HttpServlet {

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
        
            try{
                
                
                EditStudentsTable edt=new EditStudentsTable();
                EditBooksTable ebt=new EditBooksTable();
                String bsc_results=edt.getcount("BSc");
                System.out.println(bsc_results);
                String  phd_results=edt.getcount("PhD");
                                System.out.println(phd_results);

                String mhd_results=edt.getcount("Mhd");
                                                System.out.println(mhd_results);

               // JsonArray ss=ebt.getbooksbygenre();
//                                                System.out.println(ss.toString());
            }catch (Exception e){
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
