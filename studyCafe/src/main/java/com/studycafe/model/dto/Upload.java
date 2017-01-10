package com.studycafe.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Upload implements Serializable {

	private int uploadNo;
	private String title;
	private String content;
	private int readCount;
	private Timestamp regDate;
	private String status;
	private int memberNo;
	private int pageNo;
	
	private List<UploadFile> files;
	private List<Member> memberId;
	
	
	public Upload() {}
	
	public int getUploadNo() {
		return uploadNo;
	}
	public void setUploadNo(int uploadNo) {
		this.uploadNo = uploadNo;
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
	public List<UploadFile> getFiles() {
		return files;
	}
	public void setFiles(List<UploadFile> files) {
		this.files = files;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Member> getMemberId() {
		return memberId;
	}
	public void setMemberId(List<Member> memberId) {
		this.memberId = memberId;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	
}
