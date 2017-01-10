package com.studycafe.model.dao;

import java.util.List;

import com.studycafe.model.dto.BoardMember;
import com.studycafe.model.dto.Member;

public interface MemberDao {

//OracleMemberDao의 function을 여기서 나열! 

	
	
	void insert(Member member);
	void update(Member member);

	List<Member> getList();

	Member getMemberById(String id);

	Member getMemberByIdAndPasswd(String id, String passwd);

	Member getMemberByEmail(String email);
	
	Member getMemberByMemberNo(int memberNo);

	void changePassword(Member member);
	void deleteByMemberNo(int memberNo);
	
	void insertBoardMemberByBoardNoMemberNo(BoardMember boardMember);
	List<BoardMember> selectBoardMemberByBoardNo(int boardNo);
	
	int selectBoardMemberCountByBoardNo(int boardNo);
	
	
}