package com.studycafe.controller;

import com.studycafe.common.Util;
import com.studycafe.model.dto.Member;
import com.studycafe.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@Controller //request 받고-> db 처리 -> toss to view
@RequestMapping(value = "/member/") //(value= url)로 접속해서 들어오는 client 요청을 받는 주소. 즉, 내 주소
public class MemberController {


	//java @bean이란?
	// serializable: 인자없는 생성자를 가지며, getter ,setter 메소드를 통하여 프로퍼티에 접근 가능한 Java Object
	@Autowired
	@Qualifier("memberService")
	private MemberService memberService;


	// jsp에서 <form action= list.action> 걸어놓은 곳에서 데이터 받아옴 from.
	// (value=url)에 .jsp 주소만 주기도 하지만, action으로 정확히 어디에 적용되는지 명시! !!!!
	@RequestMapping(value = "list.action", method = RequestMethod.GET) // method = default:POST
	public String list(Model model) {

		// 1. Model이는 우체부 request씨한테서 From (list.action) <form> 이 보낸 데이터를 받음
		// 2. memerService(dao와 같은 method list)로 데이터 처리
		// 	  memberServiceimpl는 Mapper와 연결해줌
		// 3. .addAttribute(id, value)함
		// 4. return(To) jsp 주소로 내용을 보내달라고 함

		// * jsp는 이 소포를 받음: Model이가 정한 id로 .addAttribute()한 내용 꺼내씀: ${ members }
		// * model을 쓰면 return 값이 jsp 주소 이므로, return datatype은 String이 어야함.
		
		//1. 데이터 조회
		List<Member> members = memberService.getList(); //dao는 mapper 연결해줌
		
		// 데이터 처리가 잘되었는지 확인: 리스트의 첫번째 Member Object받기
		// Member member = members.get(0);
		// System.out.println(member.getMemberId());

		//2. 데이터 저장 (jsp에서 사용할 수 있도록)
		model.addAttribute("members", members);// (id, value)

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
//		// 아니면
//		ModelAndView mav = new ModelAndView() 하고 상황 별로 보내버리기 위해서
// 		mav.setViewName("abc"); 할 수 있음
//		//3. 뷰 반환
//		return mav;		
//	}

	//list.jsp
	@RequestMapping(value = "view.action", method = RequestMethod.GET)
	public String view(@RequestParam("memberid") String memberId, Model model) {
					// @requestParam은 mapping에서 들어오는 value를
					// httpRequest 대신해서 파라미터에(String memberId) 바로 전달해준다.
					// 즉, String memberid = request.getParameter("memberId") 안해도됨  			// boardNo

		// 1. 들어온 데이터(memberId) 확인
		if (memberId == null || memberId.length() == 0) { //1.0 data 실패
			return "redirect:/member/list.action";
		}
														  //1.1 성공
		Member member = memberService.searchMemberByMemberId(memberId);
		model.addAttribute("member", member);
		
		return "member/view";
	}

	
	
// ---------- 회원 가입 -> term.jsp 약관 동의 -> 회원 등록페이지 --------------
	@RequestMapping(value = "term.action", method = RequestMethod.GET)
	public String term(@ModelAttribute("member")Member member) {
		// check 했는지는 js로 확인함
		return "member/term";
	}

	
	@RequestMapping(value = "register.action", method = RequestMethod.GET)
	public String registerForm(@ModelAttribute("member")Member member) {
		// 중요! @ModelAttribute: 스프링 taglib를 <form:form> 사용하기 위해 전달인자
		// ModelAttribute는 .addAttribute() 대신 (nametag를 파라미터에서 설정)-> 보내주는 컨텐츠는 member
		// @ModelAttribute가 젤 첫번째 파라미터로 와야된다고 들었음..
		member.setRegDate(new Timestamp(System.currentTimeMillis()));
		//timestamp parameter는 Long type이어야함
		return "member/registerform2";
	}

	
	@RequestMapping(value = "registerSuccess.action", method = RequestMethod.GET)
	public String registerSuccess(String memberId, Model model) {
		Member member = memberService.searchMemberByMemberId(memberId);
		model.addAttribute("member", member);
		return "member/registerSuccess";
	}

	// 중요! @responseBody: 데이터 자체를 보내줌 -> model 할 필요없음,
	// 이건 data에 select 아니고, update처럼 db 처리만 하고 끝나는 경우에 씀.
	// 따로 data를 받아야 될 경우(GET인 경우) model의 id, contents 필요하기 때문에 못씀
	// 이 method는 void여도 되는데, string으로 해서 return 값에 의미없는 단어를 넣음
	// mapping은 array로 2 route: 작동 안되면 밑에 pw method()풀것
	@RequestMapping(value = {"changepassword.action", "edit.action"}, method = RequestMethod.POST)
	@ResponseBody
	public String changePassword(String currentPasswd, String newPasswd, HttpSession session) {

		//1. 세션의 사용자 정보 읽기
		Member member  = (Member) session.getAttribute("loginuser");// (member)로 casting 해야됨.
		currentPasswd = Util.getHashedString(currentPasswd, "SHA-256");
		newPasswd = Util.getHashedString(newPasswd, "SHA-256");
		// member.getPasswd()로 구해도 되고, 파라미터로 password받지만 보안을 위해 hashcode로 변경

		//2. member 아이디와 currentPasswd로 db에서 selectMember
		Member member2 =  memberService.login(member.getMemberId(), currentPasswd);
		if (member2 != null) {	//2.1 db에서 멤버 정보 조회 성공

		//3. 새 패스워드 설정
			member2.setPasswd(newPasswd);
			memberService.changePassword(member2); // parameter가 Member여야함
			return "success";
		} else {				//2.0 조회 실패
			return "fail:invalid old password";
		}
	}
//	@RequestMapping(value = "edit.action", method = RequestMethod.POST)
//	public String password(@ModelAttribute Member member) {
//		return "redirect:/member/changePassword.action";
//	}
	
	@RequestMapping(value = "mypage.action", method = RequestMethod.GET)
	public String myPage(Model model, HttpSession session) {

		Member member  = (Member) session.getAttribute("loginuser");
		memberService.getMemberByMemberNo(member.getMemberNo());

		model.addAttribute("member", member);
		return "member/mypage";
	}
	
	//	register -> Mypage -> choose interest Category
	//  BindingResult: @valid을 체크 하여,오류가 있는지를 확인
	@RequestMapping(value = "register.action", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("member")Member member, BindingResult bindingResult) {
		// register.jsp에서 form에 입력한 데이터 member에 저장되어 있고, model이 보내는 소포의 nametag도 member임
		// 패스워드는 hash로 넣으려고 일부러 setPassword로 함!
		String pw = member.getPasswd();
		String hashedPw = Util.getHashedString(pw, "SHA-256");

		if(bindingResult.hasErrors()){
			return "member/registerform2";
		}
		member.setPasswd(hashedPw);
		memberService.insertMember(member);
		return "redirect:/member/registerSuccess.action?" +
				"memberId="+member.getMemberId();
	}


	@RequestMapping(value = "delete.action", method = RequestMethod.GET)
	public String delete(HttpSession session) {
		Member member  = (Member) session.getAttribute("loginuser");
		memberService.deleteByMemberNo(member.getMemberNo()); // member.status will be marked as deactive

        session.removeAttribute("loginuser");
		return "redirect:/";
	}
}