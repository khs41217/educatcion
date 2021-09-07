package com.itkey.sam.login.service;

import org.springframework.stereotype.Service;

import com.itkey.sam.member.dto.MemberDTO;


@Service("memberService")
public class MemberServiceImpl implements MemberService {

	public int userLogin(MemberDTO eDTO) throws Exception {
		int result = (Integer)dao.selectMember(eDTO);
		return 0;
	}

}
