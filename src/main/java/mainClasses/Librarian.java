/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainClasses;

import mainClasses.User;

/**
 *
 * @author Mike
 */
public class Librarian extends User{
    int library_id;
    String libraryname,libraryinfo;

    public int getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(int library_id) {
        this.library_id = library_id;
    }

    public String getLibraryname() {
        return libraryname;
    }

    public void setLibraryname(String libraryname) {
        this.libraryname = libraryname;
    }

    public String getLibraryinfo() {
        return libraryinfo;
    }

    public void setLibraryinfo(String libraryinfo) {
        this.libraryinfo = libraryinfo;
    }


  
}
