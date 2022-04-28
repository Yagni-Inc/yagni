package com.yagni.model;

import java.sql.*;
import com.yagni.controller.*;

/*
* This class Creates a new Item in the inventory
*/
public class Create {

    // String Variables
    private static String productId;
    private static String supplierId;
    private static String quantity;
    private static String salePrice;
    private static String wholeSale;

    // Constructor for the Create class
    // Takes in all field inputs
    public Create(String productIdIn, String quantityIn, String wholeSaleIn, String salePriceIn, String supplierIdIn) {
        productId = productIdIn;
        supplierId = supplierIdIn;
        quantity = quantityIn;
        salePrice = salePriceIn;
        wholeSale = wholeSaleIn;
    }

    // Method runs a sql insert statement
    // Takes a DbConnection object as an argument
    public void addRecord(DbConnection linkDB) {

        // Inserting into the database
        // Try catches SQLException
        try {
            // Creates a statement object
            Statement statement = linkDB.getConnection().createStatement();
            // Calling the execute method to execute an INSERT statement
            statement.execute("INSERT INTO `product`(product_id, quanity, Whole_sale, Sale_cost, vendor_id) "
                            + " VALUES ('"+productId+"',"+quantity+","+wholeSale+","+salePrice+",'"+supplierId+"')");

            System.out.println("\nSuccessfully added the product to inventory");
        } catch (SQLException e) {
            System.out.println("Oops! An error has occurred.");
            System.out.println(e);
        }
    }
}
