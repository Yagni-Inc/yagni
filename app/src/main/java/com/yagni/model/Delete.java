package com.yagni.model;

import java.sql.*;
import com.yagni.controller.*;

/*
* This class Deletes a new Item in the inventory
*/
public class Delete {

    private static String deleteID;

    // CONSTRUCTOR!!!!!!!!!
    public Delete(String deleteIDin) {
        deleteID = deleteIDin;
    }
	
    // Method runs a SQL statement that deletes an Item from the database that
    // matches the ID input
    public void delete(DbConnection linkDB) {

        // Try catches SQL exception
        try {
            // Creates a statement object
            Statement delete_statement = linkDB.getConnection().createStatement();

            // Calling the execute method to execute a DELETE statement with given ID
            delete_statement.execute("DELETE FROM `yagni_inv_db`.`product` WHERE (`product_id` = '" + deleteID + "');");

            System.out.println("\nSuccessfully deleted the product from the inventory.");
            System.out.println("-----------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Oops! An error has occurred.");
            System.out.println(e);
        }
    }
}
