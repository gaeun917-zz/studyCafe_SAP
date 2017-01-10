package com.studycafe.model.mapper;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import com.studycafe.model.dto.Page;
import com.studycafe.model.dto.PageImage;
import com.studycafe.model.dto.PageMenu;

public interface PageMapper {
	
	List<PageMenu> selectPageMenuList(int pageNo);
	List<Page> selectPageNoByMemberNo(int memberNo);
	PageMenu selectMemberPageByMenuNo(int menuno);
	int insertPageMenuByAjax(PageMenu menu);
	PageMenu selectPageMenuByPageNoNotice(int pageNo);
	void insertImageFile(PageImage pi);
	int selectMemberPageCountByMemberNo(int memberNo);
	int insertPage(Page page);
	void insertMemberPageByMemberNo(HashMap<String, Object> map);
	void insertPageMenuNotice(int pageNo);
	int selectPageNo();
	

}
