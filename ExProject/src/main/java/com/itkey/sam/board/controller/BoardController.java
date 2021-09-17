package com.itkey.sam.board.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
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
	

	//첨부파일 다운로드
//	@RequestMapping(value = "fileDown")
//	public void fileDown(BoardDTO dto, HttpServletResponse response) throws Exception{
//		List<Map<String, Object>> resultMap = boardService.selectFileInfo(dto.getFileIdx());
//		String oriFileName = (String) resultMap.get("FILE_ORIGINAL_NAME");
//		String oldFileName = (String) resultMap.get("FILE_CHANGED_NAME");
//		
//		byte fileByte[] =org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\Users\\USER\\git\\educatcion\\ExProject\\src\\main\\webapp\\resources\\resources"+oriFileName));
//		
//		response.setContentType("application/octet-stream");
//		response.setContentLength(fileByte.length);
//		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(oriFileName, "UTF-8")+"\";");
//		response.getOutputStream().write(fileByte);
//		response.getOutputStream().flush();
//		response.getOutputStream().close();
//	}
	
}