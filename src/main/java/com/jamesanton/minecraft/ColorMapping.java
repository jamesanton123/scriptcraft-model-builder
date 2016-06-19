package com.jamesanton.minecraft;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.configuration.ConfigurationUtils;

public class ColorMapping {
	public File[] textureFiles = null;
	public Color[] averageColorsOfTextures = null;
	
	public ColorMapping(){
		textureFiles = getTextures();
		getAverageColorsOfTextures();
	}	
	
	public String getClosestBlock(Color in){
		int colorIndex = -1;
		double closestDistanceSoFar = 1000;
		for(int i = 0; i < averageColorsOfTextures.length; i++){
			double colorDistance = getColorDistance(in, averageColorsOfTextures[i]);
			if(colorDistance < closestDistanceSoFar){
				closestDistanceSoFar = colorDistance;
				colorIndex = i;
			}
		}		
		String fileName = textureFiles[colorIndex].getName().replace(".png", "");	
		return fileName;		
	}
	
	
	public void getAverageColorsOfTextures(){		
		List<Color> colors = new ArrayList<Color>(0);
		// For each image
		for (File f : textureFiles) {
			int redColors = 0;
		    int greenColors = 0;
		    int blueColors = 0;
		    int pixelCount = 0;			
			BufferedImage img = null;
			try {
				img = ImageIO.read(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			int width = img.getWidth();
			int height = img.getHeight();
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					Color c = new Color(img.getRGB(x, y));
					redColors += c.getRed(); 
					greenColors += c.getGreen();
					blueColors += c.getBlue();
					pixelCount++;
				}
			}
			 // calculate average of bitmap r,g,b values
		    int red = (redColors/pixelCount);
		    int green = (greenColors/pixelCount);
		    int blue = (blueColors/pixelCount);
			colors.add(new Color(red,green,blue));			    
		}		
		averageColorsOfTextures = colors.toArray(new Color[colors.size()]);
	}
	
	public File[] getTextures(){
		List<File> files = new ArrayList<File>(0);
		
		File cobblestone = new File(ConfigurationUtils.locate("cobblestone.png").getPath());
		File dirt = new File(ConfigurationUtils.locate("dirt.png").getPath());
		File glowstone = new File(ConfigurationUtils.locate("glowstone.png").getPath());
		File gold = new File(ConfigurationUtils.locate("gold.png").getPath());
		File netherrack = new File(ConfigurationUtils.locate("netherrack.png").getPath());
		File obsidian = new File(ConfigurationUtils.locate("obsidian.png").getPath());
		
		files.add(cobblestone);
		files.add(dirt);
		files.add(glowstone);
		files.add(gold);
		files.add(netherrack);
		files.add(obsidian);		
		return files.toArray(new File[files.size()]);		
	}
	
	public static double getColorDistance(Color c1, Color c2) {
		double rmean = (c1.getRed() + c2.getRed()) / 2;
		int r = c1.getRed() - c2.getRed();
		int g = c1.getGreen() - c2.getGreen();
		int b = c1.getBlue() - c2.getBlue();
		double weightR = 2 + rmean / 256;
		double weightG = 4.0;
		double weightB = 2 + (255 - rmean) / 256;
		double currentDist = Math.sqrt(weightR * r * r + weightG * g * g + weightB * b * b);
		return currentDist;
	}
}
