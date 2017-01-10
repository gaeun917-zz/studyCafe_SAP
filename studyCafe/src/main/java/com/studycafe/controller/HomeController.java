package com.studycafe.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.studycafe.model.dto.Board;
import com.studycafe.model.dto.BoardMember;
import com.studycafe.model.dto.Member;
import com.studycafe.model.dto.Page;
import com.studycafe.model.mapper.MemberMapper;
import com.studycafe.model.service.BoardService;
import com.studycafe.model.service.CalendarService;
import com.studycafe.model.service.MemberService;
import com.studycafe.model.service.PageBoardService;
import com.studycafe.model.service.PageService;
import com.studycafe.model.service.UploadService;

@Controller
public class HomeController {
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home1(Locale locale, Model model) {		
//		return "index"; // /WEB-INF/views/ + index + .jsp
//	}	
//	@RequestMapping(value = "/home.action", method = RequestMethod.GET)
//	public String home2(Locale locale, Model model) {		
//		return "index"; // /WEB-INF/views/ + index + .jsp
//	}	
	//2개 이상의 경로를 배열로 묶어서 매핑 가능
	
	@Autowired
	@Qualifier("pageBoardService")
	private PageBoardService pageBoardService;

	@Autowired
	@Qualifier("pageService")
	private PageService pageService;

	@Autowired
	@Qualifier("memberService")
	private MemberService memberService;
	
	@Autowired
	@Qualifier("calendarService")
	private CalendarService calendarService;
	
	@Autowired
	@Qualifier("uploadService")
	private UploadService uploadService;
	
	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;
	
	@RequestMapping(value = { "/", "home.action" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		// board 테이블의 closedate와 sysdate의 차가 음수일 경우  board테이블의 status를 0으로 설정
		List<Board> boards = boardService.selectBoardListByTime();
		if(boards != null){
			for(Board board : boards){
				boardService.deleteBoard(board.getBoardNo());
				System.out.println(board.getCloseDate());
				
				Page page = new Page();
				page.setCloseDate(board.getCloseDate());
				
				pageService.insertPage(page);
				int pageNo = pageService.selectPageNo();
				
				List<BoardMember> members = memberService.selectBoardMemberByBoardNo(board.getBoardNo());
				if(members != null){
					for(BoardMember member : members){
						
						pageService.insertMemberPageByMemberNo(member.getMemberNo(), pageNo, board.getTitle());
					}
					
				}
				pageService.insertPageMenuNotice(pageNo);
			}
			
		}
//		// board 테이블의 status가 0인 board테이블의 board_no로 member_page의 같은 board_no가 존재하는지 확인
//		int memberPageCount = pageService.selectMemberPageCountByMemberNo(memberNo);
		// page_menu(공지사항, status 5), page_calendar(행하나 추가)
		
		// page_upload테이블의 새로운 행을 생성 () 
		
		// member_board 테이블에 등록시킴(board_no, member_no) <<참가 인원!
		
		return "index"; // /WEB-INF/views/ + index + .jsp
	}
	
}




