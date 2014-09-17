package com.fourbit.subscriptionmanagement.windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
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
				}while(!isNumber(input) || input == null);
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
				File[] files = new File(".").listFiles();
				if(files != null)for(File t : files){
					if(!t.getName().equalsIgnoreCase(logger.getFileName()) && t.getName().contains(".log")){
						logger.logInfo("Deleting log file " + t.getName());
						t.delete();
					}
				}
			}
		});
		frame.add(generate);
		frame.add(deleteLogs);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
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