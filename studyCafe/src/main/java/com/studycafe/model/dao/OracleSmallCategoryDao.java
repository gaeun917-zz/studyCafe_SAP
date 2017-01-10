package com.studycafe.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.studycafe.model.dto.SmallCategory;
import com.studycafe.model.mapper.BoardMapper;

@Repository("oracleSmallCategoryDao")
public class OracleSmallCategoryDao implements SmallCategoryDao {
	@Autowired
	@Qualifier("boardMapper")
	private BoardMapper boardMapper;

	@Override
	public SmallCategory getSmallCategoryNameBySmallCategoryNo(int smallCategoryNo) {

		return boardMapper.selectSmallCategoryNameBySmallCategoryNo(smallCategoryNo);
	}

	
//	public SmallCategory selectSmallCategoryByBoardNo(int smallCategoryNo) {
//		// TODO Auto-generated method stub
//		return boardMapper.selectSmallCategoryByBoardNo(smallCategoryNo);
//	}
	


}
