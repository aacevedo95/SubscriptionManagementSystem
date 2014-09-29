package com.fourbit.subscriptionmanagement.baseutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import com.fourbit.subscriptionmanagement.Logger;
import com.fourbit.subscriptionmanagement.SystemSettings;
import com.fourbit.subscriptionmanagement.baseutils.clientmanagement.ClientList;
import com.fourbit.subscriptionmanagement.windows.WindowClientList;


public class BaseUtility {

	static protected Logger logger;
	static protected ClientList clientList;
	static protected SystemSettings settings;
	static protected StatisticsTracker statTracker;
	static protected WindowClientList windowClientList;
	
	static boolean confirmDialog(String msg){
		int opt = 0;
		JOptionPane.showConfirmDialog(null, msg, "Confirm", opt);
		if(opt == JOptionPane.YES_OPTION)return true;
		return false;
	}

	private final String EXTENSION = ".cdp";

	private String fileName;

	public void setup(){
		logger = new Logger();
		settings = new SystemSettings();
		logger.logInfo("Setting up BaseUtil");
		clientList = new ClientList();
		statTracker = new StatisticsTracker();
		loadData();
		statTracker.modTotalRuns();
		logger.logInfo("Done setting up BaseUtil, starting program");
		windowClientList = 	new WindowClientList();
	}

	public void loadData(){
		clientList = (ClientList)load("clientlist");
		if(clientList == null){
			clientList = new ClientList();
			logger.logInfo("No previous client list found, creating a new one");
		}
		statTracker = (StatisticsTracker)load("statistics");
		if(statTracker == null){
			statTracker = new StatisticsTracker();
			logger.logInfo("No previous statistics data found, creating new file");
		}
		settings = (SystemSettings)load("settings");
		if(settings == null){
			settings = new SystemSettings();
			logger.logInfo("No previous settings found, creating defaults");
		}
	}

	public void close(){
		logger.logInfo("Starting to close program");
		save(statTracker, "statistics");
		save(clientList, "clientlist");
		save(settings, "settings");
		logger.logInfo("Finished saving data");
		logger.close();
		System.exit(0);
	}
	
	public void save(Object obj, String fileName){
		fileName+=EXTENSION;
		File dataFile = new File(fileName);
		logger.logInfo("Starting to save " + fileName);
		try {
			ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(dataFile));
			OOS.writeObject(obj);
			OOS.close();
			logger.logInfo("Done saving to " + fileName);
		} catch (FileNotFoundException e) {
			logger.logError("Could not find file " + fileName);
		} catch (IOException e) {
			logger.logError("Unidentified IO error occurred while saving");
		}
	}

	public Object load(String fileName){
		File dataFile = new File(fileName + EXTENSION);
		logger.logInfo("Starting to load " + dataFile);
		try {
			ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(dataFile));
			Object tmp = (Object)OIS.readObject();
			logger.logInfo("Done loading " + dataFile);
			OIS.close();
			return tmp;
		} catch (FileNotFoundException e) {
			logger.logError("Could not find file " + dataFile);
		} catch (IOException e) {
			logger.logError("Unidentified IO error occurred while loading");
		} catch (ClassNotFoundException e) {
			logger.logError("Could not find class \'Client.class\'");
		}
		return null;
	}

	public String getFileName(){
		return fileName;
	}

	public void setFileName(String name){
		fileName = name;
	}
	
	public boolean showConfirmDialog(String msg){
		int opt = JOptionPane.showConfirmDialog(null, msg);
		if(opt == JOptionPane.YES_OPTION)return true;
		return false;
	}
}