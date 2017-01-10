package com.studycafe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.studycafe.model.dao.CalendarDao;
import com.studycafe.model.dao.UploadDao;
import com.studycafe.model.dto.Calendar;
import com.studycafe.model.dto.Upload;
import com.studycafe.model.dto.UploadFile;

@Service("calendarService")
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	@Qualifier("oracleCalendarDao")
	private CalendarDao dao;

	@Override
	public void registerCalendar(Calendar calendar) {
		// TODO Auto-generated method stub
		dao.insertCalendar(calendar);
		
	}

	@Override
	public List<Calendar> searchCalendarList(int memberpageno) {
		// TODO Auto-generated method stub
		return dao.getCalendarList(memberpageno);
	}

	@Override
	public Calendar searchCalendarByCalendarNo(int calendarNo) {
		// TODO Auto-generated method stub
		return dao.getCalendarByCalendarNo(calendarNo);
	}

	@Override
	public void deleteCalendar(int calendarNo) {
		// TODO Auto-generated method stub
		dao.deleteCalendar(calendarNo);
	}

}
