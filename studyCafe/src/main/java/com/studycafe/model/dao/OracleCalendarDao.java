package com.studycafe.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.studycafe.model.dto.Calendar;
import com.studycafe.model.dto.Upload;
import com.studycafe.model.dto.UploadFile;
import com.studycafe.model.mapper.CalendarMapper;
import com.studycafe.model.mapper.UploadMapper;

@Repository(value = "oracleCalendarDao")
public class OracleCalendarDao implements CalendarDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	@Qualifier("calendarMapper")
	private CalendarMapper calendarMapper;

	@Override
	public void insertCalendar(Calendar calendar) {
		// TODO Auto-generated method stub
		calendarMapper.insertCalendar(calendar);
		/*return calendar.getCalendarNo();*/
	}

	@Override
	public List<Calendar> getCalendarList(int memberpageno) {
		// TODO Auto-generated method stub
		List<Calendar> calendars = calendarMapper.selectCalendarList(memberpageno);
		return calendars;
	}

	@Override
	public Calendar getCalendarByCalendarNo(int calendarNo) {
		// TODO Auto-generated method stub
		Calendar calendar = calendarMapper.selectCalendarByCalendarNo(calendarNo);
		return calendar;
	}
	
	@Override
	public void deleteCalendar(int calendarNo) {
		// TODO Auto-generated method stub
		calendarMapper.deleteCalendar(calendarNo);
	}
	

}
