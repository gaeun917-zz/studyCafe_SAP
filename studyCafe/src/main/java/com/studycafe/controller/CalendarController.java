package com.studycafe.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.studycafe.model.dao.CalendarDao;
import com.studycafe.model.dao.MemberDao;
import com.studycafe.model.dto.Calendar;
import com.studycafe.model.dto.Member;
import com.studycafe.model.dto.Page;
import com.studycafe.model.dto.PageMenu;
import com.studycafe.model.service.CalendarService;
import com.studycafe.model.service.PageService;
import com.studycafe.model.service.UploadService;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarController{

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	/*@Autowired
	@Qualifier(value = "oracleCalendarDao")
	private CalendarDao dao;
	public void setCalendarDao(CalendarDao calendarDao) {
		this.dao = calendarDao;
	}*/
	
	@Autowired
	@Qualifier("pageService")
	private PageService pageService;
	
	@Autowired
	@Qualifier("calendarService")
	private CalendarService calendarService;
		
	//2. 일정 달력에 전부 보여주기
	@RequestMapping(value = "list.action", method = RequestMethod.GET)
	public ModelAndView list(Model model, HttpSession session, int memberpageno) {
		
		ModelAndView mav = new ModelAndView();
		Member member = (Member) session.getAttribute("loginuser");

		List<Page> pages = pageService.searchPageNoByMemberNo(member.getMemberNo());
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		List<Calendar> calendars = calendarService.searchCalendarList(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		String json = "[";
		for (int i = 0; i < calendars.size(); i++) {
				Calendar c = calendars.get(i);
				System.out.println(c.getContent());
				System.out.println(c.getTitle());
				json += "{";
				json += "title : '" + c.getTitle() + "',";
				json += "url : 'localhost:8088/studyCafe/calendar/view.action?calendarno=" + c.getCalendarNo() + "&memberpageno=" + memberpageno + "&noticemenu=" + noticeMenu.getMenuNo() +"',";
				json += "start : '" + format.format(c.getStartDate()) + "'";
				if(c.getDueDate()!=null){//마감일 없을 시
					json += ", end : '" + format.format(c.getDueDate()) + "'";
				}
				json += "}";
								
				if (i < calendars.size() - 1) {
					json += ",";
				}
		}	
		json += "]";		
		
		mav.setViewName("calendar/list");
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("pages", pages);
		mav.addObject("calendars", calendars);
		mav.addObject("calendarJson", json);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);
		
		return mav;
//		return "calendar/list";
	}	
	//0. 일정 작성 폼
	@RequestMapping(value = "writeform.action", method = RequestMethod.GET)
	public ModelAndView getScheduleWriteForm(HttpSession session, int memberpageno) {
		
		ModelAndView mav = new ModelAndView();
		
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		
		mav.setViewName("calendar/writeform");
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);
		
		return mav;
//		return "calendar/writeform";
	}
	
	//1. 일정 등록하기
	@RequestMapping(value = "write.action", method = RequestMethod.POST)
	public ModelAndView calendar(Calendar calendar, HttpSession session, int memberpageno) {
	
		ModelAndView mav = new ModelAndView();
		
		
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		calendar.setPageNo(memberpageno);
		calendarService.registerCalendar(calendar);
		
		mav.setViewName("redirect:/calendar/list.action");
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);
		
		return mav;
//		return "redirect:/calendar/list.action";
	} 
	
	//3.일정 클릭 시 상세 보여주기
	@RequestMapping(value = "view.action", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("calendarno") int calendarNo, Model model, HttpSession session,
			int memberpageno) {
		
		ModelAndView mav = new ModelAndView();
		
		List<PageMenu> pageMenu = pageService.selectPageMenuByPageNo(memberpageno);
		PageMenu noticeMenu = pageService.selectPageMenuByPageNoNotice(memberpageno);
		
		Calendar calendar = calendarService.searchCalendarByCalendarNo(calendarNo);
//		model.addAttribute("calendar", calendar);
		
		int c = calendar.getCalendarNo();
//		model.addAttribute("c", c);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startDate2 = format.format(calendar.getStartDate());
		String dueDate2 = format.format(calendar.getDueDate());
		
		System.out.println(startDate2);
		System.out.println(dueDate2);
		
		mav.setViewName("calendar/view");
		mav.addObject("pagemenus", pageMenu);
		mav.addObject("calendar", calendar);
		
		mav.addObject("c", c);
		mav.addObject("memberpageno", memberpageno);
		mav.addObject("noticemenu", noticeMenu);
		mav.addObject("startDate2", startDate2);
		mav.addObject("dueDate2", dueDate2);
		
		return mav;
//		return "calendar/view";
	}
	@RequestMapping(value = "delete.action", method = RequestMethod.GET)
	public String delete(@RequestParam("calendarno") int calendarNo, int memberpageno) {
		//1. 요청 데이터 읽기 (글번호)
//		String uploadNo = req.getParameter("uploadno");
//		if (uploadNo == null || uploadNo.length() == 0) {
//			return "redirect:/upload/list.action";
//		}

		//2. 데이터 처리 (db에서 데이터 변경)
		calendarService.deleteCalendar(calendarNo);

		//3. 목록으로 이동
		return "redirect:/calendar/list.action?memberpageno=" + memberpageno;
	}
	
}
