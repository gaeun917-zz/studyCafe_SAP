package com.studycafe.model.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;

public class Member implements Serializable {
	
	private int memberNo;
	
	@NotEmpty(message ="ID is required")
	@Pattern(regexp = "[A-Za-z0-9]{8,15}", message = "Error")
	private String memberId;
	private String name;
	
	@NotEmpty(message ="Password is required")
	@Pattern(regexp = "[A-Za-z0-9]{6,15}", message = "a combination of 6~15 alphabetical and numeric characters")
	private String passwd;
	private String confirmPasswd;

	@NotEmpty(message ="Email is required")
	@Email(message ="ex) abc@email.com")
	private String email;
	private Timestamp regDate;
	
	private String gender;
	private String facebookId;
	private String kakaoId;
	private String status;
	private int gradeLevel;
	private String introduction;
	
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getConfirmPasswd() {
		return confirmPasswd;
	}
	public void setConfirmPasswd(String confirmPasswd) {
		this.confirmPasswd = confirmPasswd;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	public String getKakaoId() {
		return kakaoId;
	}
	public void setKakaoId(String kakaoId) {
		this.kakaoId = kakaoId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getGradeLevel() {
		return gradeLevel;
	}
	public void setGradeLevel(int gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Member(String email, String passwd, String name, Timestamp regDate) {
		this.email = email;
		this.passwd = passwd;
		this.name = name;
		this.regDate = regDate;
	}
	
	public void changePassword(String oldPassword, String newPassword) {
		if (!passwd.equals(oldPassword))
			throw new IdPasswordNotMatchingException();
		this.passwd = newPassword;
	}
	
	
	
	
}
