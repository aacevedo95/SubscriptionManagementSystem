package com.fourbit.subscriptionmanagement;

import java.util.Random;

import com.fourbit.subscriptionmanagement.baseutils.BaseUtility;
import com.fourbit.subscriptionmanagement.baseutils.Client;
import com.fourbit.subscriptionmanagement.windows.WindowRegisterUser;

public class ClientGenerator extends BaseUtility{
	
	public ClientGenerator(){
	}
	
	public Client[] generateClientList(int size){
		logger.logInfo("Generating list with size " + size);
		Client[] list = new Client[size];
		for(int x = 0; x < size; x++){
			list[x] = generateClient();
		}
		return list;
	}
	
	public Client generateClient(){
		String id = WindowRegisterUser.generateId();
		logger.logInfo("Generating data for new user " + id);
		Client c = new Client();
		c.setUserId(id);
		String name = generateString();
		c.setFirstName(name);
		String middle = generateString();
		c.setMiddleName(middle);
		String last = generateString();
		c.setLastName(last);
		String phone = generatePhone();
		c.setPhone(phone);
		c.setEmail("DUMMY_CLIENT@4.bit");
		return c;
		
	}
	
	private String generateString(){
		char[] letter = new String("abcdefghijklmnopqrstuvwxyz").toCharArray();
		Random g = new Random();
		String str = "";
		int length = g.nextInt(8)+4;
		for(int x = 0; x < length; x++){
			str += letter[g.nextInt(letter.length-1)];
		}
		return str;
	}
	
	private String generatePhone(){
		Random g = new Random();
		int ln = g.nextInt(1)+10;
		String str = "";
		if(ln == 11)str+=1;
		for(int x = 0; x < 10; x++){
			str += g.nextInt(9);
		}
		return str;
	}
}