package com.yagni.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.yagni.controller.*;
import com.yagni.model.*;

public class OrdersGUI implements ActionListener, FocusListener, MouseListener {

	private static JFrame ordersFrame; // JFrame that holds all elements
	private static JTable ordersTable; // JTable that displays customer orders
	private static JScrollPane tableScroll; //JScrollPane that allows mouse scrolling through ordersTable 
	private static JMenuBar menuReportsBar; //JMenuBar that holds all JMenu/JMenuItems
	private static DbConnection linkDB; // Variable for DB connection

	// Variable declaration for all JMenu tabs
	private static JMenu marketReports;
	private static JMenu financeReports;
	private static JMenu help;

	// Variable declaration for all JMenuItems
	private static JMenuItem dailyMarket;
	private static JMenuItem weeklyMarket;
	private static JMenuItem monthlyMarket;
	private static JMenuItem weeklyFinance;
	private static JMenuItem quarterlyFinance;
	private static JMenuItem about;

	// Variable declaration for all JPanels
	private static JPanel headerPanel;
	private static JPanel backPanel; 
	private static JPanel logoutPanel; 
	private static JPanel bodyPanel; 
	private static JPanel footerPanel; 
	private static JPanel tablePanel; 
	private static JPanel controlsPanel; 

	// Variable declaration for all JLabels
	private static JLabel headerLabel;
	private static JLabel footerLabel;
	private static JLabel controlsTitle;
	private static JLabel orderLabel;
	private static JLabel dateLabel; 
	private static JLabel productLabel; 
	private static JLabel quantityLabel; 
	private static JLabel searchLabel; 
	private static JLabel orderALabel; 

	// Variable declaration for all JImageIcons
	private static ImageIcon logoImg; 
	private static ImageIcon searchImg; 
	
	// Variable declaration for all JButtons
	private static JButton loadButton; 
	private static JButton reloadButton; 
	private static JButton searchButton; 
	private static JButton backButton; 
	private static JButton logoutButton;
	private static JButton clearButton; 

	// Variable declaration for all JTextFields
	private static JTextField orderNumberField; 
	private static JTextField orderDateField; 
	private static JTextField productIDField; 
	private static JTextField productQuantityField; 
	private static JTextField searchField; 

	OrdersGUI(DbConnection linkDBIn) {

		// Setting connection to DB
		linkDB = linkDBIn;

		// Initializing JFrame, JTable, JScrollPane, JMenuBar
		ordersFrame = new JFrame("Manage Orders Window");
		ordersTable = new JTable();
		tableScroll = new JScrollPane();
		menuReportsBar = new JMenuBar();

		// Initializing JMenu tabs
		marketReports = new JMenu("Marketing Reports");
		financeReports = new JMenu("Finance Reports");
		help = new JMenu("Help");

		// Initializing JMenuItems
		dailyMarket = new JMenuItem("Daily Market Report");
		weeklyMarket = new JMenuItem("Weekly Market Report");
		monthlyMarket = new JMenuItem("Monthly Market Report");
		weeklyFinance = new JMenuItem("Weekly Finance Report");
		quarterlyFinance = new JMenuItem("Quarterly Finance Report");
		about = new JMenuItem("Assistance");

		// Initializing JPanels
		headerPanel = new JPanel(); // Creates header content area
		backPanel = new JPanel(); // Creates panel for back button
		logoutPanel = new JPanel(); // Creates panel for logout button
		bodyPanel = new JPanel(); // Creates body content area
		footerPanel = new JPanel(); // Creates footer content area
		tablePanel = new JPanel(); // Creates panel for table area
		controlsPanel = new JPanel(); // Creates panel for controls area
		
		// Initializing JLabels
		headerLabel = new JLabel(); // text & image label for header
		footerLabel = new JLabel(); // text label for footer
		controlsTitle = new JLabel("Actions");
		orderLabel = new JLabel("Order Number");
		dateLabel = new JLabel("Order Date");
		productLabel = new JLabel("Product ID");
		quantityLabel = new JLabel("Product Quantity");
		searchLabel = new JLabel("Search Actions");
		orderALabel = new JLabel("Order Actions");

		// Initializing JImageIcons
		logoImg = new ImageIcon("App/assets/img/YagniLogoOnly-50percent.png"); // load logo image
		searchImg = new ImageIcon("App/assets/img/searchIcon.png"); // image for search

		// Initializing JButtons
		loadButton = new JButton("Load Order Data");
		reloadButton = new JButton("Refresh");
		searchButton = new JButton(); // Button with searchIcon.png
		backButton = new JButton("Back");
		logoutButton = new JButton("Logout");
		clearButton = new JButton("Clear");

		//Initializing JTextFields
		orderNumberField = new JTextField(20);
		orderDateField = new JTextField(20);
		productIDField = new JTextField(20);
		productQuantityField = new JTextField(20);
		searchField = new JTextField(20);

		// Importing and setting custom font Caveat for all text components
		try {
			File font_file = new File("App/assets/fonts/Caveat-VariableFont_wght.ttf");
			Font caveatFont = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(20f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));
			headerLabel.setFont(caveatFont.deriveFont(Font.BOLD, 35f));
			footerLabel.setFont(caveatFont.deriveFont(16f));
			controlsTitle.setFont(caveatFont.deriveFont(Font.BOLD, 25f));
			loadButton.setFont(caveatFont);
			reloadButton.setFont(caveatFont);
			backButton.setFont(caveatFont);
			logoutButton.setFont(caveatFont);
			clearButton.setFont(caveatFont);
			orderLabel.setFont(caveatFont);
			orderALabel.setFont(caveatFont);
			dateLabel.setFont(caveatFont);
			productLabel.setFont(caveatFont);
			quantityLabel.setFont(caveatFont);
			searchLabel.setFont(caveatFont);
			searchField.setFont(caveatFont);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		/* ------- Header Content ------- */
		// Set default size and layout of body panel
		headerPanel.setPreferredSize(new Dimension(100, 100));
		headerPanel.setBackground(Color.LIGHT_GRAY);

		// Set text and image alignment in header
		headerLabel.setIcon(logoImg);
		headerLabel.setIconTextGap(20);
		headerLabel.setText("Yagni Inc. Order Management Menu ");
		headerLabel.setVerticalTextPosition(JLabel.CENTER);
		headerLabel.setHorizontalTextPosition(JLabel.RIGHT);

		/* ------- Back Panel/Back Button ------- */
		backPanel.setBounds(10, 15, 100, 40);
		backPanel.setBackground(Color.LIGHT_GRAY);
		backPanel.setLayout(new GridLayout(1, 1, 0, 0));
		ordersFrame.add(backPanel);
		backButton.addActionListener(this);
		backPanel.add(backButton);
		backButton.setHorizontalTextPosition(JLabel.CENTER);
		backButton.setVerticalTextPosition(JLabel.CENTER);

		/* ------- Logout Panel/Logout Button ------- */
		logoutPanel.setBounds(780, 15, 100, 40);
		logoutPanel.setBackground(Color.LIGHT_GRAY);
		logoutPanel.setLayout(new GridLayout(1, 1, 0, 0));
		ordersFrame.add(logoutPanel);
		logoutButton.addActionListener(this);
		logoutPanel.add(logoutButton);
		logoutButton.setHorizontalTextPosition(JLabel.CENTER);
		logoutButton.setVerticalTextPosition(JLabel.CENTER);

		// Add header label to header panel
		headerPanel.add(headerLabel);

		/* ------- Body Content ------- */
		bodyPanel.setLayout(null);
		bodyPanel.setBackground(Color.LIGHT_GRAY);
		bodyPanel.add(controlsPanel);

		/* ------- CRUD Controls Panel------- */
		controlsPanel.setBounds(30, 30, 270, 500);
		controlsPanel.setBackground(Color.LIGHT_GRAY);
		controlsPanel.setLayout(new GridLayout(17, 1, 0, 5));
		controlsPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
		controlsTitle.setHorizontalAlignment(JLabel.CENTER);
		controlsPanel.add(controlsTitle);
		
		// Adding JLabels, JTextFields to controlsPanel
		controlsPanel.add(orderLabel);
		controlsPanel.add(orderNumberField);
		controlsPanel.add(dateLabel);
		controlsPanel.add(orderDateField);
		controlsPanel.add(productLabel);
		controlsPanel.add(productIDField);
		controlsPanel.add(quantityLabel);
		controlsPanel.add(productQuantityField);
		controlsPanel.add(searchLabel);
		controlsPanel.add(searchField);

		// Setting searchField text and adding listener to searchField
		searchField.setText("Search Order #");
		searchField.setHorizontalAlignment(JTextField.CENTER);
		searchField.setForeground(Color.GRAY);
		searchField.addFocusListener(this);

		// Adding png image to searchButton w/ listener
		searchButton.setIcon(searchImg);
		controlsPanel.add(searchButton);
		searchButton.addActionListener(this);

		// Adding clearButton w/ listener to controlsPanel
		controlsPanel.add(orderALabel);
		controlsPanel.add(clearButton);
		clearButton.addActionListener(this);

		/* ------- Table Panel & Load/Reload Button ------- */
		tablePanel.setBounds(310, 0, 570, 520);
		tablePanel.setLayout(null);
		tablePanel.setBackground(Color.LIGHT_GRAY);
		bodyPanel.add(tablePanel);

		loadButton.setBounds(370, 0, 200, 40);
		loadButton.addActionListener(this);
		reloadButton.setBounds(370, 0, 200, 40);
		reloadButton.addActionListener(this);
		tablePanel.add(loadButton);
		tableScroll.setBounds(0, 45, 570, 480);
		tablePanel.add(tableScroll);
		tableScroll.setViewportView(ordersTable);
		ordersTable.addMouseListener(this);

		/* ------- Footer Content ------- */
		// Setting fonts and text for footerPanel/Label
		footerPanel.setPreferredSize(new Dimension(100, 30));
		footerPanel.setBackground(Color.DARK_GRAY);
		footerLabel.setText("Created by Yagni Inc. © 2022 ");
		footerLabel.setForeground(Color.WHITE);

		// Adding footer text to footerPanel
		footerPanel.add(footerLabel);

		/* ------- Frame Content ------- */
		ordersFrame.setSize(900, 700); // set default size. layout and style to the frame
		ordersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit out of the application
		ordersFrame.setResizable(false); // disables resize features of the frame to the user

		// Adding the header, body and footer panels to the frame
		ordersFrame.add(headerPanel, BorderLayout.NORTH);
		ordersFrame.add(bodyPanel);
		ordersFrame.add(footerPanel, BorderLayout.SOUTH);

		/* ------- MenuBar Content ------- */
		ordersFrame.setJMenuBar(menuReportsBar); // adding JMenuBar to ordersFrame
		menuReportsBar.add(marketReports); // adding Market Reports drop-down menu
		menuReportsBar.add(financeReports); // adding Finance Reports drop-down menu
		menuReportsBar.add(help); // adding help section

		/* ------- Market Reports Content ------- */
		marketReports.add(dailyMarket);
		dailyMarket.addActionListener(this);
		marketReports.add(weeklyMarket);
		weeklyMarket.addActionListener(this);
		marketReports.add(monthlyMarket);
		monthlyMarket.addActionListener(this);

		/* ------- Finance Reports Content ------- */
		financeReports.add(weeklyFinance);
		weeklyFinance.addActionListener(this);
		financeReports.add(quarterlyFinance);
		quarterlyFinance.addActionListener(this);

		/* ------- Help Content ------- */
		help.add(about);
		about.addActionListener(this);

		// Displays all content to the GUI
		ordersFrame.setVisible(true);
	} // End of constructor

	// Event handler for button clicks
	@Override
	public void actionPerformed(ActionEvent e) {
		// On click of Load button, display all data from product inventory table
		if (e.getSource() == loadButton) {
			// On click loads data into orderTables using ReadAllOrders
			tablePanel.remove(loadButton);
			tablePanel.repaint();
			tablePanel.add(reloadButton);
			
			// Creates a ReadAllOrders object from model/ReadAllOrders.java and passes in ordersTable
			ReadAllOrders read = new ReadAllOrders(ordersTable);
			
			// Calls the readAllOrders method and passes in the database connection
			read.readAllOrders(linkDB);

		} else if (e.getSource() == reloadButton) {
			// On click of the reload button refresh the products table
			refreshOrders();

		} else if (e.getSource() == clearButton) {
			// On click clears data from all JTextFields
			clearTextFields();

		} else if (e.getSource() == logoutButton) {
			// Closes DB connection and navigates to HomeGUI
			tablePanel.remove(reloadButton);
			tablePanel.repaint();
			tablePanel.add(loadButton);
			linkDB.closeConnection();
			clearTextFields();
			clearOrdersTable();
			ordersFrame.dispose();
			
			// Creates HomeGUI object after disposing ordersFrame
			new HomeGUI();

		} else if (e.getSource() == backButton) {
			// On click navigates to EmployeeGUI
			tablePanel.remove(reloadButton);
			tablePanel.repaint();
			tablePanel.add(loadButton);
			clearTextFields();
			clearOrdersTable();
			ordersFrame.dispose();
			
			// Disposes ordersFrame, clears JTextFields, repaints loadButton, creates EmployeeGUI object
			new EmployeeGUI(linkDB);

		} else if (e.getSource() == searchButton) {
			// On click clears ordersTable and stores orderNum for searchField
			clearOrdersTable();
			String orderNum = searchField.getText();
			
			// Creates a searchObj object from ReadOneOrder.java and passes in the ordersTable
			ReadOneOrder searchObj = new ReadOneOrder(orderNum, ordersTable);
			
			// Calls the readOneOrder method and passes in the database connection
			searchObj.readOneOrder(linkDB);

		} else if (e.getSource() == dailyMarket) {
			// On click of dailyMarket MenuItem, displays popup
			MarketRepo day = new MarketRepo();
			
			// Calls dayReport method from MarketRepo.java, passes in DB connection
			day.dayReport(linkDB);

		} else if (e.getSource() == weeklyMarket) {
			// On click of weeklyMarket MenuItem, displays popup
			MarketRepo week = new MarketRepo();
			
			// Calls weekReport method from MarketRepo.java, passes in DB connection
			week.weekReport(linkDB);

		} else if (e.getSource() == monthlyMarket) {
			// On click of monthlyMarket MenuItem, displays popup
			MarketRepo month = new MarketRepo();
			
			// Calls monthReport method from MarketRepo.java, passes in DB connection
			month.monthReport(linkDB);

		} else if (e.getSource() == weeklyFinance) {
			// On click of weeklyFinance MenuItem, displays popup
			FinanceRepo fin = new FinanceRepo();

			// Calls financeRepo method from FinanceRepo.java, passes in DB connection
			fin.financeRepo(linkDB);

		} else if (e.getSource() == quarterlyFinance) {
			// On click of quarterlyFinance MenuItem, displays popup
			JOptionPane.showMessageDialog(null,
					"Jesse was here :'(",
					"Quarterly Finance Report", JOptionPane.INFORMATION_MESSAGE);

		} else if (e.getSource() == about) {
			// On click of about/help, display popup
			JOptionPane.showMessageDialog(null,
					"For customer support please, call 1-800 YAGNICS",
					"Help", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// Method to refresh the JTable
	public void refreshOrders() {
		DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
		model.setRowCount(0);
		
		// Creates a ReadAllOrders object from ReadAllOrders.java & passes in ordersTable
		ReadAllOrders displayTable = new ReadAllOrders(ordersTable);
		displayTable.readAllOrders(linkDB);
	}

	// Method to clear the ordersTable 
	public void clearOrdersTable() {
		DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
		model.setRowCount(0);
	}

	// Method that clears all JTextFields
	public void clearTextFields() {
		productIDField.setText("");
		orderDateField.setText("");
		productQuantityField.setText("");
		orderNumberField.setText("");
	}

	// this focusGained clears the gray text from the searchfield when it is clicked
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == searchField && searchField.getText().equals("Search Order #")) {
			searchField.setText("");
			searchField.setForeground(Color.BLACK);
			searchField.setHorizontalAlignment(JTextField.LEFT);
		}
	}

	// this focusLost event sets the searchfield text to "Search Order #" and sets
	// the text to gray
	@Override
	public void focusLost(FocusEvent e) {
		if (searchField.getText().isEmpty()) {
			searchField.setText("Search Order #");
			searchField.setForeground(Color.GRAY);
			searchField.setHorizontalAlignment(JTextField.CENTER);
		}
	}

	// mouseClicked event allows a user to populate all JTextFields by clicking a row
	// from the JTable
	@Override
	public void mouseClicked(MouseEvent e) {
		DefaultTableModel tmodel = (DefaultTableModel) ordersTable.getModel();
		int selectrowindex = ordersTable.getSelectedRow();
		orderNumberField.setText(tmodel.getValueAt(selectrowindex, 0).toString());
		orderDateField.setText(tmodel.getValueAt(selectrowindex, 1).toString());
		productIDField.setText(tmodel.getValueAt(selectrowindex, 4).toString());
		productQuantityField.setText(tmodel.getValueAt(selectrowindex, 5).toString());
	}

	//Don't delete these Overrides below
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
	} // TODO Auto-generated method stub

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
	} // TODO Auto-generated method stub

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
	} // TODO Auto-generated method stub

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
	} // TODO Auto-generated method stub
}
