package com.itkey.sam.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itkey.sam.board.dto.BoardDTO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	
	// Logback logger (package : org.slf4j.Logger & org.slf4j.LoggerFactory)
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Mybatis SqlSessionTemplate
	@Autowired private SqlSessionTemplate sqlSession;
	
	public List<BoardDTO> selectBoardList(BoardDTO eDTO) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : " + eDTO.toString());
		List<BoardDTO> out = sqlSession.selectList("selectBoard", eDTO);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + out.toString());
		return out;
	};
	
	public int selectBoardCount(BoardDTO eDTO) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : " + eDTO.toString());
		int result = sqlSession.selectOne("selectBoardCount", eDTO);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};

	public int insertBoard(BoardDTO eDTO) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : " + eDTO.toString());
		int result = sqlSession.insert("insertBoard", eDTO);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};

	public int updateBoard(BoardDTO eDTO) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : " + eDTO.toString());
		int result = sqlSession.update("updateBoard", eDTO);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};

	public int deleteBoard(String keyId) throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : " + keyId);
		int result = sqlSession.delete("deleteBoard", keyId);
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	}

	public int totalContent() throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : totalContent");
		int result = sqlSession.selectOne("countTotalContent");
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};

	public int totalMember() throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : totalMember");
		int result = sqlSession.selectOne("countTotalMember");
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};

	public int todayContent() throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : todayContent");
		int result = sqlSession.selectOne("todayContent");
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};

	public int todayMember() throws Exception {
		logger.debug("* [DAO] Input  ◀ (Service) : todayMember");
		int result = sqlSession.selectOne("todayMember");
		logger.debug("* [DAO] Output ◀ (Mybatis) : " + result);
		return result;
	};
	
}