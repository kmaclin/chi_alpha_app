package com.kmac_enterprises.chialphaapp;

import java.util.ArrayList;

/**
 * Created by Kelsey Maclin on 12/23/2015.
 */
public abstract class Person {
    final private String first_name;
    final private String last_name;
    final private String email;
    final private String campus;
    final private ArrayList<Person> discipled_by = new ArrayList<Person>();
    final private ArrayList<Person> discipling = new ArrayList<Person>();
    final private AdminPermissions admin;
    public boolean firstLogIn = true;
    final private int userID;

    public Person(String f_n, String l_n, String email, String campus, AdminPermissions autho) {
        this.first_name = f_n;
        this.last_name = l_n;
        this.email = email;
        this.campus = campus;
        this.admin = autho;
        //figure out how to generate unique user IDs
        userID = 1;
    }

    public String getName() {
        return first_name + " " + last_name;
    }

    public String getFirst_name() {return first_name; }

    public String getLast_name() {return last_name; }

    public String getEmail() {
        return email;
    }

    public String getCampus() {
        return campus;
    }

    public ArrayList<Person> getDisciplers() {
        return discipled_by;
    }

    public ArrayList<Person> getDisciples() {
        return discipling;
    }

    public String toString() {
        return first_name + ":" + last_name + ":" + email + ":" + campus;
    }

    public AdminPermissions getAdminStatus() {
        return admin;
    }


    public void addDiscipler(Person person) {
        if (!(person instanceof User)) {

        }
    }

    public int hashCode() {
        int hash = email.hashCode();
        return hash;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }
        Person other = (Person)obj;
        boolean s1 = email.equals(other.getEmail());

        if (s1) {
            return true;
        }
        return false;
    }
}
