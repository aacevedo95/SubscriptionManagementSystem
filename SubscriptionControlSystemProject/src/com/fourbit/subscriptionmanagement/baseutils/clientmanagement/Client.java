package com.fourbit.subscriptionmanagement.baseutils.clientmanagement;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Client implements Serializable{

	private final double PRICE_PER_MONTH = 15.0;

	private String userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String phone;
	private String email;
	private double totalPayed;
	private double totalDue;

	public Client(){
		totalPayed = 0;
		totalDue = PRICE_PER_MONTH;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserId(String id){
		userId = id;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setFirstName(String n){
		firstName = n;
	}

	public String getMiddleName(){
		return middleName;
	}

	public void setMiddleName(String mn){
		middleName = mn;
	}

	public String getLastName(){
		return lastName;
	}

	public void setLastName(String ln){
		lastName = ln;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String p){
		phone = p;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String e){
		email = e;
	}

	public double getTotalPayed(){
		return totalPayed;
	}

	public double getTotalDue(){
		return totalDue;
	}

	public void addPayment(){
		totalPayed += PRICE_PER_MONTH;
		totalDue -= PRICE_PER_MONTH;
	}

	public void addDebt(){
		totalDue += PRICE_PER_MONTH;
	}
	
	public int compareTo(Client c, int col){
		switch(col){
			case 0: return getUserId().compareTo(c.getUserId());
			case 1: return getFirstName().compareTo(c.getFirstName());
			case 2: return getMiddleName().compareTo(c.getMiddleName());
			case 3: return getLastName().compareTo(c.getLastName());
			case 4: return getPhone().compareTo(c.getPhone());
			case 5: return getEmail().compareTo(c.getEmail());
		}
		return -2;
	}
	
	public boolean isLike(String txt){
		if(userId.toLowerCase().contains(txt.toLowerCase()))return true;
		if(firstName.toLowerCase().contains(txt.toLowerCase()))return true;
		if(middleName.toLowerCase().contains(txt.toLowerCase()))return true;
		if(lastName.toLowerCase().contains(txt.toLowerCase()))return true;
		if(email.toLowerCase().contains(txt.toLowerCase()))return true;
		if(phone.toLowerCase().contains(txt.toLowerCase()))return true;
		return false;
	}
}