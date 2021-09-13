package com.itkey.sam.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itkey.sam.file.dto.FileDTO;
import com.itkey.sam.member.dto.MemberDTO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired private SqlSessionTemplate sqlSession;

	public int checkId(String id) throws Exception {
		int result = sqlSession.selectOne("checkid", id);
		return result;
	}

	public int login(String member_id, String member_pw) throws Exception{	//-2 아이디 없음, -1 서버오류, 0 비밀번호틀림 , 1 성공
		String pw = null;
		try {
			pw = sqlSession.selectOne("login", member_id);
			
			if(pw!=null) {
				return pw.equals(member_pw)? 1 : 0;
			} else {
				return -2;
			}	
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public MemberDTO getMember(String id) throws Exception{
		MemberDTO mDTO = null;
		try {
			mDTO = (MemberDTO)sqlSession.selectOne("getMember", id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mDTO;
	}

	public void insertMember(MemberDTO eDTO) {
		sqlSession.insert("insertMember", eDTO);
	}

	public int deleteMember(String id) {
		int result = sqlSession.delete("deleteMember", id);
		return result;
	}

	public int idCheck(MemberDTO dto) throws Exception {
		return sqlSession.selectOne("checkid", dto);
	}

	public int insertProfile(FileDTO eDTO) throws Exception {
		return sqlSession.insert("insertProfile", eDTO);
	}



}
