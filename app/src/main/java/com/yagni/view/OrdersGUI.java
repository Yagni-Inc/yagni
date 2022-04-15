package com.yagni.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.yagni.controller.*;
import com.yagni.model.*;

public class OrdersGUI implements ActionListener,FocusListener,MouseListener{

	private static JFrame ordersFrame = new JFrame("Manage Orders Window");  
	private static JTable ordersTable = new JTable();
	private static JScrollPane tableScroll = new JScrollPane();
	private static JMenuBar menuReportsBar = new JMenuBar();
	private static DbConnection linkDB;
	
	private static JMenu marketReports = new JMenu("Marketing Reports");
	private static JMenu financeReports = new JMenu("Finance Reports");

	private static JMenuItem dailyMarket = new JMenuItem("Daily Market Report");  
	private static JMenuItem weeklyMarket = new JMenuItem("Weekly Market Report"); 
	private static JMenuItem monthlyMarket = new JMenuItem("Monthly Market Report");
	private static JMenuItem weeklyFinance = new JMenuItem("Weekly Finance Report");
	private static JMenuItem quarterlyFinance = new JMenuItem("Quarterly Finace Report");

	private static JPanel headerPanel = new JPanel();               //Creates header content are
	private static JPanel backPanel = new JPanel(); 
	private static JPanel logoutPanel = new JPanel();            
	private static JPanel bodyPanel = new JPanel();                //Creates body content area 
	private static JPanel footerPanel = new JPanel();              //Creates footer content area 
	private static JPanel tablePanel = new JPanel();               //Creates panel for table area
	private static JPanel controlsPanel = new JPanel();            //Creates panel for controls area

	private static JLabel headerLabel = new JLabel();              //text/image label for header 
	private static JLabel footerLabel = new JLabel();              //text label for footer
	private static JLabel controlsTitle = new JLabel("Actions");
	private static JLabel orderLabel = new JLabel("Order Number");
	private static JLabel dateLabel = new JLabel("Order Date");
	private static JLabel productLabel = new JLabel("Product ID");
	private static JLabel quantityLabel = new JLabel("Product Quantity");
	private static JLabel searchLabel = new JLabel("Search Actions");
	private static JLabel orderALabel = new JLabel("Order Actions");

	private static ImageIcon logoImg = new ImageIcon("App/assets/img/YagniLogoOnly-50percent.png"); //load logo image  
	private static ImageIcon searchImg = new ImageIcon("App/assets/img/searchIcon.png"); //image for search

	private static JButton loadButton = new JButton("Load Order Data");
	private static JButton reloadButton = new JButton("Refresh");
	private static JButton searchButton = new JButton();		//button with searchIcon.png
	private static JButton backButton = new JButton("Back");
	private static JButton logoutButton = new JButton("Logout");
	private static JButton clearButton = new JButton("Clear");

	private static JTextField orderNumberField = new JTextField(20);
	private static JTextField orderDateField = new JTextField(20);
	private static JTextField productIDField = new JTextField(20);
	private static JTextField productQuantityField = new JTextField(20);
	private static JTextField searchField = new JTextField(20);
        
	OrdersGUI(DbConnection linkDBIn) {

		linkDB = linkDBIn;

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
		//set default size and layout of body panel 
		headerPanel.setPreferredSize(new Dimension(100, 100));
		headerPanel.setBackground(Color.LIGHT_GRAY);

		//set text and image alignment in header
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
	
		//add header label to header panel 
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
		//adding buttons/textfields to actual panel
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
	
		searchField.setText("Search Order #");
		searchField.setHorizontalAlignment( JTextField.CENTER);
		searchField.setForeground(Color.GRAY);
		searchField.addFocusListener(this);
	
		searchButton.setIcon(searchImg);
		controlsPanel.add(searchButton);
		searchButton.addActionListener(this);

		controlsPanel.add(orderALabel);
		controlsPanel.add(clearButton);
		clearButton.addActionListener(this);
		
		/* ------- Tabel Panel & Load/Reload Button ------- */
		tablePanel.setBounds(310, 0, 570, 520);
		tablePanel.setLayout(null);
		tablePanel.setBackground(Color.LIGHT_GRAY);
		bodyPanel.add(tablePanel);

		loadButton.setBounds(370, 0, 200, 40);
		loadButton.addActionListener(this);
		reloadButton.setBounds(370,0,200,40);
		reloadButton.addActionListener(this);
		tablePanel.add(loadButton);
		tableScroll.setBounds(0, 45, 570, 480);
		tablePanel.add(tableScroll);
		tableScroll.setViewportView(ordersTable);
		ordersTable.addMouseListener(this);
	
		/* ------- Footer Content ------- */
		//set defualts and text of footer panel
		footerPanel.setPreferredSize(new Dimension(100, 30));
		footerPanel.setBackground(Color.DARK_GRAY);
		footerLabel.setText("Created by Yagni Inc. © 2022 ");
		footerLabel.setForeground(Color.WHITE);

		//add footer text to footer panel 
		footerPanel.add(footerLabel);

		/* ------- Frame Content ------- */
		//set default size. layout and style to the frame 
		ordersFrame.setSize(900,700);
		ordersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit out of the application
		ordersFrame.setResizable(false); //this disable the resize features of the frame to the user
	
		//add the header, body and footer panels to the frame 
		ordersFrame.add(headerPanel, BorderLayout.NORTH);
		ordersFrame.add(bodyPanel);
		ordersFrame.add(footerPanel, BorderLayout.SOUTH);

		/* ------- MenuBar Content ------- */ 
		ordersFrame.setJMenuBar(menuReportsBar);		//adding JMenuBar to ordersFrame
		menuReportsBar.add(marketReports);		//adding Market Reports drop-down menu 
		menuReportsBar.add(financeReports);		//adding Finance Reports drop-down menu
		
		/* ------- Market Reports Content ------- */
		marketReports.add(dailyMarket);
		dailyMarket.addActionListener(this);
		marketReports.add(weeklyMarket);
		weeklyMarket.addActionListener(this); 
		marketReports.add(monthlyMarket);
		monthlyMarket.addActionListener(this);
		
		/* ------- Finace Reports Content ------- */
		financeReports.add(weeklyFinance);
		weeklyFinance.addActionListener(this);
		financeReports.add(quarterlyFinance);
		quarterlyFinance.addActionListener(this);

		//displays all content to the GUI 
		ordersFrame.setVisible(true);
	}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //on click of Load button, display all data from product inventory table 
        if(e.getSource() == loadButton){
            tablePanel.remove(loadButton);
            tablePanel.repaint();
            tablePanel.add(reloadButton);
            ReadAllOrders read = new ReadAllOrders(ordersTable); // creates a ReadAllOrders object from model/ReadAllOrders.java and passes in the products table
            read.readAllOrders(linkDB); // calls the readAllOrders method and passes in the database connection
        }
        else if (e.getSource() == reloadButton){
            refreshOrders();
        }
        else if (e.getSource() == clearButton){
            clearTextFields();
        }
        else if(e.getSource() == logoutButton){
            tablePanel.remove(reloadButton);
            tablePanel.repaint();
            tablePanel.add(loadButton);
            linkDB.closeConnection();
            clearTextFields();
            clearOrdersTable();
            ordersFrame.dispose();
            
            new HomeGUI(); 
        }
        else if(e.getSource() == backButton){
            tablePanel.remove(reloadButton);
            tablePanel.repaint();
            tablePanel.add(loadButton);
            clearTextFields();
            clearOrdersTable();
            ordersFrame.dispose();
            
            new EmployeeGUI(linkDB);
        }
        else if(e.getSource() == searchButton){
            clearOrdersTable();
            String orderNum = searchField.getText();
            ReadOneOrder searchObj = new ReadOneOrder(orderNum, ordersTable); // creates a searchObj object from model/ReadOneOrder.java and passes in the products table
            searchObj.readOneOrder(linkDB); // calls the readOneOrder method and passes in the database connection
        }
		else if(e.getSource() == dailyMarket){
			JOptionPane.showMessageDialog(null,"<html>Most popular orders:<br>1.<br>2.<br>3.<br>Top customers by dollar amount:<br>1.<br>2.<br>3.</html>", "Daily Market Report", JOptionPane.INFORMATION_MESSAGE);
			//method to get data from database will go here
			//JOptionPane code above should be copied into MarketRepo.java file, then deleted
		}
		else if(e.getSource() == weeklyMarket){
			JOptionPane.showMessageDialog(null,"<html>Most popular orders:<br>1.<br>2.<br>3.<br>Top customers by dollar amount:<br>1.<br>2.<br>3.</html>", "Weekly Market Report", JOptionPane.INFORMATION_MESSAGE);
			//method to get data from database will go here
			//JOptionPane code above should be copied into MarketRepo.java file, then deleted
		}
		else if(e.getSource() == monthlyMarket){
			JOptionPane.showMessageDialog(null,"<html>Most popular orders:<br>1.<br>2.<br>3.<br>Top customers by dollar amount:<br>1.<br>2.<br>3.</html>", "Monthly Market Report", JOptionPane.INFORMATION_MESSAGE);
			//method to get data from database will go here
			//JOptionPane code above should be copied into MarketRepo.java file, then deleted
		}
		else if(e.getSource() == weeklyFinance){
			FinanceRepo fin = new FinanceRepo();
			fin.financeRepo(linkDB);		//calls financeRepo method and passes in the database connection
		}
		else if(e.getSource() == quarterlyFinance){
			JOptionPane.showMessageDialog(null,"Jesse was here :'(", "Quarterly Finance Report", JOptionPane.INFORMATION_MESSAGE);
		}
        
    }  
    //Method to refresh the JTable 
    public void refreshOrders(){
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
        model.setRowCount(0);
        ReadAllOrders displayTable = new ReadAllOrders(ordersTable); // creates a ReadAllOrders object from model/ReadAllOrders.java and passes in the products table
        displayTable.readAllOrders(linkDB); 
    }
    //Method to refresh the ordersTable JTable 
    public void clearOrdersTable(){
        DefaultTableModel model = (DefaultTableModel) ordersTable.getModel();
        model.setRowCount(0);
    }
    public void clearTextFields(){
           
		productIDField.setText("");
		orderDateField.setText("");
		productQuantityField.setText("");
		orderNumberField.setText("");
    }
   
    @Override
    public void focusGained(FocusEvent e){
        if(e.getSource() == searchField && searchField.getText().equals("Search Order #")){
            searchField.setText("");
            searchField.setForeground(Color.BLACK);
            searchField.setHorizontalAlignment(JTextField.LEFT);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(searchField.getText().isEmpty()){
            searchField.setText("Search Order #");
            searchField.setForeground(Color.GRAY);
            searchField.setHorizontalAlignment(JTextField.CENTER);
        }
    }
        
    @Override
    public void mouseClicked(MouseEvent e) {
       
        DefaultTableModel tmodel=(DefaultTableModel)ordersTable.getModel();
        int selectrowindex=ordersTable.getSelectedRow();
        orderNumberField.setText(tmodel.getValueAt(selectrowindex, 0).toString());
        orderDateField.setText(tmodel.getValueAt(selectrowindex, 1).toString());
        productIDField.setText(tmodel.getValueAt(selectrowindex, 4).toString());
        productQuantityField.setText(tmodel.getValueAt(selectrowindex, 5).toString());

    }
    
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
    }
}
