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
import database.tables.EditLibrarianTable;
import java.sql.SQLException;
import mainClasses.JSON_Converter;
import com.google.gson.JsonObject;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 *
 * @author christosfysarakis
 */
public class DeleteUser extends HttpServlet {

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
   
        System.out.println("MPika");
          response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject ja = new Gson().fromJson(request.getReader().readLine(), JsonObject.class);
        HttpSession session = request.getSession(true);
        String username = ja.get("username").getAsString();
        System.out.println(username);
        try {
            EditStudentsTable eut = new EditStudentsTable();
            EditLibrarianTable edit = new EditLibrarianTable();
            String user_lib = edit.databaseusername(username);
        System.out.println(user_lib );
            String st_user = eut.databaseStudentUserToJSON(username);
            if (st_user == null && user_lib == null) {
             response.setStatus(409);
                Gson gson = new Gson();
                JsonObject jo = new JsonObject();
                jo.addProperty("error", "The user hasn't exist");
                response.getWriter().write(jo.toString());

            } else if (st_user == null) { //iagrafi librarian
                edit.deleteuser(username);
                response.setStatus(200);
            } else { // diagrafi user
                eut.deleteuser(username);
                response.setStatus(200);
            }
        } catch (Exception e) {
            response.setStatus(403);
            JsonObject jo = new JsonObject();
            jo.addProperty("error", "Database error!");
            response.getWriter().write(jo.toString());
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
