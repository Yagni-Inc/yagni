package com.yagni.model;

import java.io.*;

/*
* This class reads through a CSV until finished and calls
* the processOne methods to create to process the orders
*/
public class ParseOrders {

    private static String file;

    private static String line;

    private static BufferedReader reader;

    private static ProcessOne item;

    ParseOrders() {
        file = "customer_orders_team4.csv"; // variable to reference .csv file
        reader = null; // new reader to be able to read the .csv file
        line = ""; // variable to read each line within the file
        item = new ProcessOne();
    }

    public static void main(String[] args) {
        ParseOrders orders = new ParseOrders();
        orders.parse();
    }

    public void parse() {
        try {
            reader = new BufferedReader(new FileReader(file)); // instantiates the reader to read file
            String date, custEmail, custLocation, productId, productQuantity;
            int count = 0;

            // Continuously read the next line until there is no more data to be read
            while ((line = reader.readLine()) != null) {

                if (count != 0) {
                    // split each line at the commas
                    String[] row = line.split(","); 

                    // assigning to the variables from the string array
                    date = row[0];
                    custEmail = row[1];
                    custLocation = row[2];
                    productId = row[3];
                    productQuantity = row[4];

                    // calling the set methods for each variable
                    item.setDate(date);
                    item.setEmail(custEmail);
                    item.setLocation(custLocation);
                    item.setProductId(productId);
                    item.setQuantity(productQuantity);

                    // calling the methods to update the database
                    item.updateInventory();
                    item.InsertCustInfo();
                    item.InsertOrderHist();
                }
                count += 1;
                System.out.println(count);
            }
            System.out.println("Data Successfully entered into Data Base!");
            System.exit(0);
        } catch (IOException err) {
            err.printStackTrace();
            System.out.println("ERROR");
        } finally {
            try {
                reader.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }
}