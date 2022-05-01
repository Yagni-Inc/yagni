package com.yagni.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.yagni.controller.*;
import com.yagni.model.*;

<<<<<<< HEAD
public class ManageInvGUI implements ActionListener,FocusListener,MouseListener{

        private static JFrame inventoryFrame = new JFrame("Product Inventory Window");  
        private static JTable productsTable = new JTable();
        private static JScrollPane tableScroll = new JScrollPane();
        private static DbConnection linkDB;
        
        private static JPanel headerPanel = new JPanel(); //Creates header content are
        private static JPanel backPanel = new JPanel(); 
        private static JPanel logoutPanel = new JPanel();            
        private static JPanel bodyPanel = new JPanel(); //Creates body content area 
        private static JPanel footerPanel = new JPanel(); //Creates footer content area 
        private static JPanel tablePanel = new JPanel(); //Creates panel for table area
        private static JPanel controlsPanel = new JPanel();            //Creates panel for controls area
       
        private static JLabel headerLabel = new JLabel();              //text/image label for header 
        private static JLabel footerLabel = new JLabel();              //text label for footer
        private static JLabel controlsTitle = new JLabel("Actions");
        private static JLabel productIDLabel = new JLabel("Product ID");
        private static JLabel quantityLabel = new JLabel("Quantity");
        private static JLabel wholeSaleLabel = new JLabel("Whole Sale Price");
        private static JLabel salePriceLabel = new JLabel("Sale Price");
        private static JLabel supplierIDLabel = new JLabel("Supplier ID");
        private static JLabel productActionsLabel = new JLabel("Product Actions");

        private static ImageIcon logoImg = new ImageIcon("App/assets/img/YagniLogoOnly-50percent.png"); //load logo image  
        private static ImageIcon searchImg = new ImageIcon("App/assets/img/searchIcon.png"); //image for search
        private static ImageIcon warningImg = new ImageIcon("App/assets/img/warning.png");
        
        private static JButton addButton = new JButton("Add New Product");
        private static JButton updateButton = new JButton("Update Product");
        private static JButton deleteButton = new JButton("Delete Product"); 
        private static JButton loadButton = new JButton("Load Inventory Data");
        private static JButton reloadButton = new JButton("Refresh");
        private static JButton searchButton = new JButton(); //button with searchIcon.png
        private static JButton backButton = new JButton("Back");
        private static JButton logoutButton = new JButton("Logout");
        private static JButton clearButton = new JButton("Clear");
       
        private static JTextField productIDField = new JTextField(20);
        private static JTextField quantityField = new JTextField(20);
        private static JTextField wholeSaleField = new JTextField(20);
        private static JTextField salePriceField = new JTextField(20);
        private static JTextField supplierIDField = new JTextField(20);
        private static JTextField searchField = new JTextField(20);
        
        ManageInvGUI(DbConnection linkDBIn){
            // setting the connection with database
        	linkDB = linkDBIn;

            // Importing and setting custom font Caveat for all text components 
            try {
                File font_file = new File("App/assets/fonts/Caveat-VariableFont_wght.ttf");
                Font caveatFont = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(20f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));
                headerLabel.setFont(caveatFont.deriveFont(Font.BOLD, 35f));
                controlsTitle.setFont(caveatFont.deriveFont(Font.BOLD, 25f));
                addButton.setFont(caveatFont);
                productIDLabel.setFont(caveatFont);
                productActionsLabel.setFont(caveatFont);
                quantityLabel.setFont(caveatFont);
                wholeSaleLabel.setFont(caveatFont);
                salePriceLabel.setFont(caveatFont);
                supplierIDLabel.setFont(caveatFont);
                updateButton.setFont(caveatFont);
                deleteButton.setFont(caveatFont);
                loadButton.setFont(caveatFont);
                reloadButton.setFont(caveatFont);
                searchField.setFont(caveatFont);
                backButton.setFont(caveatFont);
                logoutButton.setFont(caveatFont);
                clearButton.setFont(caveatFont);
                footerLabel.setFont(caveatFont.deriveFont(16f));	    
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
=======
public class ManageInvGUI implements ActionListener, FocusListener, MouseListener {

    private static JFrame inventoryFrame; // Jframe to display all elements
    private static JTable productsTable; // JTable to display all products
    private static JScrollPane tableScroll; // JScrollPane to scroll through the products JTable
    private static DbConnection linkDB; // Variable to persist connection to db

    // Variable declarations for all JPanels needed within GUI
    private static JPanel headerPanel;
    private static JPanel backPanel;
    private static JPanel logoutPanel;
    private static JPanel bodyPanel;
    private static JPanel footerPanel;
    private static JPanel tablePanel;
    private static JPanel controlsPanel;

    // Variable declarations for all JLabels needed within GUI
    private static JLabel headerLabel;
    private static JLabel footerLabel;
    private static JLabel controlsTitle;
    private static JLabel productIDLabel;
    private static JLabel quantityLabel;
    private static JLabel wholeSaleLabel;
    private static JLabel salePriceLabel;
    private static JLabel supplierIDLabel;
    private static JLabel productActionsLabel;

    // Variable declarations for all Images needed within GUI
    private static ImageIcon logoImg;
    private static ImageIcon searchImg;
    private static ImageIcon warningImg;

    // Variable declarations for all JButtons needed within GUI
    private static JButton addButton;
    private static JButton updateButton;
    private static JButton deleteButton;
    private static JButton loadButton;
    private static JButton reloadButton;
    private static JButton searchButton;
    private static JButton backButton;
    private static JButton logoutButton;
    private static JButton clearButton;

    // Variable declarations for all JTextFields needed within GUI
    private static JTextField productIDField;
    private static JTextField quantityField;
    private static JTextField wholeSaleField;
    private static JTextField salePriceField;
    private static JTextField supplierIDField;
    private static JTextField searchField;

    ManageInvGUI(DbConnection linkDBIn) {

        // Set the connection with db
        linkDB = linkDBIn;

        // Initializing JFrame, Jtable and JScrollPane
        inventoryFrame = new JFrame("Product Inventory Window");
        productsTable = new JTable();
        tableScroll = new JScrollPane();

        // Initializing JPanels
        headerPanel = new JPanel();
        backPanel = new JPanel();
        logoutPanel = new JPanel();
        bodyPanel = new JPanel();
        footerPanel = new JPanel();
        tablePanel = new JPanel();
        controlsPanel = new JPanel();

        // Initializing JLabels
        headerLabel = new JLabel();
        footerLabel = new JLabel("Created by Yagni Inc. © 2022 ");
        controlsTitle = new JLabel("Actions");
        productIDLabel = new JLabel("Product ID");
        quantityLabel = new JLabel("Quantity");
        wholeSaleLabel = new JLabel("Whole Sale Price");
        salePriceLabel = new JLabel("Sale Price");
        supplierIDLabel = new JLabel("Supplier ID");
        productActionsLabel = new JLabel("Product Actions");

        // Initializing ImageIcons
        logoImg = new ImageIcon("App/assets/img/YagniLogoOnly-50percent.png");
        searchImg = new ImageIcon("App/assets/img/searchIcon.png");
        warningImg = new ImageIcon("App/assets/img/warning.png");

        // Initializing JButtons
        addButton = new JButton("Add New Product");
        updateButton = new JButton("Update Product");
        deleteButton = new JButton("Delete Product");
        loadButton = new JButton("Load Inventory Data");
        reloadButton = new JButton("Refresh");
        searchButton = new JButton();
        backButton = new JButton("Back");
        logoutButton = new JButton("Logout");
        clearButton = new JButton("Clear Fields");

        // Initializing JTextFields
        productIDField = new JTextField(20);
        quantityField = new JTextField(20);
        wholeSaleField = new JTextField(20);
        salePriceField = new JTextField(20);
        supplierIDField = new JTextField(20);
        searchField = new JTextField("Search Product ID", 20);

        // Importing and setting custom font Caveat for all text components
        try {
            File font_file = new File("App/assets/fonts/Caveat-VariableFont_wght.ttf");
            Font caveatFont = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));
            headerLabel.setFont(caveatFont.deriveFont(Font.BOLD, 35f));
            controlsTitle.setFont(caveatFont.deriveFont(Font.BOLD, 25f));
            addButton.setFont(caveatFont);
            productIDLabel.setFont(caveatFont);
            productActionsLabel.setFont(caveatFont);
            quantityLabel.setFont(caveatFont);
            wholeSaleLabel.setFont(caveatFont);
            salePriceLabel.setFont(caveatFont);
            supplierIDLabel.setFont(caveatFont);
            updateButton.setFont(caveatFont);
            deleteButton.setFont(caveatFont);
            loadButton.setFont(caveatFont);
            reloadButton.setFont(caveatFont);
            searchField.setFont(caveatFont);
            backButton.setFont(caveatFont);
            logoutButton.setFont(caveatFont);
            clearButton.setFont(caveatFont);
            footerLabel.setFont(caveatFont.deriveFont(16f));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
>>>>>>> 76074fc44c2fbeda4af92bbaf5208e17af0d68f5

        /* ------- Header Content ------- */
        // Set default size and layout of body panel
        headerPanel.setPreferredSize(new Dimension(100, 100));
        headerPanel.setBackground(Color.LIGHT_GRAY);

        // Set text and image alignment in header
        headerLabel.setIcon(logoImg);
        headerLabel.setIconTextGap(20);
        headerLabel.setText("Yagni Inc. Employee Actions Menu ");
        headerLabel.setVerticalTextPosition(JLabel.CENTER);
        headerLabel.setHorizontalTextPosition(JLabel.RIGHT);

        /* ------- Back Panel/Back Button ------- */
        backPanel.setBounds(10, 15, 100, 40);
        backPanel.setBackground(Color.LIGHT_GRAY);
        backPanel.setLayout(new GridLayout(1, 1, 0, 0));
        inventoryFrame.add(backPanel);
        backButton.addActionListener(this);
        backPanel.add(backButton);
        backButton.setHorizontalTextPosition(JLabel.CENTER);
        backButton.setVerticalTextPosition(JLabel.CENTER);

        /* ------- Logout Panel/Logout Button ------- */
        logoutPanel.setBounds(780, 15, 100, 40);
        logoutPanel.setBackground(Color.LIGHT_GRAY);
        logoutPanel.setLayout(new GridLayout(1, 1, 0, 0));
        inventoryFrame.add(logoutPanel);
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
        controlsPanel.setLayout(new GridLayout(16, 1, 0, 5));
        controlsPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        controlsTitle.setHorizontalAlignment(JLabel.CENTER);
        controlsPanel.add(controlsTitle);

        // Adding buttons/textfields to controlsPanel
        controlsPanel.add(addButton);
        controlsPanel.add(productIDLabel);
        controlsPanel.add(productIDField);
        controlsPanel.add(quantityLabel);
        controlsPanel.add(quantityField);
        controlsPanel.add(wholeSaleLabel);
        controlsPanel.add(wholeSaleField);
        controlsPanel.add(salePriceLabel);
        controlsPanel.add(salePriceField);
        controlsPanel.add(supplierIDLabel);
        controlsPanel.add(supplierIDField);
        controlsPanel.add(productActionsLabel);
        controlsPanel.add(clearButton);
        controlsPanel.add(updateButton);
        controlsPanel.add(deleteButton);

        // Passing scope to action listener for buttons
        addButton.addActionListener(this);
        clearButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        /* ------- Tabel Panel & Load/Reload Button ------- */
        tablePanel.setLayout(null);
        tablePanel.setBackground(Color.LIGHT_GRAY);
        tablePanel.setBounds(310, 0, 570, 520);
        loadButton.setBounds(370, 0, 200, 40);
        reloadButton.setBounds(370, 0, 200, 40);
        tableScroll.setBounds(0, 45, 570, 480);
        bodyPanel.add(tablePanel);
        tablePanel.add(loadButton);
        tablePanel.add(tableScroll);

        // Passing scope to action listener for buttons
        tableScroll.setViewportView(productsTable);
        loadButton.addActionListener(this);
        reloadButton.addActionListener(this);
        productsTable.addMouseListener(this);

        /* ------- Search Textfield & Button ------- */
        searchField.setBounds(0, 0, 200, 40);
        searchField.setHorizontalAlignment(JTextField.CENTER);
        searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(this);

        tablePanel.add(searchField);

        searchButton.setBounds(200, 0, 40, 40);
        searchButton.setIcon(searchImg);
        searchButton.addActionListener(this);

        tablePanel.add(searchButton);

        /* ------- Footer Content ------- */
        // Set defualts and text of footer panel
        footerPanel.setPreferredSize(new Dimension(100, 30));
        footerPanel.setBackground(Color.DARK_GRAY);
        footerLabel.setForeground(Color.WHITE);

        // Add footer text to footer panel
        footerPanel.add(footerLabel);

        /* ------- Frame Content ------- */
        // Set default size, layout and style to the frame
        inventoryFrame.setSize(900, 700);
        // Exit out of the application
        inventoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Disables the resize features of the frame to the user
        inventoryFrame.setResizable(false);

        // Add the header, body and footer panels to the frame
        inventoryFrame.add(headerPanel, BorderLayout.NORTH);
        inventoryFrame.add(bodyPanel);
        inventoryFrame.add(footerPanel, BorderLayout.SOUTH);

        // Displays all content to the GUI
        inventoryFrame.setVisible(true);
    }// End of constructor

    // Event handler for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // On click of Load button, display all data from product inventory table
        if (e.getSource() == loadButton) {
            tablePanel.remove(loadButton);
            tablePanel.repaint();
            tablePanel.add(reloadButton);

            // Creates a ReadAll object from model/ReadAll.java and passes in productsTable
            ReadAll read = new ReadAll(productsTable);

            // Calls the readAll method and passes in the db connection
            read.readAll(linkDB);

        } else if (e.getSource() == addButton) {
            // add button set variables to user input from text fields
            final String productId = productIDField.getText();
            final String quantity = quantityField.getText();
            final String wholeSale = wholeSaleField.getText();
            final String salePrice = salePriceField.getText();
            final String supplierId = supplierIDField.getText();

            // Clear all text fields
            clearTextFields();

            // Creating the object to call addRecord and passing in user input
            Create addRecord = new Create(productId, quantity, wholeSale, salePrice, supplierId);
            addRecord.addRecord(linkDB);

            // Reload product inventory data table to display newly added data
            refreshProducts();

        } else if (e.getSource() == clearButton) {
            // On click of the clear button clear all text fields
            clearTextFields();

        } else if (e.getSource() == updateButton) {
            // On click of the update button set variables to user input from text fields
            final String updateID = productIDField.getText();
            final String updateQuant = quantityField.getText();
            final String updateWholesale = wholeSaleField.getText();
            final String updatePrice = salePriceField.getText();
            final String updateSupplierID = supplierIDField.getText();

            // Creating the object to call update and passing in user input
            Update updateRecord = new Update(updateID, updateQuant, updateWholesale, updatePrice, updateSupplierID);
            updateRecord.update(linkDB);

            // Reload product inventory data table to display updated data
            refreshProducts();

        } else if (e.getSource() == reloadButton) {
            // On click of the reload button refresh the products table
            refreshProducts();

        } else if (e.getSource() == logoutButton) {
            // On click of logout button close the db connection and dispose the JFrame
            linkDB.closeConnection();
            inventoryFrame.dispose();

            // Create a new HomeGUI object to display the Home page
            new HomeGUI();

        } else if (e.getSource() == backButton) {
            // On click of the back button dispose the current JFrame
            inventoryFrame.dispose();

            // Passing the db connection, with new object to render Employee GUI page
            new EmployeeGUI(linkDB);

        } else if (e.getSource() == searchButton) {
            // On click of the search button clear all displayed products in the JTable
            clearProductsTable();

            // set productId to the user input
            final String productId = searchField.getText();

            // Create a search object passing in productID and products table
            Search searchObj = new Search(productId, productsTable);

            // call the readOne method and pass through the db connection
            searchObj.readOne(linkDB);

        } else if (e.getSource() == deleteButton) {
            // On click of delete, set variable to user input from productID text field
            final String deleteID = productIDField.getText();

            // JOptionPane to display a pop up to the user to confirm product delete
            final int action = JOptionPane.showConfirmDialog(null,
                    "Do you really want to delete this product?\nThis action cannot be undone.", "Delete",
                    JOptionPane.YES_NO_OPTION, 3, warningImg);

            // If the user selects Yes in the pop up, perform the delete action
            if (action == 0) {
                // Create object to call delete, passing through deleteID and db connection
                Delete deleteObj = new Delete(deleteID);
                deleteObj.delete(linkDB);

                // Refresh products Table
                refreshProducts();
            }
        }
    }

    // Event handler for searchField & searchButton
    @Override
    public void focusGained(FocusEvent e) {
        // When user clicks on search field, remove placeholder text
        if (e.getSource() == searchField && searchField.getText().equals("Search Product ID")) {
            searchField.setText("");
            // Set text to normal black and left aligned
            searchField.setForeground(Color.BLACK);
            searchField.setHorizontalAlignment(JTextField.LEFT);
        }
    }

    // Event handler for searchField & searchButton
    @Override
    public void focusLost(FocusEvent e) {
        // Display the placeholder text when the user click off an empty search field
        if (searchField.getText().isEmpty()) {
            setPlaceholderText();
        }
    }

    // Method to set the default placeholder text for the search field
    private void setPlaceholderText() {
        searchField.setText("Search Product ID");
        searchField.setForeground(Color.GRAY);
        searchField.setHorizontalAlignment(JTextField.CENTER);
    }

    // Method to refresh the JTable
    public void refreshProducts() {
        // Clear all text fields
        clearTextFields();
        // Create tableModel object and set the row count to 0
        DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
        model.setRowCount(0);

        // Creates a ReadAll object from model/ReadAll.java and passes in productsTable
        ReadAll displayTable = new ReadAll(productsTable);

        // Calls the readAll method and passes in the db connection
        displayTable.readAll(linkDB);
    }

    // Method to clear the productsTable JTable
    public void clearProductsTable() {
        // Create tableModel object and set the row count to 0
        DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
        model.setRowCount(0);
    }

    // Method to clear all text fields
    public void clearTextFields() {
        // Sets all text fields to empty strings
        productIDField.setText("");
        quantityField.setText("");
        wholeSaleField.setText("");
        salePriceField.setText("");
        supplierIDField.setText("");
        // Reset placeholder text of the search field
        setPlaceholderText();
    }

    // Method that populates text fields with product data when user clicks a row on
    // the JTable
    @Override
    public void mouseClicked(MouseEvent e) {
        // Create tableModel object and set the index for each cell in the row
        DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
        final int selectRowIndex = productsTable.getSelectedRow();

        // Set the text of each text field to the computed row index
        productIDField.setText(model.getValueAt(selectRowIndex, 0).toString());
        quantityField.setText(model.getValueAt(selectRowIndex, 1).toString());
        wholeSaleField.setText(model.getValueAt(selectRowIndex, 2).toString());
        salePriceField.setText(model.getValueAt(selectRowIndex, 3).toString());
        supplierIDField.setText(model.getValueAt(selectRowIndex, 4).toString());
    }

    /*
     * mousePressed, mouseReleased, mouseEntered, mouseExited methods are all
     * required to utilize mouseClicked. No functionality is currently impleemnted
     * with these methods
     */

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }
}
