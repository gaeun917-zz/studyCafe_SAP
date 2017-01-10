package com.studycafe.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.studycafe.model.dto.BoardMember;
import com.studycafe.model.dto.Member;

public interface MemberMapper {

	
// MemberMapper.xml의 sql statement를 여기서 나열! 	
	
	
	void insertMember(Member member);
	List<Member> selectMembers();
	Member selectMemberById(String memberId);
	Member selectMemberByIdAndPasswd(HashMap<String, Object> params);
	Member selectMemberByEmail(String email);
	Member selectMemberByMemberNo(int memberNo);
	void changePassword(Member member);
	void deleteByMemberNo(int memberNo);
	
	void insertBoardMemberByBoardNoMemberNo(BoardMember boardMember);
	List<BoardMember> selectBoardMemberByBoardNo(int boardNo);
	int selectBoardMemberCountByBoardNo(int boardNo);
	
}



