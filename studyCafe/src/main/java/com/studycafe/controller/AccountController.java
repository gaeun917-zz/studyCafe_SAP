package com.studycafe.controller;

import com.studycafe.common.Util;
import com.studycafe.model.dto.Member;
import com.studycafe.model.dto.Page;
import com.studycafe.model.dto.PageMenu;
import com.studycafe.model.service.MemberService;
import com.studycafe.model.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	@Qualifier("memberService")
	private MemberService memberService;
	
	@Autowired
	@Qualifier("pageService")
	private PageService pageService;
	
	@Autowired
	@Qualifier("pageMenuService")
	
	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public String loginForm() {
		return "account/loginform";
	}
	
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	@ResponseBody   // responseBody: 메소드가 반환하는 값이 페이지 이름이 아니고, 데이터 그 자체임! db에 post만 하고 끝나기 때문에 from 주소만 있으면 됨
	public String login(String memberId, String passwd, HttpSession session) {

		passwd = Util.getHashedString(passwd, "SHA-256");

		Member member = memberService.login(memberId, passwd);// getMemberByIdAndPasswd(id, passwd);
		if (member != null) {
			session.setAttribute("loginuser", member);// 세션에 로그인 정보 저장

			List<Page> pages = pageService.searchPageNoByMemberNo(member.getMemberNo());
			if(pages != null){
				session.setAttribute("userpages",pages);
			}
			return "success";
			
		} else {
			return "fail";
		}
	}

	
	@RequestMapping(value = "/check-duplicate.action", method = RequestMethod.POST)
	@ResponseBody   // responseBody: 메소드가 반환하는 값이 페이지 이름이 아니고, 데이터 그 자체임!
	public String checkDuplicate(String memberId) {
		
		Member member = memberService.searchMemberByMemberId(memberId);
		
		if (member != null) {
			return "fail"; 			
		} else {			
			return "success";
		}		
		
	}


	@RequestMapping(value = "/facebookLogin.action", method = RequestMethod.POST)
	@ResponseBody
	public String facebooklogin(String name, HttpSession session) {
		//페이스 북은 세션 정보만 전해주지 그 세션 정보를 저장할 수 없음.
		session.setAttribute("facebookLoginuser", name);
//		Member member = memberService.login(memberId, passwd);
//		if (member != null) {
//			//세션에 로그인 정보 저장
//			session.setAttribute("facebookLoginuser", member);
//			return "redirect:/home.action"; 
//		} else {
//			return "account/loginform";
//		}		
		return "success";
	}
	
	@RequestMapping(value = "/kakaoLogin.action", method = RequestMethod.POST)
	@ResponseBody
	public String kakaologin(String nickname, HttpSession session) {

		session.setAttribute("kakaoLoginuser", nickname);
//		Member member( = memberService.login(memberId, passwd);
//		if (member != null) {
//			//세션에 로그인 정보 저장
//			session.setAttribute("loginuser", member);
//			return "redirect:/home.action"; 
//		} else {
//			return "account/loginform";
//		}		
		return "success";
	}
	
	@RequestMapping(value = "/logout.action", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.removeAttribute("loginuser");//로그아웃
		session.removeAttribute("userpages");//로그아웃
		
		return "redirect:/home.action";
	}
	
	@RequestMapping(value = "/facebookLogout.action", method = RequestMethod.GET)
	public String facebookLogout(HttpSession session) {
		
		session.removeAttribute("facebookLoginuser");
		return "redirect:/home.action";
	}
	
	@RequestMapping(value = "/kakaoLogout.action", method = RequestMethod.GET)
	public String kakaoLogout(HttpSession session) {
		
		session.removeAttribute("kakaoLoginuser");
		return "redirect:/home.action";
	}
	
	@RequestMapping(value = "/pageList.action", method = RequestMethod.GET, produces = "text/plain; charset=utf8")
	@ResponseBody
	public String getpageList(HttpSession session, HttpServletResponse response) {
		
		Member member = (Member) session.getAttribute("loginuser");
		if(member == null){// return을 html로 함! pageList의 <form>에서 받나봄
			return "<hr><li style='text-align: center'>Please Login...</li><hr>";
		}

		List<Page> pages = pageService.searchPageNoByMemberNo(member.getMemberNo());
		if(pages == null){
			return "<hr><li style='text-align: center'>No Search Your Room</li><hr>";
		}
		
		String html = "";
		for(Page page : pages){
			System.out.println(page.getName());
			PageMenu menu = pageService.selectPageMenuByPageNoNotice(page.getPageNo());
			System.out.println(menu.getMenuNo());
			html +=  "<hr><li style='text-align: center'>" +
						"<a href=\"javascript:" +
									"window.open('/studyCafe/page/board/list.action?" +
												"menuno=" + menu.getMenuNo() +
												"&memberpageno=" + page.getPageNo() +
												"', '', 'width=1200, height=1000, resizable=yes');" +
						"\">"+  page.getName() +"</a></li><hr>";
		}
		return html;
	}
}

