package com.studycafe.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Member implements Serializable {
	
	private int memberNo;
	
	@NotEmpty(message ="*아이디를 넣어주세요")
	//@Pattern(regexp = "[A-Za-z0-9]{8,15}", message = "아이디 형식 오류")
	private String memberId;
	private String name;
	
	@NotEmpty(message ="*비밀번호를 넣어주세요")
	@Pattern(regexp = "[A-Za-z0-9]{6,15}", message = "알파벳, 숫자 조합으로 6~15자리")
	private String passwd;
	private String confirmPasswd;

	@NotEmpty(message ="*이메일을를 넣어주세요")
	@Email(message ="*이메일 형식을 넣어주세요")
	private String email;
	private Timestamp regDate;
	
	
	//@NotEmpty
	//@Pattern(regexp = "(user|admin){1}")
	
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
	public Member(){}
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
