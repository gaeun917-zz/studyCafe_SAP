package com.studycafe.controller;

import com.studycafe.model.dto.Board;
import com.studycafe.model.dto.BoardMember;
import com.studycafe.model.dto.Page;
import com.studycafe.model.service.BoardService;
import com.studycafe.model.service.MemberService;
import com.studycafe.model.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

@Controller
public class HomeController {

	//2개 이상의 경로를 배열로 묶어서 매핑 가능
	//	@RequestMapping(value = "/", method = RequestMethod.GET)
	//	public String home1(Locale locale, Model model) {
	//		return "index"; // /WEB-INF/views/ + index + .jsp
	//	}

	//	@RequestMapping(value = "/home.action", method = RequestMethod.GET)
	//	public String home2(Locale locale, Model model) {
	//		return "index"; // /WEB-INF/views/ + index + .jsp
	//	}

	@Autowired
	@Qualifier("pageService")
	private PageService pageService;

	@Autowired
	@Qualifier("memberService")
	private MemberService memberService;

	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;
	
	@RequestMapping(value = { "/", "home.action" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		//1. boardList 가져와서 삭제
		List<Board> boards = boardService.selectBoardListByTime();  // 등록된 전체 리스트를 다 가져 오나 봄
		if(boards != null){											// 1.1 board 리스트가 있으면
			for(Board board : boards){								// Board item:BList 다 돌려서
				boardService.deleteBoard(board.getBoardNo());		// 전체 삭제

		//2. page 마감일 등록
				Page page = new Page();
				page.setCloseDate(board.getCloseDate());			// page 마감일 설정
				pageService.insertPage(page);						// page db에 등록

		//3. 모임 #로 모임멤버 찾기
				List<BoardMember> members =
						memberService.selectBoardMemberByBoardNo(board.getBoardNo());
				int pageNo = pageService.selectPageNo();
				if(members != null){								// 멤버가 있으면
					for(BoardMember member : members){				// DTO에 get해서 insert함
						pageService.insertMemberPageByMemberNo( member.getMemberNo(), pageNo, board.getTitle());
					}
				}
				pageService.insertPageMenuNotice(pageNo);
			}
		}
		// closedate - sysdate < 0 : board DTO status =0으로 설정
		// (board dto) status = 0인 board_no == (member_page) board_no가 존재하는지 확인
		// int memberPageCount = pageService.selectMemberPageCountByMemberNo(memberNo);

		// page_menu(공지사항, status 5), page_calendar(행하나 추가)
		// page_upload테이블의 새로운 행을 생성 ()
		// member_board 테이블에 등록시킴(board_no, member_no) <<참가 인원!
		
		return "index"; // /WEB-INF/views/ + index + .jsp
	}
	
}




