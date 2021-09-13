package com.itkey.sam.board.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.itkey.sam.board.dto.BoardDTO;
import com.itkey.sam.board.model.service.BoardService;
import com.itkey.sam.file.dto.FileDTO;

@Controller
public class BoardController {
	// Logback logger (package : org.slf4j.Logger & org.slf4j.LoggerFactory)
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// Dependency Injection With BoardService
	@Autowired BoardService boardService;

	/**
	 * @param  requestParam 
	 * @Method Post
	 * @return ModelAndView
	 * @url    [default] http://localhost:8080/sam/main.do 
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/main")
	public ModelAndView sample(@RequestParam Map<String, Object> requestParam, Model model) throws Exception {
		// Logger
		logger.debug("Board List Page Response");
		
		ModelAndView mv = new ModelAndView("main");
		BoardDTO eDTO = new BoardDTO();
		
		String row = "0";
		String offset = "10";
		Map<String, Object> info = new HashMap<String, Object>();
		
		eDTO.setRow(row);
		eDTO.setOffset(offset);
		
		logger.debug("* [CONTROLLER] Input ◀ (Service) : countTotalContent");
		info.put("totalContent", boardService.countTotalContent());	//전체 게시물 수
		logger.debug("* [CONTROLLER] Input ◀ (Service) : countTotalMember");
		info.put("totalMember", boardService.countTotalMember());	//전체 회원 수
		logger.debug("* [CONTROLLER] Input ◀ (Service) : todayContent" );
		info.put("todayContent", boardService.todayContent());	// 당일 게시글 수
		logger.debug("* [CONTROLLER] Input ◀ (Service) : todayMember" );
		info.put("todayMember",boardService.todayMember());	// 당일 가입회원 수
		mv.addObject("info", info);
	
		logger.debug("* [CONTROLLER] Input ◀ (Service) : " + eDTO.toString());
		List<BoardDTO> oList = boardService.getBoardList(eDTO);
		
		mv.addObject("list", oList);
		return mv;
	}
	
	@RequestMapping(value="write", method= RequestMethod.GET)	// 글쓰기
	public ModelAndView wirter(HttpSession session, Model model) throws Exception{
		String userid = (String)session.getAttribute("user_id");
		model.addAttribute("user_id", userid);
		ModelAndView mv = new ModelAndView("write");
		return mv;
	}
	
	
	
	@RequestMapping(value="writeAction", method=RequestMethod.POST)	// 새 게시글 작성
	public String addWrite(BoardDTO eDTO, HttpSession session, MultipartHttpServletRequest multi, Model model) throws Exception	{
		System.out.println(eDTO.getBoardContents());
		//파일 업로드
		MultipartFile mf = multi.getFile("file");
		
		String oldFileName = mf.getOriginalFilename();
		
		String changeFileName= oldFileName;
		
		String path = "C:\\Users\\USER\\git\\educatcion\\ExProject\\src\\main\\webapp\\resources\\resources";
		
		FileDTO fDTO = new FileDTO();
		
		fDTO.setFileOriginalName(oldFileName);
		fDTO.setFileChangedName(changeFileName);
		fDTO.setFilePath(path);
		
		String safeFile = path + System.currentTimeMillis() + oldFileName;
		
		try {
			boardService.addFile(fDTO);
			mf.transferTo(new File(safeFile));
			
			Date today = new Date();
			eDTO.setBoardWriteDate(today);
			model.addAttribute("list", eDTO);
			eDTO.setFileIdx(eDTO.getFileIdx());
			boardService.addBoard(eDTO);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		boardService.findFileIdx(fDTO);
		
		return "main";
	}
}
