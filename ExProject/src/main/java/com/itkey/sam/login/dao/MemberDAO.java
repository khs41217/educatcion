package com.itkey.sam.login.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.itkey.sam.member.dto.MemberDTO;

public interface MemberDAO {
	
	public int checkMember(MemberDTO eDTO) throws Exception;
	
	
}
