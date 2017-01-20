package com.studycafe.model.dao;

import com.studycafe.model.dto.PageBoard;

import java.util.List;

public interface PageBoardDao {

	List<PageBoard> selectPageBoardList(int menuNo);
	List<PageBoard> selectPageBoardListAdmin(int menuNo);
	int insertPageBoard(PageBoard board);

	List<PageBoard> selectBoardList(int start, int last, int menuno);
	List<PageBoard> selectBoardList2(int start, int last, int menuno, String search);
	List<PageBoard> selectBoardListAdmin(int start, int last, int menuno);

	PageBoard selectPageBoardByBoardNo(int boardNo);
	PageBoard selectPageBoardNoticeList(int pageNo);
	List<PageBoard> selectPageBoardListBySearch(String search);

	int getBoardCount(int menuno);

	void insertMemberPageByMemberNo(int pageNo, int memberNo, String name);
	void deleteBoard(int boardNo);
}