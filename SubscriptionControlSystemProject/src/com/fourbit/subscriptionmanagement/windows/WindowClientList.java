package com.fourbit.subscriptionmanagement.windows;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.fourbit.subscriptionmanagement.baseutils.Client;

public class WindowClientList extends Window{

	private JTable table;

	public WindowClientList(Client[] list){
		super();
		logger.logInfo("Creating new ClientList window");
		frame.setTitle("Client list");
		table = new JTable(getData(list), new String("ID,First name,Middle name,Last name, Phone, Email").split(","));
		JScrollPane scrollPane = new JScrollPane(table);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		frame.setLayout(new FlowLayout());
		frame.add(scrollPane);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		JButton addButton = new JButton("Add");
		JButton removeButton = new JButton("Remove");
		JButton viewButton = new JButton("View");
		JButton settingsButton = new JButton("Settings");
		JButton creditsButton = new JButton("Credits");
		JButton exitButton = new JButton("Exit");
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new WindowRegisterUser();
			}
		});
		buttonPanel.add(addButton);
		removeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int id = (int)table.getModel().getValueAt(row, 0);
				clientList.deleteClientById(id);
				frame.dispose();
				new WindowClientList(clientList.getCompressedList());
			}
		});
		buttonPanel.add(removeButton);
		viewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int id = (int)table.getModel().getValueAt(row, 0);
				Client temp = clientList.searchForClient(id);
				JOptionPane.showMessageDialog(null, "Name: " + temp.getFirstName() + " " + temp.getMiddleName() + " " + temp.getLastName() +
						"\nPhone number: " + temp.getPhone() +
						"\nEmail address: " + temp.getEmail()
						);
			}
		});
		buttonPanel.add(viewButton);
		creditsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new WindowCredits();
			}
		});
		buttonPanel.add(settingsButton);
		settingsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new WindowSettings();
			}
		});
		buttonPanel.add(creditsButton);
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
				System.exit(0);
			}
		});
		buttonPanel.add(exitButton);
		frame.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	close();
		    }
		});
		frame.add(buttonPanel);
		frame.pack();
		frame.setVisible(true);
	}

	private Object[][] getData(Client[] ls){
		if(ls != null){
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
}