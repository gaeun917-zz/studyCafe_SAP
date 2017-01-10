package com.studycafe.model.dto;

import java.util.Date;
import java.util.List;

public class Board {
	
	private int boardNo;
	private String title;
	private String content;
	private Date closeDate;
	private Date startDate;
	private int memberCount;
	private String status;
	private String place;
	private int memberNo;
	private String purpose;
	private String period;
	private int frequency;
	private int smallCategoryNo;
	
	private List<BoardFile> files;
	private List<Member> memberId;	
	private List<SmallCategory> smallCategoryName;
	
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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
	public Date getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}


	public List<Member> getMemberId() {
		return memberId;
	}
	public void setMemberId(List<Member> memberId) {
		this.memberId = memberId;
	}
	public List<SmallCategory> getSmallCategoryName() {
		return smallCategoryName;
	}
	public void setSmallCategoryName(List<SmallCategory> smallCategoryName) {
		this.smallCategoryName = smallCategoryName;
	}
	public int getSmallCategoryNo() {
		return smallCategoryNo;
	}
	public void setSmallCategoryNo(int smallCategoryNo) {
		this.smallCategoryNo = smallCategoryNo;
	}
	public List<BoardFile> getFiles() {
		return files;
	}
	public void setFiles(List<BoardFile> files) {
		this.files = files;
	}
	

}
