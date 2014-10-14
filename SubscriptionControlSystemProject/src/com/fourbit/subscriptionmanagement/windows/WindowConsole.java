package com.fourbit.subscriptionmanagement.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WindowConsole extends Window {
	
	private JTextArea area;

	public WindowConsole() {
		super();
		frame.setTitle("Console log");
		area = new JTextArea();
		area.setEditable(false);
		area.setBackground(Color.black);
		area.setForeground(Color.white);
		JScrollPane pane = new JScrollPane(area);
		pane.setPreferredSize(new Dimension(750,475));
		frame.add(pane);
		frame.pack();
		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				settings.setConsoleVisible(false);
			}
		});
		center();
	}
	
	public void setVisible(boolean b){
		frame.setVisible(b);
	}
	
	public JTextArea getArea(){
		return area;
	}
}