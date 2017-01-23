package com.studycafe.model.mapper;

import com.studycafe.model.dto.Calendar;

import java.util.List;

public interface CalendarMapper {

	void insertCalendar(Calendar calendar);
	
	List<Calendar> selectCalendarList(int memberpageno);
	
	Calendar selectCalendarByCalendarNo(int calendarNo);

	void deleteCalendar(int calendarNo);

	
}
