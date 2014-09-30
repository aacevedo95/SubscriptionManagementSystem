package com.fourbit.subscriptionmanagement.baseutils;
import java.io.Serializable;

@SuppressWarnings("serial")
public class StatisticsTracker extends BaseUtility implements Serializable{
	
	private int totalPeople;
	private int totalPayments;
	private int totalRuns;
	private double totalIncome;
	
	public StatisticsTracker(){
		totalPeople = 0;
		totalPayments = 0;
		totalRuns = 0;
		totalIncome = 0.0;
	}
	
	public int getTotalPeopleRegistered(){
		return totalPeople;
	}
	
	public void modTotalPeopleRegistered(int mod){
		totalPeople+=mod;
	}
	
	public void modTotalPeopleRegistered(){
		totalPeople++;
	}
	
	public int getTotalPayments(){
		return totalPayments;
	}
	
	public void modTotalPayments(int mod){
		totalPayments+=mod;
	}
	
	public void modTotalPayments(){
		totalPayments++;
	}
	
	public void modTotalRuns(){
		totalRuns++;
	}
	
	public void modTotalRuns(int mod){
		totalRuns += mod;
	}
	
	public int getTotalRuns(){
		return totalRuns;
	}
	
	public double getTotalIncome(){
		return totalIncome;
	}
	
	public void modTotalIncome(double mod){
		totalIncome+=mod;
	}
	
	public void modTotalIncome(){
		totalIncome++;
	}
}