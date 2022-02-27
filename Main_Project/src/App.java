package Main_Project.src;

import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import java.util.*;
import java.sql.*;

public class App {
    

    /** MAIN LOOP **/
    public static void main(String[] args) throws IOException {


        //Main manageInv = new Main(); 

        Scanner userInput = new Scanner(System.in); // global userInput scanner for all userInput
        String fileName = "inventory_team4.csv"; // variable for hardcoded file name
        String userName, password;

        //Connect to database and create database connection object
        System.out.println("User Name: ");
        userName = userInput.nextLine();
        System.out.println("Password: ");
        password = userInput.nextLine();
        Connection connection = connect(userName, password);
        
        boolean run = true;
        while (run) {

            int selection;
            System.out.println("-------------------------------------");
            System.out.println("Choose 1 to Create");
            System.out.println("Choose 2 to Read");
            System.out.println("Choose 3 to Update");
            System.out.println("Choose 4 to Delete");
            System.out.println("Choose 5 to EXIT");
            System.out.println("-------------------------------------");
            selection = userInput.nextInt();

            switch (selection) {
                case 1:

                    /** prompt user for product id, index 0 **/
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("You have chosen to create a new product.\n");

                    System.out.println("Enter new product ID:");
                    String prodID = userInput.nextLine();
                    prodID = userInput.nextLine();

                    /** prompt user for quantity, index 1 **/
                    System.out.println("Enter product quantity:");
                    int quant = userInput.nextInt();

                    /** prompt user for wholesale_cost, index 2 **/
                    System.out.println("Enter the wholesale cost:");
                    float wholesaleCost = userInput.nextFloat();

                    /** prompt user for sale_price, index 3 **/
                    System.out.println("Enter the sale price:");
                    float salePrice = userInput.nextFloat();

                    /** prompt user for supplier_ID, index 4 **/
                    System.out.println("Enter the supplier ID:");
                    String supplierID = userInput.nextLine();
                    supplierID = userInput.nextLine();

                    /** METHOD CALL ADJUST AS NEEDED **/
                    App.addRecord(prodID, quant, wholesaleCost, salePrice, supplierID, connection);
                    System.out.println("-----------------------------------------------------------------------------------");
                    break;

                case 2:
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("You have chosen to read the file.");
                    System.out.println("What do you want to view");
                    System.out.println("1. Display All Inventory");
                    System.out.println("2. Display one product from Inventory");
                    int readChoice = userInput.nextInt();
                    if (readChoice == 1) {
                        App.readAll();
                    } else if (readChoice == 2) {
                        System.out.println("Enter product ID of item:");
                        String readProdID = userInput.nextLine();
                        readProdID = userInput.nextLine();
                        /** if product ID exists, call readOne method. if not, throw exception. **/
                        App.readOne(readProdID);
                    } else{
                        System.out.println("Oops! You have entered an invalid choice!");
                    }
                    System.out.println("-----------------------------------------------------------------------------------");
                    break;

                case 3:
                    /** UPDATE METHOD **/
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("You have chosen to update an item.");
					System.out.println("If you wish to keep a value the same, enter nothing.");

                    /** input product id of product to update **/
                    System.out.println("Enter product ID to update:");
                    String updateID = userInput.nextLine();
                    updateID = userInput.nextLine();
                    String removalID = updateID;

                    System.out.println("Enter updated product information");

                    System.out.println("Enter new quantity:");
                    int updateQuant = userInput.nextInt();

                    System.out.println("Enter new wholesale cost:");
                    float updateWholesale = userInput.nextFloat();
                    

                    System.out.println("Enter new sale price:");
                    float updatePrice = userInput.nextFloat();

                    System.out.println("Enter new supplier ID:");
                    String updateSupplierID = userInput.nextLine();
                    updateSupplierID = userInput.nextLine();

                    /** UPDATE METHOD CALL **/
                    App.update(updateID, updateQuant, updateWholesale, updatePrice, updateSupplierID, fileName,
                            removalID);
                    System.out.println("-----------------------------------------------------------------------------------");
                    break;

                case 4:
                    /** DELETE METHOD **/
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("You have chosen to delete an item.");
                    System.out.println("Enter product ID of item to delete:");
                    String deleteID = userInput.nextLine();
                    deleteID = userInput.nextLine();

                    /** DELETE METHOD CALL **/
                    App.deleteRecord(deleteID, connection);
                    System.out.println("-----------------------------------------------------------------------------------");
                    break;

                case 5:
                    /** SYSTEM EXIT **/
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("Exiting the Inventory Management System. Have a nice day!");
                    System.out.println("-----------------------------------------------------------------------------------");
                    userInput.close();
                    run = false;
                    break;
            }
        }
    }

    /**
     * input '1'
     * CREATE METHOD called from case 1 of switch case
     * takes in 5 parameters, used in a SQL INSERT satment
     * Inserts a new record into yagni_inv_db.product
     * Implemented by Kyle Zimmerman 2.12.22
     * Revised 2.23.21
     **/
    public static void addRecord(String productIdIn, int quantityIn, float wholesaleCostIn, float salePriceIn,
            String supplierIdIn, Connection connection) {

        // String product_id, quantity, wholesale_cost, sale_price, supplier_id;
        //Setting up all the sting variables that I will be sett to user inputs
        Float wholesaleCost = wholesaleCostIn;
        Float salePrice = salePriceIn;
        String productId = productIdIn;
        String supplierId = supplierIdIn;
        int quantity = quantityIn;
        
        // Inserting into the database
        // Try catches SQLExecption
        try {
            // Creates a statement object
            Statement statement = connection.createStatement();
            // Calling the execute method to execute an INSERT statement
            statement.execute("INSERT INTO `product`(product_id, quanity, Whole_sale, Sale_cost, vendor_id) VALUES ('"+productId+"',"+quantity+","+wholesaleCost+","+salePrice+",'"+supplierId+"')");
            System.out.println("\nSuccessfully added the product to inventory");
            System.out.println("Product ID:\t"+ productId + "\nQuantity:\t" + quantity + "\nWholesale Cost:\t" + wholesaleCost + "\nSale Price:\t" + salePrice + "\nSupplier ID:\t" + supplierId);
            System.out.println("-----------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Oops! An error has occured.");
            System.out.println(e);
        }
    }

    /** input '2' **/
    /** READ METHOD **/
    /** read all or one product? **/
    /** if all, loop through array and print all to console **/
    public static void readAll() {
        String file = "test.csv"; // variable to reference .csv file
        BufferedReader reader = null; // new reader to be able to read the .csv file
        String line = ""; // variable to read each line within the file

        try {
            reader = new BufferedReader(new FileReader(file)); // instatiates the reader to read file

            // Continuously read the next line until there is no more data to be read
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(","); // split each line at the commas

                // For each row of data, print the comma separated values
                for (String index : row) {
                    System.out.printf("%-15s", index);
                }
                System.out.println();

            }
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }

    /** if one, prompt for product id and print product array to console **/
    public static void readOne(String readProdID) {
        String readOneID = readProdID;
        String file = "inventory_team4.csv";
        BufferedReader reader = null; // new reader to be able to read the .csv file
        String line = ""; // variable to read each line within the file
        String titleRow[] = {"Product ID","Quantity","Wholesale Cost","Sale Price","Supplier ID"};
        try{
            reader = new BufferedReader(new FileReader(file)); //instatiates the reader to read file 
            
            // Continuously read the next line until there is no more data to be read 
            while((line = reader.readLine())!= null){
                String[] row = line.split(","); //split each line at the commas
                if(row[0].equals(readOneID)){
                    for(String title : titleRow){
                        System.out.printf("%-15s", title);
                    }
                    System.out.println();
                    System.out.println("-----------------------------------------------------------------------------------");
                    for(String index : row){
                        System.out.printf("%-15s", index);
                    }
                    System.out.println();
                }
            }
        }
        catch(IOException err){
            err.printStackTrace();
        }
        finally{
            try{
            reader.close();
            }
            catch(IOException err){
                err.printStackTrace();
            }
        }
    }

    /** input '3' **/
    /** UPDATE METHOD **/
    /**
     * note: if nothing is entered for a prompt, the current value will stay the
     * same.
     **/

    public static void update(String updateID, int updateQuant, float updateWholesale, float updatePrice,
            String updateSupplierID, String fileName, String removalID) throws IOException {

        String deleteProduct = removalID;
        String prodID = updateID;
        int quanity = updateQuant;
        float whole_sale = updateWholesale;
        float sale_price = updatePrice;
        String supplier_ID = updateSupplierID;
        String updateFile = fileName;

        //deleteRecord(deleteProduct);
        //addRecord(prodID, quanity, whole_sale, sale_price, supplier_ID, updateFile);
		
		//set variables to old values, if any value is changed, changed that value to the new value, then concatenate sql query and execute

		//or, a set of if-else statements checking if input == null (they want the quantity the same), if not null, add string to update method
		//Statement update_statement = connection.createStatement()

		//update_statement.execute("UPDATE...........")

        System.out.println("Your file has been Successfully updated!");
        System.out.println("-----------------------------------------------------------------------------------");


    }

    /** input '4' **/
    /** DELETE METHOD **/
    public static void deleteRecord(String deleteID, Connection connection) throws IOException {
		try {
            // Creates a statement object
            Statement delete_statement = connection.createStatement();
            // Calling the execute method to execute a DELETE statement with given ID
            delete_statement.execute("DELETE FROM `yagni_inv_db`.`product` WHERE (`product_id` = '" + deleteID + "');");
            System.out.println("\nSuccessfully deleted the product from the inventory.");
            System.out.println("-----------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Oops! An error has occured.");
            System.out.println(e);
        }
    }
    /**Connection to database method**/
    /**Returns a Connection object**/
    public static Connection connect(String userName, String password){
        
        //Try block catches a class not found execption
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        //Try block catches SQLExecption
        try{
            //Making a connection to the database and setting it to a connection object
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yagni_inv_db",userName,password);

            //returning the connection object so it can be used through out the app
            return connection;

        } catch (SQLException e) {
            System.out.println(e);
        }
        //if the connection does not work it returns null.
        System.out.println("ERROR: Connection to database faild.");
        return null;

    }
}
