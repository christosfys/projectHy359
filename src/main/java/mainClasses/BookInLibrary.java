/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainClasses;

/**
 *
 * @author Mike
 */
public class BookInLibrary {
    String isbn;
    int bookcopy_id,library_id;
    String available;
    
    public int getBookcopy_id() {
        return bookcopy_id;
    }

    public void setBookcopy_id(int bookcopy_id) {
        this.bookcopy_id = bookcopy_id;
    }
    

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(int library_id) {
        this.library_id = library_id;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

 

  
    
}
