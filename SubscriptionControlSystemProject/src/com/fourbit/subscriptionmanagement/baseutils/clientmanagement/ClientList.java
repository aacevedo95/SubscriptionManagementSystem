package com.fourbit.subscriptionmanagement.baseutils.clientmanagement;
import java.io.Serializable;

import com.fourbit.subscriptionmanagement.baseutils.BaseUtility;


@SuppressWarnings("serial")
public class ClientList extends BaseUtility implements Serializable{

	private Client[] list;
	private int clients;
	private final int BASE_SIZE = 128;
	private int sizeModifier;

	public ClientList(){
		setFileName("clientlist");
		list = new Client[BASE_SIZE];
		clients = 0;
		sizeModifier = 0;
	}
	
	private void verifyList(){
		logger.logInfo("Verifying clientlist size");
		if(clients == list.length){
			sizeModifier++;
			int newSize = BASE_SIZE*(int)(Math.pow(2, sizeModifier));
			logger.logInfo("Client list is full, rescaling to " + newSize + " slots");
			Client[] temp = new Client[newSize];
			for(int x = 0;x < list.length; x++){
				temp[x] = list[x];
			}
			list = temp;
		}
	}

	public void addClient(Client c){
		verifyList();
		logger.logInfo("Adding user " + c.getUserId() + " to ClientList");
		boolean found = false;
		for(int x = 0; x < list.length; x++){
			if(list[x] == null){
				list[x] = c;
				clients++;
				logger.logInfo("Added user " + c.getUserId() + " at position " + x);
				found = true;
				break;
			}
		}
		if(!found)logger.logSevere("The list has no space, could not add user " + c.getUserId());
	}

	public Client getClient(int x){
		if(x < 0 || x > list.length){
			return null;
		}else{
			return list[x];
		}
	}

	public Client searchForClient(String name){
		int index = searchForClientIndex(name);
		if(index == -1)return null;
		else return list[index];
	}

	public Client searchForClientById(String id){
		int index = searchForClientIndexById(id);
		if(index == -1)return null;
		else return list[index];
	}

	public int searchForClientIndex(String name){
		/*
		 * Write code for the event that a name appears multiple times
		 */
		for(int x = 0; x < list.length; x++){
			if(list[x].getFirstName().equalsIgnoreCase(name)){
				return x;
			}
		}
		return -1;
	}
	
	public int searchForClientIndexById(String id){
		for(int x = 0; x < list.length; x++){
			if(list[x].getUserId().equalsIgnoreCase(id)){
				return x;
			}
		}
		return -1;
	}
	
	public int searchForClientIndex(int id){
		logger.logInfo("Searching for user " + id);
		for(int x = 0; x < list.length; x++){
			if(list[x].getUserId().equals(id)){
				logger.logInfo("Found user " + id + " with name " + list[x].getFirstName() + " " + list[x].getMiddleName() + " " + list[x].getLastName());
				return x;
			}
		}
		logger.logInfo("Could not find user " + id);
		return -1;
	}
	
	public void deleteClient(String name){
		deleteClientByIndex(searchForClientIndex(name));
	}

	public void deleteClientByIndex(int index){
		logger.logInfo("Deleting user " + list[index].getUserId());
		/*
		 * Write code for the event that a name appears multiple times
		 */
		if(index >= 0 && index <= list.length){
			list[index] = null;
			clients--;
			for(int y = index; y < list.length; y++){
				if(y+1 != list.length)list[y] = list[y+1];
				else list[y] = null;
			}
		}
	}
	
	public void deleteClientById(String id){
		deleteClientByIndex(searchForClientIndex(id));
	}

	public int getNumberOfClients(){
		return clients;
	}

	public Client[] getCompressedList(){
		logger.logInfo("Generating compressed user list with size " + clients);
		Client[] temp = new Client[clients];
		for(int x = 0; x < clients; x++){
			temp[x] = list[x];
		}
		return temp;
	}
	
	public void sort(int col){
		/*
		 * DO CODE
		 */
	}
}
