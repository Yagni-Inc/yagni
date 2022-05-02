package com.yagni.view;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import com.yagni.controller.*;

public class EmployeeGUI implements ActionListener {
	
	private static DbConnection linkDB; // Variable to persist connection to db
	private static JFrame frame; // JFrame to display all elements of EmployeeGUI

	// Variable declarations for all JPanels needed within Employee GUI
	private static JPanel headerPanel;
	private static JPanel bodyPanel;
	private static JPanel buttonPanel;
	private static JPanel footerPanel;

	// Variable declarations for all JLabels needed within Employee GUI
	private static JLabel headerLabel;
	private static JLabel bodyLabel;
	private static JLabel bodySubLabel;
	private static JLabel footerLabel;

	// Variable declarations for all JButtons needed within GUI
	private static JButton inventoryButton;
	private static JButton orderButton;
	private static JButton logoutButton;

	private static ImageIcon logoImg; // Variable to display Yagni logo

	EmployeeGUI(DbConnection linkDBIn) {

		// Set the connection with db
		linkDB = linkDBIn;

		// Initializing JFrame
		frame = new JFrame("Employee Main Page");

		// Initializing JPanels
		headerPanel = new JPanel();
		bodyPanel = new JPanel();
		buttonPanel = new JPanel();
		footerPanel = new JPanel();

		// Initializing JLabels
		headerLabel = new JLabel();
		bodyLabel = new JLabel("Welcome Employee! ");
		bodySubLabel = new JLabel("Choose an option to continue");
		footerLabel = new JLabel();

		// Initializing JButtons
		inventoryButton = new JButton("Manage Inventory");
		orderButton = new JButton("Manage Orders");
		logoutButton = new JButton("Logout");

		// Initializing ImageIcon with Yagni logo file path
		logoImg = new ImageIcon("App/assets/img/YagniLogoOnly.png");

		// Importing and setting custom font Caveat for all text components
		try {
			File font_file = new File("App/assets/fonts/Caveat-VariableFont_wght.ttf");
			Font caveatFont = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(25f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font_file));
			headerLabel.setFont(caveatFont.deriveFont(Font.BOLD, 35f));
			bodyLabel.setFont(caveatFont.deriveFont(Font.BOLD, 30f));
			bodySubLabel.setFont(caveatFont);
			inventoryButton.setFont(caveatFont);
			logoutButton.setFont(caveatFont);
			orderButton.setFont(caveatFont);
			footerLabel.setFont(caveatFont.deriveFont(16f));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		/* ------- Header Content ------- */
		// Set default size and layout of body panel
		headerPanel.setPreferredSize(new Dimension(100, 200));
		headerPanel.setBackground(Color.LIGHT_GRAY);

		// Set text and image alignment in header
		headerLabel.setIcon(logoImg);
		headerLabel.setIconTextGap(20);
		headerLabel.setText("Yagni Inc. Employee Actions Menu ");
		headerLabel.setVerticalTextPosition(JLabel.CENTER);
		headerLabel.setHorizontalTextPosition(JLabel.RIGHT);

		// Add header label to header panel
		headerPanel.add(headerLabel);

		// Format logout button layout
		logoutButton.setBounds(780, 15, 100, 40);
		logoutButton.setHorizontalTextPosition(JLabel.CENTER);
		logoutButton.setVerticalTextPosition(JLabel.CENTER);

		// Passing scope to action listener for logout button
		logoutButton.addActionListener(this);

		// Add the button to the frame to render to the page
		frame.add(logoutButton);

		/* ------- Body Content ------- */
		// Set default size and layout of body panel
		bodyPanel.setPreferredSize(new Dimension(100, 100));
		bodyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bodyPanel.setBackground(Color.LIGHT_GRAY);

		// Set default size and layout of button panel relative to body panel
		buttonPanel.setPreferredSize(new Dimension(400, 350));
		buttonPanel.setBackground(Color.DARK_GRAY);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		// Set style of body text labels
		bodyLabel.setForeground(Color.WHITE);
		bodySubLabel.setForeground(Color.WHITE);

		// Set button sizes of employee action buttons
		inventoryButton.setPreferredSize(new Dimension(200, 60));
		orderButton.setPreferredSize(new Dimension(200, 60));

		// Passing scope to action listener for employee action buttons
		inventoryButton.addActionListener(this);
		orderButton.addActionListener(this);

		// Adding body components to the body/button panels
		bodyPanel.add(buttonPanel);
		buttonPanel.add(bodyLabel);
		buttonPanel.add(bodySubLabel);
		buttonPanel.add(inventoryButton);
		buttonPanel.add(orderButton);

		/* ------- Footer Content ------- */
		// Set defaults and text of footer panel
		footerPanel.setPreferredSize(new Dimension(100, 30));
		footerPanel.setBackground(Color.DARK_GRAY);
		footerLabel.setText("Created by Yagni Inc. © 2022");
		footerLabel.setForeground(Color.WHITE);

		// Add footer text to footer panel
		footerPanel.add(footerLabel);

		/* ------- Frame Content ------- */
		// Exit out of application when closing the window
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Set default size, layout and style to the frame
		frame.setSize(900, 700);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);

		// Disable the resize features of the frame to the user
		frame.setResizable(false);

		// add the header, body and footer panels to the frame with BorderLayout manager
		frame.add(headerPanel, BorderLayout.NORTH);
		frame.add(bodyPanel, BorderLayout.CENTER);
		frame.add(footerPanel, BorderLayout.SOUTH);

		// display all content to the GUI
		frame.setVisible(true);
	}

	// Event handler for button clicks
	@Override
	public void actionPerformed(ActionEvent e) {
		// On click of inventory button dispose this frame
		if (e.getSource() == inventoryButton) {
			frame.dispose();

			// Passing the db connection with new object to render Employee GUI page
			new ManageInvGUI(linkDB);
		}
		if (e.getSource() == orderButton) {
			// On click of order button dispose this frame
			frame.dispose();

			// Passing the db connection with new object to render Orders GUI page
			new OrdersGUI(linkDB);
		}
		if (e.getSource() == logoutButton) {
			// On click of logout button close the db connection and dispose the JFrame
			linkDB.closeConnection();
			frame.dispose();

			// Create a new HomeGUI object to display the Home page
			new HomeGUI();
		}
	}
}
