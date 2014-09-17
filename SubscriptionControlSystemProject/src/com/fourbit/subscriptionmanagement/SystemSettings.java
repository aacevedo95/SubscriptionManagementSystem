package com.fourbit.subscriptionmanagement;

import java.io.Serializable;


@SuppressWarnings("serial")
public class SystemSettings implements Serializable{
	
	private boolean showConsole;
	
	public SystemSettings(){
		showConsole = false;
	}
	
	public boolean consoleIsVisible(){
		return showConsole;
	}
	
	public void setConsoleVisible(boolean visibility){
		showConsole = visibility;
	}
}