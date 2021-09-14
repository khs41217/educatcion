package com.itkey.sam.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itkey.sam.board.dto.BoardDTO;
import com.itkey.sam.file.dto.FileDTO;
import com.itkey.sam.paging.Criteria;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	
	// Logback logger (package : org.slf4j.Logger & org.slf4j.LoggerFactory)
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Mybatis SqlSessionTemplate
	@Autowired private SqlSessionTemplate sqlSession;
	
	public List<BoardDTO> selectBoardList(BoardDTO eDTO) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : " + eDTO.toString());
		List<BoardDTO> out = sqlSession.selectList("sample.selectBoard", eDTO);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + out.toString());
		return out;
	};
	
	public int selectBoardCount(BoardDTO eDTO) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : " + eDTO.toString());
		int result = sqlSession.selectOne("sample.selectBoardCount", eDTO);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};

	public int insertBoard(BoardDTO eDTO) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : " + eDTO.toString());
		int result = sqlSession.insert("sample.insertBoard", eDTO);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};

	public int updateBoard(BoardDTO eDTO) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : " + eDTO.toString());
		int result = sqlSession.update("sample.updateBoard", eDTO);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};

	public int deleteBoard(String keyId) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : " + keyId.toString());
		int result = sqlSession.delete("sample.deleteBoard", keyId);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	}

	public int totalContent() throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : totalContent");
		int result = sqlSession.selectOne("sample.countTotalContent");
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};

	public int totalMember() throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : totalMember");
		int result = sqlSession.selectOne("sample.countTotalMember");
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};

	public int todayContent() throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : todayContent");
		int result = sqlSession.selectOne("sample.todayContent");
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};

	public int todayMember() throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : todayMember");
		int result = sqlSession.selectOne("sample.todayMember");
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	}
	//파일업로드
	public int insertFile(FileDTO dto) throws Exception {
		return sqlSession.insert("insertFile", dto);
	}
	
	//업로드 파일 이름 뿌리기
	public FileDTO fileName(FileDTO dto) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : "+ dto);
		FileDTO fDto  = sqlSession.selectOne("fileName", dto);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + fDto);
		
		return fDto;
	}

	//업로드 파일 이름 뿌리기
	public FileDTO boardFileIdx(int fileIdx) throws Exception {
		return sqlSession.selectOne("boardFileIdx", fileIdx);
		
	}

	@Override
	public BoardDTO pagePre(int boardIdx) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : "+ boardIdx);
		BoardDTO state = sqlSession.selectOne("pagePre",boardIdx);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + state);
		return state;
	}

	@Override
	public BoardDTO nextPage(int boardIdx) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : "+ boardIdx);
		BoardDTO state = sqlSession.selectOne("nextPage", boardIdx);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + state);
		return state;
	}

	@Override
	public int getTotalCount(Criteria cri) throws Exception {
		int result = sqlSession.selectOne("getTotalCount", cri);
		return result;
	}

	@Override
	public List<BoardDTO> pageList(Criteria cri) throws Exception {
		return sqlSession.selectList("sample.pageList", cri);
	}

	
	public int count() throws Exception{
		int result = sqlSession.selectOne("count");
		return result;
	}
//	public int insertFile(FileDTO fDTO) throws Exception {
//		logger.debug("* [DAO] Input  ◀ (Service) : "+ fDTO.toString());
//		int result = sqlSession.insert("sample.insertFile", fDTO);
//		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
//		return result;
//	}
//
//	public FileDTO findFileIdx(FileDTO fDTO) throws Exception {
//		logger.debug("* [DAO] Input  ◀ (Service) : " + fDTO.toString());
//		logger.debug("* [DAO] Output ◀ (Mybatis) : ");
//		return sqlSession.selectOne("sample.findFileIdx", fDTO); 
//	}
//	
//	public FileDTO findBoardFileIdx(int fileIdx)throws Exception{
//		logger.debug("* [DAO] Input  ◀ (Service) : "+ fileIdx);		
//		logger.debug("* [DAO] Output ◀ (Mybatis) : ");
//		return sqlSession.selectOne("sample.findBoardFileIdx", fileIdx);
//	}

	
}