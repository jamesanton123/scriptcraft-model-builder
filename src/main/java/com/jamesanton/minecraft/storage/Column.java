package com.jamesanton.minecraft.storage;

import java.util.ArrayList;
import java.util.List;

public class Column {
	private List<Cell> cells = null;
	
	public Column(){
		cells = new ArrayList<Cell>(0);
	}
	
	public void add(Cell c){
		cells.add(c);
	}

	public List<Cell> getCells() {
		return cells;
	}	
}
