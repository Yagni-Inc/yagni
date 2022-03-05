package Main_Project.src.Views;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CustomerGUI {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("I will eventually lead to the customer Web page!");

    CustomerGUI(){

        label.setBounds(100,0,700,200);
        label.setFont(new Font(null, Font.BOLD, 20));

        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setResizable(false); //this disable the resize features of the frame to the user
        frame.setLayout(null);
        frame.setVisible(true);

        
    }
}