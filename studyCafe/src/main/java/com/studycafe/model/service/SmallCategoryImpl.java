package com.studycafe.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.studycafe.model.dao.SmallCategoryDao;
import com.studycafe.model.dao.MemberDao;
import com.studycafe.model.dto.SmallCategory;

@Service("smallCategoryService")
public class SmallCategoryImpl implements SmallCategoryService {
	

	@Autowired
	@Qualifier("oracleSmallCategoryDao")
	private SmallCategoryDao smallCategoryDao;

	@Override
	public SmallCategory selectSmallCategoryNameBySmallCategoryNo(int smallCategoryNo) {
		// TODO Auto-generated method stub
		return smallCategoryDao.getSmallCategoryNameBySmallCategoryNo(smallCategoryNo);
	}


}
