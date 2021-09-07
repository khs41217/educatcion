package com.itkey.sam.member.service;

import com.itkey.sam.member.dto.MemberDTO;

public interface MemberService {
	
	public int userLogin(MemberDTO eDTO) throws Exception;
	
	public int checkPw(String user_pw) throws Exception;
	
	
	

}
