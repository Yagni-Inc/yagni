package Main_Project.src.Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class HomeGUI implements ActionListener{

    JFrame frame = new JFrame();
    JButton employeeButton = new JButton("Employee Login");
    JButton customerButton = new JButton("Customer Login");
    

    HomeGUI(){
        employeeButton.setBounds(150, 160, 200, 40);
        employeeButton.addActionListener(this);

        customerButton.setBounds(150, 360, 200, 40);
        customerButton.addActionListener(this);



        frame.add(employeeButton);
        frame.add(customerButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == employeeButton){
            frame.dispose();
            new LoginGUI();
        }else if(e.getSource() == customerButton){
            frame.dispose();
            new CustomerGUI();
        }
        
    }

    
}
