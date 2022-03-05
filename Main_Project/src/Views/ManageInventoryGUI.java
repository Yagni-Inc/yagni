package Main_Project.src.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ManageInventoryGUI {

        private static JFrame inventoryFrame = new JFrame("Product Inventory Window");  
        private static JLabel inventoryLabel = new JLabel();
        private static JPanel headerPanel = new JPanel();              //Creates header content area
        private static JPanel bodyPanel = new JPanel();                //Creates body content area 
        private static JPanel footerPanel = new JPanel();              //Creates footer content area 
        private static JLabel headerLabel = new JLabel();              //text/image label for header 
        private static JLabel footerLabel = new JLabel();              //text label for footer
        private static ImageIcon logoImg = new ImageIcon("Main_Project/assets/img/YagniLogoOnly-50percent.png"); //load logo image  

        private static JPanel tablePanel = new JPanel();               //Creates panel for table area
        private static JPanel controlsPanel = new JPanel();            //Creates panel for controls area
        private static JButton addButton = new JButton("Add New Product");
        private static JButton updateButton = new JButton("Update Product");
        private static JButton deleteButton = new JButton("Delete Product"); 
        private static JLabel controlsTitle = new JLabel("Product Lookup");
        private static JLabel productIDLabel = new JLabel("Product ID: ");
        private static JTextField productIDField = new JTextField(20);
        private static JLabel quantityLabel = new JLabel("Quantity");
        private static JTextField quantityField = new JTextField(20);
        private static JLabel wholeSaleLabel = new JLabel("Whole Sale Price: ");
        private static JTextField wholeSaleField = new JTextField(20);
        private static JLabel salePriceLabel = new JLabel("Sale Price: ");
        private static JTextField salePriceField = new JTextField(20);
        private static JLabel supplierIDLabel = new JLabel("Supplier ID: ");
        private static JTextField supplierIDField = new JTextField(20);
        private static JTable inventoryTable = new JTable();

        ManageInventoryGUI(){

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

        //add header label to header panel 
        headerPanel.add(headerLabel);

        

         /* ------- Body Content ------- */ 
        bodyPanel.setLayout(null);
        bodyPanel.setBackground(Color.LIGHT_GRAY);
        bodyPanel.add(controlsPanel);
       
        

        //CRUD controls Panel 
       
        
        controlsPanel.setBounds(30, 30, 270, 500);
        controlsPanel.setBackground(Color.LIGHT_GRAY);
        controlsPanel.setLayout(new GridLayout(14, 1, 0, 5));
        controlsPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        controlsTitle.setHorizontalAlignment(JLabel.CENTER);
        controlsPanel.add(controlsTitle); 
        
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
        //controlsPanel.add(new JLabel(""));
        controlsPanel.add(updateButton);
        controlsPanel.add(deleteButton);

        //controlsPanel.add(controlContentPanel);

        //Table  Panel 
    
        
        tablePanel.setBounds(330, 30, 550, 500);
        tablePanel.setLayout(null);
        bodyPanel.add(tablePanel);
       
        
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
       
        //display all content to the GUI 
        inventoryFrame.setVisible(true);

        }

           /**********FOR TESTING PURPOSES************/
   public static void main(String[] args) {


        new ManageInventoryGUI();

    }

       
}
