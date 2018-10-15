package com.example.pasindusenarathne.testproject5;

/**
 * Created by Pasindu Senarathne on 10/14/2018.
 */

public class User {

    private String id;
    private String name;
    private String gender;
    private String dateofbirth;
    private String password;

    public static class UserDetails{

        public static final String TABLE_NAME = "user";
        public static final String T1COL1 = "userid";
        public static final String T1COL2 = "username";
        public static final String T1COL3 = "userpassword";
        public static final String T1COL4 = "userbithday";
        public static final String T1COL5 = "usergender";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
