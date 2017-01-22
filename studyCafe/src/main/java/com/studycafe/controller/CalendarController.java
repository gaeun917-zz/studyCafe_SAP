package com.studycafe.controller;

import com.studycafe.model.dto.Calendar;
import com.studycafe.model.dto.Member;
import com.studycafe.model.dto.Page;
import com.studycafe.model.dto.PageMenu;
import com.studycafe.model.service.CalendarService;
import com.studycafe.model.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarController{

	@InitBinder // Date Form과 관련 있어 보임...
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@Autowired
	@Qualifier("pageService")
	private PageService pageService;
	
	@Autowired
	@Qualifier("calendarService")
	private CalendarService calendarService;
		
	//1. 일정 달력에 전부 보여주기
	@RequestMapping(value = "list.action", method = RequestMethod.GET)
	public ModelAndView list(Model model, HttpSession session, int memberpageno) {
		ModelAndView mav = new ModelAndView();

		//1. loginuser로부터 pageNo, pageMenu, pageMenuByPageNotice, CalendarList 구하기
		Member member = (Member) session.getAttribute("loginuser");

		List<Page> pages = pageService.searchPageNoByMemberNo(member.getMemberNo());
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		List<Calendar> calendars = calendarService.searchCalendarList(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		//2. Calendar 정보 구하기- JSON형식으로
		String json = "[";
			for(Calendar c:calendars) {
				//c = calendars.get(i);
				json += "{";
					json += "title : '" + c.getTitle() + "',";
					json += "url : 'localhost:8088/studyCafe/calendar/view.action?" +
							"calendarno=" + c.getCalendarNo() +
							"&memberpageno=" + memberpageno +
							"&noticemenu=" + noticeMenu.getMenuNo() +"',";
					json += "start : '" + format.format(c.getStartDate()) + "'"; // 시작일

					if(c.getDueDate()!=null){
					json += ", end : '" + format.format(c.getDueDate()) + "'"; // 마감일(마감일 없으면 정보 보내지 않음)
					}
				json += "}";
			}
		json += "]"; // JSON END

		//3. mav에 add&set
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("pages", pages);
		mav.addObject("calendars", calendars);
		mav.addObject("calendarJson", json);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);

		mav.setViewName("calendar/list");
		return mav;
	}


	//2. 일정 작성 폼
	@RequestMapping(value = "writeform.action", method = RequestMethod.GET)
	public ModelAndView getScheduleWriteForm(int memberpageno) {
		ModelAndView mav = new ModelAndView();

		// 1. pageMenu, pageMenuByPageNotice 구하기
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);

		//2. mav add&set
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);

		mav.setViewName("calendar/writeform");
		return mav;
	}


	//3. 일정 등록하기
	@RequestMapping(value = "write.action", method = RequestMethod.POST)
	public ModelAndView calendar(Calendar calendar, int memberpageno) {
		ModelAndView mav = new ModelAndView();

		// 1. pageMenu, pageMenuByPageNotice구하기
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);

		//2. calendar 등록
		calendar.setPageNo(memberpageno);
		calendarService.registerCalendar(calendar);

		//3. mav add&set
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);

		mav.setViewName("redirect:/calendar/list.action");
		return mav;
	}


	//4.일정 클릭 시 상세 보여주기
	@RequestMapping(value = "view.action", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("calendarno")int calendarNo, int memberpageno) {
		ModelAndView mav = new ModelAndView();

		//1. pageMenu, noticeMenu 구하기
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);

		//2. Calendar, Calendarno 구하기
		Calendar calendar = calendarService.searchCalendarByCalendarNo(calendarNo);
		int calendarno = calendar.getCalendarNo();

		//2. Calendar start & due date 구하기
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startDate2 = format.format(calendar.getStartDate());
		String dueDate2 = format.format(calendar.getDueDate());

		//3.mav add & set
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("noticemenu", noticeMenu);
		mav.addObject("calendar", calendar);
		mav.addObject("calendarno", calendarno);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("startDate2", startDate2);
		mav.addObject("dueDate2", dueDate2);

		mav.setViewName("calendar/view");
		return mav;
	}

	//5. 삭제
	@RequestMapping(value = "delete.action", method = RequestMethod.GET)
	public String delete(@RequestParam("calendarno") int calendarNo, int memberpageno) {

		//2. 데이터 처리 (db에서 데이터 변경)
		calendarService.deleteCalendar(calendarNo);

		//3. 목록으로 이동
		return "redirect:/calendar/list.action?memberpageno=" + memberpageno;
	}
	
}
