package com.jamesanton.minecraft.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HomeFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final Color LIGHT_TAN = new Color(185,187,163);
	public static final Color SOME_OTHER_COLOR = new Color(89,102,78);
	public JPanel mainPanel = null;
	
	public HomeFrame() {
		// Create and set up the window.
		JFrame frame = new JFrame("VoxToJs");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new UploadFilePanel();
		
		frame.add(mainPanel);
		frame.setBackground(SOME_OTHER_COLOR);

		Dimension size = new Dimension(200,90);
		frame.setSize(size);
		frame.setResizable(false);

		frame.setLocationRelativeTo(null);
		frame.setMaximumSize(size);
		frame.setPreferredSize(size);
		frame.setMinimumSize(size);
				
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
