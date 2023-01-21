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
import database.tables.EditLibrarianTable;
import javax.servlet.http.HttpSession;
import com.google.gson.JsonObject;
import database.tables.EditStudentsTable;
import database.tables.EditLibrarianTable;
import java.sql.SQLException;
import mainClasses.JSON_Converter;
import mainClasses.Student;
import mainClasses.Librarian;
import mainClasses.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 *
 * @author christosfysarakis
 */
public class UpdateData extends HttpServlet {

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
        response.setContentType("text/JSON;charset=UTF-8");
        JSON_Converter conv = new JSON_Converter();
        String json1 = conv.getJSONFromAjax(request.getReader());
        HttpSession session = request.getSession();
      
        EditLibrarianTable li = new EditLibrarianTable();
        

        try {
            //   System.out.println("Vrika ton "+username+" kai edose nea stoixea to  "+json1);
            EditStudentsTable st = new EditStudentsTable();
       

            String username = (String) session.getAttribute("loggedIn");
            String lib = li.databaseusername(username);
            String stu = st.databaseStudentUserToJSON(username);
            if (lib == null) {
                Student studen = st.jsonToStudent(json1);
                studen.setUsername(username);

           
                updatefunctionforstudents(studen);
                
            }else{
                  Librarian lib1 = li.jsonToLibrarian(json1);
                lib1.setUsername(username);
                updatefunctionforlibrarians(lib1);
                
            
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
    
    public void updatefunctionforstudents(User studen){
            EditStudentsTable st = new EditStudentsTable();
             
              try{
        if(studen.getPassword()!=null){
            st.updateStudent(studen.getUsername(),studen.getPassword(),"password");            
        }
        if(studen.getCountry()!=null){
              st.updateStudent(studen.getUsername(),studen.getCountry(),"country");            
           
        }
         if(studen.getAddress()!=null){
              st.updateStudent(studen.getUsername(),studen.getAddress(),"address");            
           
        }
         if(studen.getCity()!=null){
                st.updateStudent(studen.getUsername(),studen.getCity(),"city");    
         }
         if(studen.getTelephone()!=null){
               st.updateStudent(studen.getUsername(),studen.getTelephone(),"telephone");    
             
         }
         if(studen.getPersonalpage()!=null){
             st.updateStudent(studen.getUsername(),studen.getPersonalpage(),"personalpage");
         }
         
        
              }catch (Exception e) {

        } 
    }
       public void updatefunctionforlibrarians(User std){
            EditLibrarianTable st = new EditLibrarianTable();
             
              try{
        if(std.getPassword()!=null){
            st.updateLibrarian(std.getUsername(),std.getPassword(),"password");            
        }
        if(std.getCountry()!=null){
              st.updateLibrarian(std.getUsername(),std.getCountry(),"country");            
           
        }
         if(std.getAddress()!=null){
              st.updateLibrarian(std.getUsername(),std.getAddress(),"address");            
           
        }
         if(std.getCity()!=null){
                st.updateLibrarian(std.getUsername(),std.getCity(),"city");    
         }
         if(std.getTelephone()!=null){
               st.updateLibrarian(std.getUsername(),std.getTelephone(),"telephone");    
             
         }
         if(std.getPersonalpage()!=null){
             st.updateLibrarian(std.getUsername(),std.getPersonalpage(),"personalpage");
         }
         
        
              }catch (Exception e) {

        } 
    }
    
   

}   