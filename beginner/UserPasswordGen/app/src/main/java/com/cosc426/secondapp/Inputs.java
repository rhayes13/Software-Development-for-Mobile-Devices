package com.cosc426.secondapp;

public class Inputs {
    static String firstName;
    static String lastName;
    static String dateOfBirth;
    static String userID;
    static String userPassword;

    /* Pass in Strings when constructed */
    public Inputs(String f, String l, String dob) {
        firstName = f;
        lastName = l;
        dateOfBirth = dob;
    }

    /* Converts Strings to lowercase, returns userID String based on assignment guidelines */
    public String getUserID(String f, String l, String dob) {
        f = f.toLowerCase();
        l = l.toLowerCase();

        userID = f.substring(0, 1) + l + dob.substring(3, 5);
        return userID;
    }

    /* Converts Strings to lowercase, returns userPassword String based on assignment guidelines */
    public String getUserPassword(String f, String l, String dob) {
        f = f.toLowerCase();
        l = l.toLowerCase();

        userPassword = f.substring(0,1) + f.charAt(f.length()-1) +
                dob.substring(0, 2) + dob.substring(8, dob.length()) +
                l.substring(0, 3) + (f.length()) + (l.length());

        return userPassword;
    }
}
