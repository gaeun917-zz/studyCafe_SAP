package com.studycafe.model.dao;

import com.studycafe.model.dto.Board;
import com.studycafe.model.dto.BoardFile;
import com.studycafe.model.dto.SmallCategory;

import java.util.List;

public interface BoardDao {

	int insertBoard(Board board);
	void insertBoardFile(BoardFile file);

	List<Board> selectBoardList();
	List<Board> selectBoardListBySmallCategoryNo();
	List<Board> selectBoardListByTime();

	Board selectBoardByBoardNo(int boardNo);
	Board selectBoardByBoardNo2(int boardNo);
	Board selectByCategory(int categoryBoardNo);
	Board searchBoardByBoardNo(int boardNo);

	SmallCategory selectSmallCategoryByBoardNo(int smallCategoryNo);

	int selectBoardCountByBoardNo(int boardNo);
	void deleteBoard(int boardNo);
}