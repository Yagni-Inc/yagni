package com.yagni.model;

import java.sql.*;
import com.yagni.controller.*;

public class Create {
    
    private static String productId, supplierId, quantity, salePrice, wholeSale;

    public Create(String productIdIn, String quantityIn, String wholeSaleIn, String salePriceIn, String supplierIdIn){
        productId = productIdIn;
        supplierId = supplierIdIn;
        quantity = quantityIn;
        salePrice = salePriceIn;
        wholeSale = wholeSaleIn;
    }

    public void addRecord(DbConnection linkDB) {

        // Inserting into the database
        // Try catches SQLExecption
        try {
            // Creates a statement object
            Statement statement = linkDB.getConnection().createStatement();
            // Calling the execute method to execute an INSERT statement
            statement.execute("INSERT INTO `product`(product_id, quanity, Whole_sale, Sale_cost, vendor_id) VALUES ('"+productId+"',"+quantity+","+wholeSale+","+salePrice+",'"+supplierId+"')");
            System.out.println("\nSuccessfully added the product to inventory");
        } catch (SQLException e) {
            System.out.println("Oops! An error has occured.");
            System.out.println(e);
        }
    }
}
