/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import database.tables.EditBooksTable;
import database.DB_Connection;
import database.tables.EditReviewsTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.Book;
import mainClasses.BookInLibrary;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;



/**
 *
 * @author mountant
 */
public class EditBooksInLibraryTable {

    
    public void addBookInLibraryFromJSON(String json) throws ClassNotFoundException{
         BookInLibrary msg=jsonTobookInLibrary(json);
         createNewBookInLibrary(msg);
    }
    public String bookInLibraryToJSON(BookInLibrary tr) {
        Gson gson = new Gson();

        String json = gson.toJson(tr, BookInLibrary.class);
        return json;
    }

    public BookInLibrary jsonTobookInLibrary(String json) {
        Gson gson = new Gson();
        BookInLibrary tr = gson.fromJson(json, BookInLibrary.class);
        return tr;
    }
    
    public JsonArray databaseToBookInLibrary(String id) throws SQLException, ClassNotFoundException{
          String query = ("SELECT bookcopy_id FROM booksinlibraries WHERE library_id='"+id+"'");
      Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        
        
        
        
        JsonArray ja=new JsonArray();
        ResultSet rs;
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                
                JsonObject json = DB_Connection.getResultsToJSONObject(rs);
                System.out.println(json.toString());
                Gson gson = new Gson();
                ja.add(json);
            }
            return ja;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    

    public void createBooksInLibrary() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String sql = "CREATE TABLE booksinlibraries "
                + "(bookcopy_id INTEGER not NULL AUTO_INCREMENT, "
                + "isbn  VARCHAR(13) not null,"
                + "library_id INTEGER not null,"
                + "available VARCHAR(5) not null,"
                + "FOREIGN KEY (isbn) REFERENCES books(isbn), "
                + "FOREIGN KEY (library_id) REFERENCES librarians(library_id), "
                + "PRIMARY KEY ( bookcopy_id ))";
        stmt.execute(sql);
        stmt.close();
        con.close();

    }
    
    public void updateBookInLibrary(String bookcopy_id, String available) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        System.out.println("OLa kala mpika me times " + bookcopy_id);
        String update="UPDATE booksinlibraries SET available='"+available+"'  WHERE bookcopy_id = '"+ bookcopy_id+"'";
        stmt.executeUpdate(update);
    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewBookInLibrary(BookInLibrary bi) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " booksinlibraries (isbn,library_id,available) "
                    + " VALUES ("
                    + "'" + bi.getIsbn() + "',"
                    + "'" + bi.getLibrary_id()+ "',"
                    + "'" + bi.getAvailable()+ "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The entry was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();
                 con.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditBooksTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public String getid(String isbn)  throws SQLException, ClassNotFoundException {
     Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
         try {
            rs = stmt.executeQuery("SELECT bookcopy_id FROM booksinlibraries WHERE isbn = '" + isbn + "' AND available='true' ");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
            
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
