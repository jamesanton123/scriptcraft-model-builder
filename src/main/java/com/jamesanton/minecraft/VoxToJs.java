package com.jamesanton.minecraft;

import com.jamesanton.minecraft.reader.VOXReader;
import com.jamesanton.minecraft.ui.HomeFrame;
import com.jamesanton.minecraft.utils.Commander;

public class VoxToJs extends Thread{
	public String filePath = null;
	
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
            	new HomeFrame();
            }
        });
	}
	
	public void init(String filePath){		
		this.filePath = filePath;		
	}
	
	public void run(){
		VOXReader voxReader = new VOXReader(filePath);
		// Get the commander from the voxReader (The commander stores the javascript commands
		Commander commander = voxReader.getCommander();
		commander.printCommandsToJs(filePath);		
	}
	
}
