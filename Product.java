/*
 * Product Class 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Product{
    // We should think about building a Product Class. below is some starter variables to begin working off of 
    // String product_ID; 
    // int quantity; 
    // double wholesale_cost;
    // double sale_price; 
    // String supplier_ID;

    // public Product (String product_ID, int quantity, double wholesale_cost, double sale_price, String supplier_ID){
    //     this.product_ID  = product_ID; 
    //     this.quantity = quantity; 
    //     this.wholesale_cost = wholesale_cost;
    //     this.sale_price = sale_price;
    //     this.supplier_ID = supplier_ID;

    // }

    // TODO- move read all into this readAll() method and integrate into the whole project
  

    public static void main(String[] args){
        String file = "yagni/test.csv"; // variable to reference .csv file
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
}