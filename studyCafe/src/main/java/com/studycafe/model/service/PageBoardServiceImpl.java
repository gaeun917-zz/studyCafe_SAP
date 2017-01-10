package com.studycafe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.studycafe.model.dao.MemberDao;
import com.studycafe.model.dao.PageBoardDao;
import com.studycafe.model.dto.Member;
import com.studycafe.model.dto.PageBoard;

@Service("pageBoardService")
public class PageBoardServiceImpl implements PageBoardService {

	@Autowired
	@Qualifier("oraclePageBoardDao")
	private PageBoardDao pageBoardDao;

	@Override
	public List<PageBoard> getAllPageBoards(int menuNo) {
		return pageBoardDao.selectPageBoardList(menuNo);
	}

	@Override
	public PageBoard getPageBoardByBoardNo(int boardNo) {
		return pageBoardDao.selectPageBoardByBoardNo(boardNo);
	}

	@Override
	public int insertPageBoard(PageBoard board) {
		return pageBoardDao.insertPageBoard(board);
	}

	@Override
	public List<PageBoard> getAllPageBoardsBySearch(String search) {
		return pageBoardDao.selectPageBoardListBySearch(search);
	}

	@Override
	public List<PageBoard> getBoardList(int start, int last, int menuno) {
		return pageBoardDao.selectBoardList(start, last, menuno);
	}
	
	@Override
	public List<PageBoard> getBoardList2(int start, int last, int menuno, String search) {
		return pageBoardDao.selectBoardList2(start, last, menuno, search);
	}


	@Override
	public int getBoardCount(int menuno) {
		return pageBoardDao.getBoardCount(menuno);
	}

	@Override
	public void deleteBoard(int boardNo) {
		pageBoardDao.deleteBoard(boardNo);
	}

	@Override
	public List<PageBoard> getAllPageBoardsAdmin(int menuNo) {
		return pageBoardDao.selectPageBoardListAdmin(menuNo);
	}

	@Override
	public List<PageBoard> selectBoardListAdmin(int start, int last, int menuno) {
		return pageBoardDao.selectBoardListAdmin(start, last, menuno);
	}

	@Override
	public PageBoard selectPageBoardNoticeList(int pageNo) {
		return pageBoardDao.selectPageBoardNoticeList(pageNo);
	}

	@Override
	public void insertMemberPageByMemberNo(int pageNo, int memberNo, String name) {
		pageBoardDao.insertMemberPageByMemberNo(pageNo, memberNo, name);
		
	}

}
