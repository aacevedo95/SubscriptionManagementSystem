package com.fourbit.subscriptionmanagement;

import java.io.Serializable;

import com.fourbit.subscriptionmanagement.baseutils.BaseUtility;


@SuppressWarnings("serial")
public class SystemSettings extends BaseUtility implements Serializable{
	
	private boolean showConsole;
	
	public SystemSettings(){
		showConsole = false;
	}
	
	public boolean consoleIsVisible(){
		return showConsole;
	}
	
	public void setConsoleVisible(boolean visibility){
		showConsole = visibility;
		logger.setConsoleVisible(visibility);
	}
}