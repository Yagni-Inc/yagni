package com.yagni.controller;

//java sql library import 

import java.sql.*;

//Class decleration 

public class DbConnection {

    // variables

    String userName, password;
    Connection linkDB;

    // Method decleration

    public DbConnection(String userNameIn, String passwordIn) {

        // Method specific variables

        userName = userNameIn;
        password = passwordIn;

        // setting up the connection object

        linkDB = connect(userName, password);
    }

    public static Connection connect(String userName, String password) {

        // Try block catches a class not found execption

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        // Try block catches SQLExecption

        try {

            // Making a connection to the database and setting it to a connection object

            Connection linkDB = DriverManager.getConnection(
                    "jdbc:mysql://yag-01.cldkvvrrriir.us-west-1.rds.amazonaws.com:3306/yagni_inv_db", userName,
                    password);

            // returning the connection object so it can be used through out the app
            return linkDB;

            // Catch decleration for error handling of MySQL syntax issues

        } catch (SQLException e) {
            System.out.println(e);

            // Print statement for syntax error and null return

            System.out.println("ERROR: Connection to database failed.");
            return null;
        }
    }

    // Method for connection establishing

    public Connection getConnection() {
        return linkDB;
    }

    // Method that closes the connection to the MySQL database

    public void closeConnection() {

        // Try and Catch for error handling for the closeConnection method

        try {
            linkDB.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
