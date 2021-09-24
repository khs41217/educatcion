package com.itkey.sam.board.model.service;

import java.util.List;
import java.util.Map;

import com.itkey.sam.board.dto.BoardDTO;
import com.itkey.sam.file.dto.FileDTO;
import com.itkey.sam.member.dto.MemberDTO;
import com.itkey.sam.util.Criteria;

/**
 * Service for SAMPLE_BOARD_TB table : 게시판 정보
**/
public interface BoardService {

	/**
	 * 게시판 정보 조회
	 * @param eDTO 조회 조건
	 * @return 결과 목록
	 */
	public List<BoardDTO> getBoardList(BoardDTO eDTO) throws Exception;

	
	/**
	 * 게시판 정보 데이터 갯수 조회
	 * @param eDTO 조회 조건
	 * @return  데이터 갯수
	 */
	public int getBoardCount(BoardDTO eDTO) throws Exception;

	/**
	 * 게시판 정보 추가
	 * @param eDTO 생성할 데이터
	 * @return 입력 데이터 개수 (selectKey 를 사용하여 key 를 딴 경우 입력 DTO에 해당 key 사용)
	 * @throws Exception
	 */
	public int addBoard(BoardDTO eDTO) throws Exception;

	/**
	 * 게시판 정보 수정
	 * @param eDTO 수정할 데이터
	 * @return 성공여부 (수정된 데이터 개수)
	 * @throws Exception
	 */
	public int chgBoard(BoardDTO eDTO) throws Exception;

	/**
	 * 게시판 정보 삭제
	 * @param eDTO 삭제할 데이터 키값
	 * @return 성공여부 (삭제된 데이터 개수)
	 * @throws Exception
	 */
	public int delBoard(int boardIdx) throws Exception;

	
	// 총 게시글 수 조회
	public int countTotalContent() throws Exception;
	
	// 총 회원가입 수 조회
	public int countTotalMember() throws Exception;
	
	// 당일 가입회원 수 조회
	public int todayMember() throws Exception;
	
	// 당일 게시글 수 조회
	public int todayContent() throws Exception;
	
	// 게시판 파일 업로드
//	public int addFile(FileDTO fDTO) throws Exception;
//	
//	public FileDTO findFileIdx(FileDTO fDTO) throws Exception;
//	
//	public FileDTO findBoardFileIdx(int fileIdx) throws Exception;

	//파일 업로드
	public int insertFile(FileDTO dto) throws Exception;
	
	//파일 이름
	public FileDTO fileName(FileDTO dto) throws Exception;
	
	//파일이름
	public FileDTO boardFileIdx(int fileIdx) throws Exception;
	
	public FileDTO getFileName(int fileIdx) throws Exception;
	
	//이전페이지
	public BoardDTO prePage(int boardIdx) throws Exception;
	
	//다음페이지	
	public BoardDTO nextPage(int boardIdx) throws Exception;
	
	public int getTotalCount(Criteria cri) throws Exception;
	
	public List<BoardDTO> pageList(Criteria cri) throws Exception;
	
	public int count() throws Exception;
	
	public List<MemberDTO> memberList(Criteria cri) throws Exception;
	
//	//첨부파일 다운로드
//	public List<Map<String, Object>> selectFileInfo(int fileIdx) throws Exception;
	
	//
	
	
}
