package com.yagni.model;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.yagni.controller.*;

public class ReadAllOrders {
    
    private static JTable ordersTable;

    public ReadAllOrders(JTable ordersTableIn){
        ordersTable = ordersTableIn;
    }

    public void readAllOrders(DbConnection linkDB) {
        
        try {
            
            Statement state = linkDB.getConnection().createStatement(); // creates a statment object

            // executes a SQL statment that reads all from the purchase history table
            ResultSet result = state.executeQuery("SELECT * FROM yagni_inv_db.purchase_history;"); 
 
            // creates a DefaultTableModel object
            DefaultTableModel tableModel = (DefaultTableModel) ordersTable.getModel();


            // initialize strings for the data in each field
            String orderNum, orderDate, hashEmail, location, productID, productQuantity;

            // Creates a string array that is the size of our number of columns
            String [] columnName = {"Order Number", "Order Date", "Hashed Email", "Location", "Product ID", "Product Quantity"};

            tableModel.setColumnIdentifiers(columnName);

            // updates the JTable object ordersTable with the result data
            while(result.next()){
                orderNum = result.getString(1);
                orderDate = result.getString(2);
                hashEmail = result.getString(3);
                location = result.getString(4);
                productID = result.getString(5);
                productQuantity = result.getString(6);
                String [] row = {orderNum, orderDate, hashEmail, location, productID, productQuantity};
                tableModel.addRow(row);
            }
			
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Oops! An error has occured!");
        } 
    }
}
