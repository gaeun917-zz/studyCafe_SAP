package com.studycafe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.studycafe.model.dao.BoardDao;
import com.studycafe.model.dto.Board;
import com.studycafe.model.dto.BoardFile;
import com.studycafe.model.dto.SmallCategory;
import com.studycafe.model.dto.Upload;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired	
	@Qualifier("oracleBoardDao")
	private BoardDao boardDao;

	@Override
	public int insertBoard(Board board) {		
		return boardDao.insertBoard(board);
	}
	@Override
	public void registerBoardFile(BoardFile boardFile) {
		// TODO Auto-generated method stub
		boardDao.insertBoardFile(boardFile);
	}

	@Override
	public List<Board> selectBoardList() {
		
		return boardDao.selectBoardList();
	}

	@Override
	public Board selectBoardByBoardNo(int boardNo) {
		return boardDao.selectBoardByBoardNo(boardNo);
	}


	public Board selectByCategory(int smallCategoryNo) {
		return boardDao.selectByCategory(smallCategoryNo);
	}

	@Override
	public SmallCategory selectSmallCategoryByBoardNo(int sCNo) {
		return boardDao.selectSmallCategoryByBoardNo(sCNo);
	}

	@Override
	public Board selectBoardByBoardNo2(int boardNo) {
		return boardDao.selectBoardByBoardNo2(boardNo);
	}
	@Override
	public void deleteBoard(int boardNo) {
		boardDao.deleteBoard(boardNo);
	}

	@Override
	public List<Board> selectBoardListBySmallCategoryNo() {
		
		return boardDao.selectBoardListBySmallCategoryNo();
	}
	@Override
	public List<Board> selectBoardListByTime() {
		return boardDao.selectBoardListByTime();
	}
	@Override
	public int selectBoardCountByBoardNo(int boardNo) {
		return boardDao.selectBoardCountByBoardNo(boardNo);
	}
}
