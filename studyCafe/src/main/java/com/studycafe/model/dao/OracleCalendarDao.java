package com.studycafe.model.dao;

import com.studycafe.model.dto.Calendar;
import com.studycafe.model.mapper.CalendarMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
		calendarMapper.insertCalendar(calendar);
	}

	@Override
	public List<Calendar> getCalendarList(int memberpageno) {
		List<Calendar> calendars = calendarMapper.selectCalendarList(memberpageno);
		return calendars;
	}

	@Override
	public Calendar getCalendarByCalendarNo(int calendarNo) {
		Calendar calendar = calendarMapper.selectCalendarByCalendarNo(calendarNo);
		return calendar;
	}
	
	@Override
	public void deleteCalendar(int calendarNo) {
		calendarMapper.deleteCalendar(calendarNo);
	}
	

}
