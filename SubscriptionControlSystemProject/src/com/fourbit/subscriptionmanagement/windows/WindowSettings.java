package com.fourbit.subscriptionmanagement.windows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class WindowSettings extends Window{
	
	JCheckBox checkboxConsole;
	
	public WindowSettings(){
		super();
		frame.setTitle("Settings");
		JPanel settingsPanel = new JPanel();
		checkboxConsole = new JCheckBox("Show console");
		checkboxConsole.setSelected(settings.consoleIsVisible());
		settingsPanel.add(checkboxConsole);
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
		frame.add(settingsPanel);
		JPanel buttonPanel = new JPanel();
		JButton reset = new JButton("Reset");
		JButton done = new JButton("Done");
		buttonPanel.add(reset);
		buttonPanel.add(done);
		buttonPanel.setLayout(new FlowLayout());
		frame.add(buttonPanel);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				checkboxConsole.setSelected(false);
				settings.setConsoleVisible(false);
			}
		});
		done.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				settings.setConsoleVisible(checkboxConsole.isSelected());
				frame.dispose();
			}
		});
		frame.pack();
		finalize();
	}
}