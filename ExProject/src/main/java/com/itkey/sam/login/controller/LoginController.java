package com.itkey.sam.login.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itkey.sam.login.service.MemberService;
import com.itkey.sam.member.dto.MemberDTO;

@Controller
public class LoginController {
	// Logback logger (package : org.slf4j.Logger & org.slf4j.LoggerFactory)
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// Dependency Injection With BoardService
	@Autowired MemberService memberservice;

	/**
	 * @param  requestParam 
	 * @Method Post
	 * @return ModelAndView
	 * @url    [default] http://localhost:8080/sam/main.do 
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/login")
	public String loginPage() throws Exception {
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginCheck(MemberDTO dto, Model model, HttpSession session, HttpServletResponse response) throws Exception {
		
		int result = MemberService.userLogin(dto);
	}

	
}
