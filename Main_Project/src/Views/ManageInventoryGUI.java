package Main_Project.src.Views;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ManageInventoryGUI {

        JFrame inventoryFrame = new JFrame("Product Inventory Window");  
        JLabel inventoryLabel = new JLabel();

        ManageInventoryGUI(){

            inventoryLabel.setText("This will eventualy show CRUD operations and Inventory.");  // Window for future CRUD operations
            inventoryLabel.setBounds(325, 80, 85, 25);
            inventoryLabel.setFont(new Font("Calibri",Font.BOLD,20));
            inventoryLabel.setHorizontalAlignment(JLabel.CENTER);
            inventoryLabel.setVerticalAlignment(JLabel.TOP);

            inventoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit out of application.
            inventoryFrame.setSize(700, 500);
            inventoryFrame.add(inventoryLabel);
            inventoryFrame.setVisible(true);

        }
}
