package com.studycafe.model.service;

import java.util.List;

import com.studycafe.model.dto.Calendar;

public interface CalendarService {

	//1. 일정 작성하기
	void registerCalendar(Calendar calendar);
	
	//2. 달력에 전체 일정 표시하기
	List<Calendar> searchCalendarList(int memberpageno);
	
	//3. 클릭시 상세 일정 보여주기
	Calendar searchCalendarByCalendarNo(int calendarNo);
	
	void deleteCalendar(int calendarNo);
	
}
