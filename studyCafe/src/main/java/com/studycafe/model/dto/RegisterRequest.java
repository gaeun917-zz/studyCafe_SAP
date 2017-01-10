package com.studycafe.model.dto;

public class RegisterRequest {

	private String email;
	private String passwd;
	private String confirmPasswd;
	private String name;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPasswordEqualToConfirmPassword() {
		return passwd.equals(confirmPasswd);
	}

}
