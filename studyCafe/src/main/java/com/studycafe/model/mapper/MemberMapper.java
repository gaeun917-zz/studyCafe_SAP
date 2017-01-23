package com.studycafe.model.mapper;

import com.studycafe.model.dto.BoardMember;
import com.studycafe.model.dto.Member;

import java.util.HashMap;
import java.util.List;

public interface MemberMapper {

	// MemberMapper.xml(sql)

	void insertMember(Member member);
	List<Member> selectMembers();
	Member selectMemberById(String memberId);
	Member selectMemberByIdAndPasswd(HashMap<String, Object> params);
	Member selectMemberByEmail(String email);
	Member selectMemberByMemberNo(int memberNo);
	void changePassword(Member member);
	void deleteByMemberNo(int memberNo);

	//board 관련
	void insertBoardMemberByBoardNoMemberNo(BoardMember boardMember);
	List<BoardMember> selectBoardMemberByBoardNo(int boardNo);
	int selectBoardMemberCountByBoardNo(int boardNo);
	
}



