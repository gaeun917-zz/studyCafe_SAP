package com.studycafe.model.dao;

import java.util.HashMap;
import java.util.List;

import com.studycafe.model.dto.Board;
import com.studycafe.model.dto.BoardComment;
import com.studycafe.model.dto.PageBoard;

public interface PageBoardDao {

	List<PageBoard> selectPageBoardList(int menuNo);
	List<PageBoard> selectPageBoardListAdmin(int menuNo);
	PageBoard selectPageBoardByBoardNo(int boardNo);
	int insertPageBoard(PageBoard board);
	PageBoard selectPageBoardNoticeList(int pageNo);
	List<PageBoard> selectPageBoardListBySearch(String search);
	List<PageBoard> selectBoardList(int start, int last, int menuno);
	List<PageBoard> selectBoardList2(int start, int last, int menuno, String search);
	List<PageBoard> selectBoardListAdmin(int start, int last, int menuno);
	int getBoardCount(int menuno);
	void deleteBoard(int boardNo);
	void insertMemberPageByMemberNo(int pageNo, int memberNo, String name);
}