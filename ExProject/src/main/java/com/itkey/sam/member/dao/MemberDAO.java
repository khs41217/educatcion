package com.itkey.sam.member.dao;

import com.itkey.sam.file.dto.FileDTO;
import com.itkey.sam.member.dto.MemberDTO;

public interface MemberDAO {
	
	int checkId(String member_id) throws Exception;	// 아이디 중복 확인
	int login(String member_id, String member_pw) throws Exception;	// 로그인 처리
	MemberDTO getMember(String member_id) throws Exception;
	void insertMember(MemberDTO eDTO) throws Exception;	// 회원가입
	int deleteMember(String member_id) throws Exception; // 회원삭제 
	int idCheck(MemberDTO eDTO) throws Exception;
	int insertProfile(FileDTO eDTO) throws Exception;
	
	
	
	
	
	
}
