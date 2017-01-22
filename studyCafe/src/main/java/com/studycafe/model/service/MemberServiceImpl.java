package com.studycafe.model.service;

import com.studycafe.model.dao.MemberDao;
import com.studycafe.model.dto.BoardMember;
import com.studycafe.model.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	@Qualifier("oracleMemberDao")
	private MemberDao memberDao;

	@Override
	public void insertMember(Member member) {
				memberDao.insert(member);
	}

	@Override
	public void update(Member member) {
				memberDao.update(member);
	}

	@Override
	public List<Member> getList() {
		return memberDao.getList();
	}

	@Override
	public Member searchMemberByMemberId(String id) {
		return memberDao.getMemberById(id);
	}

	@Override
	public Member getMemberByEmail(String email) {
		return memberDao.getMemberByEmail(email);
	}

	@Override
	public Member login(String id, String passwd) {
		return memberDao.getMemberByIdAndPasswd(id, passwd);
	}

	@Override
	public Member getMemberByMemberNo(int memberNo) {
		return memberDao.getMemberByMemberNo(memberNo);
	}

	@Override
	public void changePassword(Member member) {
				memberDao.changePassword(member);
	}

	@Override
	public void deleteByMemberNo(int memberNo) {
				memberDao.deleteByMemberNo(memberNo);
	}

	@Override
	public void insertBoardMemberByBoardNoAndMemberNo(BoardMember boardMember) {
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
