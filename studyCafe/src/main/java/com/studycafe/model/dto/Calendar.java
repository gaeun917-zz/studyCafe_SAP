package com.studycafe.model.dto;

import java.util.Date;
import java.util.List;

public class Calendar {

	private int calendarNo;
	private String title;
	private String content;
	private Date startDate;
	private Date dueDate;
	private int pageNo;
	private int memberNo;

	private List<Member> memberId;
	
	public List<Member> getMemberId() {
		return memberId;
	}
	public void setMemberId(List<Member> memberId) {
		this.memberId = memberId;
	}
	
	public int getCalendarNo() {
		return calendarNo;
	}

	public void setCalendarNo(int calendarNo) {
		this.calendarNo = calendarNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
