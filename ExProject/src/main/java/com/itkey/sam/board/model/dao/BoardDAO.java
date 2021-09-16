package com.itkey.sam.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itkey.sam.board.dto.BoardDTO;
import com.itkey.sam.file.dto.FileDTO;
import com.itkey.sam.util.Criteria;

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
	public int deleteBoard(int boardIdx) throws Exception;
	
	//전체 게시물 수 조회
	public int totalContent()throws Exception;
	
	
	// 전체 가입회원 수 조회
	public int totalMember()throws Exception;

	
	// 하루 게시물 수 조회
	public int todayContent()throws Exception;
	
	
	// 하루 가입회원 수 조회
	public int todayMember()throws Exception;
	
	// 파일 업로드
//	public int insertFile(FileDTO fDTO)throws Exception;
//	
//	public FileDTO findFileIdx(FileDTO fDTO)throws Exception;
//	
//	public FileDTO findBoardFileIdx(int fileIdx)throws Exception;
	
	//파일 업로드
	public int insertFile(FileDTO dto) throws Exception;
	
	//파일 이름
	public FileDTO fileName(FileDTO dto) throws Exception;
	
	//파일이름
	public FileDTO boardFileIdx(int fileIdx) throws Exception;

	public FileDTO getFileName(int fileIdx) throws Exception;

	public BoardDTO prePage(int boardIdx) throws Exception;
	
	public BoardDTO nextPage(int boardIdx) throws Exception;
	
	public List<BoardDTO> pageList(Criteria cri) throws Exception;
	
	public int getTotalCount(Criteria cri) throws Exception;
	
	public int count() throws Exception;
	
	
	//첨부파일 다운로드
//	public List<Map<String, Object>> selectFileInfo(int fileIdx) throws Exception;
	
	
}