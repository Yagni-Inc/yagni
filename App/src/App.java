package App.src;

import java.io.Console;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.*;
import java.sql.*;

public class App {
    /** MAIN LOOP **/
    public static void main(String[] args) throws IOException {



        //Main manageInv = new Main(); 

        Console cnsl= System.console();
        Scanner userInput = new Scanner(System.in); // global userInput scanner for all userInput
        String fileName = "inventory_team4.csv"; // variable for hardcoded file name
        String userName, strPassword;
        char [] password;


        //Connect to database and create database connection object
        System.out.println("User Name: ");
        userName = userInput.nextLine();
        password = cnsl.readPassword("Password:\n");
        strPassword = String.valueOf(password);
        
        // Call the connect method and pass the username and password through
        Connection connection = connect(userName, strPassword);
        
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
                        App.readAll(connection);
                    } else if (readChoice == 2) {
                        System.out.println("Enter product ID of item:");
                        String readProdID = userInput.nextLine();
                        readProdID = userInput.nextLine();
                        /** if product ID exists, call readOne method. if not, throw exception. **/
                        App.readOne(readProdID, connection);
                    } else{
                        System.out.println("Oops! You have entered an invalid choice!");
                    }
                    System.out.println("-----------------------------------------------------------------------------------");
                    break;

                case 3:
                    /** UPDATE METHOD **/
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("You have chosen to update an item.");
					System.out.println("If you wish to keep a value the same, hit 'enter'.");

                    /** input product id of product to update **/
                    System.out.println("Enter product ID to update (REQUIRED) :");
                    String updateID = userInput.nextLine();
					updateID = userInput.nextLine();

                    System.out.println("Enter new quantity:");
					//input as String
					String updateQuant = userInput.nextLine();
					if (updateQuant.isEmpty()) { //user does not want to update quantity
						updateQuant = null;
					}

                    System.out.println("Enter new wholesale cost:");
					//input as String
                    String updateWholesale = userInput.nextLine();
					if (updateWholesale.isEmpty()) { //user does not want to update wholesale cost
                        updateWholesale = null;
					}
                    
                    System.out.println("Enter new sale price:");
					//input as String
                    String updatePrice = userInput.nextLine();
					if (updatePrice.isEmpty()) { //user does not want to update cost
						updatePrice = null;
					}

                    System.out.println("Enter new supplier ID:");
                    String updateSupplierID = userInput.nextLine();
					if (updateSupplierID.isEmpty()) { //user does not want to update supplier ID
						updateSupplierID = null;
					}

                    /** UPDATE METHOD CALL **/
                    App.update(updateID, updateQuant, updateWholesale, updatePrice, updateSupplierID, connection);
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
    /** if all, loop through the reslut set and print each row **/
    public static void readAll(Connection connection) {
        

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM yagni_inv_db.product;");

            while(resultSet.next()){
                //System.out.println(resultSet.getString(1), "%-15s", resultSet.getString(2), "%-15s", resultSet.getString(3), "%-15s", resultSet.getString(4), "%-15s", resultSet.getString(5), "\n");
                System.out.printf("%-15s%-15s%-15s%-15s%s\n",resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Oops! An error has occured!");
        } 
    }

    /** if one, prompt for product id and print result set to the console**/
    public static void readOne(String readProdID, Connection connection) {

        // Try catches a SQLEsecption
        try {
            // Creating a statment object to pass a SQL statment to the database
            Statement statement = connection.createStatement();

            // Calling the executeQuery method to execute a statement to the database
            ResultSet resultSet = statement.executeQuery("SELECT * FROM yagni_inv_db.product WHERE product_id = '" + readProdID + "';");
            
            // Printing out the result set of the SQL statment
            resultSet.next();
            System.out.println("-----------------------------------------------------------------------------------\nHere is your product:\n");
            //System.out.printf("Project ID: " + resultSet.getString(1)+"  Quantity: "+resultSet.getString(2)+"  Wholsale cost: "+resultSet.getString(3)+"  Sale Price: "+resultSet.getString(4)+"  Supplier ID: "+resultSet.getString(5)+"\n");
            System.out.println("Product ID:\t"+ resultSet.getString(1) + "\nQuantity:\t" + resultSet.getString(2) + "\nWholesale Cost:\t" + resultSet.getString(3) + "\nSale Price:\t" + resultSet.getString(4) + "\nSupplier ID:\t" + resultSet.getString(5));
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Oops! The product ID you entered does not exist!");
        } 
    }

    /** input '3' **/
    /** UPDATE METHOD **/
    public static void update(String updateID, String updateQuant, String updateWholesale, String updatePrice, String updateSupplierID, Connection connection) throws IOException {

		//UPDATE `yagni_inv_db`.`product` SET `quanity` = '1614', `Whole_sale` = '135.92', `Sale_cost` = '207.56', `vendor_id` = 'WBWVYLRD' WHERE (`product_id` = '001LORWG0PC0');

		String sql_statement = "UPDATE `yagni_inv_db`.`product` SET ";
		int count = 0;

		if (updateQuant != null){
			sql_statement = sql_statement + "`quanity` = '" + updateQuant + "'";
			count ++;
		}
		if (updateWholesale != null) {
			if (count > 0) {
				sql_statement = sql_statement + ", ";
			}
			sql_statement = sql_statement + "`Whole_sale` = '" + updateWholesale + "'";
			count ++;
		}
		if (updatePrice != null) {
			if (count > 0) {
				sql_statement = sql_statement + ", ";
			}
			sql_statement = sql_statement + "`Sale_cost` = '" + updatePrice + "'";
			count ++;
		}
		if (updateSupplierID != null) {
			if (count > 0) {
				sql_statement = sql_statement + ", ";
			}
			sql_statement = sql_statement + "`vendor_id` = '" + updateSupplierID + "'";
			count ++;
		}

		sql_statement = sql_statement + " WHERE (`product_id` = '" + updateID + "');";

		try {
            // Creates a statement object
            Statement update_statement = connection.createStatement();
            // Calling the execute method to execute a DELETE statement with given ID
            update_statement.execute(sql_statement);
            System.out.println("\nSuccessfully updated product " + updateID + " in the inventory.");
            System.out.println("-----------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Oops! An error has occured.");
            System.out.println(e);
        }
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
