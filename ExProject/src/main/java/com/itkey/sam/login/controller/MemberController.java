package com.itkey.sam.login.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(value = "register")	//회원가입
	public String memberRegister() throws Exception{
		return "register";
	}
	
	@ResponseBody
	@RequestMapping(value = "membersignup")	//회원가입 폼
	public void memberRegisterAction(MemberDTO eDTO, RedirectAttributes red, HttpServletResponse response, Model model, MultipartHttpServletRequest request) throws Exception{
		MultipartFile multi = request.getFile("file");
		String oldFileName = multi.getOriginalFilename();
		String changeFileName = oldFileName;
		String path = "C:\\Users\\USER\\git\\educatcion\\ExProject\\src\\main\\webapp\\resources";
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
	
}
