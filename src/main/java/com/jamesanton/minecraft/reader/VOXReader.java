package com.jamesanton.minecraft.reader;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jamesanton.minecraft.ColorMapping;
import com.jamesanton.minecraft.storage.Cell;
import com.jamesanton.minecraft.storage.Column;
import com.jamesanton.minecraft.ui.ProgressBar;
import com.jamesanton.minecraft.utils.Commander;
import com.jamesanton.minecraft.utils.FileUtils;

/**
 * This class reads the vox file into a commander
 * @author James
 *
 */
public class VOXReader {
	private byte[] fileContents = null; 
	private int byteCounter = 0;
	private ColorMapping cm = null;
	public VOXReader(String filepath){
		fileContents = FileUtils.getBytes(filepath);
		cm = new ColorMapping();
	}
	
	public Commander getCommander() {
		int depth = 0;
		int height = 0;
		int width = 0;
		
		// read in the bytes
		int numMipMapLevels = 1;
		byte[] voxdata = null;
		
		// Read the depth, height, and width, and the vox data
		for (int i = 0; i < numMipMapLevels; i++) {
			// Get depth
			depth = getNextByte(4);
			System.out.println("depth = " + depth);

			// Get width
			width = getNextByte(4);
			System.out.println("width = " + width);

			// Get height
			height = getNextByte(4);
			System.out.println("height = " + height);
		
			
			// Dimensions should be positive
			height = Math.abs(height);
			width = Math.abs(width);
			depth = Math.abs(depth);
			
			int var3dSize = depth * width * height;
			int palleteSize = 768;			
						
			System.out.println("volume = " + var3dSize);
			
			// voxdata is a byte array. The value of the each vox will be a color reference, or -1 for empty space
			voxdata = Arrays.copyOfRange(fileContents, fileContents.length - palleteSize - var3dSize, fileContents.length - palleteSize);			
		}
					
		Commander comm = new Commander();
		comm.addCommand("");
		List<Column> columns = new ArrayList<Column>(0);
		int cellCounter = 0;
		// loop through each byte
		for (int i = 0; i < voxdata.length; i = i + height) {
			Column column = new Column();
			// for each of the cells in the column
			for (int h = 0; h < height; h++) {
				Cell cell = new Cell();
				cell.value = byteToInt(voxdata[cellCounter]);
				column.add(cell);
				cellCounter++;						
			}
						
			columns.add(column);			
			
		}
		ProgressBar.getInstance();
		ProgressBar.jProgressBar.setMaximum(columns.size());			
		ProgressBar.jProgressBar.setValue(0);
		int numColumnsInARow = width;
		int counter = 0;
		// For each column
		for (Column c : columns) {
			// Move the drone back up
			for (int i = 0; i < height; i++) {
				comm.up(1);
			}

			// Check if its a new row
			if (counter % numColumnsInARow == 0) {
				// Move drone forward and to the left
				comm.forward(1);
				comm.left(width - 1);
			} else {
				comm.right(1);
			}

			for (Cell cell : c.cells) {				
				if (cell.isBlock()) {			
					// add the block name to the commands. 
					// The block name is obtained by finding the closest image to the color at the index of the counter.
					comm.printBlock(cm.getClosestBlock(getColorAtIndex(Math.abs(cell.value))));	
				}
				
				comm.down(1);
			}
			ProgressBar.jProgressBar.setValue(counter);			
			counter = counter + 1;			
		}
		return comm;
	}

	public static int byteToInt(byte b) {
		return Integer.valueOf(b);
	}

//	private String getMinecraftTypeAtIndex(int index){
//		Color c = getColorAtIndex(index);
//		// find the most matching minecraft block
//		
//		
//		
//		
//		
//		
//	}
	
	private Color getColorAtIndex(int index) {
		// if its a negative, well subtract it from the end
		if (index == -1) {
			return null;
		} else {
			return getColorArray()[255 - index];
		}
	}

	public Color[] getColorArray() {
		List<Color> colorsList = new ArrayList<Color>(0);
		byte[] colors = getPallete();
		for (int i = 0; i < colors.length; i = i + 3) {
			int r = byteToInt(colors[i]);
			int g = byteToInt(colors[i + 1]);
			int b = byteToInt(colors[i + 2]);
			colorsList.add(new Color(r, g, b));
			// System.out.println("RGB = " + r + " " + g + " " + b);
		}
		return colorsList.toArray(new Color[colorsList.size()]);
	}

	public byte[] getPallete() {
		int numColors = 256;
		int numBytesPerColor = 3;
		int start = fileContents.length - (numColors * numBytesPerColor);
		int end = fileContents.length;
		return Arrays.copyOfRange(fileContents, start, end);
	}

	public int getNextByte(int numBytes) {
		String retValue = null;

		int valInt;
		String valStr = "";
		// for the number of bytes they requested
		for (int i = numBytes - 1; i >= 0; i--) {
			valInt = Integer.valueOf(fileContents[byteCounter + i]);
			valStr = String.valueOf(valInt);
			if (retValue != null) {
				retValue = retValue + valStr;
			} else {
				retValue = valStr;
			}
		}
		byteCounter = byteCounter + numBytes;

		int retInt = 0;
		try {
			retInt = Integer.valueOf(retValue.replaceFirst("^0+(?!$)", ""));
		} catch (Exception e) {
		}
		return retInt;
	}
}
