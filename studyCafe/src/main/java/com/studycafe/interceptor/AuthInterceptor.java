package com.studycafe.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.studycafe.model.dto.Member;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(
		HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

//		HttpSession session = req.getSession();
//		Member member = (Member)session.getAttribute("loginuser");
//		
//		String url = req.getRequestURI();
//		boolean redirect = false;
//		if (url.contains("/member/")) {
//			if (member != null && 
//				member.getUserType().toLowerCase().equals("admin")) {
//			} else {
//				redirect = true;
//			}
//		} else if (url.contains("/board/") ||
//				   url.contains("/upload/")) {
//			if (member != null) {				
//			} else {
//				redirect = true;
//			}
//		}
//		if (redirect) {
//			resp.sendRedirect(
//				"/demoweb-spring-compact6/account/login.action");
//			return false;
//		} else {
//			return true;//정상 진행 (Controller로 이동)
//		}
		return true;
	}	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("Interceptor.afterCompletion");
	}
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {	
		System.out.println("Interceptor.postHandle");
	}



}
