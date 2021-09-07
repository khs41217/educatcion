package com.itkey.sam.board.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itkey.sam.board.dto.BoardDTO;
import com.itkey.sam.board.model.dao.BoardDAO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	// Logback logger (package : org.slf4j.Logger & org.slf4j.LoggerFactory)
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/* 
	 * Follow can be used for DI
	 * @Autowired BoardDAO dao;
	 */
	
	// Dependency Injection With BoardDAO
	@Resource(name="boardDAO") BoardDAO dao;

	public List<BoardDTO> getBoardList(BoardDTO eDTO) throws Exception {
		logger.debug("* [SERVICE] Input  ◀ (Controller) : " + eDTO.toString());
		List<BoardDTO> returnList = dao.selectBoardList(eDTO);
		logger.debug("* [SERVICE] Output ◀ (DAO) : " + returnList.toString());
		return returnList;
	};
	
	public int getBoardCount(BoardDTO eDTO) throws Exception {
		logger.debug("* [SERVICE] Input  ◀ (Controller) : " + eDTO.toString());
		int result = (Integer)dao.selectBoardCount(eDTO);
		logger.debug("* [SERVICE] Output ◀ (DAO) : " + result);
		return result;
	};

	public int addBoard(BoardDTO eDTO) throws Exception {
		logger.debug("* [SERVICE] Input  ◀ (Controller) : " + eDTO.toString());
		int result = dao.insertBoard(eDTO);
		logger.debug("* [SERVICE] Output ◀ (DAO) : " + result);
		return result;
	};

	public int chgBoard(BoardDTO eDTO) throws Exception {
		logger.debug("* [SERVICE] Input  ◀ (Controller) : " + eDTO.toString());
		int result = dao.updateBoard(eDTO);
		logger.debug("* [SERVICE] Output ◀ (DAO) : " + result);
		return result;
	};

	public int delBoard(String keyId) throws Exception {
		logger.debug("* [SERVICE] Input  ◀ (Controller) : " + keyId);
		int result = dao.deleteBoard(keyId);
		logger.debug("* [SERVICE] Output ◀ (DAO) : " + result);
		return result;
	};

}