package com.itkey.sam.board.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itkey.sam.board.dto.BoardDTO;
import com.itkey.sam.board.model.dao.BoardDAO;
import com.itkey.sam.file.dto.FileDTO;
import com.itkey.sam.paging.Criteria;

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
	}

	public int countTotalContent() throws Exception {
		logger.debug("* [SERVICE] Input  ◀ (Controller)");
		int result = dao.totalContent();
		logger.debug("* [SERVICE] Output ◀ (DAO) : " + result);
		return result;
	}

	public int countTotalMember() throws Exception {
		logger.debug("* [SERVICE] Input  ◀ (Controller)");
		int result = dao.totalMember();
		logger.debug("* [SERVICE] Output ◀ (DAO) : " + result);
		return result;
	}

	public int todayMember() throws Exception {
		logger.debug("* [SERVICE] Input  ◀ (Controller)");
		int result = dao.todayMember();
		logger.debug("* [SERVICE] Output ◀ (DAO) : " + result);
		return result;
	}

	public int todayContent() throws Exception {
		logger.debug("* [SERVICE] Input  ◀ (Controller)");
		int result = dao.todayContent();
		logger.debug("* [SERVICE] Output ◀ (DAO) : " + result);
		return result;
	}

//	public int addFile(FileDTO fDTO) throws Exception {
//		logger.debug("* [SERVICE] Input  ◀ (Controller)");
//		int result = dao.insertFile(fDTO);
//		logger.debug("* [SERVICE] Output ◀ (DAO) : " + result);
//		return result;
//	}
//
//	public FileDTO findFileIdx(FileDTO fDTO) throws Exception {
//		logger.debug("* [SERVICE] Input  ◀ (Controller)");
//		logger.debug("* [SERVICE] Output ◀ (DAO) : ");
//		return dao.findFileIdx(fDTO);
//	};
//	
//	public FileDTO findBoardFileIdx(int fileIdx) throws Exception{
//		logger.debug("* [SERVICE] Input  ◀ (Controller)");		
//		logger.debug("* [SERVICE] Output ◀ (DAO) : ");
//		return dao.findBoardFileIdx(fileIdx);
//	}
	
	//파일 업로드
	public int insertFile(FileDTO dto) throws Exception{
		return dao.insertFile(dto);
	}

	//파일이름
	public FileDTO fileName(FileDTO dto) throws Exception {	
		return dao.fileName(dto);
	}
	
	//파일이름
	public FileDTO boardFileIdx(int fileIdx) throws Exception {
		return dao.boardFileIdx(fileIdx);
	}


	public BoardDTO pagePre(int boardIdx) throws Exception {
		logger.debug("* [SERVICE] Output ◀ (DAO) : "+ boardIdx);
		BoardDTO state = dao.pagePre(boardIdx);
		logger.debug("* [SERVICE] Input  ◀ (Controller)"+ state);		
		return state;
	}
	
	public BoardDTO nextPage(int boardIdx) throws Exception{
		logger.debug("* [SERVICE] Output ◀ (DAO) : "+ boardIdx);
		BoardDTO state = dao.nextPage(boardIdx);
		logger.debug("* [SERVICE] Input  ◀ (Controller)" +state);		
		return state;
		
	}
	
	public int getTotalCount(Criteria cri) throws Exception{
		logger.debug("* [SERVICE] Output ◀ (DAO) : getTotalCount");
		int result = dao.getTotalCount(cri);
		logger.debug("* [SERVICE] Input  ◀ (Controller)" + result);		
		return result;
	}

	@Override
	public List<BoardDTO> pageList(Criteria cri) throws Exception {
		return dao.pageList(cri);
	}
	
	public int count() throws Exception{		
		int result = dao.count();
		return result;
	}
	
	
}