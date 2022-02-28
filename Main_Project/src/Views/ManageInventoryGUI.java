package Main_Project.src.Views;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageInventoryGUI {


        JFrame userFrame = new JFrame("Product Inventory Window");  
        JLabel userLabel = new JLabel();

        ManageInventoryGUI(){

            userLabel.setText("This will soon show CRUD operations.");   // Window for future CRUD operations
            userLabel.setBounds(325, 50, 85, 25);
            userLabel.setHorizontalAlignment(JLabel.CENTER);
            userLabel.setVerticalAlignment(JLabel.TOP);

            userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit out of application.
            userFrame.setSize(900, 900);
            userFrame.add(userLabel);
            userFrame.setVisible(true);

        }

        

    
}
