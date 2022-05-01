package com.yagni.model;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.yagni.controller.*;

public class ReadOneOrder {

    private static String orderNum;
    private static JTable ordersTable;
    
    public ReadOneOrder(String orderNumIn, JTable ordersTableIn) {
        orderNum = orderNumIn;
        ordersTable = ordersTableIn;
    }
	
    public void readOneOrder(DbConnection linkDb) {
        try {
            // Creating a statement object to pass a SQL statment to the database
            Statement state = linkDb.getConnection().createStatement();

            // Calling the executeQuery method to execute a statement to the database
            ResultSet result = state.executeQuery("SELECT * FROM yagni_inv_db.purchase_history WHERE order_number = '" + orderNum + "';");
           
            // Creates a DefaultTableModel object
            DefaultTableModel tableModel = (DefaultTableModel) ordersTable.getModel();

            // Initialize strings for the data in each field
            String orderNum, orderDate, hashEmail, location, productID, productQuantity;

            // Creates a string array that is the size of our number of columns
            String [] columnName = {"Order Number", "Order Date", "Hashed Email", "Location", "Product ID", "Product Quantity"};

            // Setting columns of the table model object to the column name array
            tableModel.setColumnIdentifiers(columnName);

            // Printing out the result set of the SQL statement
            result.next();
            
            orderNum = result.getString(1);
            orderDate = result.getString(2);
            hashEmail = result.getString(3);
            location = result.getString(4);
            productID = result.getString(5);
            productQuantity = result.getString(6);
            String [] row = {orderNum, orderDate, hashEmail, location, productID, productQuantity};
            tableModel.addRow(row);
		} catch (SQLException e) {
            System.out.println(e);
            System.out.println("Oops! The order number you entered does not exist!");
        }
    }
}
