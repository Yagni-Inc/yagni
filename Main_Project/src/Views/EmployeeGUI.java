package Main_Project.src.Views;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeGUI implements ActionListener {

    JFrame frame = new JFrame("User Home Page");   // Crates Frame & Tittle
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JLabel label2 = new JLabel();
    JButton inventoryButton = new JButton();        // Button for Inventory 
    JButton orderButton = new JButton();            // Button for orders

    EmployeeGUI() {
       
        label.setText("Welcome!");                  
        label.setBounds(325, 50, 85, 25);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);

        label2.setText("Choose an option to continue.");
        label2.setBounds(220, 70, 300, 25);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.TOP);

        inventoryButton.setBounds(300, 125, 150, 50);
        inventoryButton.setText("Manage Inventory");
        inventoryButton.addActionListener(this);

        orderButton.setBounds(300, 200, 150, 50);
        orderButton.setText("Manage Orders");
        orderButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit out of application.
        frame.setSize(700, 500);
        panel.setBackground(Color.lightGray);
        frame.add(inventoryButton);
        frame.add(orderButton);
        frame.add(label);
        frame.add(label2);
        frame.add(panel);
        frame.setVisible(true);

    }

   /**********FOR TESTING PURPOSES************/
   /* public static void main(String[] args) {

        EmployeeGUI user = new EmployeeGUI();
    }*/
   /**************************************/
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == inventoryButton){
            frame.dispose();
            ManageInventoryGUI window = new ManageInventoryGUI();

        }

        if(e.getSource() == orderButton){
            frame.dispose();
            OrdersGUI window2 = new OrdersGUI();

        }
        
    }

}

