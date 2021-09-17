package com.itkey.sam.login.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itkey.sam.admin.dto.AdminDTO;
import com.itkey.sam.file.dto.FileDTO;
import com.itkey.sam.member.dto.MemberDTO;
import com.itkey.sam.member.service.MemberService;

@Controller
public class MemberController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired MemberService memberService;

	@RequestMapping(value = "login")	// 로그인 
	public String loginPage() throws Exception {
		return "login";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.GET)	//회원가입
	public String memberRegister() throws Exception{
		return "register";
	}
	
	@ResponseBody
	@RequestMapping(value = "register", method = RequestMethod.POST)	//회원가입 폼
	public void memberRegisterAction(MemberDTO eDTO, RedirectAttributes red, HttpServletResponse response, Model model, MultipartHttpServletRequest request) throws Exception{
		MultipartFile multi = request.getFile("file");
		String oldFileName = multi.getOriginalFilename();
		String changeFileName = oldFileName + UUID.randomUUID();
		String path = "C:\\Users\\USER\\git\\educatcion\\ExProject\\src\\main\\webapp\\resources\\resources";
		FileDTO fDTO = new FileDTO();
		fDTO.setFileOriginalName(oldFileName);
		fDTO.setFileChangedName(changeFileName);
		fDTO.setFilePath(path);
		
		String safeFile = path + System.currentTimeMillis() + oldFileName;
		try {
			multi.transferTo(new File(safeFile));
			//회원가입
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			int result = memberService.idCheck(eDTO);
			String body = "";

			Date today = new Date();
			eDTO.setBoardWriterJoinDate(today);
			
			if(result == 1) {
				body =	"<script>"
						+ "alert('중복된 아이디 입니다.');"
						+ "location.href='/sam/register';"
						+ "</script>";
				out.print(body);
			} else {
				memberService.insertProfile(fDTO);
				
				eDTO.setFileIdx(fDTO.getFileIdx());
				memberService.insertMember(eDTO);
				body =	"<script>"
						+ "alert('회원가입이 완료 되었습니다.');"
						+ "location.href='/sam/login';"
						+ "</script>";
				out.print(body);
			}
			
		}catch (IllegalStateException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//로그인
	@RequestMapping(value = "loginMember")
	public void loginCheck(@RequestParam String username, @RequestParam String pass, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int result = memberService.login(username, pass);
		/* -2 : 아이디 없음
		 * -1 : 서버오류
		 *  0 : 비밀번호 틀림
		 *  1 : 성공
		 */
		
		request.setAttribute("result",result);
		String body = "";
		if(result ==1 ) {
			body = "<script>"
					+ "location.href='/sam/main.do';"
					+ "</script>";
			out.print(body);
			session.setAttribute("user_id",username);

			} else if(result == 0) {
				body = "<script>"
						+ "alert('비밀번호를 확인해 주세요.');" +"location.href='/sam/login';"+ "</script>";
				out.print(body);
			} else if(result == 2) {
				body = "<script>" + "alert('아이디를 확인해 주세요.');" + "location.href='/sam/login';"+ "</script>";
				out.print(body);
				
			} else {
				body = "<script>" + "alert('서버오류 입니다 나중에 다시 시도해 주세요');" + "location.href='/sam/login';" + "</script>";
				out.print(body);
			}
		}
	//정보수정
	@RequestMapping(value="modify", method=RequestMethod.GET)
	public String modify() throws Exception{
		
		return "/modify";
	}
	
	//유저 정보 수정
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String updateModify(MemberDTO dto, HttpSession session, Model model) throws Exception{
		int result = memberService.updateInfo(dto);
		String state = "";
		if(result == 1) {
			state="login";
		} else {
			state="modify";
		}
		session.invalidate();
		return state;
	}
	
	//로그아웃
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/login";
	}
	
	//회원 탈퇴
	@RequestMapping(value = "/delUser", method = RequestMethod.GET)
	public String getDelete(HttpSession session) throws Exception{
		
		MemberDTO dto = new MemberDTO();
		String memberId = (String) session.getAttribute("user_id");
		
		dto.setBoardWriter(memberId);
		
		memberService.deleteMember(memberId);
		
		return "/login";
	}
	
	@RequestMapping(value= "/adminLogin", method = RequestMethod.GET)
	public String adminLogin() throws Exception{
		return "/adminLogin";
	}
	
	//관리자 로그인
	@RequestMapping(value= "/adminLogin", method = RequestMethod.POST)
	public void adminLoginAction(Model model, HttpServletRequest request, HttpServletResponse response, AdminDTO dto) throws Exception{
		response.setCharacterEncoding("text/html charset=\"UTF_8\"");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int result = 0;// 관리자 로그인 서비스
		
		String body="";
			
			if(result == 1) {
				body ="<script>"
						+"location.href='/sam/adminBoard'"
						+"</script>";
				out.print(body);
				session.setAttribute("user_id", dto);
			} 
		

				
		
	}
	
}
