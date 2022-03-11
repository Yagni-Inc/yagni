package Main_Project.src.Views;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



import Main_Project.src.Controller.*;

import Main_Project.src.Model.*;


public class ManageInventoryGUI implements ActionListener,FocusListener,MouseListener{

        private static JFrame inventoryFrame = new JFrame("Product Inventory Window");  
        private static JTable productsTable = new JTable();
        private static JScrollPane tableScroll = new JScrollPane();
        private static DbConnection linkDB;
        
        
        private static JPanel headerPanel = new JPanel();               //Creates header content are
        private static JPanel navPanel = new JPanel();             
        private static JPanel bodyPanel = new JPanel();                //Creates body content area 
        private static JPanel footerPanel = new JPanel();              //Creates footer content area 
        private static JPanel tablePanel = new JPanel();               //Creates panel for table area
        private static JPanel controlsPanel = new JPanel();            //Creates panel for controls area
       
        private static JLabel headerLabel = new JLabel();              //text/image label for header 
        private static JLabel footerLabel = new JLabel();              //text label for footer
        private static JLabel controlsTitle = new JLabel("Actions");
        private static JLabel productIDLabel = new JLabel("Product ID");
        private static JLabel quantityLabel = new JLabel("Quantity");
        private static JLabel wholeSaleLabel = new JLabel("Whole Sale Price");
        private static JLabel salePriceLabel = new JLabel("Sale Price");
        private static JLabel supplierIDLabel = new JLabel("Supplier ID");

        private static ImageIcon logoImg = new ImageIcon("Main_Project/assets/img/YagniLogoOnly-50percent.png"); //load logo image  
        private static ImageIcon searchImg = new ImageIcon("Main_Project/assets/img/searchIcon.png"); //image for search
        
        private static JButton addButton = new JButton("Add New Product");
        private static JButton updateButton = new JButton("Update Product");
        private static JButton deleteButton = new JButton("Delete Product"); 
        private static JButton loadButton = new JButton("Load Inventory Data");
        private static JButton reloadButton = new JButton("Refresh");
        private static JButton searchButton = new JButton(); //button with searchIcon.png
        private static JButton backButton = new JButton("Back");
        private static JButton logoutButton = new JButton("Logout");
       
        private static JTextField productIDField = new JTextField(20);
        private static JTextField quantityField = new JTextField(20);
        private static JTextField wholeSaleField = new JTextField(20);
        private static JTextField salePriceField = new JTextField(20);
        private static JTextField supplierIDField = new JTextField(20);
        private static JTextField searchField = new JTextField(20);
        
         ManageInventoryGUI(DbConnection linkDBIn){
            
            // setting the connection with database
            linkDB = linkDBIn;

            // Importing and setting custom font Caveat for all text components 
            try {
                File font_file = new File("Main_Project/assets/fonts/Caveat-VariableFont_wght.ttf");
                Font caveatFont = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(20f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));
                headerLabel.setFont(caveatFont.deriveFont(Font.BOLD, 35f));
                controlsTitle.setFont(caveatFont.deriveFont(Font.BOLD, 25f));
                addButton.setFont(caveatFont);
                productIDLabel.setFont(caveatFont);
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
                footerLabel.setFont(caveatFont.deriveFont(16f));	    
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
            headerLabel.setText("Yagni Inc. Employee Actions Menu ");
            headerLabel.setVerticalTextPosition(JLabel.CENTER);
            headerLabel.setHorizontalTextPosition(JLabel.RIGHT);

            //adding back button inside header panel
            backButton.setBounds(0, 0, 70, 40);
            backButton.setHorizontalAlignment(JLabel.LEFT);
            backButton.setVerticalAlignment(JLabel.TOP);
            backButton.addActionListener(this);
           // headerPanel.add(backButton);
           
            //adding logout button inside header panel
            logoutButton.setBounds(830, 0, 70, 40);
            logoutButton.setHorizontalAlignment(JLabel.LEFT);
            logoutButton.setVerticalAlignment(JLabel.TOP);
            logoutButton.addActionListener(this);
            //headerPanel.add(logoutButton);

            //add header label to header panel 
            headerPanel.add(headerLabel);

             /* ------- Body Content ------- */ 
            bodyPanel.setLayout(null);
            bodyPanel.setBackground(Color.LIGHT_GRAY);
            bodyPanel.add(controlsPanel);
        
             /* ------- CRUD Controls Panel------- */
            controlsPanel.setBounds(30, 30, 270, 500);
            controlsPanel.setBackground(Color.LIGHT_GRAY);
            controlsPanel.setLayout(new GridLayout(14, 1, 0, 5));
            controlsPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
            controlsTitle.setHorizontalAlignment(JLabel.CENTER);
            controlsPanel.add(controlsTitle); 
            //adding buttons/textfields to actual panel
            controlsPanel.add(addButton);
            addButton.addActionListener(this);
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
            controlsPanel.add(updateButton);
            updateButton.addActionListener(this);
            controlsPanel.add(deleteButton);
            deleteButton.addActionListener(this);
            
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
            tableScroll.setViewportView(productsTable);
            productsTable.addMouseListener(this);
            
            
            
            
            /* ------- Search Textfield & Button ------- */
            searchField.setBounds(0, 0, 200, 40);
            searchField.setText("Search Product ID");
            searchField.setHorizontalAlignment( JTextField.CENTER);
            searchField.setForeground(Color.GRAY);
            searchField.addFocusListener(this);
            
            tablePanel.add(searchField); 
        
            searchButton.setBounds(200, 0, 40, 40);
            searchButton.setIcon(searchImg);
            searchButton.addActionListener(this);
            
            tablePanel.add(searchButton);
            
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
            inventoryFrame.setSize(900,700);
            inventoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit out of the application
            inventoryFrame.setResizable(false); //this disable the resize features of the frame to the user
            

            //add the header, body and footer panels to the frame 
            inventoryFrame.add(headerPanel, BorderLayout.NORTH);
            inventoryFrame.add(bodyPanel);
            inventoryFrame.add(footerPanel, BorderLayout.SOUTH);
        
            //displays all content to the GUI 
            inventoryFrame.setVisible(true);

            }// end of ManageInventoryGUI(DbConnection linkDBIn)

    @Override // event handler for button clicks
    public void actionPerformed(ActionEvent e) {
        //on click of Load button, dsiplay all data from product inventory table 
        if(e.getSource() == loadButton){
            tablePanel.remove(loadButton);
            tablePanel.repaint();
            tablePanel.add(reloadButton);
            ReadAll read = new ReadAll(productsTable); // creates a ReadAll object from model/ReadAll.java and passes in the products table
            read.readAll(linkDB); // calls the readAll method and passes in the database connection

        }
        else if(e.getSource() == addButton){
            // setting our variables to user input
            String productId = productIDField.getText();
            String quantity = quantityField.getText();
            String wholeSale = wholeSaleField.getText();
            String salePrice = salePriceField.getText();
            String supplierId = supplierIDField.getText();

            clearTextFields();

            // creating a Creat obj and calling addRecord passing in user input
            Create addRecord = new Create(productId, quantity, wholeSale, salePrice, supplierId);
            addRecord.addRecord(linkDB);
            refreshProducts();
        }
		else if (e.getSource() == updateButton){
			// setting our variables to user input
            String updateID = productIDField.getText();
            String updateQuant = quantityField.getText();
            String updateWholesale = wholeSaleField.getText();
            String updatePrice = salePriceField.getText();
            String updateSupplierID = supplierIDField.getText();

			Update updateRecord = new Update(updateID, updateQuant, updateWholesale, updatePrice, updateSupplierID);
			updateRecord.update(linkDB);
			refreshProducts();
		}
        else if (e.getSource() == reloadButton){
            refreshProducts();
        }
        
        else if(e.getSource() == logoutButton){
            inventoryFrame.dispose();
            new HomeGUI(); 

        }
        else if(e.getSource() == backButton){
            inventoryFrame.dispose();
            new EmployeeGUI(linkDB);
        }
        else if(e.getSource() == searchButton){
            clearProductsTable();
           
            String productId = searchField.getText();

            Search searchObj = new Search(productId, productsTable); // creates a searchObj object from model/Search.java and passes in the products table
            searchObj.readOne(linkDB); // calls the readOne method and passes in the database connection
            }


        else if(e.getSource() == deleteButton){
            
            String deleteID = productIDField.getText();
            Delete deleteObj = new Delete(deleteID);
            deleteObj.delete(linkDB);

            refreshProducts();
            
        }

        


    }

    @Override //focusGained & focusLost Override's both belong to searchField & searchButton
    public void focusGained(FocusEvent e){
        if(e.getSource() == searchField && searchField.getText().equals("Search Product ID")){
            searchField.setText("");
            searchField.setForeground(Color.BLACK);
            searchField.setHorizontalAlignment(JTextField.LEFT);
            

        }// end of if
    }// end of focusGained
    
    @Override //focusGained & focusLost Override's both belong to searchField & searchButton
    public void focusLost(FocusEvent e) {
        if(searchField.getText().isEmpty()){
            searchField.setText("Search Product ID");
            searchField.setForeground(Color.GRAY);
            searchField.setHorizontalAlignment(JTextField.CENTER);
        }// end of if
    }// end of focusLost

    //Method to refresh the productsTable JTable 
    public void refreshProducts(){
        DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
        model.setRowCount(0);
        ReadAll displayTable = new ReadAll(productsTable); // creates a ReadAll object from model/ReadAll.java and passes in the products table
        displayTable.readAll(linkDB);


    }
    //Method to refresh the productsTable JTable 
    public void clearProductsTable(){
        DefaultTableModel model = (DefaultTableModel) productsTable.getModel();
        model.setRowCount(0);
        
    }
    public void clearTextFields(){
           
            productIDField.setText("");
            quantityField.setText("");
            wholeSaleField.setText("");
            salePriceField.setText("");//hi
            supplierIDField.setText("");

    }
    
    @Override //method that populates our textfields when you click a row on the JTable
    public void mouseClicked(MouseEvent e) {
        
        DefaultTableModel tmodel=(DefaultTableModel)productsTable.getModel();
        int selectrowindex=productsTable.getSelectedRow();
        productIDField.setText(tmodel.getValueAt(selectrowindex, 0).toString());
        quantityField.setText(tmodel.getValueAt(selectrowindex, 1).toString());
        wholeSaleField.setText(tmodel.getValueAt(selectrowindex, 2).toString());
        salePriceField.setText(tmodel.getValueAt(selectrowindex, 3).toString());
        supplierIDField.setText(tmodel.getValueAt(selectrowindex, 4).toString());
        
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // need this here app breaks if you remove these events
        
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // need this here app breaks if you remove these events
        
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // need this here app breaks if you remove these events
        
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // need this here app breaks if you remove these events
        
    }
    


    
       
}
