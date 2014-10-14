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
		logger.logInfo("Searching for user " + name);
		for(int x = 0; x < list.length; x++){
			if(list[x].getFirstName().equalsIgnoreCase(name)){
				logger.logInfo("Found user " + name);
				return x;
			}
		}
		logger.logInfo("Could not find user " + name);
		return -1;
	}
	
	public int searchForClientIndexById(String id){
		logger.logInfo("Searching for user " + id);
		for(int x = 0; x < list.length; x++){
			if(list[x].getUserId().equals(id)){
				logger.logInfo("Found user " + id);
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
	
	public Object[][] getData(){
		return getData(getCompressedList());
	}
	
	public Object[][] getData(Client[] l){
		if(l == null || l.length == 0)return null;
		Object[][] tmp = new Object[l.length][6];
		for(int x = 0; x < l.length; x++){
			tmp[x][0] = l[x].getUserId();
			tmp[x][1] = l[x].getFirstName();
			tmp[x][2] = l[x].getMiddleName();
			tmp[x][3] = l[x].getLastName();
			tmp[x][4] = l[x].getPhone();
			tmp[x][5] = l[x].getEmail();
		}
		return tmp;
	}
	
	public Client[] search(String q){
		Client[] t = new Client[8];
		int n = 0;
		for(int x = 0; x < clients; x++){
			if(list[x].isLike(q)){
				if(n==t.length){
					Client[] tmp = new Client[t.length*2];
					for(int y = 0; y < n; y++)tmp[y]=t[y];
					t = tmp;
				}
				t[n]=list[x];
				n++;
			}
		}
		if(n==0)return null;
		Client[] temp = new Client[n];
		for(int x = 0; x < n; x++)temp[x]=t[x];
		return temp;
	}
}