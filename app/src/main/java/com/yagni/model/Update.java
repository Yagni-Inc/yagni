package com.yagni.model;

import java.sql.*;
import com.yagni.controller.*;

public class Update {

	// String Variables declaration
	private static String updateID;
	private static String updateSupplierID;
	private static String updateQuant;
	private static String updatePrice;
	private static String updateWholesale;

	// Constructor
	public Update(String productIdIn, String quantityIn, String wholeSaleIn, String salePriceIn, String supplierIdIn) {

		// String variable initialization
		updateID = productIdIn;
		updateQuant = quantityIn;
		updateWholesale = wholeSaleIn;
		updatePrice = salePriceIn;
		updateSupplierID = supplierIdIn;
	}

	// Update Method
	public void update(DbConnection linkDB) {

		// start the sql statement
		String sql_statement = "UPDATE `yagni_inv_db`.`product` SET ";

		// initialize a counter to help with comma placement
		int count = 0;

		if (updateQuant != null) { // if a new quantity is inputted
			// add quantity statement to sql statement
			sql_statement = sql_statement + "`quanity` = '" + updateQuant + "'";
			// increase count by 1, so next item will have a comma before it
			count++;
		}
		if (updateWholesale != null) { // if a new wholesale is inputted
			if (count > 0) {
				// if there is a statement before, add a comma
				sql_statement = sql_statement + ", ";
			}
			sql_statement = sql_statement + "`Whole_sale` = '" + updateWholesale + "'";
			count++;
		}
		if (updatePrice != null) {
			if (count > 0) {
				sql_statement = sql_statement + ", ";
			}
			sql_statement = sql_statement + "`Sale_cost` = '" + updatePrice + "'";
			count++;
		}
		if (updateSupplierID != null) {
			if (count > 0) {
				sql_statement = sql_statement + ", ";
			}
			sql_statement = sql_statement + "`vendor_id` = '" + updateSupplierID + "'";
			count++;
		}

		// finish sql statement off with final keywords/product ID
		sql_statement = sql_statement + " WHERE (`product_id` = '" + updateID + "');";

		try {
			// Creates a statement object
			Statement update_statement = linkDB.getConnection().createStatement();
			// Calling the execute method to execute a DELETE statement with given ID
			update_statement.execute(sql_statement);
			System.out.println("\nSuccessfully updated product " + updateID + " in the inventory.");
			System.out.println("-----------------------------------------------------------------------------------");
		} catch (SQLException e) {
			System.out.println("Oops! An error has occurred.");
			System.out.println(e);
		}
	}
}
