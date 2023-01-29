/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import mainClasses.Borrowing;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainClasses.Book;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 *
 * @author Mike
 */
public class EditBorrowingTable {

   
    public void addBorrowingFromJSON(String json) throws ClassNotFoundException{
         Borrowing r=jsonToBorrowing(json);
         System.out.println(r.getStatus());
         createNewBorrowing(r);
    }
    
    
     public Borrowing databaseToBorrowing(int id) throws SQLException, ClassNotFoundException{
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT * FROM borrowing WHERE borrowing_id= '" + id + "'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            Gson gson = new Gson();
            Borrowing bt = gson.fromJson(json, Borrowing.class);
            return bt;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    
    

      
     public Borrowing jsonToBorrowing(String json) {
        Gson gson = new Gson();
        Borrowing r = gson.fromJson(json, Borrowing.class);
        return r;
    }
     
         
      public String borrowingToJSON(Borrowing r) {
        Gson gson = new Gson();

        String json = gson.toJson(r, Borrowing.class);
        return json;
    }


    public void updateBorrowing(int borrowingID,String  status) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String updateQuery = "UPDATE borrowing SET status='"+status+"' WHERE borrowing_id='"+borrowingID+"'";
        
        stmt.executeUpdate(updateQuery);
        stmt.close();
        con.close();
    }
    
    public void returnbook(String bookcopy_id,String user) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String updateQuery = "UPDATE borrowing SET status='returned' WHERE bookcopy_id='"+bookcopy_id+"' AND user_id='"+user+"'";
        System.out.println(bookcopy_id+" "+user);
        stmt.executeUpdate(updateQuery);
        stmt.close();
        con.close();
    }
    
    
    
    
    
    
    
    
    
    

    public void deleteBorrowing(int randevouzID) throws SQLException, ClassNotFoundException{
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String deleteQuery = "DELETE FROM borrowing WHERE borrowing_id='" + randevouzID + "'";
        stmt.executeUpdate(deleteQuery);
        stmt.close();
        con.close();
    }



    public void createBorrowingTable() throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String sql = "CREATE TABLE borrowing "
                + "(borrowing_id INTEGER not NULL AUTO_INCREMENT, "
                + " bookcopy_id INTEGER not NULL, "
                + " user_id INTEGER not NULL, "
                + " fromdate DATE not NULL, "
                + " todate DATE not NULL, "
                + " status VARCHAR(15) not NULL, "
                + "FOREIGN KEY (user_id) REFERENCES students(user_id), "
                + "FOREIGN KEY (bookcopy_id) REFERENCES booksinlibraries(bookcopy_id), "
                + " PRIMARY KEY (borrowing_id))";
        stmt.execute(sql);
        stmt.close();
        con.close();

    }

    /**
     * Establish a database connection and add in the database.
     *
     * @throws ClassNotFoundException
     */
    public void createNewBorrowing(Borrowing bor) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String insertQuery = "INSERT INTO "
                    + " borrowing (bookcopy_id,user_id,fromDate,toDate,status)"
                    + " VALUES ("
                    + "'" + bor.getBookcopy_id() + "',"
                    + "'" + bor.getUser_id() + "',"
                    + "'" + bor.getFromDate() + "',"
                    + "'" + bor.getToDate() + "',"
                    + "'" + bor.getStatus() + "'"
                    + ")";
            //stmt.execute(table);

            stmt.executeUpdate(insertQuery);
            System.out.println("# The borrowing was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(EditBorrowingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public String getbook(int id) throws SQLException,ClassNotFoundException{
        
         Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery("SELECT bookcopy_id FROM borrowing WHERE borrowing_id='"+id+"'");
            rs.next();
            String json=DB_Connection.getResultsToJSON(rs);
            return json;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
        
      
        
    }
    
    
public JsonArray threedaysexception (String id)   throws SQLException,ClassNotFoundException{
        
        
        String query =("SELECT todate FROM borrowing WHERE user_id='"+id+"' AND status='borrowing'");
       Connection con = DB_Connection.getConnection();
//        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        JsonArray ja = new JsonArray();
        ResultSet rs;
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                JsonObject json = DB_Connection.getResultsToJSONObject(rs);
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
 public String sendwarnings(String id,String todate)      throws SQLException,ClassNotFoundException{
     String query=("SELECT bok.title "
             + "FROM booksinlibraries lib , borrowing bor,books bok"
             + " WHERE bor.user_id='"+id+" ' "
             + " AND bor.todate='"+todate+" ' "
             + " AND bor.bookcopy_id=lib.bookcopy_id"
             + " AND lib.isbn=bok.isbn");
               
             Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs;
        try {
            rs = stmt.executeQuery(query);
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
