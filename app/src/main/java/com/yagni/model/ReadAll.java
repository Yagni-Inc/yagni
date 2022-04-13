package com.yagni.model;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.yagni.controller.*;

public class ReadAll {

    private static JTable productsTable;

    public ReadAll(JTable productsTableIn){
        productsTable = productsTableIn;
    }

    // readAll method takes a connection object as an argument
    // readAll creates a table based on the query that will update the JTable for the products table
    public void readAll(DbConnection linkDB) {
        
        // catches SQLExecption
        try {
            
            Statement statement = linkDB.getConnection().createStatement(); // creates a statment object

            // executes a SQL statment that reads all from the products table
            ResultSet resultSet = statement.executeQuery("SELECT * FROM yagni_inv_db.product;"); 
 
            // creates a DefaultTableModel object
            DefaultTableModel tableModel = (DefaultTableModel) productsTable.getModel();

            // initialize strings for the data in each field
            String productId, quantity, wholesaleCost, salePrice, supplierId;

            // Creates a string array that is the size of our number of columns
            String [] columnName = {"Product ID", "Quantity", "Wholesale Cost", "Sale Price", "Supplier ID"};

            // CAN USE IF WE WANT TO TARGET THE SPECIFIC COLUMN NAMES FROM THE TABLE IN THE DATABASE
            // Creates a ResultSetMetaData object
            // ResultSetMetaData rsMetaData = resultSet.getMetaData();
            // creates an int that is equal to the number columns
            // int columns = rsMetaData.getColumnCount();
            // Assosiates the column name with each column
            // for(int i = 0; i < columns; i++){
            //     columnName [i] = rsMetaData.getColumnName(i + 1);
            // }

            // Setting columns of the table model object to the column name array
            tableModel.setColumnIdentifiers(columnName);

            // updates the JTable object procutsTable with the resultset data
            while(resultSet.next()){
                productId = resultSet.getString(1);
                quantity = resultSet.getString(2);
                wholesaleCost = resultSet.getString(3);
                salePrice = resultSet.getString(4);
                supplierId = resultSet.getString(5);
                String [] row = {productId, quantity, wholesaleCost, salePrice, supplierId};
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Oops! An error has occured!");
        } 
    }
}
