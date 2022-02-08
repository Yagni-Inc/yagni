/*
    Programmer:        Kyle Zimmerman
    Program Date:      2/8/2022
    Program Purpose:   This program takes user inputs and adds records to the inventory CSV file. 
*/

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Add {

    public void AddRecord() throws IOException {

        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("inventory_team4.csv",true));
        Scanner strInput2 = new Scanner(System.in);
        
        String product_id, quantity, wholesale_cost, sale_price, supplier_id;

        System.out.print("Enter the product ID: ");
    	product_id = strInput2.nextLine();
    	System.out.print("Enter the Quantity: ");
    	quantity = strInput2.nextLine();
    	System.out.print("Enter the wholesale cost: ");
    	wholesale_cost = strInput2.nextLine();
    	System.out.print("Enter the sale cost: ");
    	sale_price = strInput2.nextLine();
        System.out.print("Enter the supplier ID: ");
    	supplier_id = strInput2.nextLine();

        buffWrite.write(product_id+","+quantity+","+wholesale_cost+","+sale_price+","+supplier_id);
        buffWrite.flush();
        buffWrite.newLine();
        buffWrite.close();
        strInput2.close();
    }
}