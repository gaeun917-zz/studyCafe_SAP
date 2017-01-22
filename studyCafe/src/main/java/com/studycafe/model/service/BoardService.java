package com.studycafe.model.service;

import com.studycafe.model.dto.Board;
import com.studycafe.model.dto.BoardFile;
import com.studycafe.model.dto.SmallCategory;

import java.util.List;

public interface BoardService {

	int insertBoard(Board board);
	void registerBoardFile(BoardFile boardFile);
	
	List<Board> selectBoardList();
	Board selectBoardByBoardNo(int boardNo);
	Board selectBoardByBoardNo2(int boardNo);

	Board selectByCategory(int smallCategoryNo);
	SmallCategory selectSmallCategoryByBoardNo(int sCNo);
	List<Board> selectBoardListBySmallCategoryNo();
	void deleteBoard(int boardNo);
	
	List<Board> selectBoardListByTime();
	int selectBoardCountByBoardNo(int boardNo);
		
}
