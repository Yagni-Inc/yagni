package com.yagni.model;

import java.sql.*;
import javax.swing.*;
import com.yagni.controller.*;

public class FinanceRepo {

    // String variable declarations
    private static String newOrders;
    private static String oldOrders;
    private static String newDollar;
    private static String oldDollar;

    // declaring new integers to calculate difference of previous week orders
    // and current week orders
    private static int diffOrders;
    private static int newIntOrders; 
    private static int oldIntOrders;    

    // declaring new double to calculate difference of previous week dollar total
    // and current week dollar total
    private static double diffDollar; 
    private static double newDoubleDollar;
    private static double oldDoubleDollar; 
    
    public FinanceRepo(){
        
        newOrders = "0";
    }
    public void financeRepo(DbConnection linkDb){
        try{
            Statement state = linkDb.getConnection().createStatement();

            //querying data for newOrders "current week order count"
            ResultSet result = state.executeQuery("SELECT Count(*) FROM yagni_inv_db.purchase_history "
                    + "where order_date >= DATE_ADD(curdate(),INTERVAL -1 WEEK) "
                    + "AND order_date <= curdate();");
            result.next();
            newOrders = result.getString(1);

            //querying data for newDollar "current week dollar sum"
            result = state.executeQuery("SELECT SUM(Sale_cost) FROM yagni_inv_db.purchase_history "
                    + "LEFT JOIN yagni_inv_db.product ON purchase_history.product_id = product.product_id "
                    + "where order_date >= DATE_ADD(curdate(),INTERVAL -1 WEEK) AND order_date <= curdate();");
            result.next();
            newDollar = result.getString(1);

            //querying data for oldOrders "previous week order count"
            result = state.executeQuery("SELECT COUNT(*) FROM yagni_inv_db.purchase_history where order_date >= "
                    + "DATE_ADD(curdate(),INTERVAL -2 WEEK) AND order_date <= DATE_ADD(curdate(),INTERVAL -1 WEEK);");
            result.next();
            oldOrders = result.getString(1);

            //changing newOrders and oldOrders from a string into integers to perform math calculation
            newIntOrders = Integer.parseInt(newOrders);
            oldIntOrders = Integer.parseInt(oldOrders);
            diffOrders = newIntOrders - oldIntOrders;
            
            //querying data for oldDollar "previous week dollar sum"
            result = state.executeQuery("SELECT SUM(Sale_cost) FROM yagni_inv_db.purchase_history "
                    + "LEFT JOIN yagni_inv_db.product ON purchase_history.product_id = product.product_id "
                    + "where order_date >= DATE_ADD(curdate(),INTERVAL -2 WEEK) "
                    + "AND order_date <= DATE_ADD(curdate(),INTERVAL -1 WEEK);");
            result.next();
            oldDollar = result.getString(1);

            //changing newDollar and oldDollar from a string into a double to correctly perform math calculation
            newDoubleDollar = Double.parseDouble(newDollar);
            oldDoubleDollar = Double.parseDouble(oldDollar);
            diffDollar = newDoubleDollar - oldDoubleDollar;
            
            //rounding diffDollar to hundredths decimal since we don't want to see fractions of a penny
            double roundDiff = Math.round(diffDollar*100.0)/100.0;
            
            //creating a pop-up that displays a message w/ variables created earlier
            JOptionPane.showMessageDialog(null,
                    "<html>New customer orders this week: " + newOrders + "<br>Dollar total of new orders this week: $"
                    + newDollar + "<br>Customer orders difference from last week vs. this week: " + diffOrders
                    + "<br>Dollar total difference of last week vs. this week: $" + roundDiff + "</html>",
              "Weekly Finance Report", JOptionPane.INFORMATION_MESSAGE);

        }catch (SQLException e) {
            System.out.println(e);
            System.out.println("Oops an error occurred!");
        } 
    }
}
