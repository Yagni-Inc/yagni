package com.yagni.controller;

import java.sql.*;

public class DbConnection {
    // variables
    String userName, password;
    Connection linkDB;

    public DbConnection(String userNameIn, String passwordIn){

        //variables
        userName = userNameIn;
        password = passwordIn;
        
        // setting up the connection object
        linkDB = connect(userName, password);
    }

    public static Connection connect(String userName, String password){
        
        //Try block catches a class not found execption
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        //Try block catches SQLExecption
        try{
            //Making a connection to the database and setting it to a connection object
            Connection linkDB = DriverManager.getConnection("jdbc:mysql://yag-01.cldkvvrrriir.us-west-1.rds.amazonaws.com:3306/yagni_inv_db",userName,password);

            //returning the connection object so it can be used through out the app
            return linkDB;

        } catch (SQLException e) {
            System.out.println(e);
            
            //if the connection does not work it returns null.
            System.out.println("ERROR: Connection to database failed.");
            return null;
        }
    }
    
    public Connection getConnection(){
        return linkDB;
    }
    
    public void closeConnection(){

        try{
        linkDB.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
