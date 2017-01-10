package com.studycafe.model.service;

import java.util.List;

import com.studycafe.model.dto.Member;
import com.studycafe.model.dto.Page;
import com.studycafe.model.dto.PageBoard;

public interface PageBoardService {

	List<PageBoard> getAllPageBoards(int menuNo);
	List<PageBoard> getAllPageBoardsAdmin(int menuNo);
	
	PageBoard getPageBoardByBoardNo(int boardNo);
	int insertPageBoard(PageBoard board);
	PageBoard selectPageBoardNoticeList(int pageNo);
	List<PageBoard> getAllPageBoardsBySearch(String search);
	List<PageBoard> getBoardList(int start, int last, int menuno);
	List<PageBoard> getBoardList2(int start, int last, int menuno, String search);
	List<PageBoard> selectBoardListAdmin(int start, int last, int menuno);
	int getBoardCount(int menuno);
	void deleteBoard(int boardNo);
	void insertMemberPageByMemberNo(int pageNo, int memberNo, String name);
}
