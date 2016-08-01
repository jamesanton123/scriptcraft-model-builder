package com.jamesanton.minecraft.js;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.jamesanton.minecraft.ui.ProgressBar;

/**
 * This class is to add commands to the command queue. It also builds the lines
 * that will be written to the javascript file
 */
public class JsBuilder {
	private List<String> commands = null;

	public JsBuilder() {
		commands = new ArrayList<String>(0);
	}

	public void addCommand(String command) {
		commands.add(command);
	}

	public void forward(int i) {
		String lastCommand = commands.get(commands.size() - 1);
		if (lastCommand.contains("fwd")) {
			int oldI = Integer.parseInt(lastCommand.replaceAll("[\\D]", ""));
			i = oldI + i;
			commands.set(commands.size() - 1, "fwd(" + i + ")");
		} else {
			commands.add("fwd(" + i + ")");
		}
	}

	

	public void left(int i) {
		String lastCommand = commands.get(commands.size() - 1);
		if (lastCommand.contains("left")) {
			int oldI = Integer.parseInt(lastCommand.replaceAll("[\\D]", ""));
			i = oldI + i;
			commands.set(commands.size() - 1, "left(" + i + ")");
		} else {
			commands.add("left(" + i + ")");
		}
	}

	public void right(int i) {
		String lastCommand = commands.get(commands.size() - 1);
		if (lastCommand.contains("right")) {
			int oldI = Integer.parseInt(lastCommand.replaceAll("[\\D]", ""));
			i = oldI + i;
			commands.set(commands.size() - 1, "right(" + i + ")");
		} else {
			commands.add("right(" + i + ")");
		}
	}

	public void down(int i) {
		String lastCommand = commands.get(commands.size() - 1);
		if (lastCommand.contains("down")) {
			int oldI = Integer.parseInt(lastCommand.replaceAll("[\\D]", ""));
			i = oldI + i;
			commands.set(commands.size() - 1, "down(" + i + ")");
		} else {
			commands.add("down(" + i + ")");
		}
	}

	public void up(int i) {
		String lastCommand = commands.get(commands.size() - 1);
		if (lastCommand.contains("up")) {
			int oldI = Integer.parseInt(lastCommand.replaceAll("[\\D]", ""));
			i = oldI + i;
			commands.set(commands.size() - 1, "up(" + i + ")");
		} else {
			commands.add("up(" + i + ")");
		}
	}

	public void printBlock(String blockType) {
		// commands.add("box(blocks." + blockType + ",1,1,1)");
		commands.add("box(blocks.oak,1,1,1)");
	}

	public void printCommandsToJs(String inFileLocation) {
		inFileLocation = inFileLocation.replace(".vox", ".js");
		String fileName = new File(inFileLocation).getName().replace(".js", "");
		List<String> lines = new ArrayList<String>(0);
		lines.add(fileName + " = function() {");
		lines.add("var d = new Drone();");
		int maxLineWidth = 50;
		String line = "";
		ProgressBar.getInstance();
		ProgressBar.jProgressBar.setMaximum(commands.size() - 1);
		ProgressBar.jProgressBar.setValue(0);
		for (int i = 0; i < commands.size(); i++) {
			if (i == 0) {
				line = line + commands.get(i);
			} else {
				line = line + "." + commands.get(i);
			}
			if (line.length() > maxLineWidth) {
				// Print what we have
				lines.add("d" + line + ";");
				line = "";
			} else if (i == commands.size() - 1) {
				lines.add("d" + line + ";");
			}
			ProgressBar.jProgressBar.setValue(i);
		}
		lines.add("}");
		System.out.println("Writing file");
		try {
			FileUtils.writeLines(new File(inFileLocation), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Complete");

	}
}
