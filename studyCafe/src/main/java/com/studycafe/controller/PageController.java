package com.studycafe.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.studycafe.model.dto.Member;
import com.studycafe.model.dto.Page;
import com.studycafe.model.dto.PageBoard;
import com.studycafe.model.dto.PageImage;
import com.studycafe.model.dto.PageMenu;
import com.studycafe.model.service.MemberService;
import com.studycafe.model.service.PageBoardService;
import com.studycafe.model.service.PageService;
import com.studycafe.ui.ThePager3;

@Controller
@RequestMapping(value = "/page")
public class PageController implements ApplicationContextAware, BeanNameAware {

	// private MemberDao dao = new OracleMemberDao();
	// @Autowired
	// @Qualifier("oracleMemberDao")
	// private MemberDao dao;
	// public void setMemberDao(MemberDao memberDao) {
	// this.dao = memberDao;

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
	@Qualifier("pageBoardService")
	private PageBoardService pageBoardService;

	@Autowired
	@Qualifier("pageService")
	private PageService pageService;

	@Autowired
	@Qualifier("memberService")
	private MemberService memberService;

	@RequestMapping(value = "/board/list.action", method = RequestMethod.GET)
	public ModelAndView pageBoardList(int memberpageno ,int menuno, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member member = (Member) session.getAttribute("loginuser");
		String swich = member.getStatus();
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		//공지사항 board를 조회
		//조건이 필요
		// List<PageBoard> boards = pageBoardService.getAllPageBoards(menuno);
		ArrayList<Member> members = new ArrayList<>();
		int currentPage = 1;
		int pageSize = 10;
		int dataCount = 0;
		int pagerSize = 5;
		String url = "list.action";
		String page = request.getParameter("pageno");
		if (page != null && page.length() > 0 && !page.equals("null")) {
			currentPage = Integer.parseInt(page);
		}
		int startRow = (currentPage - 1) * pageSize + 1;

		String queryString = request.getQueryString();

		if (queryString == null) {
			queryString = "myp=test";
		} else if (queryString.length() == 0) {
			queryString += "myp=test";
		} else {
			queryString = queryString.replace("&myp=test", "");
			queryString = queryString.replace("&&", "&");
			queryString += "&myp=test";
		}
		List<PageBoard> boards = pageBoardService.getBoardList(startRow, startRow + pageSize, menuno);
		if (boards != null) {
			for (PageBoard b : boards) {
				Member m = new Member();
				m = memberService.getMemberByMemberNo(b.getMemberNo());
				members.add(m);
			}
		}
		PageMenu menu = pageService.selectMemberPageByMenuNo(menuno);
		dataCount = pageBoardService.getBoardCount(menuno);
		pagerSize = dataCount / pageSize;
		if(dataCount >= 1 && pagerSize >= 1){
		ThePager3 pager = new ThePager3(dataCount, currentPage, pageSize, pagerSize, url, queryString);
		mav.addObject("pager", pager);
		}
		
		mav.setViewName("page/board/list");
		mav.addObject("boards", boards);
		mav.addObject("members", members);
		mav.addObject("menu", menu);
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);
		mav.addObject("swich", swich);
		
		// **수정할 부분
		/*String pageno = (String) request.getParameter("mmpageno");
		System.out.println(pageno);
		int mmpageno = Integer.parseInt(pageno);
		System.out.println(mmpageno);*/
		// List<PageImage> pageImge = pageService.searchImageNoByPageNo(mmpageno);

		return mav;
	}

	@RequestMapping(value = "/board/detail.action", method = RequestMethod.GET)
	public ModelAndView showPageBoardByBoardNo(HttpServletRequest request, int menuno, int memberpageno,HttpSession session) {
		ModelAndView mav = new ModelAndView();

		Member member = (Member) session.getAttribute("loginuser");

		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		PageMenu menu = pageService.selectMemberPageByMenuNo(menuno);
		// 요청 정보에서 내용을 표시할 글번호를 읽고 변수에 저장
		// (없으면 목록으로 이동)
		String boardNo = request.getParameter("boardno");
		if (boardNo == null || boardNo.length() == 0) {
			mav.setViewName("redirect:/page/board/list.action");
			return mav;
		}
		int no = Integer.parseInt(boardNo);

		// 데이터베이스에서 데이터 조회
		PageBoard board = pageBoardService.getPageBoardByBoardNo(no);

		// 조회 실패하면 목록으로 이동
		if (board == null) {
			mav.setViewName("redirect:/page/board/list.action");
			return mav;
		}
		member = memberService.getMemberByMemberNo(board.getMemberNo());

		String pageNo = "1";
		if (request.getParameter("pageno") != null) {
			pageNo = request.getParameter("pageno");
		}

		// 조회된 데이터를 jsp 처리할 수 있도록 request 객체에 저장
		mav.setViewName("page/board/detail");
		mav.addObject("board", board);
		mav.addObject("pageno", pageNo);
		mav.addObject("member", member);
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);
		mav.addObject("menu", menu);

		return mav;

	}
	
	@RequestMapping(value = "/board/writeform.action", method = RequestMethod.GET)
	public ModelAndView getWriteForm(int menuno, HttpSession session, int memberpageno) {
		ModelAndView mav = new ModelAndView();
		Member member = (Member) session.getAttribute("loginuser");
		
		PageMenu menu = pageService.selectMemberPageByMenuNo(menuno);

		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		
		mav.setViewName("page/board/writeform");
		mav.addObject("menu", menu);
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);
		return mav;
	}

	@RequestMapping(value = "/board/writeform.action", method = RequestMethod.POST)
	public ModelAndView setWriteForm(HttpServletRequest req, HttpSession session, int memberpageno) {
		// 1. 브라우저에 사용자가 입력한 데이터를 읽어서 변수에 저장 (요청 정보에서 데이터 읽기)
		ModelAndView mav = new ModelAndView();

		Member member = (Member) req.getSession().getAttribute("loginuser");

		List<Page> pages = pageService.searchPageNoByMemberNo(member.getMemberNo());
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String menuno = req.getParameter("menuno");

		PageBoard board = new PageBoard();
		board.setTitle(title);
		board.setContent(content);
		board.setMemberNo(member.getMemberNo());
		board.setMenuNo(Integer.parseInt(menuno));
		pageBoardService.insertPageBoard(board);
		// dao.insertBoard(board);

		mav.setViewName("redirect:/page/board/list.action?menuno=" + menuno);
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);

		return mav;
		// return "redirect:/page/board/list.action?menuno=" + menuno;
	}

	@RequestMapping(value = "/board/search.action", method = RequestMethod.GET)
	public ModelAndView search(HttpSession session, HttpServletRequest req, int pageno, int memberpageno) {
		// 1. 브라우저에 사용자가 입력한 데이터를 읽어서 변수에 저장 (요청 정보에서 데이터 읽기)
		ModelAndView mav = new ModelAndView();
		String search = req.getParameter("search");
		String menunoS = req.getParameter("menuno");
		// List<PageBoard> boards =
		// pageBoardService.getAllPageBoardsBySearch(search);
		int menuno = Integer.parseInt(menunoS);
		Member member = (Member)session.getAttribute("loginuser");
		
		PageMenu menu = pageService.selectMemberPageByMenuNo((menuno));
		ArrayList<Member> members = new ArrayList<>();
		int currentPage = 1;
		int pageSize = 10;
		int dataCount = 0;
		int pagerSize = 5;
		String url = "list.action?menu=" + menu;

		String queryString = req.getQueryString();

		if (queryString == null) {
			queryString = "myp=test";
		} else if (queryString.length() == 0) {
			queryString += "myp=test";
		} else {
			queryString = queryString.replace("&myp=test", "");
			queryString = queryString.replace("&&", "&");
			queryString += "&myp=test";
		}
		int startRow = (currentPage - 1) * pageSize + 1;
		// 데이터베이스에서 데이터 조회
		List<PageBoard> boards = pageBoardService.getBoardList2(startRow, startRow + pageSize, menuno, search);
		if (boards != null) {
			for (PageBoard b : boards) {
				Member m = new Member();
				m = memberService.getMemberByMemberNo(b.getMemberNo());
				members.add(m);
			}
		}
		
		dataCount = pageBoardService.getBoardCount(menuno);
		pagerSize = dataCount/pageSize;
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		

		mav.setViewName("page/board/list");
		mav.addObject("members", members);
		mav.addObject("boards", boards);
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("menu", menu);
		mav.addObject("noticemenu", noticeMenu);
		mav.addObject("memberpageno", memberpageno);

		return mav;
	}

	@RequestMapping(value = "/board/delete.action", method = RequestMethod.GET)
	public String delete(int boardno, int menuno, int memberpageno) {

		pageBoardService.deleteBoard(boardno);

		return "redirect:/page/board/list.action?menuno=" + menuno + "&memberpageno=" + memberpageno;
	}

	@RequestMapping(value = "/image.action", method = RequestMethod.POST)
	public ModelAndView image(MultipartHttpServletRequest req, HttpSession session, int memberpageno, int menuno) {

		ModelAndView mav = new ModelAndView();
		Member member = (Member) session.getAttribute("loginuser");

		List<Page> pages = pageService.searchPageNoByMemberNo(member.getMemberNo());
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		
		processImage(req, memberpageno);

		mav.setViewName("redirect:/page/board/list.action?menuno=" + menuno + "&pageno=1");
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("pages", pages);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);

		return mav;
		// return "redirect:/upload/list.action";
	}

	private void processImage(MultipartHttpServletRequest req, int memberpageno) {

		String path = req.getRealPath("/resources/uploadimage");// 실제 파일을 저장할 경로
		try {

			ArrayList<PageImage> files = new ArrayList<>();
			
			MultipartFile file = req.getFile("image");
			if (file != null && file.getSize() > 0) {

				String fileName = file.getOriginalFilename();
				if (fileName.contains("\\")) {
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
				}

				String uniqueFileName = memberpageno + ".jpg";

				file.transferTo(new File(path, uniqueFileName));
				
				PageImage p = new PageImage();
				p.setSavedFileName(uniqueFileName);
				p.setUserFileName(fileName);
				files.add(p);
			}
			
			System.out.println(memberpageno);
			for (PageImage pi : files) {
				pi.setPageNo(memberpageno);
				pageService.registerImageFile(pi);//UploadFile insert
			}
			
		} catch (Exception ex) {
			// tx:advice 설정으로 구현한 경우 사용
			// TransactionAspectSupport
			// .currentTransactionStatus().setRollbackOnly();

			ex.printStackTrace();
			// 직접 구현한 advice를 사용한 경우 사용
			// throw new RuntimeException(ex);
		}
	}

	@RequestMapping(value = "/board/plus.action", method = RequestMethod.GET)
	public void plus(HttpServletRequest req, HttpServletResponse response, int memberpageno) throws IOException {

		PrintWriter writer = response.getWriter();

		PageMenu menu = new PageMenu();
		menu.setName(req.getParameter("name"));
		menu.setPageNo(memberpageno);

		int menuno = pageService.insertPageMenuByAjax(menu);

		writer.print(menuno);
	}

	/*
	 * @RequestMapping( value = "write.action", method = RequestMethod.POST)
	 * public String setWrite( MultipartHttpServletRequest req, Page page) {
	 * 
	 * //processUploadTx(req, upload); //processUploadTx2(req, upload);
	 * 
	 * //processUploadTx3(req, upload);
	 * ((PageController)context.getBean(beanName)).pageImage(req, page);
	 * 
	 * return "redirect:/page/page.action"; }
	 * 
	 * 
	 * private void pageImage(MultipartHttpServletRequest req, Page page) {
	 * 
	 * String path = req.getRealPath("/WEB-INF/upload");//실제 파일을 저장할 경로 try {
	 * ArrayList<PageImage> files = new ArrayList<>(); ||||||| .r59
	 * 
	 * 
	 * 
	 * /*@RequestMapping( value = "write.action", method = RequestMethod.POST)
	 * public String setWrite( MultipartHttpServletRequest req, Page page) {
	 * 
	 * //processUploadTx(req, upload); //processUploadTx2(req, upload);
	 * 
	 * //processUploadTx3(req, upload);
	 * ((PageController)context.getBean(beanName)).pageImage(req, page);
	 * 
	 * return "redirect:/page/page.action"; }
	 * 
	 * 
	 * private void pageImage(MultipartHttpServletRequest req, Page page) {
	 * 
	 * String path = req.getRealPath("/WEB-INF/upload");//실제 파일을 저장할 경로 try {
	 * ArrayList<PageImage> files = new ArrayList<>(); ======= >>>>>>> .r76
	 * 
	 * /*
	 * 
	 * @RequestMapping( value = "write.action", method = RequestMethod.POST)
	 * public String setWrite( MultipartHttpServletRequest req, Page page) {
	 * 
	 * //processUploadTx(req, upload); //processUploadTx2(req, upload);
	 * 
	 * //processUploadTx3(req, upload);
	 * ((PageController)context.getBean(beanName)).pageImage(req, page);
	 * 
	 * return "redirect:/page/page.action"; }
	 * 
	 * 
	 * private void pageImage(MultipartHttpServletRequest req, Page page) {
	 * 
	 * String path = req.getRealPath("/WEB-INF/upload");//실제 파일을 저장할 경로 try {
	 * ArrayList<PageImage> files = new ArrayList<>();
	 * 
	 * MultipartFile file = req.getFile("attach"); if (file != null &&
	 * file.getSize() > 0) {
	 * 
	 * String fileName = file.getOriginalFilename(); if
	 * (fileName.contains("\\")) { fileName = fileName.substring(
	 * fileName.lastIndexOf("\\") + 1); }
	 * 
	 * String uniqueFileName = Util.getUniqueFileName(path, fileName);
	 * 
	 * file.transferTo(new File(path, uniqueFileName));
	 * 
	 * PageImage p = new PageImage(); p.setSavedFileName(uniqueFileName);
	 * p.setUserFileName(fileName); files.add(p); }
	 * 
	 * String pageno = req.getParameter("pageno");//Upload insert
	 * 
	 * // int x = 10 / 0;
	 * 
	 * for (PageImage pi : files) { pi.setPageNo(Integer.parseInt(pageno));
	 * pageService.insertImage(pi);//UploadFile insert }
	 * 
	 * } catch (Exception ex) { //tx:advice 설정으로 구현한 경우 사용 //
	 * TransactionAspectSupport //
	 * .currentTransactionStatus().setRollbackOnly();
	 * 
	 * ex.printStackTrace(); //직접 구현한 advice를 사용한 경우 사용 //throw new
	 * RuntimeException(ex); } }
	 */

}
