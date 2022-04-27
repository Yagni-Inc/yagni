package com.yagni.model;

import java.sql.*;
import java.util.*;
import java.io.*;
import com.yagni.controller.*;

/*
* This class takes order details and updates the database with the correct information 
*/
public class ProcessOne {

    //String Variables
    private static String date;
    private static String email;
    private static String location;
    private static String productId;
    private static String quantity;
    private static String hashedEmail;
    private static String user;
    private static String password;
    private static String propsFile;

    //DbConnection Variable
    private static DbConnection linkDb;

    //Properties Variable
    private static Properties props;

    //First constructor passes in all order details
    public ProcessOne(String dateIn, String emailIn, String locationIn, String productIdIn, String quantityIn) {

        propsFile = "App\\config.properties"; 

        props = new Properties();
        try {
            props.load(new FileInputStream(propsFile));
        } catch (FileNotFoundException e) {
            System.out.println("Error Finding the properties file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error with file IO");
            e.printStackTrace();
        }
        user = props.getProperty("user");
        password = props.getProperty("password");
        date = dateIn.replace("-", "");
        email = emailIn;
        location = locationIn;
        productId = productIdIn;
        quantity = quantityIn;
        linkDb = new DbConnection(user, password);
    }

    // constructor if there are no variables to pass in
    // used if parsing though a .csv file
    public ProcessOne() {

        propsFile = "App\\config.properties";

        props = new Properties(); // creating a properties object
        try {
            props.load(new FileInputStream(propsFile));
        } catch (FileNotFoundException e) {
            System.out.println("Error Finding the properties file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error with file IO");
            e.printStackTrace();
        }

        user = props.getProperty("user");
        password = props.getProperty("password");

        date = "";
        email = "";
        location = "";
        productId = "";
        quantity = "";
        hashedEmail = "";

        linkDb = new DbConnection(user, password);
    }

    public void updateInventory() {
        // select the product and pull the quantity from the products table and assign
        // it to a variable for that quantity
        // Creating a statement object to pass a SQL statement to the database
        String totalStock, updatedStock;
        int intStock, intQuantity;

        try {
            Statement statement = linkDb.getConnection().createStatement();

            // Calling the executeQuery method to execute a statement to the database
            ResultSet resultSet = statement.executeQuery("SELECT * FROM yagni_inv_db.product" 
                                                        + " WHERE product_id = '" + productId + "';");
            resultSet.next();
            totalStock = resultSet.getString(2);

            // Converting the variables to ints and updating the intStock by subtracting the
            // input quantity from the value
            // gained from the select statement
            intStock = Integer.parseInt(totalStock);
            intQuantity = Integer.parseInt(quantity);
            intStock = intStock - intQuantity;
            updatedStock = String.valueOf(intStock);

            // Use an update statement to update the products table in the database
            statement.execute("UPDATE `yagni_inv_db`.`product` SET `quanity` = '" + updatedStock 
                            + "' WHERE (`product_id` = '" + productId + "');");

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    // Inserts into the Customer Information Table
    public void InsertCustInfo() {
        try {
            Statement statement = linkDb.getConnection().createStatement();
            ResultSet querySet = statement.executeQuery("SELECT '1' FROM `yagni_inv_db`.`customer_information` " 
                                                        +" WHERE EXISTS (SELECT * FROM `yagni_inv_db`.`customer_information` "
                                                                        + " WHERE `customer_email` ='" + email + "')");

            // if the query returns an empty RS drops to the else
            if (querySet.next()) {
                querySet = statement.executeQuery("SELECT * FROM `yagni_inv_db`.`customer_information`" 
                                                + " WHERE `customer_email` = '" + email + "'");
                querySet.next();
                hashedEmail = querySet.getString(1);
            } else {
                hashedEmail = UserEncryption.userEncryption(email);
                statement.execute("INSERT INTO `yagni_inv_db`.`customer_information` (`hashed_email`, `customer_email`)" 
                                + " VALUES ('"+ hashedEmail + "', '" + email + "');");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Inserts into the Order history table
    public void InsertOrderHist() {
        // setting up the sql statement formatted like an sql statement
        String insert = "INSERT INTO `yagni_inv_db`.`purchase_history` (`order_date`, `hashed_email`, `customer_location`, `product_id`, `product_quanity`) " 
                      + "VALUES ('" + date + "', '" + hashedEmail + "', '" + location + "', '" + productId + "', '" + quantity + "');";
        try {
            Statement statement = linkDb.getConnection().createStatement();

            statement.execute(insert);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // The fallowing methods are setters for this class
    public void setDate(String dateIn) {
        date = dateIn.replace("-", "");
    }

    public void setEmail(String emailIn) {
        email = emailIn;
    }

    public void setLocation(String locationIn) {
        location = locationIn;
    }

    public void setProductId(String prodIdIn) {
        productId = prodIdIn;
    }

    public void setQuantity(String quantityIn) {
        quantity = quantityIn;
    }
}