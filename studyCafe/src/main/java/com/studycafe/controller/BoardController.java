package com.studycafe.controller;

import com.studycafe.model.dto.*;
import com.studycafe.model.service.BoardService;
import com.studycafe.model.service.MemberService;
import com.studycafe.model.service.SmallCategoryService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/board")
public class BoardController implements ApplicationContextAware, BeanNameAware {

	private ApplicationContext context;
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.context = arg0;
	}

	private String beanName;
	@Override
	public void setBeanName(String arg0) {
		this.beanName = arg0;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat =
				new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(
				Date.class, new CustomDateEditor(dateFormat, false));
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
	public String writeBoard(HttpServletRequest req, Date closeDate,
								Date startDate, int memberCount, int frequency) {

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

	public void processBoard(MultipartHttpServletRequest req, Board board, int memberNo) {
		//1. 파일 get
		String path = req.getRealPath("/resources/boardimage");//실제 파일을 저장할 경로
		MultipartFile file = req.getFile("boardimage`");
		ArrayList<BoardFile> files = new ArrayList<>();

		try {
			//2. MemberNo, boardNo(모임no)구하기
			BoardMember boardMember = new BoardMember();
			int newBoardNo = boardService.insertBoard(board);	// upload image: insert하고 return으로 int 받음
			boardMember.setMemberNo(memberNo);
			boardMember.setBoardNo(newBoardNo);

			//3. memeber 모임에 파일 등록
			memberService.insertBoardMemberByBoardNoAndMemberNo(boardMember); // newBoardNo, memberNo 들어있음
			if (file != null && file.getSize() > 0) {							//3.1 파일 not null -> 파일 naming
				String fileName = file.getOriginalFilename();

			// 4. file naming
				if (fileName.contains("\\")) {									//4.0 파일 naming condition
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
				}
				String uniqueFileName = newBoardNo + ".jpg";					//4.1 파일 naming unique

			//5. file 전송
				file.transferTo(new File(path, uniqueFileName));
				BoardFile b = new BoardFile();
						  b.setSavedFileName(uniqueFileName);
						  b.setUserFileName(fileName);
				files.add(b);
			}

			for (BoardFile b : files) {
				b.setBoardNo(newBoardNo);
				boardService.registerBoardFile(b);			//UploadFile insert
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	@RequestMapping(value = "write.action", method = RequestMethod.POST)
	public ModelAndView Board(
				MultipartHttpServletRequest multiReq, HttpSession session, Board board) {

			//1. loginuser 구하기
			Member member = (Member) session.getAttribute("loginuser");

			//2. 위에 만든 method로 모임 보드에 파일 등록
			processBoard(multiReq, board, member.getMemberNo());

			//3.mav to 주소 선정: Board는 processBoard 수행하게하는 method()이고, 따로 data 수행 없음
			ModelAndView mav = new ModelAndView("redirect:/board/list.action");
			return mav;
		}

	
	@RequestMapping(value = "list.action", method = RequestMethod.GET)
	public String list(Model model) {

		List<Board> boards = boardService.selectBoardList();
		ArrayList<Member> members = new ArrayList<>(); // capacity 설정 안함. 지 맘대로 늘어남
		ArrayList<SmallCategory> smallCategory = new ArrayList<>();
		// for statement 안에서 처음 쓰이고, 밖에서도 쓰이기 때문에 global로 declare 해줘야함

		// 1. member, sC구하기
		for (Board b : boards) {
			Member member = memberService.getMemberByMemberNo(b.getMemberNo());
			SmallCategory category =
							smallCategoryService.selectSmallCNameBySmallCNo(b.getSmallCategoryNo());

			//2. member, sc에 구한값 add하기
			members.add(member);
			smallCategory.add(category);
		}

		//3. model에 전달
		model.addAttribute("boards", boards);
		model.addAttribute("members", members);
		model.addAttribute("smallCategory", smallCategory);
		return "board/list";
	}


	/* 여기는 인덱스에서 선택한 카테고리를 보여줍니다!! */
	@RequestMapping(value = "list2.action", method = RequestMethod.GET)
	public String list2(Model model, int bigCategory) {

		List<Board> boards = boardService.selectBoardList();
		ArrayList<Member> members = new ArrayList<>();
		ArrayList<SmallCategory> smallCategory = new ArrayList<>();

		//1. 해당 보드의 멤버구하기 (ArrayList로.. 멤버 여러명임)
		for (Board b : boards) {
			Member member = memberService.getMemberByMemberNo(b.getMemberNo());
			members.add(member);// ArrayList에 담음
		}

		//2. model에 memberinfo 전달
		model.addAttribute("bigCategory", bigCategory);
		model.addAttribute("boards", boards);
		model.addAttribute("members", members);
		model.addAttribute("smallCategory", smallCategory);
		return "board/list";
	}


	@RequestMapping(value = "listbycategory.action", method = RequestMethod.GET)
	public String listByCategory(Model model) {

		List<Board> boards = boardService.selectBoardListBySmallCategoryNo();
		ArrayList<SmallCategory> smallCategory = new ArrayList<>();		//model에 담을 sc 소포상자

		//1. 해당 보드의 카테고리 구하기 (ArrayList로.. 멤버 여러명임)
		for (Board b : boards) {
			SmallCategory category = smallCategoryService.selectSmallCNameBySmallCNo(b.getSmallCategoryNo());
			smallCategory.add(category); // arraylist에 카테고리 담기
		}

		model.addAttribute("boards", boards);
		model.addAttribute("smallCategory", smallCategory);
		return "board/listbycategory";
	}
	
	

	@RequestMapping(value = "detail.action", method = RequestMethod.GET)
	public ModelAndView showBoardByBoardNo(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView(); // mav를 가지고 각각의 if null에 대해서 다양한 경로로 보내버림

		// 1. boardNo 구하기(request)
		String boardNo = request.getParameter("boardno"); 			// boardNo
		int pBoardNo = Integer.parseInt(boardNo); 						// parsing

			if (boardNo == null || boardNo.length() == 0) {				// 1.0 boardNo null,
				mav.setViewName("redirect:/board/list.action");			//return list.action
				return mav;
			}else {														// 1.1 boardNo not null
		// 2 Board 구하기(boardService)
				Board board = boardService.selectBoardByBoardNo(pBoardNo);
				if (board == null) {                                     //2.0 if (selected) board null
					mav.setViewName("redirect:/board/list.action");      //return list.action
					return mav;
				}
		//3. 해당 Board에 속한 멤버 구하기
				Member member = (Member)session.getAttribute("loginuser"); 	// 현재 유저의
				// 해당 모임에 속한 멤버를(list<BoardMember>로 받음) 모임 구하기(BoardNo로 묶어놓음)
				List<BoardMember> boardMembers = memberService.selectBoardMemberByBoardNo(board.getBoardNo());

				if(boardMembers != null){										//3.1 Board에 가입한 멤버들 있으면
					for(BoardMember boardMember : boardMembers){
						if(boardMember.getMemberNo() == member.getMemberNo()){ // 3.1.1 현재 로그인 멤버가 보드의 멤버면
							mav.addObject("swich", "full");
						}
					}
				}

		// 4. 이 보드의 회원 수 구하기
				int memberCount = memberService.selectBoardMemberCountByBoardNo(board.getBoardNo());

				// 5. 조회된 데이터를 jsp 처리하도록 mav에 저장
				mav.addObject("board", board);
				mav.addObject("membercount", memberCount);
				mav.setViewName("board/detail2");
				return mav;
			}
	}

	@RequestMapping(value = "edit.action", method = RequestMethod.GET)
	public ModelAndView showBoardEditForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		//1. 보드넘버 구하기
		String boardNo = request.getParameter("boardno");
		int pBoardNo = Integer.parseInt(boardNo); 				// parsing

		if (boardNo == null || boardNo.length() == 0) {			//1.0 보드넘버 null
			// 여기서 mav에 .addObject()한 게 없으면, 그냥 return으로만 처리 할 수 있지 않나?
			// -> 위에 보면 return 값이 ModelAndView 이다. 무조건 여기 담아서 보내야 한다..이런..
			mav.setViewName("redirect:/board/list.action");
			return mav;

		}else {													//1.1 보드넘버 not null
		//2. 보드 구하기
			Board board = boardService.selectBoardByBoardNo2(pBoardNo);
			if (board == null) {								//2.0 보드 null
				mav.setViewName("redirect:/board/list.action"); // return list.action
				return mav;
			}else {												//2.1 보드 not null
																// mav.addObject(),.setViewName()
				mav.addObject("board", board);
				mav.setViewName("board/editform");
				return mav;
			}
		}
	}

	@RequestMapping(value = "delete.action", method = RequestMethod.GET)
	public String deleteBoard(@RequestParam("boardno") int boardNo) {
		// @requestParam이 <form>에서 현재 boardno를 파라미터로 받아옴
		// = req.getParameter('boardno')

		// 1. 데이터 처리 (db에서 데이터)
		Board board = boardService.selectBoardByBoardNo(boardNo);
		Date dDate = java.sql.Date.valueOf("2016-05-22"); 			// java.util이 아닌 sql
		board.setCloseDate(dDate);

		// 3. 목록으로 이동
		return "redirect:/board/list.action";
	}



	@RequestMapping(value = "enjoy.action", method = RequestMethod.GET)
	@ResponseBody
	public String enjoy(HttpSession session, HttpServletRequest request, int boardno) {

		//1. loginuser와 해당 모임의 멤버 구하기
		Member member = (Member)session.getAttribute("loginuser");
		//String boardNoS = request.getParameter("boardno");

		BoardMember boardMember = new BoardMember();
					boardMember.setMemberNo(member.getMemberNo());
					boardMember.setBoardNo(boardno);

		// 2. 모임에 참가
		memberService.insertBoardMemberByBoardNoAndMemberNo(boardMember);
		// 3. 모임 참가 인원수 +1
		int count = memberService.selectBoardMemberCountByBoardNo(boardno);

		String html= String.valueOf(count);
		return html;
	}
}