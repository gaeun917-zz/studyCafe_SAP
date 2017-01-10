package com.studycafe.model.service;

import java.sql.Timestamp;
import java.util.Date;

import com.studycafe.model.dao.MemberDao;
import com.studycafe.model.dto.AlreadyExistingMemberException;
import com.studycafe.model.dto.Member;
import com.studycafe.model.dto.RegisterRequest;

public class MemberRegisterService {
	private MemberDao memberDao;

	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void regist(RegisterRequest req) {
		Member member = memberDao.getMemberByEmail(req.getEmail());
		if (member != null) {
			throw new AlreadyExistingMemberException("dup email " + req.getEmail());
		}
		
		Member newMember = new Member(
				req.getEmail(), req.getPasswd(), req.getName(),
				new Timestamp(0));
		memberDao.insert(newMember);
	}
}
