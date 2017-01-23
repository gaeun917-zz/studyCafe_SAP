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
	@ResponseBody   // 데이터 보내는거 model 필요없음, 데이터 그 자체임! db에 post, update만 하고 끝
	public String login(String memberId, String passwd, HttpSession session) {

		//1. 로그인: getMemberByIdAndPasswd(id, passwd)
		passwd = Util.getHashedString(passwd, "SHA-256");
		Member member = memberService.login(memberId, passwd);

		if (member != null) {                //1.1 selectMember 성공
			//2. 세션에 로그인 정보 저장
			session.setAttribute("loginuser", member);

			//3. 페이지 넘버 구하기
			List<Page> pages = pageService.searchPageNoByMemberNo(member.getMemberNo());
			if (pages != null) {            //3.1 페이지No 있으면
				//4. 페이지No 세션에 저장
				session.setAttribute("userpages", pages);
			}
			return "success";
		} else {                            //1.0 로그인 실패(db에서 id,pw matching 찾을 수 없으면)
			return "fail";
		}
	}


	@RequestMapping(value = "/check-duplicate.action", method = RequestMethod.POST)
	@ResponseBody
	public String checkDuplicate(String memberId) {        // 같은 아이디 존재여부확인
		// 1. db에서 해당 id로 Member 찾기
		Member member = memberService.searchMemberByMemberId(memberId);
		// 2. 해당 id가 db에 이미 존재하는지 확인
		if (member != null) {   //1.0 같은 아이디 있음
			return "fail";
		} else {                //1.1 같은 아이디 없음
			return "success";
		}
	}


	@RequestMapping(value = "/facebookLogin.action", method = RequestMethod.POST)
	@ResponseBody
	public String facebooklogin(String FBid, HttpSession session) {
		//페이스 북 아이디 세션에 저장: fbid를 db에 저장을 못함 -> 가입안됨
		session.setAttribute("facebookLoginuser", FBid);
		return "success";
	}


	@RequestMapping(value = "/kakaoLogin.action", method = RequestMethod.POST)
	@ResponseBody
	public String kakaologin(String kakaoId, HttpSession session) {
		session.setAttribute("kakaoLoginuser", kakaoId);
		return "success";
	}

	@RequestMapping(value = "/logout.action", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loginuser");
		session.removeAttribute("userpages");
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

		//1. session Member 구하기
		Member member = (Member) session.getAttribute("loginuser");
		if (member == null) {// return을 html로 함! pageList의 <form>에서 받나봄
			return "<hr><li style='text-align: center'>Please Login...</li><hr>";
		} else {

			//2. pageNo 구하기 (세션에 저장되어 있음)session은 HttpSession을 리턴하기 때문에 (casting)을 해줘여함
			@SuppressWarnings("unchecked") // unchecked cast warning 떠서 @suppress 해줌
			List<Page> pages = (List<Page>) session.getAttribute("userpages");
//			List<Page> pages = pageService.searchPageNoByMemberNo(member.getMemberNo());
			if (pages == null) {
				return "<hr><li style='text-align: center'>No Search result </li><hr>";
			} else {

				//3. 페이지 메뉴 보여주기
				String html = "";
				for (Page page : pages) {
					//System.out.println(page.getName());
					PageMenu menu = pageService.selectPageMenuByPageNoNotice(page.getPageNo());
					//System.out.println(menu.getMenuNo());

					//4. html/js string
					html += "<hr><li style='text-align: center'>" +
							"<a href=\"javascript:" +
							"window.open('/studyCafe/page/board/list.action?" +
							"menuno=" + menu.getMenuNo() +
							"&memberpageno=" + page.getPageNo() +
							"', '', 'width=1200, height=1000, resizable=yes');" +
							"\">" + page.getName() + "</a></li><hr>";
				}
				return html;
			}
		}
	}
}