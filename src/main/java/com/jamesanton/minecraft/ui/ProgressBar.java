package com.jamesanton.minecraft.ui;

import javax.swing.JProgressBar;

/**
 * This is a singleton class to easily get access to a progress bar from other classes.
 * @author James
 *
 */
public class ProgressBar{
	public static JProgressBar jProgressBar = new JProgressBar();	
	private static ProgressBar instance = null;
	
	protected ProgressBar() {
		// Exists only to defeat instantiation.
	}

	public static ProgressBar getInstance() {
		if (instance == null) {
			instance = new ProgressBar();
			ProgressBar.jProgressBar.setStringPainted(true);
		}
		return instance;
	}
	
}
