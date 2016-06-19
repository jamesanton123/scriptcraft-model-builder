package com.jamesanton.minecraft.storage;

public class Cell {
	public int value = 0;
	
	public boolean isBlock(){
		if(value != -1){
			return true;
		}else{
			return false;
		}
	}
}
