package com.fourbit.subscriptionmanagement.windows;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.GridBagConstraints;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import com.fourbit.subscriptionmanagement.baseutils.clientmanagement.Client;

public class WindowClientList extends Window{

	private JTable table;
	private static final String[] columns = new String("ID,First name,Middle name,Last name, Phone, Email").split(",");

	@SuppressWarnings("serial")
	public WindowClientList(){
		super();

		table = new JTable(new DefaultTableModel(clientList.getData(), columns){
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			};
		});
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1280, 720));
		
		/*table.getTableHeader().addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				JTableHeader header = table.getTableHeader();
				int selectedColumn = header.columnAtPoint(e.getPoint());
				sort(selectedColumn);
			}
		});*/
		JButton addButton = new JButton("Add");
		JButton deleteButton = new JButton("Delete");
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
		
		JPanel buttonPanel = new JPanel();{
			buttonPanel.setLayout(new GridBagLayout());
			addButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new WindowRegisterUser();
				}
			});
			buttonPanel.add(addButton, gbc);
			gbc.gridy++;
			deleteButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					String id = (String)table.getModel().getValueAt(row, 0);
					int index = clientList.searchForClientIndexById(id);
					if(showConfirmDialog("Are you sure you would like to unregister and delete all data related to user " + id + "?")){
						clientList.deleteClientByIndex(index);
						refresh();
					}
					setSelectedRow(row);
				}
			});
			buttonPanel.add(deleteButton, gbc);
			gbc.gridy++;
			viewButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = table.getSelectedRow();
					if(row!=-1){
						String id = (String)table.getModel().getValueAt(row, 0);
						Client temp = clientList.searchForClientById(id);
						new WindowViewEdit(temp);
					}
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
		}

		gbc.gridy++;
		frame.setLayout(new FlowLayout());
		frame.add(scrollPane);
		frame.add(buttonPanel);
		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				close();
			}
		});
		frame.setTitle("Client list");
		frame.pack();
		finalize();
	}

	public void refresh(){
		logger.logInfo("Refreshing table model");
		((DefaultTableModel)table.getModel()).setDataVector(clientList.getData(), columns);
	}

	private void setSelectedRow(int row){
		table.setRowSelectionInterval(row, row);
	}
}