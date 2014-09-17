package com.fourbit.subscriptionmanagement.windows;
import javax.swing.JFrame;

import com.fourbit.subscriptionmanagement.baseutils.BaseUtility;

public class Window extends BaseUtility{

	protected JFrame frame;
	protected final int TEXT_FIELD_LENGTH = 20;

	public Window(){
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
	}

	public JFrame getFrame(){
		return frame;
	}
}