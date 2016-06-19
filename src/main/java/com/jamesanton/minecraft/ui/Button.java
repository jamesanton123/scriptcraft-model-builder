package com.jamesanton.minecraft.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Button extends JButton {
	private static final long serialVersionUID = 1L;

	public Button(String text, int width, int height, Color background, Color foreground){
		this.setText(text);
		Dimension btnSize = new Dimension(width, height);
		setSize(btnSize);		
		setMaximumSize(btnSize);
		setPreferredSize(btnSize);
		setMinimumSize(btnSize);
		setBackground(background);
		setForeground(foreground);
		setBorder(BorderFactory.createEmptyBorder());
	}
}
