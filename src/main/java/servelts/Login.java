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
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.JsonObject;

import com.google.gson.Gson;

/**
 *
 * @author christosfysarakis
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
           HttpSession session=request.getSession();
        if(session.getAttribute("loggedIn")!=null){
           response.setStatus(200);
           
           String u=session.getAttribute("loggedIn").toString();
           response.getWriter().write(u);
        }
        else{
            response.setStatus(409);
        }
        
        
        
        
       
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
        String username = ja.get("username").getAsString();
        String password = ja.get("password").getAsString();
        //  HttpSession session=request.getSession(true);
        try {

            EditStudentsTable eut = new EditStudentsTable();
            EditLibrarianTable edit = new EditLibrarianTable();
            String loggedinlibr = edit.librarian_validation(username, password);
            String loggedin = eut.student_validation(username, password);
            if (loggedinlibr != null || loggedin != null) {
                System.out.println(loggedin);
                session.setMaxInactiveInterval(30*60);
                
                session.setAttribute("loggedIn",username);
                JsonObject jo = new JsonObject();
                jo.addProperty("success", "You have loggedin");
                response.setStatus(200);
                response.getWriter().write(jo.toString());
            } else {
                JsonObject jo = new JsonObject();
                jo.addProperty("error", "Wrong Credential");
                response.setStatus(403);
                response.getWriter().write(jo.toString());

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
