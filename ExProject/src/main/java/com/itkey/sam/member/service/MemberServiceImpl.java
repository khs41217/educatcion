package com.itkey.sam.member.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itkey.sam.admin.dto.AdminDTO;
import com.itkey.sam.file.dto.FileDTO;
import com.itkey.sam.member.dao.MemberDAO;
import com.itkey.sam.member.dto.MemberDTO;
import com.itkey.sam.util.Criteria;


@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Resource(name="memberDAO") MemberDAO dao;

	public int checkid(String member_id) throws Exception {
		int result = dao.checkId(member_id);
		return result;
	}

	public int login(String member_id, String member_pw) throws Exception {
		int result = dao.login(member_id, member_pw);
		return result;
	}

	public MemberDTO getMember(String member_id) throws Exception {
		return dao.getMember(member_id);
	}

	public void insertMember(MemberDTO eDTO) throws Exception {
		dao.insertMember(eDTO);		
	}

	public int deleteMember(String member_id) throws Exception {
		int result = dao.deleteMember(member_id);
		return result;
	}
	
	public int idCheck(MemberDTO eDTO) throws Exception {
		int result = dao.idCheck(eDTO);
		return result;
	}

	
	public int insertProfile(FileDTO eDTO) throws Exception {
		return dao.insertProfile(eDTO);
	}
	
	public int updateInfo(MemberDTO dto) throws Exception{
		int result = dao.updateInfo(dto); 
		return result;
	}

	public int adminLogin(AdminDTO dto) throws Exception {
		int result = dao.adminLoginCheck(dto);
		return result;
	}

	@Override
	public void adminDelete(MemberDTO dto) throws Exception {
		dao.adminDelete(dto);
	}



}
