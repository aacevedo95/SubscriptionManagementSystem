package com.fourbit.subscriptionmanagement;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fourbit.subscriptionmanagement.baseutils.BaseUtility;

public class Logger extends BaseUtility{

	private File log;
	private PrintWriter printWriter;
	
	public Logger(){
		if(!new File("logs").exists())new File("logs").mkdir();
		log = new File("logs/log " + getDateAndTimeLogName() + ".log");
		try {
			log.createNewFile();
		} catch (IOException e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Contact administrator!\n" + e.getMessage() + "\n" + e.getLocalizedMessage());
		}
		try {
			printWriter = new PrintWriter(log);
		} catch (IOException e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Contact administrator!\n" + e.getMessage() + "\n" + e.getLocalizedMessage() + e.getStackTrace());
		}
	}
	
	public void close(){
		printWriter.close();
	}
	
	public void logError(String msg){
		writeToLog("[ERROR] " + msg);
	}
	
	public void logInfo(String msg){
		writeToLog("[INFO] " + msg);
	}
	
	public void logSevere(String msg){
		writeToLog("[SEVERE] " + msg);
	}
	
	private void writeToLog(String msg){
		printWriter.println(getDateAndTime() + " " + msg);
		if(settings.consoleIsVisible())
			System.out.println(getDateAndTime() + " " + msg);
	}
	
	public String getDateAndTime(){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public String getDateAndTimeLogName(){
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public String getDate(){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public String getTime(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	@Override
	public String getFileName(){
		return log.getName();
	}
}