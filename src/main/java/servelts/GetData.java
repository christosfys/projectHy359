package servelts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.tables.EditStudentsTable;
import database.tables.EditLibrarianTable;
import javax.servlet.http.HttpSession;
import com.google.gson.JsonObject;
import mainClasses.Student;
import mainClasses.Librarian;
import com.google.gson.*;

/**
 *
 * @author christosfysarakis
 */
public class GetData extends HttpServlet {

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
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("loggedIn");
//        System.out.println(username);

        try {

            EditStudentsTable edt = new EditStudentsTable();
            EditLibrarianTable elt = new EditLibrarianTable();
            String data = edt.databaseStudentUserToJSON(username);

            if (data != null) {
                System.out.println(data);
                // Gson g = new Gson();  
                Gson g = new Gson();
                Student s = g.fromJson(data, Student.class);
                JsonElement jsonElement = g.toJsonTree(s);
                jsonElement.getAsJsonObject().addProperty("position", "student");
                data = g.toJson(jsonElement);

                response.setStatus(200);
                response.getWriter().write(data);
            } else {
                data = elt.databaseLibrarianToJson(username);
                Gson g = new Gson();
                Librarian s = g.fromJson(data, Librarian.class);
                JsonElement jsonElement = g.toJsonTree(s);
                jsonElement.getAsJsonObject().addProperty("position", "librarian");
                data = g.toJson(jsonElement);

                response.setStatus(200);
                response.getWriter().write(data);
            }

        } catch (Exception e) {

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
