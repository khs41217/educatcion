package com.itkey.sam.member.service;

import java.util.List;

import com.itkey.sam.admin.dto.AdminDTO;
import com.itkey.sam.file.dto.FileDTO;
import com.itkey.sam.member.dto.MemberDTO;
import com.itkey.sam.util.Criteria;

public interface MemberService {
	
	public int checkid(String member_id)throws Exception;
	public int login(String member_id, String member_pw) throws Exception;
	public MemberDTO getMember(String member_id) throws Exception;
	public void insertMember(MemberDTO eDTO) throws Exception;	
	public int deleteMember(String member_id) throws Exception;
	public int idCheck(MemberDTO dto) throws Exception;
	public int insertProfile(FileDTO eDTO) throws Exception;
	
	public int updateInfo(MemberDTO dto) throws Exception;
	public int adminLogin(AdminDTO dto) throws Exception;
	
	public void adminDelete(MemberDTO dto) throws Exception;
	

}
