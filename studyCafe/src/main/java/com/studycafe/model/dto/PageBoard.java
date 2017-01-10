package com.studycafe.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class PageBoard implements Serializable{

	private int PBoardNo;
	private String title;
	private String content;
	private Timestamp Boarddate;
	private String status;
	private int menuNo;
	private int memberNo;
	
	public int getPBoardNo() {
		return PBoardNo;
	}
	public void setPBoardNo(int pBoardNo) {
		PBoardNo = pBoardNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getBoarddate() {
		return Boarddate;
	}
	public void setBoarddate(Timestamp boarddate) {
		Boarddate = boarddate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

}
