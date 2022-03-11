package Main_Project.src.Model;

import java.sql.*;
import Main_Project.src.Controller.DbConnection;

public class Delete {

    private static String deleteID;

    public Delete (String deleteIDin){
        deleteID = deleteIDin;

    }
    public void delete(DbConnection linkDB) {

        try {
            // Creates a statement object
            Statement delete_statement = linkDB.getConnection().createStatement();
            // Calling the execute method to execute a DELETE statement with given ID
            delete_statement.execute("DELETE FROM `yagni_inv_db`.`product` WHERE (`product_id` = '" + deleteID + "');");
            System.out.println("\nSuccessfully deleted the product from the inventory.");
            System.out.println("-----------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Oops! An error has occured.");
            System.out.println(e);
        }
    
 }
} 

