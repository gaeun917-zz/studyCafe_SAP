package com.studycafe.model.service;

import com.studycafe.model.dao.SmallCategoryDao;
import com.studycafe.model.dto.SmallCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("smallCategoryService")
public class SmallCategoryImpl implements SmallCategoryService {
	

	@Autowired
	@Qualifier("oracleSmallCategoryDao")
	private SmallCategoryDao smallCategoryDao;

	@Override
	public SmallCategory selectSmallCategoryNameBySmallCategoryNo(int smallCategoryNo) {
		return smallCategoryDao.getSmallCategoryNameBySmallCategoryNo(smallCategoryNo);
	}


}
