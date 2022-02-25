package Main_Project.src.Views;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ManageInventoryGUI {

        JFrame userFrame = new JFrame("Product Inventory Window");  
        JLabel userLabel = new JLabel();

        ManageInventoryGUI(){

            userLabel.setText("This will eventualy show CRUD operations and Inventory.");  // Window for future CRUD operations
            userLabel.setBounds(325, 80, 85, 25);
            userLabel.setFont(new Font("Calibri",Font.BOLD,20));
            userLabel.setHorizontalAlignment(JLabel.CENTER);
            userLabel.setVerticalAlignment(JLabel.TOP);

            userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit out of application.
            userFrame.setSize(700, 500);
            userFrame.add(userLabel);
            userFrame.setVisible(true);

        }
}
