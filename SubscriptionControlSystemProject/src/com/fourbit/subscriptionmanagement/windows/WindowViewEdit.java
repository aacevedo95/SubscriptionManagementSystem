package com.fourbit.subscriptionmanagement.windows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fourbit.subscriptionmanagement.baseutils.Client;

public class WindowViewEdit extends Window{
	
	public WindowViewEdit(final Client c){
		super();
		frame.setTitle("Viewing/Editing user " + c.getUserId());
		JPanel userIdPanel = new JPanel();
		frame.add(userIdPanel);
		JLabel userIdLabel = new JLabel("ID: " + c.getUserId());
		userIdPanel.add(userIdLabel);
		JPanel informationPanel = new JPanel();
		frame.add(informationPanel);
		informationPanel.setLayout(new FlowLayout());
			JPanel labelPanel = new JPanel();
			informationPanel.add(labelPanel);
			labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
				final JLabel firstNameLabel = new JLabel("First name");
				labelPanel.add(firstNameLabel);
				labelPanel.add(Box.createVerticalStrut(4));
				final JLabel middleNameLabel = new JLabel("Middle name");
				labelPanel.add(middleNameLabel);
				labelPanel.add(Box.createVerticalStrut(4));
				final JLabel lastNameLabel = new JLabel("Last name");
				labelPanel.add(lastNameLabel);
				labelPanel.add(Box.createVerticalStrut(4));
				final JLabel phoneNumberLabel = new JLabel("Phone number");
				labelPanel.add(phoneNumberLabel);
				labelPanel.add(Box.createVerticalStrut(4));
				final JLabel emailAddressLabel = new JLabel("Email address");
				labelPanel.add(emailAddressLabel);
				labelPanel.add(Box.createVerticalStrut(4));
			JPanel fieldPanel = new JPanel();
			informationPanel.add(fieldPanel);
				final JTextField firstNameField = new JTextField(TEXT_FIELD_LENGTH);
				fieldPanel.add(firstNameField);
				firstNameField.setText(c.getFirstName());
				final JTextField middleNameField = new JTextField(TEXT_FIELD_LENGTH);
				fieldPanel.add(middleNameField);
				middleNameField.setText(c.getMiddleName());
				final JTextField lastNameField = new JTextField(TEXT_FIELD_LENGTH);
				fieldPanel.add(lastNameField);
				lastNameField.setText(c.getLastName());
				final JTextField phoneNumberField = new JTextField(TEXT_FIELD_LENGTH);
				fieldPanel.add(phoneNumberField);
				phoneNumberField.setText(c.getPhone());
				phoneNumberField.addKeyListener(new KeyAdapter() {
					@Override
		            public void keyTyped(KeyEvent e) {
		                char character = e.getKeyChar();
		                if (character < '0' || character > '9' || phoneNumberField.getText().length() > 10){
		                    e.consume();
		                }
		            }
		        });
				final JTextField emailAddressField = new JTextField(TEXT_FIELD_LENGTH);
				fieldPanel.add(emailAddressField);
				emailAddressField.setText(c.getEmail());
			fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		JPanel buttonPanel = new JPanel();
			JButton resetButton = new JButton("Reset");
			buttonPanel.add(resetButton);
			resetButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					firstNameField.setText(c.getFirstName());
					middleNameField.setText(c.getMiddleName());
					lastNameField.setText(c.getLastName());
					phoneNumberField.setText(c.getPhone());
					emailAddressField.setText(c.getEmail());
				}
			});
			JButton saveButton = new JButton("Save");
			buttonPanel.add(saveButton);
			saveButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if(showConfirmDialog("Are you sure you would like to save this information? You will not be able to undo...")){
						c.setFirstName(firstNameField.getText());
						c.setMiddleName(middleNameField.getText());
						c.setLastName(lastNameField.getText());
						c.setPhone(phoneNumberField.getText());
						c.setEmail(emailAddressField.getText());
						logger.logInfo("Editing user " + c.getUserId() + "'s information");
						frame.dispose();
						windowClientList.refresh();
					}
				}
			});
		frame.add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout());
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setSize(400,300);
		//frame.pack();
		frame.setVisible(true);
	}
}