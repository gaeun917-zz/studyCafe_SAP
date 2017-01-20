package com.studycafe.model.dao;

import com.studycafe.model.dto.BoardMember;
import com.studycafe.model.dto.Member;

import java.util.List;

public interface MemberDao {

//OracleMemberDao에서 아래 method 구현함

	void insert(Member member);
	void update(Member member);

	List<Member> getList();

	Member getMemberById(String id);
	Member getMemberByEmail(String email);
	Member getMemberByIdAndPasswd(String id, String passwd);
	Member getMemberByMemberNo(int memberNo);

	void changePassword(Member member);
	void deleteByMemberNo(int memberNo);
	
	void insertBoardMemberByBoardNoMemberNo(BoardMember boardMember);
	List<BoardMember> selectBoardMemberByBoardNo(int boardNo);
	
	int selectBoardMemberCountByBoardNo(int boardNo);
}