package com.itkey.sam.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.itkey.sam.board.dto.BoardDTO;
import com.itkey.sam.board.model.service.BoardService;
import com.itkey.sam.file.dto.FileDTO;
import com.itkey.sam.member.dto.MemberDTO;
import com.itkey.sam.util.Criteria;
import com.itkey.sam.util.PageMaker;

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
	public ModelAndView sample(@RequestParam Map<String, Object> requestParam, Model model, Integer page, Criteria cri) throws Exception {
		// Logger
		logger.debug("Board List Page Response");
		
		//현재 페이지 게시물
		List<BoardDTO> boardList = boardService.pageList(cri);
		
		PageMaker pageMaker = new PageMaker(cri);
		int totalCount = boardService.getTotalCount(cri);
		
		pageMaker.setTotalCount(totalCount);
		System.out.println("끝 페이지" + pageMaker.getEndPage());
		System.out.println("시작 페이지" + pageMaker.getStartPage());
////////////////////////////////////////////////////////////////////////////////
		
		ModelAndView mv = new ModelAndView("main");

		BoardDTO eDTO = new BoardDTO();
		
		logger.debug("* [CONTROLLER] Input ◀ (Service) : countTotalMember");
		int totalMember = boardService.countTotalMember();	//전체 회원 수
		model.addAttribute("totalMember",totalMember);
		
		int totalContent =  boardService.countTotalContent();	//전체 게시물 수
		logger.debug("* [CONTROLLER] Input ◀ (Service) : countTotalContent");
		model.addAttribute("totalContent", totalContent);
		
		logger.debug("* [CONTROLLER] Input ◀ (Service) : todayMember" );
		int todayMember = boardService.todayMember();	// 당일 가입회원 수
		model.addAttribute("todayMember", todayMember);
		
		logger.debug("* [CONTROLLER] Input ◀ (Service) : todayContent" );
		int todayContent = boardService.todayContent();	// 당일 게시글 수
		model.addAttribute("todayContent", todayContent);
				
		int count = boardService.count();
		
		List<BoardDTO> detailList = boardService.getBoardList(eDTO);
		
		mv.addObject("detailList", detailList);
		mv.addObject("list", boardList);
		mv.addObject("pageNum", count);
		mv.addObject("pageMaker", pageMaker);
		
		return mv;
	}
	
	@RequestMapping(value="/write", method= RequestMethod.GET)	// 게시물 작성 페이지
	public ModelAndView wirter(HttpSession session, Model model) throws Exception{
		String userid = (String)session.getAttribute("user_id");
		model.addAttribute("user_id", userid);
		ModelAndView mv = new ModelAndView("write");
		return mv;
	}
	
	//글쓰기
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(BoardDTO dto, HttpSession session, Model model, MultipartHttpServletRequest request) throws Exception {
		String userid = (String)session.getAttribute("user_id");
		dto.setBoardWriter(userid);
		//파일 업로드
		MultipartFile mf = request.getFile("file");
		String originFileName = mf.getOriginalFilename();
		
		String changeName = originFileName + UUID.randomUUID();
		
		String path = "C:\\Users\\USER\\git\\educatcion\\ExProject\\src\\main\\webapp\\resources\\resources";
		
		FileDTO fDto = new FileDTO();
		
		fDto.setFileOriginalName(originFileName);
		fDto.setFileChangedName(changeName);
		fDto.setFilePath(path);
		
		String safeFile = path + System.currentTimeMillis() + originFileName;
		
		try {
			boardService.insertFile(fDto);
			mf.transferTo(new File(safeFile));
	
			Date today = new Date();
			dto.setBoardWriteDate(today);
			
			model.addAttribute("list", dto);
		
			dto.setFileIdx(fDto.getFileIdx());
			boardService.addBoard(dto);
			System.out.println(dto.toString());
			logger.debug("* [CONTROLLER] Input ◀ (Service) : " + dto.toString());
		
		}catch (IllegalStateException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		boardService.fileName(fDto);
		
		return "redirect:/main";
	}
	
	//게시글 상세보기
	@RequestMapping(value="/detail")
	public void detail(BoardDTO dto, Model model, @ModelAttribute("cri") Criteria cri) throws Exception{
		List<BoardDTO> detail = boardService.getBoardList(dto);
		
		BoardDTO pDTO = boardService.prePage(dto.getBoardIdx());
		BoardDTO nDTO = boardService.nextPage(dto.getBoardIdx());	
//		List<Map<String, Object>> fDTO = boardService.selectFileInfo(detail.get(0).getFileIdx());
		
//		model.addAttribute("file", fDTO);
		
		model.addAttribute("list", detail.get(0));
		model.addAttribute("prePage",pDTO);
		model.addAttribute("nextPage",nDTO);
		model.addAttribute("cri", cri);
	}
	
	//게시물 수정
	@RequestMapping(value="/updateContent", method = RequestMethod.POST)
	public String updateBoard(BoardDTO eDTO) throws Exception{
		
		boardService.chgBoard(eDTO);
		
		return "redirect:/detail?boardIdx=" + eDTO.getBoardIdx();
	}
	
	//게시물 삭제
	@RequestMapping(value= "/delete", method = RequestMethod.GET)
	public String deleteContents(@RequestParam("boardIdx") int boardIdx) throws Exception{
		
		boardService.delBoard(boardIdx);
		return "main";
	}

	
	@RequestMapping(value="adminMain")
	public String adminboard(BoardDTO dto, Model model, Criteria cri) throws Exception{
		ModelAndView  mv = new ModelAndView("adminMain");
		List<BoardDTO> adminBoardList = boardService.getBoardList(dto);
		int count = boardService.count();
		model.addAttribute("List", adminBoardList);
		
		List<BoardDTO> boards = boardService.pageList(cri);
		PageMaker pageMaker = new PageMaker(cri);
		int totalCount = boardService.getTotalCount(cri);
		
		pageMaker.setTotalCount(totalCount);
	
		model.addAttribute("pageNum", count);
		
		int totalContent = boardService.countTotalContent();
		model.addAttribute("totalContent", totalContent);
		
		
		int totalMember = boardService.countTotalMember();
		model.addAttribute("totalMember", totalMember);
		
		//오늘 게시물 
		int todayContent = boardService.todayContent();
		model.addAttribute("todayContent", todayContent);
		
		//오늘 가입자 수
		int todayUser = boardService.todayMember();
		model.addAttribute("todayMember" , todayUser);
		
		
		model.addAttribute("list", boards);
		model.addAttribute("pageNum", count);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/adminMain";
		
	}
	
	//관리자 회원관리
	@RequestMapping(value = "/adminMember")
	public String adminUser(BoardDTO dto, Model model, MemberDTO mDto, Criteria cri) throws Exception {
		
		ModelAndView mv = new ModelAndView("adminMember");

		/*List<UserDTO> adminBoardList = userService.getUserList(uDto);*/
		/*
		model.addAttribute("list", adminBoardList);*/
		
		List<MemberDTO> member = boardService.memberList(cri);
		PageMaker pageMaker = new PageMaker(cri);
		int totalCount = boardService.getTotalCount(cri);
		
		pageMaker.setTotalCount(totalCount);
	
		int count = boardService.count();
		model.addAttribute("pageNum", count);
		
		model.addAttribute("list", member);
		model.addAttribute("pageMaker", pageMaker);


		//총 가입 유저수
		int totalMember = boardService.countTotalMember();
		model.addAttribute("totalUser", totalMember);
		
		//오늘 게시물 
		int todayContent = boardService.todayContent();
		model.addAttribute("todayContent", todayContent);
		
		//오늘 가입자 수
		int todayMember = boardService.todayMember();
		model.addAttribute("todayMember", todayMember);
	
		return "/adminMember";
	}
	
}