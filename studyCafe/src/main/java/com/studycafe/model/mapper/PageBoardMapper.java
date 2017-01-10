package com.studycafe.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.studycafe.model.dto.PageBoard;

public interface PageBoardMapper {
	
	List<PageBoard> selectPageBoardList(int menuNo);
	List<PageBoard> selectPageBoardListAdmin(int menuNo);
	PageBoard selectPageBoardByBoardNo(int boardNo);
	int insertPageBoard(PageBoard board);
	PageBoard selectPageBoardNoticeList(int pageNo);
	List<PageBoard> selectPageBoardListBySearch(String search);
	List<PageBoard> selectBoardList(HashMap<String, Object> map);
	List<PageBoard> selectBoardList2(HashMap<String, Object> map);
	List<PageBoard> selectBoardListAdmin(HashMap<String, Object> map);
	
	int selectBoardCount(int menuno);
	void deleteBoard(int boardNo);
	void insertMemberPageByMemberNo(HashMap<String, Object> map);
}
