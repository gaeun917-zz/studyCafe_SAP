package com.studycafe.model.mapper;


import java.util.List;
import com.studycafe.model.dto.Board;
import com.studycafe.model.dto.BoardFile;
import com.studycafe.model.dto.SmallCategory;

public interface BoardMapper {
	
	void insertBoard(Board board);
	void insertBoardFile(BoardFile boardfile);
	
	List<Board> selectBoardList();
	Board selectBoardByBoardNo(int boardNo);
	SmallCategory selectSmallCategoryNameBySmallCategoryNo(int smallCategoryNo);
	Board selectByCategory(int smallCategoryNo);
	SmallCategory selectSmallCategoryByBoardNo(int sCNo);
	Board selectBoardByBoardNo2(int boardNo);
	List<Board> selectBoardListBySmallCategoryNo();
	void deleteBoard(int boardNo);
	int getBoardNo();
	List<Board> selectBoardListByTime();
	int selectBoardCountByBoardNo(int boardNo);
	
	
}
