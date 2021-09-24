package com.itkey.sam.member.dao;

import java.util.List;

import com.itkey.sam.admin.dto.AdminDTO;
import com.itkey.sam.file.dto.FileDTO;
import com.itkey.sam.member.dto.MemberDTO;
import com.itkey.sam.util.Criteria;

public interface MemberDAO {
	
	public int checkId(String member_id) throws Exception;	// 아이디 중복 확인
	public int login(String member_id, String member_pw) throws Exception;	// 로그인 처리
	public MemberDTO getMember(String member_id) throws Exception;
	public void insertMember(MemberDTO eDTO) throws Exception;	// 회원가입
	public int deleteMember(String member_id) throws Exception; // 회원삭제 
	public int idCheck(MemberDTO eDTO) throws Exception;
	public int insertProfile(FileDTO eDTO) throws Exception;
	
	public int updateInfo(MemberDTO dto) throws Exception;
	
	public AdminDTO adminLogin(AdminDTO dto) throws Exception;
	public int adminLoginCheck(AdminDTO dto) throws Exception;
	
	public void adminDelete(MemberDTO dto) throws Exception;
	
	
	
	
	
	
}
