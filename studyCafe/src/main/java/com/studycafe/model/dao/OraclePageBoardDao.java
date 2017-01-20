package com.studycafe.model.dao;

import com.studycafe.model.dto.PageBoard;
import com.studycafe.model.mapper.PageBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("oraclePageBoardDao")
public class OraclePageBoardDao implements PageBoardDao {

	@Autowired
	@Qualifier("PageBoardMapper")
	private PageBoardMapper pageBoardMapper;


	public List<PageBoard> selectPageBoardList(int menuNo) {
		List<PageBoard> pageBoardList = pageBoardMapper.selectPageBoardList(menuNo);
		return pageBoardList;
	}

	@Override
	public PageBoard selectPageBoardByBoardNo(int boardNo) {
		PageBoard board = pageBoardMapper.selectPageBoardByBoardNo(boardNo);
		return board;
	}

	@Override
	public int insertPageBoard(PageBoard board) {
		pageBoardMapper.insertPageBoard(board);
		return board.getPBoardNo();
	}

	@Override
	public List<PageBoard> selectPageBoardListBySearch(String search) {
		return pageBoardMapper.selectPageBoardListBySearch(search);
	}	
	
	public List<PageBoard> selectBoardList(int start, int last, int menuNo) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("last", last);
		map.put("menuno", menuNo);
		
		List<PageBoard> boardList = pageBoardMapper.selectBoardList(map);
		return boardList;
	}
	
	public List<PageBoard> selectBoardList2(int start, int last, int menuNo, String search) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("last", last);
		map.put("menuno", menuNo);
		map.put("search", search);
		
		List<PageBoard> boardList = pageBoardMapper.selectBoardList2(map);
		return boardList;
	}
	
	public int getBoardCount(int menuNo) {
		int count = pageBoardMapper.selectBoardCount(menuNo);
		return count;
	}

	@Override
	public void deleteBoard(int boardNo) {
		pageBoardMapper.deleteBoard(boardNo);
	}

	@Override
	public List<PageBoard> selectPageBoardListAdmin(int menuNo) {
		return pageBoardMapper.selectPageBoardListAdmin(menuNo);
	}

	@Override
	public List<PageBoard> selectBoardListAdmin(int start, int last, int menuNo) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("last", last);
		map.put("menuno", menuNo);
		
		List<PageBoard> boardList =	pageBoardMapper.selectBoardListAdmin(map);
		return boardList;
	}

	@Override
	public PageBoard selectPageBoardNoticeList(int pageNo) {
		return pageBoardMapper.selectPageBoardNoticeList(pageNo);
	}

	@Override
	public void insertMemberPageByMemberNo(int pageNo, int memberNo, String name) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("pageNo", pageNo);
		map.put("memberNo", memberNo);
		map.put("name", name);
		
		pageBoardMapper.insertMemberPageByMemberNo(map);
	}
}