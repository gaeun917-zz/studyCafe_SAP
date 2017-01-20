package com.studycafe.model.dao;

import com.studycafe.model.dto.BoardMember;
import com.studycafe.model.dto.Member;
import com.studycafe.model.mapper.MemberMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "oracleMemberDao")
public class OracleMemberDao implements MemberDao {


	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Autowired
	@Qualifier("memberMapper")
	private MemberMapper memberMapper;


	public void insert(Member member) {		
		memberMapper.insertMember(member);
	}
	
	
	public void update(Member member) {
		Map<String, Member> map = new HashMap<>();
		map.put(member.getEmail(), member);
	}
	
	
	public List<Member> getList() {
		List<Member> members = memberMapper.selectMembers();		
		return members;
	}

	
	public Member getMemberById(String id) {
		Member member = memberMapper.selectMemberById(id);
		return member;
	}
	
	
	public Member getMemberByEmail(String email) {	
		Member member = memberMapper.selectMemberByEmail(email);
		return member;
	}
	
	
	
	public Member getMemberByIdAndPasswd(String id, String passwd) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("memberId", id);
		map.put("passwd", passwd);
		
		Member member = memberMapper.selectMemberByIdAndPasswd(map);
		return member;
	}
	

	@Override
	public Member getMemberByMemberNo(int memberNo) {
		return memberMapper.selectMemberByMemberNo(memberNo);
	}


	public void changePassword(Member member) {
		memberMapper.changePassword(member);
		
	}


	@Override
	public void deleteByMemberNo(int memberNo) {
		memberMapper.deleteByMemberNo(memberNo);
		
	}

	@Override
	public void insertBoardMemberByBoardNoMemberNo(BoardMember boardMember) {
		memberMapper.insertBoardMemberByBoardNoMemberNo(boardMember);
	}


	@Override
	public List<BoardMember> selectBoardMemberByBoardNo(int boardNo) {
		return memberMapper.selectBoardMemberByBoardNo(boardNo);
	}


	@Override
	public int selectBoardMemberCountByBoardNo(int boardNo) {
		return memberMapper.selectBoardMemberCountByBoardNo(boardNo);
	}

	
	

}









