package com.studycafe.controller;

import com.studycafe.model.dto.*;
import com.studycafe.model.service.MemberService;
import com.studycafe.model.service.PageBoardService;
import com.studycafe.model.service.PageService;
import com.studycafe.ui.ThePager3;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/page")
public class PageController implements ApplicationContextAware, BeanNameAware {


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
		// 1. member구하기
		Member member = (Member) session.getAttribute("loginuser");
		String swich = member.getStatus();

		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		//공지사항 board를 조회
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
				Member m =  memberService.getMemberByMemberNo(b.getMemberNo());
				members.add(m);
			}
		}

		PageMenu menu = pageService.selectMemberPageByMenuNo(menuno);
		dataCount = pageBoardService.getBoardCount(menuno);
		pagerSize = dataCount / pageSize;


		ModelAndView mav = new ModelAndView("page/board/list");
		if(dataCount >= 1 && pagerSize >= 1){
			ThePager3 pager = new ThePager3(dataCount, currentPage, pageSize,
											pagerSize, url, queryString);
			mav.addObject("pager", pager);
		}
		
		mav.addObject("boards", boards);
		mav.addObject("members", members);
		mav.addObject("menu", menu);
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);
		mav.addObject("swich", swich);
		
		// **수정할 부분
		/* String pageno = (String) request.getParameter("mmpageno");
		System.out.println(pageno);
		int mmpageno = Integer.parseInt(pageno);
		System.out.println(mmpageno);*/
		// List<PageImage> pageImge = pageService.searchImageNoByPageNo(mmpageno);

		return mav;
	}


	@RequestMapping(value = "/board/detail.action", method = RequestMethod.GET)
	public ModelAndView showPageBoardByBoardNo(HttpServletRequest request,
											   int menuno, int memberpageno) {
		ModelAndView mav = new ModelAndView();

		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		PageMenu menu = pageService.selectMemberPageByMenuNo(menuno);

		// 1.request에서 boardNo 구하기
		String boardNo = request.getParameter("boardno");
		int pBoardNo = Integer.parseInt(boardNo);
		if (boardNo == null || boardNo.length() == 0) {				//1.0 boardNo null
			mav.setViewName("redirect:/page/board/list.action");	//목록으로
			return mav;
		}

		// 2. (page)Board 구하기
		PageBoard board = pageBoardService.getPageBoardByBoardNo(pBoardNo);
		if (board == null) {										//2.0 조회 data null
			mav.setViewName("redirect:/page/board/list.action");	//목록으로
			return mav;
		}

		//3. (board의 member#로) member 구하기
		Member member = memberService.getMemberByMemberNo(board.getMemberNo());

		// 4. pageNo구하기
		String pageNo = "1";
		if (request.getParameter("pageno") != null) {			//3.1 member
			pageNo = request.getParameter("pageno");
		}

		mav.setViewName("page/board/detail");
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("noticemenu", noticeMenu);
		mav.addObject("menu", menu);
		mav.addObject("board", board);
		mav.addObject("member", member);
		mav.addObject("pageno", pageNo);
		mav.addObject("memberpageno", memberpageno);

		return mav;

	}
	
	@RequestMapping(value = "/board/writeform.action", method = RequestMethod.GET)
	public ModelAndView getWriteForm(int menuno, int memberpageno) {

		PageMenu menu = pageService.selectMemberPageByMenuNo(menuno);
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);

		ModelAndView mav = new ModelAndView("page/board/writeform");
		mav.addObject("menu", menu);
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);
		return mav;
	}

	@RequestMapping(value = "/board/writeform.action", method = RequestMethod.POST)
	public ModelAndView setWriteForm(HttpServletRequest req, HttpSession session, int memberpageno) {

		Member member = (Member) session.getAttribute("loginuser");

		// 1. req로 <form>의 input을 가져오기
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String menuno = req.getParameter("menuno");
		int pMenuNo = Integer.parseInt(menuno);

		//2. pageBoard에 set
		PageBoard board = new PageBoard();
				  board.setTitle(title);
				  board.setContent(content);
		          board.setMenuNo(pMenuNo);
		          board.setMemberNo(member.getMemberNo());

		//3. DB에 pageBoard insert
		pageBoardService.insertPageBoard(board); // db insert: return 값없어도됨, 실행하고 끝

		//4. DB에서 pagemenu, noticemenu 가져오기
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);

		//5. mav
		ModelAndView mav = new ModelAndView("redirect:/page/board/list.action?menuno=" + menuno);
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("memberpageno", memberpageno); // parameter로 받음
		mav.addObject("noticemenu", noticeMenu);

		return mav;
	}


	@RequestMapping(value = "/board/search.action", method = RequestMethod.GET)
	public ModelAndView search(HttpSession session, HttpServletRequest req, int pageno, int memberpageno) {

		String search = req.getParameter("search");
		String menuno1 = req.getParameter("menuno");
		int menuno = Integer.parseInt(menuno1);
		int currentPage = 1;
		int pageSize = 10;
		int startRow = (currentPage - 1) * pageSize + 1;

		// 1. boards 구하기
		List<PageBoard> boards = pageBoardService.getBoardList2(startRow, startRow + pageSize, menuno, search);


		// List<PageBoard> boards =
		// pageBoardService.getAllPageBoardsBySearch(search);
		// Member member = (Member)session.getAttribute("loginuser");
//		int dataCount = 0;
//		int pagerSize = 5;
//		String url = "list.action?menu=" + menu;

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



		// 2. members 구하기
		ArrayList<Member> members = new ArrayList<>();
		if (boards != null) {
			for (PageBoard b : boards) {
				Member m = memberService.getMemberByMemberNo(b.getMemberNo());
				members.add(m);
			}
		}
		
		//3. pageMenu 구하기
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		//4. menu 구하기
		PageMenu menu = pageService.selectMemberPageByMenuNo(menuno);
		//5. noticeMenu 구하기
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);

		ModelAndView mav = new ModelAndView("page/board/list");
		             mav.addObject("boards", boards);
					 mav.addObject("members", members);
					 mav.addObject("pagemenus", pageMenu);
					 mav.addObject("menu", menu);
					 mav.addObject("noticemenu", noticeMenu);
					 mav.addObject("memberpageno", memberpageno);

		return mav;
	}


	@RequestMapping(value = "/board/delete.action", method = RequestMethod.GET)
	public String delete(int boardno, int menuno, int memberpageno) {
		pageBoardService.deleteBoard(boardno);
		return "redirect:/page/board/list.action?" +
				"menuno=" + menuno +
				"&memberpageno=" + memberpageno;
	}


	@RequestMapping(value = "/image.action", method = RequestMethod.POST)
	public ModelAndView image(MultipartHttpServletRequest req, HttpSession session, int memberpageno, int menuno) {

		Member member = (Member) session.getAttribute("loginuser");

		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		List<Page> pages = pageService.searchPageNoByMemberNo(member.getMemberNo());
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		
		processImage(req, memberpageno);// 아래에 method()

		ModelAndView mav = new ModelAndView("redirect:/page/board/list.action?menuno=" + menuno + "&pageno=1");
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("pages", pages);
		mav.addObject("noticemenu", noticeMenu);
		mav.addObject("memberpageno", memberpageno);

		return mav;
	}

	private void processImage(MultipartHttpServletRequest req, int memberpageno) {

		try {
				ArrayList<PageImage> files = new ArrayList<>();
				// 1. file 가져오기
				MultipartFile file = req.getFile("image");

				// 2. file namimg
				if (file != null && file.getSize() > 0) {
					String fileName = file.getOriginalFilename();

					if (fileName.contains("\\")) {
						fileName.substring(fileName.lastIndexOf("\\") + 1);
					}else {

						//3. 실제 파일을 저장할 경로, unique name
						String path = req.getRealPath("/resources/uploadimage");
						String uniqueFileName = memberpageno + ".jpg";
						file.transferTo(new File(path, uniqueFileName));

						PageImage p = new PageImage();
								  p.setSavedFileName(uniqueFileName);
								  p.setUserFileName(fileName);
						files.add(p);
					}
				}

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

}
