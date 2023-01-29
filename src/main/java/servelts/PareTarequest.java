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
//import org.json.simple.JSONObject;    

/**
 *
 * @author christosfysarakis
 */
public class PareTarequest extends HttpServlet {

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

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("loggedIn");
        System.out.println("EImai ego" + username);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            EditLibrarianTable elt = new EditLibrarianTable();
            EditBooksInLibraryTable ebt = new EditBooksInLibraryTable();

            String id_library = elt.getid(username);
            JsonObject ja = new JsonParser().parse(id_library).getAsJsonObject();
            String id = ja.get("library_id").getAsString();
            System.out.println("EInai to kleidi " + id);
            JsonArray arr = ebt.databaseToBookInLibrary(id);
            JsonArray finalArray = new JsonArray();
            GeneralQueries q = new GeneralQueries();

            for (JsonElement element : arr) {
                String name = element.getAsJsonObject().get("bookcopy_id").getAsString();

                System.out.println("BRika to Name: " + name);

                String json = q.getRequests(name);
                System.out.println(json);
                if (json != null) {
                    JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
                    System.out.println(jsonObject.toString());
                    finalArray.add(jsonObject);
                }
            }
            System.out.println("ti skata ginetai re "+finalArray.toString());
            response.getWriter().write(finalArray.toString());
                    
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
