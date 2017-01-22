package com.studycafe.model.service;

import com.studycafe.model.dto.BoardMember;
import com.studycafe.model.dto.Member;

import java.util.List;

public interface MemberService {

	void insertMember(Member member);
	void update(Member member);

	List<Member> getList();

	Member searchMemberByMemberId(String id);
	Member getMemberByEmail(String email);
	Member login(String id, String passwd);
	Member getMemberByMemberNo(int memberNo);

	void changePassword(Member member);
	void deleteByMemberNo(int memberNo);

	//board 관련
	void insertBoardMemberByBoardNoAndMemberNo(BoardMember boardMember);
	List<BoardMember> selectBoardMemberByBoardNo(int boardNo);
	int selectBoardMemberCountByBoardNo(int boardNo);
}
