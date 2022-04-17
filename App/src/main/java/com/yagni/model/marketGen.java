package com.yagni.model;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.yagni.controller.*;


public class marketGen {

    try{

        private static JOptionPane dayReport;  
        private static JOptionPane weekReport;
        private static JOptionPane monthReport;
        Statement stmt = linkDb.getConnection().createStatement();  

        public void dayReport(dayReport) {
            resultSet rs1 = stmt.executeQuery("SELECT product_id, sum(product_quanity) AS 'sum' FROM yagni_inv_db.purchase_history WHERE order_date > DATEADD(curdate(),INTERVAL -1 DAY) AND order_date <= curdate() GROUP BY product_id ORDER BY sum(product_quanity) DESC LIMIT 3;");
            resultSet rs2 = stmt.executeQuery("SELECT (SELECT sum(product_quanity) FROM purchase_history) * product.Sale_cost AS 'totalSpent', customer_email FROM product RIGHT JOIN purchase_history ON product.Sale_cost = purchase_history.product_quanity LEFT JOIN  yagni_inv_db.customer_information ON purchase_history.hashed_email = customer_information.hashed_email WHERE  order_date > date_add(curdate(), INTERVAL - 1 DAY) AND order_date <= curdate() group by customer_email ORDER BY totalSpent DESC LIMIT 3");

        }
    
        public void weekReport(weekReport) {
            resultSet rs1 = stmt.exectureQuery("SELECT product_id, sum(product_quanity) AS 'sum' FROM yagni_inv_db.purchase_history WHERE order_date > DATEADD(curdate(),INTERVAL -1 WEEK) AND order_date <= curdate() GROUP BY product_id ORDER BY sum(product_quanity) DESC LIMIT 3;");
            resultSet rs2 = stmt.executeQuery("SELECT (SELECT sum(product_quanity) FROM purchase_history) * product.Sale_cost AS 'totalSpent', customer_email FROM product RIGHT JOIN purchase_history ON product.Sale_cost = purchase_history.product_quanity LEFT JOIN  yagni_inv_db.customer_information ON purchase_history.hashed_email = customer_information.hashed_email WHERE  order_date > date_add(curdate(), INTERVAL - 1 WEEK) AND order_date <= curdate() group by customer_email ORDER BY totalSpent DESC LIMIT 3");


        }
    
        public void monthReport(monthReport) {
            resultSet rs1 = stmt.executeQuery("SELECT product_id, sum(product_i-quanity) AS 'sum' FROM yagni_inv_db.purchase_history WHERE order_date > DATEADD(curdate(),INTERVAL -1 MONTH) AND order_date <= curdate() GROUP BY product_id ORDER BY sum(product_quanity) DESC LIMIT 3;");
            resultSet rs2 = stmt.executeQuery("SELECT (SELECT sum(product_quanity) FROM purchase_history) * product.Sale_cost AS 'totalSpent', customer_email FROM product RIGHT JOIN purchase_history ON product.Sale_cost = purchase_history.product_quanity LEFT JOIN  yagni_inv_db.customer_information ON purchase_history.hashed_email = customer_information.hashed_email WHERE  order_date > date_add(curdate(), INTERVAL - 1 MONTH) AND order_date <= curdate() group by customer_email ORDER BY totalSpent DESC LIMIT 3");


        }

    } Catch (SQLException e) {
        System.out.println(e);
        System.out.println("The Query has failed, plesae try again. ");
    }


}
