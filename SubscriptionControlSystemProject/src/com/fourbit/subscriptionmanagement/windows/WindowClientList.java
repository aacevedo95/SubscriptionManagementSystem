package com.fourbit.subscriptionmanagement.windows;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.fourbit.subscriptionmanagement.baseutils.clientmanagement.Client;

public class WindowClientList extends Window{

	private JTable table;
	private final String[] columns = new String("ID,First name,Middle name,Last name, Phone, Email").split(",");

	@SuppressWarnings("serial")
	public WindowClientList(){
		super();
		frame.setTitle("Client list");
		table = new JTable(new DefaultTableModel(getData(clientList.getCompressedList()), columns){
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			};
		});
		table.getTableHeader().addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				JTableHeader header = table.getTableHeader();
				int selectedColumn = header.columnAtPoint(e.getPoint());
				sort(selectedColumn);
			}
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1280, 720));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		JButton addButton = new JButton("Add");
		JButton removeButton = new JButton("Delete");
		JButton viewButton = new JButton("View/Edit");
		JButton settingsButton = new JButton("Settings");
		JButton creditsButton = new JButton("Credits");
		JButton exitButton = new JButton("Exit");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 10;
		gbc.ipady = 10;
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new WindowRegisterUser();
			}
		});
		buttonPanel.add(addButton, gbc);
		gbc.gridy++;
		removeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = (String)table.getModel().getValueAt(row, 0);
				Client temp = clientList.searchForClientById(id);
				if(showConfirmDialog("Are you sure you would like to unregister and delete all data related to user " + id + ", also known as " + temp.getFirstName() + " " + temp.getLastName())){
					clientList.deleteClientById(id);
					refresh();
				}
				setSelectedRow(row);
			}
		});
		buttonPanel.add(removeButton, gbc);
		gbc.gridy++;
		viewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = (String)table.getModel().getValueAt(row, 0);
				Client temp = clientList.searchForClientById(id);
				new WindowViewEdit(temp);
			}
		});
		buttonPanel.add(viewButton, gbc);
		gbc.gridy++;
		creditsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new WindowCredits();
			}
		});
		buttonPanel.add(settingsButton, gbc);
		gbc.gridy++;
		settingsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new WindowSettings();
			}
		});
		JButton debug = new JButton("DEBUG");
		buttonPanel.add(debug, gbc);
		debug.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowDebug();
			}
		});

		buttonPanel.add(creditsButton, gbc);
		gbc.gridy++;
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
				System.exit(0);
			}
		});
		buttonPanel.add(exitButton, gbc);
		gbc.gridy++;
		frame.setLayout(new FlowLayout());
		frame.add(scrollPane);
		frame.add(buttonPanel);
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				close();
			}
		});
		frame.pack();
		finalize();
	}

	public void refresh(){
		logger.logInfo("Refreshing table model");
		table.setModel(new DefaultTableModel(getData(clientList.getCompressedList()), columns));
	}

	private Object[][] getData(Client[] ls){
		if(ls != null && ls.length != 0){
			Object[][] temp = new Object [ls.length][6];
			for(int x = 0; x < ls.length; x++){
				temp[x][0] = ls[x].getUserId();
				temp[x][1] = ls[x].getFirstName();
				temp[x][2] = ls[x].getMiddleName();
				temp[x][3] = ls[x].getLastName();
				temp[x][4] = ls[x].getPhone();
				temp[x][5] = ls[x].getEmail();
			}
			return temp;
		}
		return null;
	}

	private void setSelectedRow(int row){
		table.setRowSelectionInterval(row, row);
	}
	
	public void sort(int col){
		clientList.sort(col);
		refresh();
	}
}