package com.itkey.sam.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itkey.sam.member.dto.MemberDTO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired private SqlSessionTemplate sqlSession;

	public int checkMember(MemberDTO eDTO) throws Exception {
		int result = sqlSession.selectOne("checkMember", eDTO);
		return result;
	}

	public int checkPw(String user_pw) throws Exception {
		int result = sqlSession.selectOne("", user_pw);
		return result;
	}

}
