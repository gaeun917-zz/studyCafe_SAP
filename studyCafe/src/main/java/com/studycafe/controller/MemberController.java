package com.studycafe.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.studycafe.model.service.MemberService;
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
@RequestMapping(value = "/member/") //(value= url)로 접속해서 들어오는 client 요청을 받는 주소. 즉, 내 주소
public class MemberController {

	@Autowired
	@Qualifier("memberService")
	private MemberService memberService;


	// jsp에서 <form action= list.action> 걸어놓은 곳에서 데이터 받아옴 from.
	// (value=url)에 .jsp 주소만 주기도 하지만, action으로 정확히 어디에 적용되는지 명시! !!!!
	@RequestMapping(value = "list.action", method = RequestMethod.GET) // method = default:POST
	public String list(Model model) {

		// 1. Model이는 우체부 request씨한테서 From (list.action) <form> 이 보낸 데이터를 GET
		// 2.  memerService(dao)로 데이터 처리
		// 3. .addAttribute(id, value)함: nametag(id)에 데이터 처리된 애를 담아서,
		// 4. return(To) jsp 주소로 내용을 보내달라고 함
		// * jsp는 이 소포를 받음: Model이가 정한 id로 .addAttribute한 내용 꺼내씀: ${ members }
		
		//1. 데이터 조회 (memberServiceZ(interface)에는 dao와 같은 method list
		// -> implement한 애들은 Mapper와 연결해줌)
		List<Member> members = memberService.getList(); //dao는 mapper 연결해줌
		
		// 데이터 처리가 잘되었는지, memebrs list의 (indxe:0)를 열어보자
		//Member member = members.get(0);
		//System.out.println(member.getMemberId());

		//2. 데이터 저장 (jsp에서 사용할 수 있도록)
		model.addAttribute("members", members);// id:"members" value: members

		//3. 뷰 반환
		return "member/list"; // View 보내줄 곳 주소: list.jsp 
	}
	
// ModelAndView 사용법
// 	@RequestMapping(value = "list.action", method = RequestMethod.GET)
//	public ModelAndView list() { // return type이 ModelAndView!
//
// 		1. 데이터 조회
//		List<Member> members = dao.getList();// Member를 return하는 .getList()의 리스트에 모든 내용을 담음
//		
//		//2. 데이터 저장 (jsp에서 사용할 수 있도록)
//		ModelAndView mav = new ModelAndView("member/list"); // mav 객체 만들면서 return 주소부터 줌
//		mav.addObject("members", members);        // 보내야 되는 소포 addObject(nametag, 내용);
//		
//		//3. 뷰 반환
//		return mav;		
//	}
	
	@RequestMapping(value = "view.action", method = RequestMethod.GET)
	public String view(@RequestParam("memberid") String memberId, Model model) {
					//requestParam은 httpRequest를 메서드에(String memberId) 바로 전달해준다.
		if (memberId == null || memberId.length() == 0) {
			return "redirect:/member/list.action";
		}
		
		Member member = memberService.searchMemberByMemberId(memberId);
		model.addAttribute("member", member);
		
		return "member/view";
	}

	
	
// ---------- 회원 가입-> term.jsp 약관 동의 -> 회원 등록페이지 --------------
	@RequestMapping(value = "term.action", method = RequestMethod.GET)
	public String term(@ModelAttribute("member")Member member) {
		// check 했는지는 js로 확인함
		return "member/term";
	}

	
	@RequestMapping(value = "register.action", method = RequestMethod.GET)
	public String registerForm(@ModelAttribute("member")Member member) {
		//@ModelAttribute: 스프링 taglib를<form:form> 사용하기 위해 전달인자
		// ModelAttribute는 addAttribute 대신
		//member.setRegDate(new Timestamp(new Date()));
		return "member/registerform2";
	}

	
	@RequestMapping(value = "registerSuccess.action", method = RequestMethod.GET)
	public String registerSuccess(String memberId, Model model) {
		Member member = memberService.searchMemberByMemberId(memberId);
		model.addAttribute("member", member);
		return "member/registerSuccess";
	}


	@RequestMapping(value = "changepassword.action", method = RequestMethod.POST)
	@ResponseBody
	public String changePassword(String oldPasswd, String newPasswd, HttpSession session) {

		//1. 세션의 사용자 정보 읽기
		Member member  = (Member) session.getAttribute("loginuser");		
		oldPasswd = Util.getHashedString(oldPasswd, "SHA-256");			

		//2. memeber 아이디와 oldPasswd를 이용해서 조회 수행
		Member member2 =  memberService.login(member.getMemberId(), oldPasswd);

		if (member2 != null) {	//3-1. 조회가 성공: 아이디와 newPasswd를 이용해서 비밀번호 변경
			member2.setPasswd(Util.getHashedString(newPasswd, "SHA-256"));
			memberService.changePassword(member2);
			return "success";
		} else {	//3-2. 2의 조회가 실패하면 실패 메시지 전송 
			return "fail:invalid old password";
		}
	}

	
	@RequestMapping(value = "mypage.action", method = RequestMethod.GET)
	public String myPage(Model model, HttpSession session) {
		Member member  = (Member) session.getAttribute("loginuser");
		memberService.getMemberByMemberNo(member.getMemberNo());// 접속자#로 멤버 data GET

		model.addAttribute("member", member);// data를 jsp로 보냄
		return "member/mypage";
	}


	@RequestMapping(value = "edit.action", method = RequestMethod.POST)
	public String password(@ModelAttribute Member member) {
		// pw update SQL on MemebrMapper
		return "redirect:/member/changePassword.action";
		
	}
	
//	register -> MemeberData view page -> choose interest Category
	@RequestMapping(value = "register.action", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("member")Member member, BindingResult result) {
		// register.jsp에서 form에 입력한 데이터 member에 저장되어 있음
		if(result.hasErrors()){
			return "member/registerform2";
		}
		
		member.setPasswd(Util.getHashedString(member.getPasswd(), "SHA-256"));		
		memberService.insertMember(member);
		return "redirect:/member/registerSuccess.action?memberId="+member.getMemberId();

	}

	
	@RequestMapping(value = "delete.action", method = RequestMethod.GET)
	public String delete(HttpSession session) {
		Member member  = (Member) session.getAttribute("loginuser");
		memberService.deleteByMemberNo(member.getMemberNo());

		session.removeAttribute("loginuser");
		return "redirect:/";

	}
}