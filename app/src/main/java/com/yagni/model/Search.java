package com.yagni.model;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.yagni.controller.*;

public class Search {
    private static String productID;
    private static JTable productsTable;
    
    public Search(String productIDIn, JTable productsTableIn){
        productID = productIDIn;
        productsTable = productsTableIn;
    }

    public void readOne(DbConnection linkDb){
        try {
            // Creating a statment object to pass a SQL statment to the database
            Statement statement = linkDb.getConnection().createStatement();

            // Calling the executeQuery method to execute a statement to the database
            ResultSet resultSet = statement.executeQuery("SELECT * FROM yagni_inv_db.product WHERE product_id = '" + productID + "';");
           
            // creates a DefaultTableModel object
            DefaultTableModel tableModel = (DefaultTableModel) productsTable.getModel();


            // initialize strings for the data in each field
            String productId, quantity, wholesaleCost, salePrice, supplierId;

            // Creates a string array that is the size of our number of columns
            String [] columnName = {"Product ID", "Quantity", "Wholesale Cost", "Sale Price", "Supplier ID"};
            
            // Setting columns of the table model object to the column name array
            tableModel.setColumnIdentifiers(columnName);

            // Printing out the result set of the SQL statment
            resultSet.next();
            
            productId = resultSet.getString(1);
            quantity = resultSet.getString(2);
            wholesaleCost = resultSet.getString(3);
            salePrice = resultSet.getString(4);
            supplierId = resultSet.getString(5);
            String [] row = {productId, quantity, wholesaleCost, salePrice, supplierId};
            tableModel.addRow(row);
            
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Oops! The product ID you entered does not exist!");
        } 
    }
}
