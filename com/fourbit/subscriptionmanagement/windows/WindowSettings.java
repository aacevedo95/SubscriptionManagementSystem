package com.fourbit.subscriptionmanagement.windows;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WindowSettings extends Window{
	
	JCheckBox checkboxConsole;
	
	public WindowSettings(){
		super();
		frame.setTitle("Settings");
		JPanel labelPanel = new JPanel();
		JLabel consoleLabel = new JLabel("Show console");
		labelPanel.add(consoleLabel);
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
		JPanel settingPanel = new JPanel();
		checkboxConsole = new JCheckBox("test");
		settingPanel.add(checkboxConsole);
		settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.Y_AXIS));
		JPanel overallSettings = new JPanel();
		overallSettings.add(labelPanel);
		overallSettings.add(settingPanel);
		overallSettings.setLayout(new FlowLayout());
		frame.add(overallSettings);
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
		frame.setVisible(true);
	}
}