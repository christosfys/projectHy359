/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.tables;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mainClasses.Librarian;

/**
 *
 * @author mountant
 */
public class GeneralQueries {

    public ArrayList<Librarian> allLibrariesHavingABookAvailable(String isbn) throws SQLException, ClassNotFoundException {
        String query = "SELECT librarians.library_id,librarians.libraryinfo,librarians.lat,librarians.lon FROM librarians,booksinlibraries\n"
                + "where  booksinlibraries.isbn='" + isbn + "' and booksinlibraries.library_id=librarians.library_id "
                + "and booksinlibraries.available=\"true\"";
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Librarian> librarians = new ArrayList<Librarian>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String json = DB_Connection.getResultsToJSON(rs);
                Gson gson = new Gson();
                Librarian lib = gson.fromJson(json, Librarian.class);
                librarians.add(lib);
            }
            return librarians;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }String isbn,title,authors,genre,url,photo;
    int pages,publicationyear;

    public JsonArray allBooksOfALibrary(int library_id) throws SQLException, ClassNotFoundException {
        String query = "  SELECT librarians.libraryname,books.isbn, books.title, books.authors,  books.genre, books.pages, books.url, books.photo,  books.publicationyear, booksinlibraries.available \n"
                + "        FROM books,booksinlibraries,librarians\n"
                + "        where  \n"
                + "       booksinlibraries.isbn=books.isbn \n"
                + "      AND librarians.library_id='" + library_id + "'\n"
                + "      AND booksinlibraries.library_id='" + library_id + "'\n";
      
        Connection con = DB_Connection.getConnection();
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

    public JsonArray allBorrowingsOfALibrary(int library_id) throws SQLException, ClassNotFoundException {
        String query = "  SELECT booksinlibraries.bookcopy_id,books.isbn, books.title, students.firstname,students.lastname,students.university,borrowing.fromdate,borrowing.todate,borrowing.status\n"
                + "        FROM books,booksinlibraries,borrowing,students\n"
                + "        where  \n"
                + "       booksinlibraries.isbn=books.isbn \n"
                + "      and \n"
                + "      booksinlibraries.bookcopy_id=borrowing.bookcopy_id\n"
                + "      AND booksinlibraries.library_id='" + library_id + "'\n"
                + "        AND borrowing.user_id=students.user_id";
          
        Connection con = DB_Connection.getConnection();
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

    public JsonArray allOpenBorrowingsOfAStudent(int user_id) throws SQLException, ClassNotFoundException {
        String query = "SELECT students.user_id,books.isbn, books.title, students.firstname,students.lastname,students.university,borrowing.fromdate,borrowing.todate,borrowing.status\n"
                + "FROM books,booksinlibraries,borrowing,students\n"
                + "where  \n"
                + "booksinlibraries.isbn=books.isbn \n"
                + "and \n"
                + "booksinlibraries.bookcopy_id=borrowing.bookcopy_id\n"
                + "and borrowing.user_id='" + user_id + "'\n"
                + "and students.user_id='" + user_id + "'\n"
                + "and borrowing.status!='successEnd'";
        
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        JsonArray ja=new JsonArray();
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

    public JsonArray allEndedBorrowingsOfAStudent(int user_id) throws SQLException, ClassNotFoundException {
        String query = "SELECT students.user_id,books.isbn, books.title, students.firstname,students.lastname,students.university,borrowing.fromdate,borrowing.todate,borrowing.status\n"
                + "FROM books,booksinlibraries,borrowing,students\n"
                + "where  \n"
                + "booksinlibraries.isbn=books.isbn \n"
                + "and \n"
                + "booksinlibraries.bookcopy_id=borrowing.bookcopy_id\n"
                + "and borrowing.user_id='" + user_id + "'\n"
                + "and students.user_id='" + user_id + "'\n"
                + "and borrowing.status='successEnd'";
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
         JsonArray ja=new JsonArray();
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

}
