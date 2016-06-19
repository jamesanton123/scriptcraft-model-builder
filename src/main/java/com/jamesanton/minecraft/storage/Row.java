package com.jamesanton.minecraft.storage;

import java.util.ArrayList;
import java.util.List;

/**
 *  A row will be a group of columns or a wall
 */
public class Row {
	public List<Column> columns = null;
	
	public Row(){
		columns = new ArrayList<Column>(0);
	}
}
