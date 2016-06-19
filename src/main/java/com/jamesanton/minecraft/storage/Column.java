package com.jamesanton.minecraft.storage;

import java.util.ArrayList;
import java.util.List;

public class Column {
	public List<Cell> cells = null;
	
	public Column(){
		cells = new ArrayList<Cell>(0);
	}
	
	public void add(Cell c){
		cells.add(c);
	}
	
	public void printCol(){		
		for(Cell c: cells){
			System.out.println(c.toString());
		}
		
	}
}
