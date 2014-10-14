package com.fourbit.subscriptionmanagement.windows;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
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

		/*
		 * 
		 * DECLARATIONS AND INITIALISATION
		 * 
		 */
		
		table = new JTable(new DefaultTableModel(clientList.getData(), columns){
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			};
		});
		final JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1280, 720));
		final JButton addButton = new JButton("Add");
		final JButton deleteButton = new JButton("Delete");
		final JButton viewButton = new JButton("View/Edit");
		final JButton settingsButton = new JButton("Settings");
		final JButton creditsButton = new JButton("Credits");
		final JButton debug = new JButton("DEBUG");
		final JButton exitButton = new JButton("Exit");
		final JButton searchButton = new JButton("Search");
		final JButton searchResetButton = new JButton("Reset");
		final JTextField fieldSearch = new JTextField(TEXT_FIELD_LENGTH*2);

		/*
		 * 
		 * LAYOUT
		 * 
		 */
		
		JPanel mainPanel = new JPanel();{
			mainPanel.setLayout(new FlowLayout());
			JPanel sidePanel = new JPanel();{
				sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.ipadx = 20;
				gbc.ipady = 20;
				JPanel primaryButtonPanel = new JPanel(new GridBagLayout());{
					primaryButtonPanel.add(addButton, gbc);
					gbc.gridy++;
					primaryButtonPanel.add(deleteButton, gbc);
					gbc.gridy++;
					primaryButtonPanel.add(viewButton, gbc);
					gbc.gridy++;
					primaryButtonPanel.add(settingsButton, gbc);
					gbc.gridy++;
					primaryButtonPanel.add(debug, gbc);
					primaryButtonPanel.add(creditsButton, gbc);
					gbc.gridy++;
					primaryButtonPanel.add(exitButton, gbc);
					gbc.gridy++;
				}
				sidePanel.add(primaryButtonPanel);
			}
			mainPanel.add(scrollPane);
			mainPanel.add(sidePanel);
		}
		JPanel searchPanel = new JPanel();{
			searchPanel.setLayout(new FlowLayout());
			searchPanel.add(fieldSearch);
			searchPanel.add(searchButton);
			searchPanel.add(searchResetButton);
		}

		/*
		 * 
		 * LISTENERS
		 * 
		 */

		/*table.getTableHeader().addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				JTableHeader header = table.getTableHeader();
				int selectedColumn = header.columnAtPoint(e.getPoint());
				sort(selectedColumn);
			}
		});*/
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new WindowRegisterUser();
			}
		});
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = (String)table.getModel().getValueAt(row, 0);
				int index = clientList.searchForClientIndexById(id);
				if(confirmDialog("Are you sure you would like to unregister and delete all data related to user " + id + "?")){
					clientList.deleteClientByIndex(index);
					refresh();
				}
				setSelectedRow(row);
			}
		});
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
		creditsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new WindowCredits();
			}
		});
		settingsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new WindowSettings();
			}
		});
		debug.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowDebug();
			}
		});
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
				System.exit(0);
			}
		});
		searchButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String query = fieldSearch.getText();
				logger.logInfo("Performing table query - '" + query + '\'');
				if(!query.equals(""))loadModel(clientList.search(query));
				else refresh();
			}
		});
		searchResetButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				fieldSearch.setText("");
				searchButton.doClick();
			}
		});
		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				close();
			}
		});
		
		/*
		 * 
		 * FINALIZATION
		 * 
		 */

		frame.add(mainPanel);
		frame.add(searchPanel);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setTitle("Client list");
		frame.pack();
		finalize();
	}

	public void refresh(){
		logger.logInfo("Refreshing table model");
		((DefaultTableModel)table.getModel()).setDataVector(clientList.getData(), columns);
	}

	public void loadModel(Client[] l){
		logger.logInfo("Loading custom table model");
		((DefaultTableModel)table.getModel()).setDataVector(clientList.getData(l), columns);
	}

	private void setSelectedRow(int row){
		table.setRowSelectionInterval(row, row);
	}
}