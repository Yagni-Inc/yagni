package Main_Project.src.Views;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CustomerGUI {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("I will eventually lead to the customer GUI!");

    CustomerGUI(){

        label.setBounds(100,0,500,200);
        label.setFont(new Font(null, Font.BOLD, 20));

        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

        
    }
}