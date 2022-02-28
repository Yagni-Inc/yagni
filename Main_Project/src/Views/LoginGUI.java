package Main_Project.src.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI implements ActionListener {
    
    private static JLabel userLabel = new JLabel("User");
    private static JTextField userText;
    private static JLabel passwordLabel = new JLabel("Password");
    private static JPasswordField passwordText;
    private static JButton button = new JButton("Login");
    private static JLabel success = new JLabel();

    JFrame frame = new JFrame("Employee Login");    //Creates frame and title 
    JPanel headerPanel = new JPanel();              //Creates header content area
    JPanel bodyPanel = new JPanel();                //Creates body content area 
    JPanel loginPanel = new JPanel();               //Creates inner body panel for login text fields
    JPanel footerPanel = new JPanel();              //Creates footer content area 
    JLabel headerLabel = new JLabel();              //text/image label for header 
    JLabel footerLabel = new JLabel();              //text label for footer
    ImageIcon logoImg = new ImageIcon("Main_Project/assets/img/YagniLogoOnly.png"); //load logo image  

    LoginGUI(){

        // Importing and setting custom font Caveat for all text components 
        try {
            File font_file = new File("Main_Project/assets/fonts/Caveat-VariableFont_wght.ttf");
            Font caveatFont = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(25f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));
            headerLabel.setFont(caveatFont.deriveFont(Font.BOLD, 40f));
            success.setFont(caveatFont.deriveFont(Font.BOLD, 25f));
            userLabel.setFont(caveatFont);
            passwordLabel.setFont(caveatFont);
            button.setFont(caveatFont);
            footerLabel.setFont(caveatFont.deriveFont(16f));	    
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        } 
        
        /* ------- Header Content ------- */ 
        //set default size and layout of body panel 
        headerPanel.setPreferredSize(new Dimension(100, 250));
        headerPanel.setBackground(Color.LIGHT_GRAY);

        //set text and image alignment in header
        headerLabel.setIcon(logoImg);
        headerLabel.setIconTextGap(20);
        headerLabel.setText("Yagni Inc. Employee Login ");
        headerLabel.setVerticalTextPosition(JLabel.CENTER);
        headerLabel.setHorizontalTextPosition(JLabel.RIGHT);

        //add header label to header panel 
        headerPanel.add(headerLabel);
        
       
        /* ------- Body Content ------- */
         //set default size and layout of body panel and inner login panel 
        bodyPanel.setPreferredSize(new Dimension(100,100));
        bodyPanel.setBackground(Color.LIGHT_GRAY);
        loginPanel.setPreferredSize(new Dimension(400, 350));
        loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bodyPanel.add(loginPanel);
        
        // User Text and Text Field 
        userText = new JTextField(); 
        userText.setPreferredSize(new Dimension(250, 40));
        loginPanel.add(userLabel);
        loginPanel.add(userText);

        //Password Text and Text Field 
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
        //set defualts and text of footer panel
        footerPanel.setPreferredSize(new Dimension(100, 30));
        footerPanel.setBackground(Color.DARK_GRAY);
        footerLabel.setText("Created by Yagni Inc. © 2022 ");
        footerLabel.setForeground(Color.WHITE);

         //add footer text to footer panel 
        footerPanel.add(footerLabel);
        

        /* ------- Frame Content ------- */
        //set default size. layout and style to the frame 
        frame.setSize(900,900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit out of the application

        //add the header, body and footer panels to the frame 
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(bodyPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.SOUTH);

        //display all content to the GUI 
        frame.setVisible(true);

   
        
    }
        //Button click method 
        @Override
        public void actionPerformed(ActionEvent e) {
             String user = userText.getText();
             String password = passwordText.getText();
             
             //if the login button is clicked and the user name and password are correct then open the EmployeeGUI; display login error otherwise. 
            if(e.getSource() == button && user.equals("YAGNI") && password.equals("2022")) {
                success.setText("Login Successful!");
                frame.dispose();
                new EmployeeGUI(); 
            }else{
                success.setText("Invalid credentials!  Please Try Again. ");
                success.setForeground(Color.RED); 
                
                
            }// end of if statement
        }// end of button action
}