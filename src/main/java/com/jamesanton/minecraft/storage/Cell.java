package com.jamesanton.minecraft.storage;

public class Cell {
	private int value = 0;
	
	public void setValue(int value){
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public boolean isBlock(){
		if(value != -1){
			return true;
		}else{
			return false;
		}
	}
}
