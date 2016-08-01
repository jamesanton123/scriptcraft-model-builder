package com.jamesanton.minecraft.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileFilter;

import com.jamesanton.minecraft.VoxToJs;

class UploadFilePanel extends JPanel{	
	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar = null;
	
	public UploadFilePanel(){
		JButton openBtn = new Button("Open", 120,30, HomeFrame.LIGHT_TAN, HomeFrame.SOME_OTHER_COLOR);		
		   
		openBtn.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	            JFileChooser openFile = new JFileChooser();
	            openFile.setFileFilter(new FileFilter() {
	                public boolean accept(File f) {
	                  return f.getName().toLowerCase().endsWith(".vox") || f.getName().toLowerCase().endsWith(".VOX") || f.isDirectory();
	                }
	                public String getDescription() {
	                  return "Vox File";
	                }					
	            });
	            
	            int retval = openFile.showOpenDialog(null);
	            if (retval == JFileChooser.APPROVE_OPTION) {
	                File myFile = openFile.getSelectedFile();
	                VoxToJs v = new VoxToJs();
	                v.init(myFile.getAbsolutePath());
	                v.start();
	            }   
	        }
	    });
				
		new ProgressBar();
		progressBar = ProgressBar.jProgressBar;		
		this.add(openBtn);		
		this.add(progressBar);
	}
}
