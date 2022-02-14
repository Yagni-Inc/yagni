import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Main {
    /** enter path to file, set filePath equal to it **/
    String filePath;
    /** method to convert file into ArrayList **/
    public void csvToArrayList(String file) {

    }

    /** method to convert file back into csv **/
    public void arrayListToCSV(ArrayList arr) {

    }

    /** search helper method. searches array list, returns index of array w/ matching product ID**/
    public int search(int id) {

    }
     /** DEFAULT CONSTRUCTOR to access and run methods **/
     public Main(){

     }

    /** MAIN LOOP **/
    public static void main (String[] args) throws IOException{

        //Main manageInv = new Main(); 
        Scanner userInput = new Scanner(System.in);
        String fileName = "inventory_team4.csv"; // variable for hardcoded file name


        boolean run = true;
        while (run) {

            int selection;

            System.out.println("Choose 1 to Create");
            System.out.println("Choose 2 to Read");
            System.out.println("Choose 3 to Update");
            System.out.println("Choose 4 to Delete");
            System.out.println("Choose 5 to EXIT");
            selection = userInput.nextInt();
            

            switch (selection) {
                case 1:

                    /** prompt user for product id, index 0**/
                    System.out.println("You have chosen to create a new product.\n");
                    
                    System.out.println("Enter new product ID:");
                    String prodID = userInput.nextLine();
                    //prodID = userInput.nextLine();

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

                    /** METHOD CALL   ADJUST AS NEEDED **/
                    Main.addRecord(prodID, quant, wholesaleCost, salePrice, supplierID, fileName);

                    break;

                case 2:
                    System.out.println("You have chosen to read the file.");
                    System.out.println("What do you want to view");
                    System.out.println("1. Display All Inventory");
                    System.out.println("2. Display one product from Inventory");
                    int readChoice = userInput.nextInt();
                    if (readChoice == 1) {
                        Main.readAll();
                    } else if (readChoice == 2){
                        System.out.println("Enter product ID of item:");
                        String readProdID = userInput.nextLine();
                        /** if product ID exists, call readOne method. if not, throw exception. **/
                    } else{
                        System.out.println("Oops! You have entered an invalid choice!");
                    }

                    break;

                case 3:
                    /** ADJUST AS NEEDED TO WORK WITH UPDATE METHOD **/
                    System.out.println("You have chosen to update an item.");

                    /** input product id of product to update**/
                    System.out.println("Enter product ID to update:");
                    int updateID = userInput.nextInt();
                    System.out.println("Enter new values. If a value must stay the same, enter nothing.");

                    System.out.println("Enter new quantity:");
                    int updateQuant = userInput.nextInt();

                    
                    System.out.println("Enter new wholesale cost:");
                    float updateWholesale = userInput.nextFloat();

                    
                    System.out.println("Enter new sale price:");
                    float updatePrice = userInput.nextFloat();

                    
                    System.out.println("Enter new supplier ID:");
                    int updateSupplierID = userInput.nextInt();

                    /** UPDATE METHOD CALL **/
                    update(updateID, updateQuant, updateWholesale, updatePrice, updateSupplierID);

                    break;

                case 4:

                    System.out.println("You have chosen to delete an item.");
                    System.out.println("Enter product ID of item to delete:");
                    int deleteID = userInput.nextInt();

                    /** METHOD CALL **/
                    delete(deleteID);

                    break;

                case 5:
                    System.out.println("Bye!");
                    run = false;
                    break;
            }
        }
    }

    /**
     * input '1'
     * CREATE METHOD called from case 1 of switch case
     * takes in 5 parameters, turns into array, appends array to end of arraylist (csv file)
     * **/
    //Method that adds records to the inventory file
  //Implemented by Kyle Zimmerman 2.12.22
  public static void addRecord(String productIdIn, int quantityIn, float wholesaleCostIn, float salePriceIn, String supplierIdIn, String file){

    


    BufferedWriter buffWrite = null; //Setting up the buffered writer variable and setting initially to null
    //String product_id, quantity, wholesale_cost, sale_price, supplier_id; //Setting up all the sting variables that I will be sett to user inputs
    Float wholesaleCost = wholesaleCostIn;
    Float salePrice = salePriceIn;
    String productId = productIdIn;
    String supplierId = supplierIdIn;
    String fileName = file;
    int quantity = quantityIn;
    

    
    // Writing to the inventory file
    // Try catches IOExecption
    try { 
      buffWrite = new BufferedWriter(new FileWriter(fileName,true)); //Setting our buffered writer to the correct file
      buffWrite.write(productId+","+quantity+","+wholesaleCost+","+salePrice+","+supplierId); //Writing to the file
      buffWrite.flush();
      buffWrite.newLine();
      buffWrite.close();
      System.out.println("Added "+productId+","+quantity+","+wholesaleCost+","+salePrice+","+supplierId+" to the inventory!\n");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

    /** input '2' **/
    /** READ METHOD **/
    /** read all or one product? **/
    /** if all, loop through ArrayList and print all to console **/

    public static void readAll() {
        String file = "test.csv"; // variable to reference .csv file
        BufferedReader reader = null; // new reader to be able to read the .csv file
        String line = ""; // variable to read each line within the file

        try{
            reader = new BufferedReader(new FileReader(file)); //instatiates the reader to read file 
            
            // Continuously read the next line until there is no more data to be read 
            while((line = reader.readLine())!= null){
                String[] row = line.split(","); //split each line at the commas

                // For each row of data, print the comma separated values 
                for(String index : row){
                    System.out.printf("%-15s", index);
                }
                System.out.println();

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

    /** if one, prompt for product id and print product array to console **/
    public static int[] readOne(int productID) {
        /** use search() method if needed**/
    }

    /** input '3' **/
    /** UPDATE METHOD **/
    /** note: if nothing is entered for a prompt, the current value will stay the same. **/
    public static void update(int prodID, int newQuant, float newWholesale, float newPrice, int newSupplierID) {

    }


    /** input '4' **/
    /** DELETE METHOD **/
    public static void delete(int prodID) {

    }

}