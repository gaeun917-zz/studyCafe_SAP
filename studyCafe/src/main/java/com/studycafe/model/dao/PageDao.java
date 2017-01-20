package com.studycafe.model.dao;

import com.studycafe.model.dto.Page;
import com.studycafe.model.dto.PageImage;
import com.studycafe.model.dto.PageMenu;

import java.util.List;

public interface PageDao {

	List<Page> selectPageNoByMemberNo(int memberNo);
	int selectPageNo();

	PageMenu selectMemberPageByMenuNo(int menuno);
	void insertMemberPageByMemberNo(int memberNo, int pageNo, String name);
	int selectMemberPageCountByMemberNo(int memberNo);

	void insertPageMenuNotice(int pageNo);
	List<PageMenu> selectPageMenuByPageNo(int pageNo);
	PageMenu selectPageMenuByPageNoNotice(int pageNo);
	int insertPageMenuByAjax(PageMenu menu);

	void insertImageFile(PageImage pi);
	int insertPage(Page page);
}