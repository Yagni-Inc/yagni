package com.yagni.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import com.yagni.controller.*;

public class LoginGUI implements ActionListener {

    private static JLabel userLabel = new JLabel("User");
    private static JTextField userText;
    private static JLabel passwordLabel = new JLabel("Password");
    private static JPasswordField passwordText;
    private static JButton button = new JButton("Login");
    private static JLabel success = new JLabel();
    private static JButton backButton = new JButton("Back");

    JFrame frame = new JFrame("Employee Login"); // Creates frame and title
    JPanel headerPanel = new JPanel(); // Creates header content area
    JPanel bodyPanel = new JPanel(); // Creates body content area
    JPanel loginPanel = new JPanel(); // Creates inner body panel for login text fields
    JPanel footerPanel = new JPanel(); // Creates footer content area
    JLabel headerLabel = new JLabel(); // textimage label for header
    JLabel footerLabel = new JLabel(); // text label for footer
    ImageIcon logoImg = new ImageIcon("App/assets/img/YagniLogoOnly.png"); // load logo image

    LoginGUI() {

        // Importing and setting custom font Caveat for all text components
        try {
            File font_file = new File("App/assets/fonts/Caveat-VariableFont_wght.ttf");
            Font caveatFont = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));
            headerLabel.setFont(caveatFont.deriveFont(Font.BOLD, 40f));
            success.setFont(caveatFont.deriveFont(Font.BOLD, 25f));
            userLabel.setFont(caveatFont);
            passwordLabel.setFont(caveatFont);
            button.setFont(caveatFont);
            backButton.setFont(caveatFont);
            footerLabel.setFont(caveatFont.deriveFont(16f));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        /* ------- Header Content ------- */
        // set default size and layout of body panel
        headerPanel.setPreferredSize(new Dimension(100, 250));
        headerPanel.setBackground(Color.LIGHT_GRAY);

        // set text and image alignment in header
        headerLabel.setIcon(logoImg);
        headerLabel.setIconTextGap(20);
        headerLabel.setText("Yagni Inc. Employee Login ");
        headerLabel.setVerticalTextPosition(JLabel.CENTER);
        headerLabel.setHorizontalTextPosition(JLabel.RIGHT);

        // add header label to header panel
        headerPanel.add(headerLabel);

        /*-------Back Button-------*/
        backButton.setBounds(10, 15, 100, 40);
        frame.add(backButton);
        backButton.addActionListener(this);
        backButton.setHorizontalTextPosition(JLabel.CENTER);
        backButton.setVerticalTextPosition(JLabel.CENTER);

        /* ------- Body Content ------- */
        // set default size and layout of body panel and inner login panel
        bodyPanel.setPreferredSize(new Dimension(100, 100));
        bodyPanel.setBackground(Color.LIGHT_GRAY);
        loginPanel.setPreferredSize(new Dimension(400, 350));
        loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bodyPanel.add(loginPanel);

        // User Text and Text Field
        userText = new JTextField();
        userText.setPreferredSize(new Dimension(250, 40));
        loginPanel.add(userLabel);
        loginPanel.add(userText);

        // Password Text and Text Field
        passwordText = new JPasswordField();
        passwordText.setPreferredSize(new Dimension(250, 40));
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordText);

        // Login Button and Success Message
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(200, 60));
        loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        loginPanel.add(button);
        loginPanel.add(success);

        /* ------- Footer Content ------- */
        // set defaults and text of footer panel
        footerPanel.setPreferredSize(new Dimension(100, 30));
        footerPanel.setBackground(Color.DARK_GRAY);
        footerLabel.setText("Created by Yagni Inc. © 2022 ");
        footerLabel.setForeground(Color.WHITE);

        // add footer text to footer panel
        footerPanel.add(footerLabel);

        /* ------- Frame Content ------- */
        // set default size. layout and style to the frame
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose out of this GUI application only if needed,
                                                                 // program will continue to run.
        frame.setResizable(false); // disables the resize features of the frame to the user

        // add header, body and footer panels to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(bodyPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);

        // display all content to the GUI
        frame.setVisible(true);

    }

    // Button click method
    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();

        DbConnection linkDB = new DbConnection(user, password);
        System.out.println(linkDB);
        success.setText("");

        // if statement to check login credentials
        if (e.getSource() == button && linkDB.getConnection() != null) {

            System.out.println("Login Successful!");
            System.out.println("---------------------------------------");
            frame.dispose();
            new EmployeeGUI(linkDB);

            // else to error handle improper credentials
        } else {
            success.setText("Invalid credentials!  Please Try Again. ");
            success.setForeground(Color.RED);
        }

        // back button interaction
        if (e.getSource() == backButton) {
            frame.setVisible(false);
            frame.dispose();
            success.setText("");
            new HomeGUI();
            ;
        }
    }
}