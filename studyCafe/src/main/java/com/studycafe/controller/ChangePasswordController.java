//package com.studycafe.controller;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import spring.AuthInfo;
//import spring.ChangePasswordService;
//import spring.IdPasswordNotMatchingException;
//
//@Controller
//@RequestMapping(value ="/member/")
//public class ChangePasswordController  {
//	
//	private ChangePasswordController changePasswordService;
//
//	public void setChangePasswordService(
//			ChangePasswordService changePasswordService) {
//		this.changePasswordService = changePasswordService;
//	}
////ModelAttribute : 커맨드 캑체에 접근할 때 사용하는 속성명을 변경하고 싶을 때 
//	@RequestMapping(method = RequestMethod.GET)
//	public String form(@ModelAttribute Member member) {
//		return "edit/changePwdForm";
//	}
//
//	@RequestMapping(method = RequestMethod.POST)
//	public String submit(
//			@ModelAttribute("command") ChangePwdCommand pwdCmd,
//			Errors errors,
//			HttpSession session) {
//		new ChangePwdCommandValidator().validate(pwdCmd, errors);
//		if (errors.hasErrors()) {
//			return "edit/changePwdForm";
//		}
//		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
//		try {
//			changePasswordService.changePassword(
//					authInfo.getEmail(),
//					pwdCmd.getCurrentPassword(),
//					pwdCmd.getNewPassword());
//			return "edit/changedPwd";
//		} catch (IdPasswordNotMatchingException e) {
//			errors.rejectValue("currentPassword", "notMatching");
//			return "edit/changePwdForm";
//		}
//	}
//}
