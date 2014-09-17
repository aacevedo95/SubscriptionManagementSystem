package com.fourbit.subscriptionmanagement.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.fourbit.subscriptionmanagement.ClientGenerator;
import com.fourbit.subscriptionmanagement.baseutils.Client;

public class WindowDebug extends Window{

	public WindowDebug(){
		super();
		JButton generate = new JButton("Generate users");
		generate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String input;
				do{
					input = javax.swing.JOptionPane.showInputDialog(null, "How many clients would you like to generate?");
				}while(!isNumber(input));
				int quantity = Integer.parseInt(input);
				ClientGenerator gen = new ClientGenerator();
				Client[] list = gen.generateClientList(quantity);
				for(Client c : list){
					clientList.addClient(c);
				}
				frame.dispose();
				windowClientList.refresh();
			}
		});
		JButton deleteLogs = new JButton("Delete logs");
		deleteLogs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File[] files = new File("").listFiles();
				for(File t : files){
					if(!t.getName().equalsIgnoreCase(logger.getFileName()) && t.getName().contains(".log"))t.delete();
				}
			}
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 10;
		gbc.ipady = 10;
		buttonPanel.add(generate);
		gbc.gridy++;
		buttonPanel.add(deleteLogs);
		gbc.gridy++;
		frame.add(buttonPanel);
		frame.pack();
		finalize();
	}
	
	private boolean isNumber(String txt){
		for(char c : txt.toCharArray()){
			if(!Character.isDigit(c))return false;
		}
		return true;
	}
}