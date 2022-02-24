package src.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Home implements ActionListener {

    
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton empButton;
    private static JButton customerButton;
    private static JLabel success;
    private static JFrame frame = new JFrame("Home");
    private static JPanel panel = new JPanel();

    public static void main(String[] args) {


        frame.setSize(350, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        // Employee Login Button 
        empButton = new JButton("Employee Login"); // Employee GUI Button 
        empButton.setBounds(10, 80, 200, 40);
        empButton.addActionListener(Home());
        panel.add(empButton);

        // Customer Login Button 
        customerButton = new JButton("Customer Login"); // Employee GUI Button 
        customerButton.setBounds(10, 160, 200, 40);
        customerButton.addActionListener(new Home());
        panel.add(customerButton);

        success = new JLabel("");
        success.setBounds(10, 300, 300, 25);
        panel.add(success);
        
        
        frame.setVisible(true);

   
        
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            
             if(e.getSource() == empButton){
                 frame.dispose();
                 new LoginWindow();

             }
        
                success.setText("Login Successful!");
                
             // end of if statement
        }// end of button action

}
    

