package com.studycafe.model.dao;

import java.util.List;

import com.studycafe.model.dto.Board;
import com.studycafe.model.dto.BoardComment;
import com.studycafe.model.dto.BoardFile;
import com.studycafe.model.dto.SmallCategory;

public interface BoardDao {

	int insertBoard(Board board);
	void insertBoardFile(BoardFile file);

	List<Board> selectBoardList();

	Board selectBoardByBoardNo(int boardNo);
	Board selectBoardByBoardNo2(int boardNo);
	
	Board selectByCategory(int categoryBoardNo);
	Board searchBoardByBoardNo(int boardNo);
	SmallCategory selectSmallCategoryByBoardNo(int smallCategoryNo);

	List<Board> selectBoardListBySmallCategoryNo();
	void deleteBoard(int boardNo);
	
	List<Board> selectBoardListByTime();
	int selectBoardCountByBoardNo(int boardNo);

}