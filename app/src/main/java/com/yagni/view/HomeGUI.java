package com.yagni.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeGUI implements ActionListener {

    JFrame frame = new JFrame("Yagni Inc. Ordering and Inventory Management");      // Crates Frame & Title
    JButton employeeButton = new JButton("Employee Login");     // Button to launch Employee Login
    JButton customerButton = new JButton("Customer Ordering");      // Button to launch Customer Web Page
    JPanel headerPanel = new JPanel();      // Creates panel for header content
    JPanel bodyPanel = new JPanel();        // Creates panel for body content
    JPanel buttonPanel = new JPanel();      // Creates inner panel for buttons in the body
    JPanel footerPanel = new JPanel();      // Creates panel for footer content
    JLabel headerLabel = new JLabel();      // text/image label for header
    JLabel footerLabel = new JLabel();      // text label for Yagni Inc. Copyright info
    ImageIcon logoImg = new ImageIcon("App/assets/img/YAGNI_logo.png");     // loads logo image

    HomeGUI() {

        // Importing and setting custom font Caveat for all text components
        try {
            File font_file = new File("App/assets/fonts/Caveat-VariableFont_wght.ttf");
            Font caveatFont = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));
            headerLabel.setFont(caveatFont.deriveFont(Font.BOLD, 40f));
            employeeButton.setFont(caveatFont);
            customerButton.setFont(caveatFont);
            footerLabel.setFont(caveatFont.deriveFont(16f));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        /****** Header Content ******/
        // set default size and layout of body panel
        headerPanel.setPreferredSize(new Dimension(100, 250));
        headerPanel.setBackground(Color.LIGHT_GRAY);

        // set text and image alignment in header
        headerLabel.setIcon(logoImg);
        headerLabel.setIconTextGap(20);
        headerLabel.setText("Ordering and Inventory Management");
        headerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        headerLabel.setHorizontalTextPosition(JLabel.CENTER);

        // add header label to header panel
        headerPanel.add(headerLabel);

        /****** Body Content ******/
        // set default size and layout of body panel
        bodyPanel.setPreferredSize(new Dimension(100, 100));
        bodyPanel.setBackground(Color.LIGHT_GRAY);

        // set default size and layout of button panel relative to body panel
        buttonPanel.setPreferredSize(new Dimension(400, 350));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 70));
        bodyPanel.add(buttonPanel);

        // Employee Login
        employeeButton.setPreferredSize(new Dimension(300, 60));
        employeeButton.addActionListener(this);
        buttonPanel.add(employeeButton);

        // Customer Login
        customerButton.setPreferredSize(new Dimension(300, 60));
        customerButton.addActionListener(this);
        buttonPanel.add(customerButton);

        /****** Footer Content ******/
        // set defualts and text of footer panel
        footerPanel.setPreferredSize(new Dimension(100, 30));
        footerPanel.setBackground(Color.DARK_GRAY);
        footerLabel.setText("Created by Yagni Inc. © 2022");
        footerLabel.setForeground(Color.WHITE);

        // add footer text to footer panel
        footerPanel.add(footerLabel);

        /******Frame Content ******/
        // set default size. layout and style to the frame
        frame.getContentPane().setBackground(Color.LIGHT_GRAY); // Exit out of application.

        // add the header, body and footer panels to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(bodyPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        // Dispose out of this GUI application only if needed, program will continue to run.
        frame.setSize(900, 700);
        frame.setResizable(false); // this disable the resize features of the frame to the user

        // display all content to the GUI
        frame.setVisible(true);

    }

    // Button click method to navigate the user to the Employee Login screen or the
    // Customer Ordering Web page
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == employeeButton) {
            frame.dispose();
            new LoginGUI();
        } else if (e.getSource() == customerButton) {
            try {
                Desktop.getDesktop().browse(new URI("https://yagni-inc.github.io/yagni/"));
            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        }

    }
}
