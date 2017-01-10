package com.studycafe.controller;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.studycafe.common.Util;
import com.studycafe.model.dao.BoardDao;
import com.studycafe.model.dao.MemberDao;
import com.studycafe.model.dto.Board;
import com.studycafe.model.dto.BoardFile;
import com.studycafe.model.dto.BoardMember;
import com.studycafe.model.dto.Member;
import com.studycafe.model.dto.Page;
import com.studycafe.model.dto.PageMenu;
import com.studycafe.model.dto.SmallCategory;
import com.studycafe.model.dto.Upload;
import com.studycafe.model.dto.UploadFile;
import com.studycafe.model.service.BoardService;
import com.studycafe.model.service.MemberService;
import com.studycafe.model.service.SmallCategoryService;

@Controller
@RequestMapping(value = "/board")
public class BoardController implements ApplicationContextAware, BeanNameAware {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	private ApplicationContext context;
	private String beanName;
	@Override
	public void setBeanName(String arg0) {
		this.beanName = arg0;		
	}
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.context = arg0;
	}
	
	@Autowired
	@Qualifier("boardService")
	private BoardService boardService;

	@Autowired
	@Qualifier("memberService")
	private MemberService memberService;

	@Autowired
	@Qualifier("smallCategoryService")
	private SmallCategoryService smallCategoryService;

	@Autowired
	@Qualifier(value = "oracleBoardDao")
	BoardDao dao;

	@Autowired
	@Qualifier(value = "oracleMemberDao")
	MemberDao dao2;

	@Autowired
	@Qualifier("txManager")
	PlatformTransactionManager txManager;

	@Autowired
	@Qualifier("txTemplate")
	TransactionTemplate txTemplate;

	@RequestMapping(value = "write.action", method = RequestMethod.GET)
	public String getboardWriteForm() {

		return "board/writeform2";
	}

	/*@RequestMapping(value = "write.action", method = RequestMethod.POST)
	public String writeBoard(HttpServletRequest req, Date closeDate, Date startDate, int memberCount, int frequency) {
		// 1. 브라우저에 사용자가 입력한 데이터를 읽어서 변수에 저장 (요청 정보에서 데이터 읽기)
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String place = req.getParameter("place");
		String purpose = req.getParameter("purpose");
		String period = req.getParameter("period");
		String smallCategory = req.getParameter("smallCategory");
		Member member = (Member) req.getSession().getAttribute("loginuser");

		Board board = new Board();
		board.setTitle(title);
		board.setSmallCategoryNo(Integer.parseInt(smallCategory));
		board.setMemberNo(member.getMemberNo());
		board.setContent(content);
		board.setCloseDate(closeDate);
		board.setStartDate(startDate);
		board.setMemberCount(memberCount);
		board.setPlace(place);
		board.setPurpose(purpose);
		board.setPeriod(period);
		board.setFrequency(frequency);

		dao.insertBoard(board);

		return "redirect:/board/list.action";
	}*/
	@RequestMapping(value = "write.action", method = RequestMethod.POST)
		public ModelAndView Board(MultipartHttpServletRequest req, HttpSession session, Board board) {
			
			ModelAndView mav = new ModelAndView();
			Member member = (Member) session.getAttribute("loginuser");
			
			processBoard(req, board, member.getMemberNo());
			
			
			mav.setViewName("redirect:/board/list.action");
			
			return mav;
		}

	public void processBoard(MultipartHttpServletRequest req, Board board, int memberNo) {		
		
		String path = req.getRealPath("/resources/boardimage");//실제 파일을 저장할 경로
		try {
			ArrayList<BoardFile> files = new ArrayList<>();

			MultipartFile file = req.getFile("boardimage");
			int newBoardNo = boardService.insertBoard(board);//Upload insert
			BoardMember boardMember = new BoardMember();
			boardMember.setBoardNo(newBoardNo);
			boardMember.setMemberNo(memberNo);
			memberService.insertBoardMemberByBoardNoMemberNo(boardMember);
			if (file != null && file.getSize() > 0) {
			
				String fileName = file.getOriginalFilename();
				if (fileName.contains("\\")) {
					fileName = fileName.substring(
						fileName.lastIndexOf("\\") + 1);
				}
				
				String uniqueFileName = newBoardNo + ".jpg";

				file.transferTo(new File(path, uniqueFileName));
							
				BoardFile b = new BoardFile();						
				b.setSavedFileName(uniqueFileName);
				b.setUserFileName(fileName);
				files.add(b);
			}
			
			//int newBoardNo = boardService.insertBoard(board);//Upload insert
			
			for (BoardFile uf : files) {
				uf.setBoardNo(newBoardNo);
				boardService.registerBoardFile(uf);//UploadFile insert
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();	
		}
	}	
	
	@RequestMapping(value = "list.action", method = RequestMethod.GET)
	public String list(Model model) {
		

		List<Board> boards = dao.selectBoardList();
		ArrayList<Member> members = new ArrayList<>();
		ArrayList<SmallCategory> smallCategory = new ArrayList<>();

		for (Board b : boards) {
			Member member;// = new Member();
			member = memberService.getMemberByMemberNo(b.getMemberNo());
			SmallCategory category;
			category = smallCategoryService.selectSmallCategoryNameBySmallCategoryNo(b.getSmallCategoryNo());
			members.add(member);
		}
		
		model.addAttribute("boards", boards);
		model.addAttribute("members", members);
		model.addAttribute("smallCategory", smallCategory);
		return "board/list";
	}
	
	/* 여기는 인덱스에서 선택한 카테고리를 보여줍니다!! 2016-06-17 */
	@RequestMapping(value = "list2.action", method = RequestMethod.GET)
	public String list2(Model model, int bigCategory) {

		List<Board> boards = dao.selectBoardList();
		ArrayList<Member> members = new ArrayList<>();
		ArrayList<SmallCategory> smallCategory = new ArrayList<>();

		for (Board b : boards) {
			Member member;// = new Member();
			member = memberService.getMemberByMemberNo(b.getMemberNo());
			SmallCategory category;
			category = smallCategoryService.selectSmallCategoryNameBySmallCategoryNo(b.getSmallCategoryNo());
			members.add(member);
		}
		model.addAttribute("bigCategory", bigCategory);
		model.addAttribute("boards", boards);
		model.addAttribute("members", members);
		model.addAttribute("smallCategory", smallCategory);
		return "board/list";
	}

	@RequestMapping(value = "listbycategory.action", method = RequestMethod.GET)
	public String listByCategory(Model model) {

		List<Board> boards = dao.selectBoardListBySmallCategoryNo();
		ArrayList<Member> members = new ArrayList<>();
		ArrayList<SmallCategory> smallCategory = new ArrayList<>();

		for (Board b : boards) {
			Member member;// = new Member();
			member = memberService.getMemberByMemberNo(b.getMemberNo());
			SmallCategory category;
			category = smallCategoryService.selectSmallCategoryNameBySmallCategoryNo(b.getSmallCategoryNo());
			members.add(member);
		}

		model.addAttribute("boards", boards);
		model.addAttribute("members", members);
		model.addAttribute("smallCategory", smallCategory);
		return "board/listbycategory";
	}
	
	

	@RequestMapping(value = "detail.action", method = RequestMethod.GET)
	public ModelAndView showBoardByBoardNo(HttpServletRequest request, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		Member member = (Member)session.getAttribute("loginuser");
		String boardNo = request.getParameter("boardno");
		if (boardNo == null || boardNo.length() == 0) {
			mav.setViewName("redirect:/board/list.action");
			return mav;
		}
		int no = Integer.parseInt(boardNo);

		// 데이터베이스에서 데이터 조회
		Board board = dao.selectBoardByBoardNo(no);
		if (board == null) {
			mav.setViewName("redirect:/board/list.action");
			return mav;
		}
		
		List<BoardMember> boardMembers = memberService.selectBoardMemberByBoardNo(board.getBoardNo());
		if(boardMembers != null){
			for(BoardMember boardMember : boardMembers){
				if(boardMember == null || boardMember.getMemberNo() == member.getMemberNo()){
					mav.addObject("swich", "full");
				}
			}
		}
		int memberCount = memberService.selectBoardMemberCountByBoardNo(board.getBoardNo());
		
		// SmallCategory smallCategory =
		// dao.selectSmallCategoryByBoardNo(board.getSmallCategoryNo());

		// Member member = dao2.getMemberByMemberNo(board.getMemberNo());

		// 조회된 데이터를 jsp 처리할 수 있도록 request 객체에 저장
		mav.setViewName("board/detail2");
		mav.addObject("board", board);
		mav.addObject("membercount", memberCount);
		// mav.addObject("smallCategory", smallCategory);
		// mav.addObject("member", member);

		return mav;

	}

	@RequestMapping(value = "edit.action", method = RequestMethod.GET)
	public ModelAndView showBoardEditForm(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		String boardNo = request.getParameter("boardno");
		if (boardNo == null || boardNo.length() == 0) {
			mav.setViewName("redirect:/board/list.action");
			return mav;
		}
		
		Board board = dao.selectBoardByBoardNo2(Integer.parseInt(boardNo));

		if (board == null) {
			mav.setViewName("redirect:/board/list.action");
			return mav;
		}

		/*
		 * String pageNo = "1"; if (request.getParameter("pageno") != null) {
		 * pageNo = request.getParameter("pageno"); }
		 */

		mav.addObject("board", board);
		mav.setViewName("board/editform");
		return mav;
	}

	@RequestMapping(value = "delete.action", method = RequestMethod.GET)
	public String deleteBoard(@RequestParam("boardno") int boardNo) {

		// //1. 요청 데이터 읽기 (글번호)
		// String boardNo = req.getParameter("boardno");
		// if (boardNo == null || boardNo.length() == 0) {
		// return "redirect:/board/list.action";
		// }

		// 2. 데이터 처리 (db에서 데이터 변경)
		boardService.deleteBoard(boardNo);
		Board board = boardService.selectBoardByBoardNo(boardNo);
		Date dDate = java.sql.Date.valueOf("2016-05-22");
		board.setCloseDate(dDate);

		// 3. 목록으로 이동
		return "redirect:/board/list.action";
	}
	
	@RequestMapping(value = "enjoy.action", method = RequestMethod.GET)
	@ResponseBody
	public String enjoy(HttpSession session, HttpServletRequest request, int boardno) {

		Member member = (Member)session.getAttribute("loginuser");
		String html="";
		BoardMember boardMember = new BoardMember();
		
		boardMember.setMemberNo(member.getMemberNo());
//		String boardNoS = request.getParameter("boardno");
		boardMember.setBoardNo(boardno);
		//참가로직
		memberService.insertBoardMemberByBoardNoMemberNo(boardMember);
		int count = memberService.selectBoardMemberCountByBoardNo(boardno);
		
		html = String.valueOf(count);
		
		return html;
	}


}