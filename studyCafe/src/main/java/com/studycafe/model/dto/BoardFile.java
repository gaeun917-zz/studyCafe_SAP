package com.studycafe.model.dto;

import java.io.Serializable;
import java.sql.Date;

public class BoardFile implements Serializable {

	private int boardFileNo;
	private String savedFileName;
	private String userFileName;
	private int boardNo;
//	private Date regDate;
//	private int downloadCount;
	
	public BoardFile() {}

	public int getBoardFileNo() {
		return boardFileNo;
	}

	public void setBoardFileNo(int boardFileNo) {
		this.boardFileNo = boardFileNo;
	}

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}

	public String getUserFileName() {
		return userFileName;
	}

	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
}
