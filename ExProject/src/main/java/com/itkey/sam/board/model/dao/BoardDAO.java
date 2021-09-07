package com.itkey.sam.board.model.dao;

import java.util.List;

import com.itkey.sam.board.dto.BoardDTO;

public interface BoardDAO {

	/**
	 * 게시판 정보 조회
	 * @param eDTO 조회조건
	 * @return 결과 목록
	 */
	public List<BoardDTO> selectBoardList(BoardDTO eDTO) throws Exception;

	/**
	 * 게시판 정보 데이터 갯수 조회
	 * @param eDTO 조회조건
	 * @return 데이터 갯수
	 */
	public int selectBoardCount(BoardDTO eDTO) throws Exception;

	/**
	 * 게시판 정보 생성
	 * @param eDTO 생성할 데이터
	 * @return 입력 데이터 개수(selectKey 를 사용하여 key를 딴 경우 입력 DTO에 해당 key 사용)
	 * @throws Exception
	 */
	public int insertBoard(BoardDTO eDTO) throws Exception;

	/**
	 * 게시판 정보 수정
	 * @param eDTO 수정할 데이터
	 * @return 성공여부 (수정된 데이터 개수)
	 * @throws Exception
	 */
	public int updateBoard(BoardDTO eDTO) throws Exception;

	/**
	 * 게시판 정보 삭제
	 * @param eDTO 삭제할 데이터 키값
	 * @return 성공여부 (삭제된 데이터 개수)
	 * @throws Exception
	 */
	public int deleteBoard(String keyId) throws Exception;
	
	
	
}