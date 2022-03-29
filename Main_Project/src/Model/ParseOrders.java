package Main_Project.src.Model;

import java.io.*;

public class ParseOrders {

    public static void parse() {
        String file = "customer_orders_team4.csv"; // variable to reference .csv file
        BufferedReader reader = null; // new reader to be able to read the .csv file
        String line = ""; // variable to read each line within the file 

        try{
            reader = new BufferedReader(new FileReader(file)); //instatiates the reader to read file
            String date, custEmail, custLocation, productId, productQuantity; 
            int count = 0;

            // Continuously read the next line until there is no more data to be read 
            while((line = reader.readLine())!= null && count < 3){

                if (count != 0){

                    String[] row = line.split(","); //split each line at the commas
                    date = row[0];
                    custEmail = row[1];
                    custLocation = row[2];
                    productId = row[3];
                    productQuantity = row[4];

                    ProcessOne item = new ProcessOne(date,custEmail,custLocation,productId,productQuantity);

                    item.updateInventory();
                    item.InsertCustInfo();

                }

                count += 1;

            }
        }
        catch(IOException err){
            err.printStackTrace();
            System.out.println("ERROR");
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
