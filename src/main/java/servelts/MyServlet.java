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

import com.google.gson.Gson;

/**
 *
 * @author christosfysarakis
 */
public class MyServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/JSON;charset=UTF-8");
        JSON_Converter conv = new JSON_Converter();
        String json1 = conv.getJSONFromAjax(request.getReader());

       
        try {
            boolean isadmin = json1.contains("admin");

            EditStudentsTable eut = new EditStudentsTable();
            EditLibrarianTable edit = new EditLibrarianTable();

            if (isadmin) {

                Librarian li = new Librarian();
                li = edit.jsonToLibrarian(json1);

                String user_lib = edit.databaseusername(li.getUsername());

                String email_lib = edit.database_email(li.getEmail());

                String email_user = eut.databaseStudentUseremailToJSON(li.getEmail());

                String user_user = eut.databaseStudentUserToJSON(li.getUsername());
                boolean dup = checkDuplicates(user_lib, email_lib, email_user, user_user);
                System.out.println("VRika to  " + dup);
                if (!dup) {

                    edit.addLibrarianFromJSON(json1);
                    JsonObject jo = new JsonObject();
                    jo.addProperty("success", "Your registration was successfull");
                    response.setStatus(200);
                    response.getWriter().write(jo.toString());

                } else {

                    //   System.out.println("VRika kati vlaka ");
                    response.setStatus(409);
                    Gson gson = new Gson();
                    JsonObject jo = new JsonObject();
                    jo.addProperty("error", "Username or Email has been taken");
                    response.getWriter().write(jo.toString());

                }

            } else {

                Student su = new Student();
                su = eut.jsonToStudent(json1);
                //System.out.println("Vrike to "+li.getUsername());

                String user_lib = edit.databaseusername(su.getUsername());

                String email_lib = edit.database_email(su.getEmail());

                String email_user = eut.databaseStudentUseremailToJSON(su.getEmail());

                String user_user = eut.databaseStudentUserToJSON(su.getUsername());
                String ac_id = eut.databaseUserstudent_id(su.getStudent_id());
                boolean dup = CheckDuplicatesforStudents(user_lib, email_lib, email_user, ac_id, user_user);
                if (!dup) {

                    eut.addStudentFromJSON(json1);
                    JsonObject jo = new JsonObject();
                    jo.addProperty("success", "Your registration was successfull, it will be verified by an administrator.");
                    response.setStatus(200);
                } else {

                    response.setStatus(409);
                    Gson gson = new Gson();
                    JsonObject jo = new JsonObject();
                    jo.addProperty("error", "Some of credentials are used");
                response.getWriter().write(jo.toString());

                }

            }

        } catch (Exception e) {
            response.setStatus(403);
            JsonObject jo = new JsonObject();
            jo.addProperty("error", "Database error!");
            response.getWriter().write(jo.toString());
        }
//        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public boolean checkDuplicates(String s, String s1, String s2, String s3) {
        if (s1 != null || s2 != null || s3 != null || s != null) {
            return true;
        } else {
            return false;
        }

    }

    public boolean CheckDuplicatesforStudents(String s, String s1, String s2, String s3, String s4) {
        if (s1 != null || s2 != null || s3 != null || s != null || s4 != null) {
            return true;
        } else {
            return false;
        }

    }
}
