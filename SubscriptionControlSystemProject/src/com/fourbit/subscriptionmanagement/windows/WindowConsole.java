package com.fourbit.subscriptionmanagement.windows;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WindowConsole extends Window {
	
	private JTextArea area;

	public WindowConsole() {
		super();
		frame.setTitle("Console");
		area = new JTextArea();
		JScrollPane pane = new JScrollPane(area);
		pane.setPreferredSize(new Dimension(500,300));
		frame.add(pane);
		frame.pack();
		center();
	}
	
	public void setVisible(boolean b){
		frame.setVisible(b);
	}
	
	public JTextArea getArea(){
		return area;
	}
}
