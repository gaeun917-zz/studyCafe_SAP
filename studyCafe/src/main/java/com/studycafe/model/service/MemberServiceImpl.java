package com.studycafe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.studycafe.model.dao.MemberDao;
import com.studycafe.model.dto.BoardMember;
import com.studycafe.model.dto.Member;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	@Qualifier("oracleMemberDao")
	private MemberDao memberDao;

	@Override
	public void registerMember(Member member) {
		memberDao.insert(member);
	}
	
	@Override
	public List<Member> getAllMembers() {
		return memberDao.getList();
	}

	@Override
	public Member searchMemberByMemberId(String id) {
		return memberDao.getMemberById(id);
	}

	@Override
	public Member login(String id, String passwd) {
		return memberDao.getMemberByIdAndPasswd(id, passwd);
	}

	@Override
	public Member getMemberByMemberNo(int memberNo) {
		// TODO Auto-generated method stub
		return memberDao.getMemberByMemberNo(memberNo);
	}

	@Override
	public void insertBoardMemberByBoardNoMemberNo(BoardMember boardMember) {
		memberDao.insertBoardMemberByBoardNoMemberNo(boardMember);
	}

	@Override
	public List<BoardMember> selectBoardMemberByBoardNo(int boardNo) {
		return memberDao.selectBoardMemberByBoardNo(boardNo);
	}

	@Override
	public int selectBoardMemberCountByBoardNo(int boardNo) {
		return memberDao.selectBoardMemberCountByBoardNo(boardNo);
	}


}
