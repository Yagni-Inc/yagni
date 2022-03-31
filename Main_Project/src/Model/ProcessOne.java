package Main_Project.src.Model;

import java.sql.*;
import java.util.*;
import java.io.*;
import Main_Project.src.Controller.DbConnection;

public class ProcessOne {
    
    private static String date,email,location,productId,quantity,hashedEmail,user,password;
    private static String propsFile = "Main_Project\\config.properties";
    private static DbConnection linkDb;
    private static Properties props;


    public ProcessOne(String dateIn, String emailIn, String locationIn, String productIdIn, String quantityIn){

        props = new Properties();
        try {
            props.load(new FileInputStream(propsFile));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        user = props.getProperty("user"); 
        password = props.getProperty("password");
        date = dateIn.replace("-", "");
        email = emailIn;
        location = locationIn;
        productId = productIdIn;
        quantity = quantityIn;
        //hashedEmail;
        linkDb = new DbConnection(user, password);
    }
    /**
     * ToDo List
     * Create hashed email and assign to hashedEmail variable
     * Update the inventory table with the order information (completed just need to test)
     * insert into the customer information table the email and hashedEmail variables (Completed just need to test)
     * Insert into the order information the date, hashedEmail, location, productId, and quantity variables (completed Just need to test)
     * 
     */
    public ProcessOne(){

        props = new Properties();
        try {
            props.load(new FileInputStream(propsFile));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        user = props.getProperty("user");
        System.out.println(user);
        password = props.getProperty("password");
        System.out.println(password);

        date = "";
        email = "";
        location = "";
        productId = "";
        quantity = "";
        hashedEmail = "";

        linkDb = new DbConnection(user, password);
    }
    public void updateInventory(){
        // select the product and pull the quantity from the products table and assign it to a variable for that quantity
        // Creating a statment object to pass a SQL statment to the database
        String totalStock, updatedStock;
        int intStock, intQuantity;

        try{
        Statement statement = linkDb.getConnection().createStatement();

        // Calling the executeQuery method to execute a statement to the database
        ResultSet resultSet = statement.executeQuery("SELECT * FROM yagni_inv_db.product WHERE product_id = '" + productId + "';");
        resultSet.next();
        totalStock = resultSet.getString(2);
        
        // Converting the variables to ints and updating the intStock by subtracting the input quantity from the value 
        // gained from the select statement
        intStock = Integer.parseInt(totalStock);
        intQuantity = Integer.parseInt(quantity);
        intStock = intStock - intQuantity;
        updatedStock = String.valueOf(intStock);

        //Use an update statement to update the products table in the database
        statement.execute("UPDATE `yagni_inv_db`.`product` SET `quanity` = '" + updatedStock + "' WHERE (`product_id` = '" + productId +"');");

        }catch (SQLException e){
            System.out.println(e);
        }

    }

    // Inserts into the Customer Information Table
    public void InsertCustInfo(){
        try{
            Statement statement = linkDb.getConnection().createStatement();
    
            statement.execute("INSERT INTO `yagni_inv_db`.`customer_information` (`hashed_email`, `customer_email`) VALUES ('" + hashedEmail + "', '" + email + "');");
            }catch (SQLException e){
                System.out.println(e);
            }
    }

    //Inserts into the Order history table
    public void InsertOrderHist(){
        try{
            Statement statement = linkDb.getConnection().createStatement();
    
            statement.execute("INSERT INTO `yagni_inv_db`.`purchase_history` (`order_date`, `hashed_email`, `customer_location`, `product_id`, `product_quanity`) VALUES ('" + date + "', '" + hashedEmail + "', '" + location + "', '" + productId + "', '" + quantity + "');");
            }catch (SQLException e){
                System.out.println(e);
            }
    }

    public void setDate(String dateIn){
        date = dateIn.replace("-", "");
    }

    public void setEmail(String emailIn){
        email = emailIn;
    }

    public void setLocation(String locationIn){
        location = locationIn;
    }

    public void setProductId(String prodIdIn){
        productId = prodIdIn;
    }

    public void setQuantity(String quantityIn){
        quantity = quantityIn;
    }
    
}
