package com.fourbit.subscriptionmanagement.windows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fourbit.subscriptionmanagement.baseutils.clientmanagement.Client;

public class WindowRegisterUser extends Window{

	private JButton addUser;
	private JButton resetFields;
	private JButton generateId;
	private JLabel userIdLabel;
	private JTextField userIdField;
	private JLabel firstNameLabel;
	private JTextField userFirstNameField;
	private JLabel middleNameLabel;
	private JTextField userMiddleNameField;
	private JLabel lastNameLabel;
	private JTextField userLastNameField;
	private JLabel phoneLabel;
	private JTextField userPhoneField;
	private JLabel emailLabel;
	private JTextField userEmailField;


	public WindowRegisterUser(){
		super();
		addUser = new JButton("Register");
		resetFields = new JButton("Reset");
		generateId = new JButton("Generate ID");
		userIdField = new JTextField(5);
		userIdField.setEditable(false);
		userIdLabel = new JLabel("User ID*:");
		userFirstNameField = new JTextField(TEXT_FIELD_LENGTH);
		firstNameLabel = new JLabel("First name*:");
		userMiddleNameField = new JTextField(TEXT_FIELD_LENGTH);
		middleNameLabel = new JLabel("Middle name:");
		userLastNameField = new JTextField(TEXT_FIELD_LENGTH);
		lastNameLabel = new JLabel("Last name*:");
		userPhoneField = new JTextField(TEXT_FIELD_LENGTH);
		phoneLabel = new JLabel("Phone:");
		userEmailField = new JTextField(TEXT_FIELD_LENGTH);
		emailLabel = new JLabel("Email:");
		userPhoneField.addKeyListener(new KeyAdapter() {
			@Override
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                if (character < '0' || character > '9' || userPhoneField.getText().length() > 10){
                    e.consume();
                }
            }
        });
		frame.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	frame.dispose();
		    }
		});
		JPanel panel1 = new JPanel();
		panel1.add(userIdLabel);
		panel1.add(Box.createVerticalStrut(16));
		panel1.add(firstNameLabel);
		panel1.add(Box.createVerticalStrut(4));
		panel1.add(middleNameLabel);
		panel1.add(Box.createVerticalStrut(4));
		panel1.add(lastNameLabel);
		panel1.add(Box.createVerticalStrut(4));
		panel1.add(phoneLabel);
		panel1.add(Box.createVerticalStrut(4));
		panel1.add(emailLabel);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		JPanel panel2 = new JPanel();
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout());
		idPanel.add(userIdField);
		idPanel.add(generateId);
		panel2.add(idPanel);
		panel2.add(userFirstNameField);
		panel2.add(userMiddleNameField);
		panel2.add(userLastNameField);
		panel2.add(userPhoneField);
		panel2.add(userEmailField);
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		JPanel dataPane = new JPanel();
		dataPane.add(panel1);
		dataPane.add(panel2);
		dataPane.setLayout(new FlowLayout());
		frame.add(dataPane);
		JPanel buttons = new JPanel();
		buttons.add(resetFields);
		buttons.add(addUser);
		buttons.setLayout(new FlowLayout());
		frame.add(buttons);
		addUser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String id;
				if(userIdField.getText().matches("[0-9]+"))id = userIdField.getText();
				else id = generateId();
				String firstName = userFirstNameField.getText();
				String middleName = userMiddleNameField.getText();
				String lastName = userLastNameField.getText();
				String phone = userPhoneField.getText();
				String email = userEmailField.getText();
				if(!firstName.equals("") && !lastName.equals("") && phone.length() >= 10){
					if(confirmDialog("Are you sure this information is correct:\n" + 
							"\nID: " + id +
							"\nFirst name: " + firstName + 
							"\nMiddle name: " + middleName +
							"\nLast name: " + lastName +
							"\nPhone: " + phone + 
							"\nEmail: " + email)){
						Client temp = new Client();
						temp.setUserId(id);
						temp.setFirstName(firstName);
						if(middleName.equals(""))temp.setMiddleName("N/A");
						else temp.setMiddleName(middleName);
						temp.setLastName(lastName);
						temp.setPhone(phone);
						temp.setEmail(email);
						clientList.addClient(temp);
						frame.dispose();
						windowClientList.refresh();
					}
				}else if(phone.length() < 10){
					JOptionPane.showMessageDialog(null, "Phone number must be minimum 10 digits long");
				}else{
					JOptionPane.showMessageDialog(null, "Missing important information, important information is marked by an asterisk (*)");
				}
			}
		});
		resetFields.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				userFirstNameField.setText("");
				userMiddleNameField.setText("");
				userLastNameField.setText("");
				userPhoneField.setText("");
				userEmailField.setText("");
			}
		});
		generateId.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				userIdField.setText(generateId());
			}
		});
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		//frame.pack();
		frame.setSize(400,300);
		frame.setTitle("Register new user");
		finalize();
	}
	
	public static String generateId(){
		Random g = new Random();
		String id = "";
		for(int i = 0; i < 9; i++){
			id += g.nextInt(9);
		}
		return id;
		//return g.nextInt(8999) + 1000;
	}
}