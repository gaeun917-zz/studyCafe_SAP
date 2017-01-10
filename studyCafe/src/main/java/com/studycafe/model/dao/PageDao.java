package com.studycafe.model.dao;

import java.sql.Date;
import java.util.List;

import com.studycafe.model.dto.Page;
import com.studycafe.model.dto.PageImage;
import com.studycafe.model.dto.PageMenu;

public interface PageDao {

	List<Page> selectPageNoByMemberNo(int memberNo);
	List<PageMenu> selectPageMenubyPageNo(int pageNo);
	PageMenu selectMemberPageByMenuNo(int menuno);
	int insertPageMenuByAjax(PageMenu menu);
	PageMenu selectPageMenuByPageNoNotice(int pageNo);
	void insertImageFile(PageImage pi);
	int selectMemberPageCountByMemberNo(int memberNo);
	int insertPage(Page page);
	void insertMemberPageByMemberNo(int memberNo, int pageNo, String name);
	void insertPageMenuNotice(int pageNo);
	int selectPageNo();
}