package com.fourbit.subscriptionmanagement.windows;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class WindowCredits extends Window{
	
	public WindowCredits(){
		super();
		logger.logInfo("Creating new Credits window");
		frame.setTitle("Credits");
		JPanel info = new JPanel();
		JPanel header = new JPanel();
		header.add(new JLabel("4-Bit © 2014"));
		frame.add(header);
		frame.add(Box.createVerticalStrut(20));
		
		//Handles the titles panel
		JPanel titles = new JPanel();
		String[] titleList = new String("C.E.O,Lead programmer,Lead documenter,Lead analyst").split(",");
		for(int x = 0; x < titleList.length; x++){
			JLabel temp = new JLabel(titleList[x]);
			titles.add(temp, BorderLayout.WEST);
		}
		titles.setLayout(new BoxLayout(titles, BoxLayout.Y_AXIS));
		info.add(titles);
		
		//Handles the middle lines
		JPanel linePanel = new JPanel();
		for(int x = 0; x < titleList.length; x++){
			linePanel.add(new JLabel("  -  "));
		}
		linePanel.setLayout(new BoxLayout(linePanel, BoxLayout.Y_AXIS));
		info.add(linePanel);
		
		//Handles the names panel
		JPanel names = new JPanel();
		String[] nameList = new String("Raul III Roque,Juan J. Alvarez,Josue M. Molina,Odlanier Montañez").split(",");
		for(int x = 0; x < nameList.length; x++){
			JLabel temp = new JLabel(nameList[x]);
			names.add(temp, BorderLayout.EAST);
		}
		names.setLayout(new BoxLayout(names, BoxLayout.Y_AXIS));
		info.add(names);
		frame.add(info);
		frame.add(Box.createVerticalStrut(20));
		JPanel version = new JPanel();
		version.add(new JLabel("Version - " + VERSION));
		frame.add(version);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
}