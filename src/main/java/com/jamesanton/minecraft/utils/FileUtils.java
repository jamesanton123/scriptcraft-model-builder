package com.jamesanton.minecraft.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class FileUtils {
	public static void writeListToFile(String path, List<String> lines) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(path, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (String line : lines) {
			if (line.startsWith(".")) {
				line = line.substring(1, line.length());
			}
			writer.println(line);
		}
		writer.close();
	}

	public static byte[] getBytes(String filepath){
		byte[] fileContents = null;
		File f = new File(filepath);
		InputStream is = null;
		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}		
		try {
			fileContents = IOUtils.toByteArray(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContents;
	}
}
