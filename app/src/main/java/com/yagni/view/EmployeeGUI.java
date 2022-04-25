package com.yagni.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import com.yagni.controller.*;

public class EmployeeGUI implements ActionListener {

    JFrame frame = new JFrame("Employee Main Page");   // Crates Frame & Title
    JPanel headerPanel = new JPanel();          //Creates panel for header content 
    JPanel bodyPanel = new JPanel();            //Creates panel for body content 
    JPanel buttonPanel = new JPanel();          //Creates inner panel for buttons in the body 
    JPanel footerPanel = new JPanel();          //Creates panel for footer content 
    JLabel headerLabel = new JLabel();          //text/image label for header
    JLabel label = new JLabel();                //main text label for main body 
    JLabel subLabel = new JLabel();             //sub label for body text 
    JLabel footerLabel = new JLabel();          //text label for Yagni Inc. Copyright info 
    JButton inventoryButton = new JButton();    //Button for Inventory 
    JButton orderButton = new JButton();        //Button for orders
    ImageIcon logoImg = new ImageIcon("App/assets/img/YagniLogoOnly.png"); //loads logo image
    DbConnection linkDB; 
    JButton logoutButton = new JButton("Logout");

    EmployeeGUI(DbConnection linkDBIn) {

        linkDB = linkDBIn;

         // Importing and setting custom font Caveat for all text components 
         try {
            File font_file = new File("App/assets/fonts/Caveat-VariableFont_wght.ttf");
            Font caveatFont = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(25f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));
            headerLabel.setFont(caveatFont.deriveFont(Font.BOLD, 35f));
            label.setFont(caveatFont.deriveFont(Font.BOLD, 30f));
            subLabel.setFont(caveatFont);
            inventoryButton.setFont(caveatFont);
            logoutButton.setFont(caveatFont);
            orderButton.setFont(caveatFont);
            footerLabel.setFont(caveatFont.deriveFont(16f));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        /* ------- Header Content ------- */ 
        //set default size and layout of body panel 
        headerPanel.setPreferredSize(new Dimension(100, 200));
        headerPanel.setBackground(Color.LIGHT_GRAY);

        //set text and image alignment in header
        headerLabel.setIcon(logoImg);
        headerLabel.setIconTextGap(20);
        headerLabel.setText("Yagni Inc. Employee Actions Menu ");
        headerLabel.setVerticalTextPosition(JLabel.CENTER);
        headerLabel.setHorizontalTextPosition(JLabel.RIGHT);

        //add header label to header panel 
        headerPanel.add(headerLabel);

        /*------- Logout Button -------*/
        logoutButton.setBounds(780, 15, 100, 40);
        frame.add(logoutButton);
        logoutButton.addActionListener(this);
        logoutButton.setHorizontalTextPosition(JLabel.CENTER);
        logoutButton.setVerticalTextPosition(JLabel.CENTER);
       
        /* ------- Body Content ------- */
        //set default size and layout of body panel 
        bodyPanel.setPreferredSize(new Dimension(100,100));
        bodyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bodyPanel.setBackground(Color.LIGHT_GRAY);

        //set default size and layout of button panel relative to body panel
        buttonPanel.setPreferredSize(new Dimension(400, 350));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        //set text and style body text labels 
        label.setText("Welcome Employee! ");
        subLabel.setText("Choose an option to continue");  
        label.setForeground(Color.WHITE);
        subLabel.setForeground(Color.WHITE);

        //set defaults of inventory button 
        inventoryButton.setPreferredSize(new Dimension(200, 60));
        inventoryButton.setText("Manage Inventory");
        inventoryButton.addActionListener(this);
        
        //set defaults of customer orders button 
        orderButton.setPreferredSize(new Dimension(200, 60));
        orderButton.setText("Manage Orders");
        orderButton.addActionListener(this);

        //add button panel to body panel; add text labels and buttons to button panel 
        bodyPanel.add(buttonPanel);
        buttonPanel.add(label); 
        buttonPanel.add(subLabel);
        buttonPanel.add(inventoryButton);
        buttonPanel.add(orderButton);

        /* ------- Footer Content ------- */
        //set defualts and text of footer panel
        footerPanel.setPreferredSize(new Dimension(100, 30));
        footerPanel.setBackground(Color.DARK_GRAY);
        footerLabel.setText("Created by Yagni Inc. © 2022");
        footerLabel.setForeground(Color.WHITE);

        //add footer text to footer panel 
        footerPanel.add(footerLabel);

        /* ------- Frame Content ------- */ 
        //set default size. layout and style to the frame 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        // Dispose out of this GUI application only if needed, program will continue to run.
        frame.setSize(900,700);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setResizable(false); //this disable the resize features of the frame to the user

        //add the header, body and footer panels to the frame 
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(bodyPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);
        
        //display all content to the GUI 
        frame.setVisible(true);

    }

   /**********FOR TESTING PURPOSES************/
   //public static void main(String[] args) {


        //EmployeeGUI user = new EmployeeGUI();

    //}

 //Button click method to navigate the user to the Inventory management CRUD GUI or the Customer Orders Management GUI

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == inventoryButton){
            frame.dispose();
            new ManageInvGUI(linkDB);
        }
        if(e.getSource() == orderButton){
            frame.dispose();
            new OrdersGUI(linkDB);
        }
        if(e.getSource() == logoutButton){
            linkDB.closeConnection();
            frame.dispose();

            new HomeGUI(); 
        }
    }
}
