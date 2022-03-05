package Main_Project.src.Views;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class OrdersGUI {

    JFrame ordersFrame = new JFrame("Manage Orders Window");  
    JLabel ordersLabel = new JLabel();

    OrdersGUI(){

        ordersLabel.setText("This will eventually show orders page.");   // Window for future CRUD operations
        ordersLabel.setBounds(325, 80, 85, 25);
        ordersLabel.setFont(new Font("Calibri",Font.BOLD,20));
        ordersLabel.setHorizontalAlignment(JLabel.CENTER);
        ordersLabel.setVerticalAlignment(JLabel.TOP);

        ordersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit out of application.
        ordersFrame.setSize(900, 700);
        ordersFrame.setResizable(false); //this disable the resize features of the frame to the user
        ordersFrame.add(ordersLabel);
        ordersFrame.setVisible(true);

    }
    
}
