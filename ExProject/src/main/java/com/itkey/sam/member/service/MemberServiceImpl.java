package com.itkey.sam.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itkey.sam.login.dao.MemberDAO;
import com.itkey.sam.member.dto.MemberDTO;


@Service("memberService")
public class MemberServiceImpl implements MemberService {

	
	
	@Resource(name="memberDAO") MemberDAO dao;
	
	public int userLogin(MemberDTO eDTO) throws Exception {
		int result = (Integer)dao.checkMember(eDTO);
		return result;
	}

	public int checkPw(String user_pw) throws Exception {
		int result = (Integer)dao.checkPw(user_pw);
		return 0;
	}

}
