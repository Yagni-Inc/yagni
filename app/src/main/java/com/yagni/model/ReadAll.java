package com.yagni.model;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.yagni.controller.*;

public class ReadAll {

    private static JTable productsTable; // variable to display returned data in a Jtable

    public ReadAll(JTable productsTableIn) {
        // initialize products table to the products table parameter
        productsTable = productsTableIn;
    } // end of constructor

    // readAll method to create a Jtable to display all products from SQL query
    public void readAll(DbConnection linkDB) {
        try {
            // Creates a staetment object to execute statements with db connection
            Statement statement = linkDB.getConnection().createStatement();

            // Executes a SQL statment that returns all data from the products table
            ResultSet resultSet = statement.executeQuery("SELECT * FROM yagni_inv_db.product;");

            // Creates a DefaultTableModel object to map returned data to
            DefaultTableModel tableModel = (DefaultTableModel) productsTable.getModel();

            // initialize strings for the data in each field
            String productId;
            String quantity;
            String wholesaleCost;
            String salePrice;
            String supplierId;

            // Creates a string array that is holds the column names for each attribute
            String[] columnName = { "Product ID", "Quantity", "Wholesale Cost", "Sale Price", "Supplier ID" };

            // Setting columns of the table model object to the column name array
            tableModel.setColumnIdentifiers(columnName);

            // Adds the result set data to rows in the JTable object procutsTable
            while (resultSet.next()) {
                productId = resultSet.getString(1);
                quantity = resultSet.getString(2);
                wholesaleCost = resultSet.getString(3);
                salePrice = resultSet.getString(4);
                supplierId = resultSet.getString(5);
                String[] row = { productId, quantity, wholesaleCost, salePrice, supplierId };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            // Prints the SQLException error if there is one
            System.out.println(e);
            System.out.println("Oops! An error has occured!");
        }
    }
}
