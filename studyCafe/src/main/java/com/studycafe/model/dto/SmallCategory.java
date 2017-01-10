package com.studycafe.model.dto;

import java.util.ArrayList;

public class SmallCategory {
	
	private int smallCategoryNo;
	private String smallCategoryName;
	private int bigCategoryNo;
	
	private ArrayList<BigCategory> bigcategories;
	private ArrayList<SmallCategory> smallcategories;
	
	
	public int getSmallCategoryNo() {
		return smallCategoryNo;
	}
	public void setSmallCategoryNo(int smallCategoryNo) {
		this.smallCategoryNo = smallCategoryNo;
	}
	public String getSmallCategoryName() {
		return smallCategoryName;
	}
	public void setSmallCategoryName(String smallCategoryName) {
		this.smallCategoryName = smallCategoryName;
	}
	public int getBigCategoryNo() {
		return bigCategoryNo;
	}
	public void setBigCategoryNo(int bigCategoryNo) {
		this.bigCategoryNo = bigCategoryNo;
	}
	public ArrayList<BigCategory> getBigcategories() {
		return bigcategories;
	}
	public void setBigcategories(ArrayList<BigCategory> bigcategories) {
		this.bigcategories = bigcategories;
	}
	public ArrayList<SmallCategory> getSmallcategories() {
		return smallcategories;
	}
	public void setSmallcategories(ArrayList<SmallCategory> smallcategories) {
		this.smallcategories = smallcategories;
	}
	
	

}
