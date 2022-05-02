package com.yagni.model;

import java.sql.*;
import javax.swing.*;
import com.yagni.controller.*;

public class MarketRepo {

    // Best customers by dollar amount
    private static String firstCustomer;
    private static String secondCustomer;
    private static String thirdCustomer;

    // Most ordered product and their dollar amount
    private static String firstProduct;
    private static String secondProduct;
    private static String thirdProduct;
    private static String firstAmt;
    private static String secAmt;
    private static String thirdAmt;

    public void dayReport(DbConnection linkDb) {
        try {
            Statement state = linkDb.getConnection().createStatement();
            /******** FOR DAILY REPORT ********/
            ResultSet result = state.executeQuery("SELECT product_id, sum(product_quanity) AS 'sum' " 
                    + "FROM yagni_inv_db.purchase_history WHERE order_date > DATE_ADD(curdate(),INTERVAL -1 DAY) " 
                    + "AND order_date <= curdate() GROUP BY product_id ORDER BY sum(product_quanity) DESC LIMIT 3; ");
            result.next();
            // 1st "most popular order"
            firstProduct = result.getString(1); 
            result.next();

            // 2nd "most popular order"
            secondProduct = result.getString(1); 
            result.next();

            // 3rd "most popular order"
            thirdProduct = result.getString(1);
            result = state.executeQuery("SELECT (SELECT sum(product_quanity) " 
                    + "FROM purchase_history) * product.Sale_cost AS 'totalSpent', customer_email " 
                    + "FROM product RIGHT JOIN purchase_history ON product.Sale_cost = purchase_history.product_quanity " 
                    + "LEFT JOIN  yagni_inv_db.customer_information ON purchase_history.hashed_email = customer_information.hashed_email " 
                    + "WHERE  order_date > date_add(curdate(), INTERVAL - 1 DAY) AND order_date <= curdate() group by customer_email " 
                    + "ORDER BY totalSpent DESC LIMIT 3; ");
            result.next();

            // 1st "most popular customer"
            firstCustomer = result.getString(2); 
            firstAmt = result.getString(1);
            result.next();

            // 2nd "most popular customer"
            secondCustomer = result.getString(2); 
            secAmt = result.getString(1);
            result.next();

            // 3rd "most popular customer"
            thirdCustomer = result.getString(2); 
            thirdAmt = result.getString(1);

            // Creating a pop-up that displays a message w/ variables created
            JOptionPane.showMessageDialog(null,
                    "<html>Most popular orders: <br>1. " + firstProduct + " <br>2. " + secondProduct + "<br>3. "
                    + thirdProduct + "<br> <br>Top customers by dollar amount: <br>1. " + firstCustomer + " ---> $" + firstAmt
                    + "<br>2. " + secondCustomer + " ---> $" + secAmt + "<br>3. " + thirdCustomer + " ---> $"
                    + thirdAmt +"</html>",
                    "Daily Market Report", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Oops an error occurred!");
        }
    }

    public void weekReport(DbConnection linkDb) {
        try {
            Statement state = linkDb.getConnection().createStatement();
            /******** FOR WEEKLY REPORT ********/
            ResultSet result = state.executeQuery("SELECT product_id, sum(product_quanity) AS 'sum' " 
                    + "FROM yagni_inv_db.purchase_history WHERE order_date > DATE_ADD(curdate(),INTERVAL -1 WEEK) " 
                    + "AND order_date <= curdate() GROUP BY product_id ORDER BY sum(product_quanity) DESC LIMIT 3; ");
            result.next();
    
            // 1st "Most popular order"
            firstProduct = result.getString(1); 
            result.next();

            // 2nd "most popular order"
            secondProduct = result.getString(1); 
            result.next();

            // 3rd "most popular order"
            thirdProduct = result.getString(1); 
            result = state.executeQuery("SELECT (SELECT sum(product_quanity)" 
                    + "FROM purchase_history) * product.Sale_cost AS 'totalSpent', customer_email " 
                    + "FROM product RIGHT JOIN purchase_history ON product.Sale_cost = purchase_history.product_quanity "
                    + "LEFT JOIN  yagni_inv_db.customer_information ON purchase_history.hashed_email = customer_information.hashed_email " 
                    + "WHERE  order_date > date_add(curdate(), INTERVAL - 1 WEEK) AND order_date <= curdate() group by customer_email "
                    + "ORDER BY totalSpent DESC LIMIT 3; ");
            result.next();
            // 1st "most popular customer"
            firstCustomer = result.getString(2); 
            firstAmt = result.getString(1);
            result.next();

            // 2nd "most popular customer"
            secondCustomer = result.getString(2); 
            secAmt = result.getString(1);

            // 3rd "most popular customer"
            result.next();
            thirdCustomer = result.getString(2); 
            thirdAmt = result.getString(1);

            // creating a pop-up that displays a message w/ variables created
            JOptionPane.showMessageDialog(null,
                    "<html>Most popular orders:<br>1. " + firstProduct + "<br>2. " + secondProduct + "<br>3. "
                    + thirdProduct +
                    "<br> <br>Top customers by dollar amount:<br>1.  " + firstCustomer + " ---> $" + firstAmt
                    + "<br>2. " + secondCustomer + " ---> $" + secAmt + "<br>3. " + thirdCustomer + " ---> $"
                    + thirdAmt + "</html>", "Weekly Market Report", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException e) {
            System.out.println(e);
            System.out.println("Oops an error occurred!");
        }
    }

    public void monthReport(DbConnection linkDb) {
        try {
            Statement state = linkDb.getConnection().createStatement();
            /******** FOR MONTHLY REPORT ********/
            ResultSet result = state.executeQuery("SELECT product_id, sum(product_quanity) AS 'sum' " 
                    + "FROM yagni_inv_db.purchase_history WHERE order_date > " 
                    + "DATE_ADD(curdate(),INTERVAL -1 MONTH) AND order_date <= curdate() GROUP BY product_id "
                    + "ORDER BY sum(product_quanity) DESC LIMIT 3; ");
            result.next();

            // 1st "Most popular order"
            firstProduct = result.getString(1); 
            result.next();

            // 2nd "most popular order"
            secondProduct = result.getString(1); 
            result.next();

            // 3rd "most popular order"
            thirdProduct = result.getString(1); 
            result = state.executeQuery("SELECT (SELECT sum(product_quanity) "
                    + "FROM purchase_history) * product.Sale_cost AS 'totalSpent', customer_email "
                    + "FROM product RIGHT JOIN purchase_history ON product.Sale_cost = purchase_history.product_quanity "
                    + "LEFT JOIN  yagni_inv_db.customer_information ON purchase_history.hashed_email = customer_information.hashed_email " 
                    + "WHERE  order_date > date_add(curdate(), INTERVAL - 1 MONTH) AND order_date <= curdate() group by customer_email "
                    + "ORDER BY totalSpent DESC LIMIT 3; ");
            result.next();

            // 1st "most popular customer"
            firstCustomer = result.getString(2); 
            firstAmt = result.getString(1);

            // 2nd "most popular customer"
            result.next();
            secondCustomer = result.getString(2); 
            secAmt = result.getString(1);
            result.next();

            // 3rd "most popular customer"
            thirdCustomer = result.getString(2); 
            thirdAmt = result.getString(1);

            // creating a pop-up that displays a message w/ variables created
            JOptionPane.showMessageDialog(null,
                    "<html>Most popular orders:<br>1. " + firstProduct + "<br>2. " + secondProduct + "<br>3. "
                    + thirdProduct + "<br> <br>Top customers by dollar amount:<br>1. " 
                    + firstCustomer + " ---> $" + firstAmt
                    + "<br>2. " + secondCustomer + " ---> $" + secAmt + "<br>3. " + thirdCustomer + " ---> $"
                    + thirdAmt + "</html>","Monthly Market Report", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Oops an error occurred!");
        }
    }
}
