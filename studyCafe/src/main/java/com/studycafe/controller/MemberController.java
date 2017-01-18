package com.studycafe.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studycafe.model.dao.MemberDao;
import com.studycafe.model.dto.Member;
import com.studycafe.common.Util;

@Controller //request 처리하고 view로 toss해 줌
@RequestMapping(value = "/member/") //value url로 접속해서 들어오는 요청 받는 주소. 즉, 내 주소  
public class MemberController {

	@Autowired
	@Qualifier("oracleMemberDao")
	private MemberDao dao;
	public void setMemberDao(MemberDao memberDao) {// DI : new Member 안하고, setMethod로 MemebrDao가져옴  
		this.dao = memberDao;
	}

	// jsp에서 <form action= list.action> 걸어놓은 곳으로 이동. value에 jsp 주소만 주기도 하지만, 
	// action으로 정확히 어디에 적용되는지 명시! !!!!
	@RequestMapping(value = "list.action", method = RequestMethod.GET)
	public String list(Model model) {
		// Model이는 우체부 request씨에게 return jsp 주소로 addAttribute()한 내용을 보내달라고 함 
		// jsp는 이 소포를 받음: Model이가 정한 id로 Attribute 내용 꺼내씀: ${ members } 
		
		//1. 데이터 조회 (dao 사용)
		List<Member> members = dao.getList();
		
		//2. 데이터 저장 (jsp에서 사용할 수 있도록)
		Member member = members.get(0);
		System.out.println(member.getMemberId());
		model.addAttribute("members", members);
		// id:"members" value: members
		//3. 뷰 반환
		return "member/list"; // View 보내줄 곳 주소: list.jsp 
	}
	
//	@RequestMapping(
//		value = "list.action", method = RequestMethod.GET)
//	public ModelAndView list() {
//		//1. 데이터 조회 (dao 사용)
//		List<Member> members = dao.getList();
//		
//		//2. 데이터 저장 (jsp에서 사용할 수 있도록)
//		ModelAndView mav = new ModelAndView("member/list");
//		mav.addObject("members", members);
//		
//		//3. 뷰 반환
//		return mav;		
//	}
	
	@RequestMapping(value = "view.action", method = RequestMethod.GET)
	public String view(@RequestParam("memberid") String memberId, Model model) {
//						requestParam은 http request를 메서드에(String memberId) 바로 전달해준다. 
		if (memberId == null || memberId.length() == 0) {
			return "redirect:/member/list.action";
		}
		
		Member member = dao.getMemberById(memberId);
		model.addAttribute("member", member);
		
		return "member/view";
	}

	
	
// ---------- 회원 가입하면, term으로 가서 약관 동의하면 -> 회원 등록페이지 이동 --------------
	@RequestMapping(value = "term.action", method = RequestMethod.GET)
	public String term(@ModelAttribute Member member) {
		return "member/term";
	}
	

//  ----------- 회원 정 수정 
	
//	@ModelAttribute: 스프링 태그 라이브러리를 사용하기 위해 구성한 전달인자 
	@RequestMapping(value = "register.action", method = RequestMethod.GET)
	public String registerForm(@ModelAttribute("member")Member member) {
		//member.setRegDate(new Timestamp(new Date().getTime()));
	
		return "member/registerform2";
	}

//
	
	
	
	@RequestMapping(value = "registerSuccess.action", method = RequestMethod.GET)
	public String registerSuccess(String memberId, Model model) {
		Member member = dao.getMemberById(memberId);
		model.addAttribute("member", member);
		return "member/registerSuccess";
	}

	
	
	@RequestMapping(value = "registerSuccess.action", method = RequestMethod.POST)
	public String registerSuccess2(@ModelAttribute Member member) {

		return "member/registerSuccess";
	}
	
	@RequestMapping(value = "changepassword.action", method = RequestMethod.POST)
	@ResponseBody
	public String changePassword(String oldPasswd, String newPasswd, HttpSession session) {
		
		boolean success = false;
		//비밀번호 변경
		//1. 세션의 사용자 정보에서 아이디를 읽기
		Member member  = (Member) session.getAttribute("loginuser");		
		oldPasswd = Util.getHashedString(oldPasswd, "SHA-256");			
		//2. 1의 아이디와 oldPasswd를 이용해서 조회 수행
		Member member2 =  dao.getMemberByIdAndPasswd(member.getMemberId(), oldPasswd);
		//3-1. 2의 조회가 성공하면 아이디와 newPasswd를 이용해서 비밀번호 변경

		if (member2 != null) {	//3-1. 2의 조회가 성공하면 아이디와 newPasswd를 이용해서 비밀번호 변경
			member2.setPasswd(Util.getHashedString(newPasswd, "SHA-256"));
			dao.changePassword(member2);
			return "success";
		} else {	//3-2. 2의 조회가 실패하면 실패 메시지 전송 
			return "fail:invalid old password";
		}

	}

	
	@RequestMapping(value = "mypage.action", method = RequestMethod.GET)
	public String myPage(/*@ModelAttribute Member member,*/ Model model, HttpSession session) {
		Member member  = (Member) session.getAttribute("loginuser");
		dao.getMemberByMemberNo(member.getMemberNo());
		model.addAttribute("member", member);
		return "member/mypage";
		
	}
	
/*	@RequestMapping(value = "mypage.action", method = RequestMethod.POST)
	public String myPage2(@ModelAttribute Member member) {
		return "member/mypage";
		
	}*/

	@RequestMapping(value = "edit.action", method = RequestMethod.POST)
	public String password(@ModelAttribute Member member) {
		// dao에 패스워드 update하는 거 
		//sql 만들고 mapper.java에 등록 
		//dao.
		
		return "redirect:/member/changePassword.action";
		
	}
	
	
//	register하고 나서, 자신의 information 보여주고, 관심 사항을 카테고리에서 선택하게함. 
	@RequestMapping(value = "register.action", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute Member member ,BindingResult result) {		
		
		if(result.hasErrors()){
			return "member/registerform2";
		}
		
		member.setPasswd(Util.getHashedString(member.getPasswd(), "SHA-256"));		
		dao.insert(member);		
		return "redirect:/member/registerSuccess.action?memberId="+member.getMemberId();
		// 회원 가입 성공 jsp 
		
	}

	
	@RequestMapping(value = "delete.action", method = RequestMethod.GET)
	public String delete(HttpSession session) {		
		
		Member member3  = (Member) session.getAttribute("loginuser");		
		dao.deleteByMemberNo(member3.getMemberNo());
		session.removeAttribute("loginuser");
		return "redirect:/";

	}
	
	
	
	
	
}