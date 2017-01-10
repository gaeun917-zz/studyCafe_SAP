package com.studycafe.model.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.studycafe.model.dto.Page;
import com.studycafe.model.dto.PageImage;
import com.studycafe.model.dto.PageMenu;
import com.studycafe.model.mapper.PageMapper;

@Repository("oraclePageDao")
public class OraclePageDao implements PageDao {

	@Autowired
	@Qualifier("PageMapper")
	private PageMapper pageMapper;

	@Override
	public List<Page> selectPageNoByMemberNo(int memberNo) {
		
		return pageMapper.selectPageNoByMemberNo(memberNo);
	}

	@Override
	public List<PageMenu> selectPageMenubyPageNo(int pageNo) {
		
		return pageMapper.selectPageMenuList(pageNo);
	}

	@Override
	public PageMenu selectMemberPageByMenuNo(int menuno) {
		return pageMapper.selectMemberPageByMenuNo(menuno);
	}

	@Override
	public int insertPageMenuByAjax(PageMenu menu) {
		
		return pageMapper.insertPageMenuByAjax(menu);
	}

	@Override
	public PageMenu selectPageMenuByPageNoNotice(int pageNo) {
		return pageMapper.selectPageMenuByPageNoNotice(pageNo);
	}

	@Override
	public void insertImageFile(PageImage pi) {
		pageMapper.insertImageFile(pi);
	}

	@Override
	public int selectMemberPageCountByMemberNo(int memberNo) {
		return pageMapper.selectMemberPageCountByMemberNo(memberNo);
	}

	@Override
	public int insertPage(Page page) {
		return pageMapper.insertPage(page);
	}

	@Override
	public void insertMemberPageByMemberNo(int memberNo, int pageNo, String name) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("memberNo", memberNo);
		map.put("pageNo", pageNo);
		map.put("name", name);
		
		pageMapper.insertMemberPageByMemberNo(map);
	}

	@Override
	public void insertPageMenuNotice(int pageNo) {
		pageMapper.insertPageMenuNotice(pageNo);
	}

	@Override
	public int selectPageNo() {
		return pageMapper.selectPageNo();
	}


}