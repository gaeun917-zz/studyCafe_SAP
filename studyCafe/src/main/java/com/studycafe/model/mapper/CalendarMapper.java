package com.studycafe.model.mapper;

import java.util.List;

import com.studycafe.model.dto.Calendar;

public interface CalendarMapper {

	void insertCalendar(Calendar calendar);
	
	List<Calendar> selectCalendarList(int memberpageno);
	
	Calendar selectCalendarByCalendarNo(int calendarNo);

	void deleteCalendar(int calendarNo);
	
	/*Calendar selectCalendarByUploadNo2(int calendar);
	Calendar selectCalendarByUploadNo3(int calendar);*/
	
	
}
