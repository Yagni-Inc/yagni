package Main_Project.src.Controller;

import java.sql.*;

public class dbConnection {
    // variables
    String userName, password;
    Connection connection;

    public dbConnection(String userNameIn, String passwordIn){

        //variables
        userName = userNameIn;
        password = passwordIn;
        
        // setting up the connection object
        connection = connect(userName, password);
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yagni_inv_db",userName,password);

            //returning the connection object so it can be used through out the app
            return connection;

        } catch (SQLException e) {
            System.out.println(e);
            
            //if the connection does not work it returns null.
            System.out.println("ERROR: Connection to database faild.");
            return null;
        }
        

    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public void closeConnection(){

        try{
        connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
