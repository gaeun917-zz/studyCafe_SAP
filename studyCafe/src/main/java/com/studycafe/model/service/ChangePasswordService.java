package com.studycafe.model.service;

import org.springframework.transaction.annotation.Transactional;

import com.studycafe.model.dao.MemberDao;
import com.studycafe.model.dto.Member;
import com.studycafe.model.dto.MemberNotFoundException;

public class ChangePasswordService {

	private MemberDao memberDao;

	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.getMemberByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();
		
		member.changePassword(oldPwd, newPwd);
		
		memberDao.update(member);
	}

}
