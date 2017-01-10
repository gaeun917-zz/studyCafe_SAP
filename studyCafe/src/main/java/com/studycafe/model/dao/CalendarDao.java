package com.studycafe.model.dao;

import java.util.List;

import com.studycafe.model.dto.Calendar;


public interface CalendarDao {

	//1. 일정 작성하기
	void insertCalendar(Calendar calendar);
	
	//2. 달력에 전체 일정 표시하기
	List<Calendar> getCalendarList(int memberpageno);

	//3. 클릭시 상세 일정 보여주기
	Calendar getCalendarByCalendarNo(int calendarNo);

	//4. 삭제
	void deleteCalendar(int calendarNo);


}