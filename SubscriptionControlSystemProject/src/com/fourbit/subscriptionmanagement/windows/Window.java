package com.fourbit.subscriptionmanagement.windows;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.fourbit.subscriptionmanagement.baseutils.BaseUtility;

public class Window extends BaseUtility{

	protected JFrame frame;
	protected final int TEXT_FIELD_LENGTH = 20;
	protected static Image icon;

	public Window(){
		//setIcon(loadIcon("databaseicon.png"));
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}

	public JFrame getFrame(){
		return frame;
	}
	
	protected void center(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((screen.getWidth() - frame.getWidth()) /2);
		int y = (int) ((screen.getHeight() - frame.getHeight()) /2);
		frame.setLocation(x, y);
	}
	
	protected void finalize(){
		frame.setVisible(true);
		center();
	}
	
	protected void setIcon(Image i){
		frame.setIconImage(i);
	}
	
	protected Image loadIcon(String fileName){
		File file = new File(fileName);
		if(file.exists()){
			try {
				return ImageIO.read(file);
			} catch (IOException e) {
				logger.logError("Could not read from " + file.getName());
			}
		}else logger.logError("Tried to access " + file.getName() + " which doesn't exist");
		return null;
	}
}