package com.itkey.sam.member.service;

import com.itkey.sam.member.dto.MemberDTO;
import com.itkey.scam.file.dto.FileDTO;

public interface MemberService {
	
	public int checkid(String member_id)throws Exception;
	public int login(String member_id, String member_pw) throws Exception;
	public MemberDTO getMember(String member_id) throws Exception;
	public void insertMember(MemberDTO eDTO) throws Exception;	
	public int deleteMember(String member_id) throws Exception;
	public int idCheck(MemberDTO dto) throws Exception;
	public int insertProfile(FileDTO eDTO) throws Exception;
	
	

}
