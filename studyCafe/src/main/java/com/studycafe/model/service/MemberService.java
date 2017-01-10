package com.studycafe.model.service;

import java.util.List;

import com.studycafe.model.dto.BoardMember;
import com.studycafe.model.dto.Member;

public interface MemberService {

	void registerMember(Member member);
	
	List<Member> getAllMembers();
	Member searchMemberByMemberId(String id);
	
	Member login(String id, String passwd);
	
	Member getMemberByMemberNo(int memberNo);
	
	void insertBoardMemberByBoardNoMemberNo(BoardMember boardMember);
	List<BoardMember> selectBoardMemberByBoardNo(int boardNo);
	int selectBoardMemberCountByBoardNo(int boardNo);
}
