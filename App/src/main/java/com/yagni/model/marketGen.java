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
            resultSet rs = stmt.executeQuery("SELECT product_id, sum(product_i-quanity) AS 'sum' FROM yagni_inv_db.purchase_history WHERE order_date > DATEADD(curdate(),INTERVAL -1 DAY) AND order_date <= curdate() GROUP BY product_id ORDER BY sum(product_quanity) DESC LIMIT 3;");
            

        }
    
        public void weekReport(weekReport) {
            resultSet rs = stmt.exectureQuery("SELECT product_id, sum(product_i-quanity) AS 'sum' FROM yagni_inv_db.purchase_history WHERE order_date > DATEADD(curdate(),INTERVAL -1 WEEK) AND order_date <= curdate() GROUP BY product_id ORDER BY sum(product_quanity) DESC LIMIT 3;");



        }
    
        public void monthReport(monthReport) {
            resultSet rd = stmt.executeQuery("SELECT product_id, sum(product_i-quanity) AS 'sum' FROM yagni_inv_db.purchase_history WHERE order_date > DATEADD(curdate(),INTERVAL -1 MONTH) AND order_date <= curdate() GROUP BY product_id ORDER BY sum(product_quanity) DESC LIMIT 3;");



        }

    } Catch (SQLException e) {
        System.out.println(e);
        System.out.println("The Query has failed, plesae try again. ");
    }


}
