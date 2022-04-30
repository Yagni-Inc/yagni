package com.yagni.controller;

//SQL library import 

import java.sql.*;

//Class decleration 

public class DbConnection {

    // variables

    String userName, password;
    Connection linkDB;

    // Method declaration

    public DbConnection(String userNameIn, String passwordIn) {

        // method specific variables

        userName = userNameIn;
        password = passwordIn;

        // setting up the connection object

        linkDB = connect(userName, password);
    }

    public static Connection connect(String userName, String password) {

        // try block catches a class not found execption

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        // try block catches SQLExecption

        try {

            // making a connection to the database and setting it to a connection object

            Connection linkDB = DriverManager.getConnection(
                    "jdbc:mysql://yag-01.cldkvvrrriir.us-west-1.rds.amazonaws.com:3306/yagni_inv_db", userName,
                    password);

            // returning the connection object so it can be used through out the app
            return linkDB;

            // catch decleration for error handling of MySQL syntax issues

        } catch (SQLException e) {
            System.out.println(e);

            // rint statement for syntax error and null return

            System.out.println("ERROR: Connection to database failed.");
            return null;
        }
    }

    // method for connection establishing

    public Connection getConnection() {
        return linkDB;
    }

    // method that closes the connection to the MySQL database

    public void closeConnection() {

        // try and catch for error handling for the closeConnection method

        try {
            linkDB.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
