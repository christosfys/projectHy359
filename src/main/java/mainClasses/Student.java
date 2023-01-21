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
public class Student extends User{
    
    

    int user_id;
    String student_id;
    String university,department,student_type;
    String student_id_from_date,student_id_to_date;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStudent_type() {
        return student_type;
    }

    public void setStudent_type(String student_type) {
        this.student_type = student_type;
    }

    public String getStudent_id_from_date() {
        return student_id_from_date;
    }

    public void setStudent_id_from_date(String student_id_from_date) {
        this.student_id_from_date = student_id_from_date;
    }

    public String getStudent_id_to_date() {
        return student_id_to_date;
    }

    public void setStudent_id_to_date(String student_id_to_date) {
        this.student_id_to_date = student_id_to_date;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
}
