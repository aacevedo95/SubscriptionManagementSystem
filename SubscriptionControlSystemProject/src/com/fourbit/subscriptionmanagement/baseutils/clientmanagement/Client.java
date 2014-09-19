package com.fourbit.subscriptionmanagement.baseutils.clientmanagement;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


@SuppressWarnings("serial")
public class Client implements Serializable{

	private final double PRICE_PER_MONTH = 15.0;

	private String userId; //5 digit number
	private String firstName;
	private String middleName;
	private String lastName;
	private String phone;
	private String email;
	private long joinTime;
	private double totalPayed;
	private double totalDue;
	/*
	 * Add remaining variables with getters & setters
	 */

	public Client(){
		joinTime = System.currentTimeMillis();
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

	public String getDateJoined(){
		return new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date(joinTime));
	}

	public long getDateJoinedMillis(){
		return joinTime;
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
}