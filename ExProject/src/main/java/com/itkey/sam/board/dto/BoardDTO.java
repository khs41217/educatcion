package com.itkey.sam.board.dto;

import java.util.Date;

/**
 * @author LKM
 */
public class BoardDTO {
	
	private int boardIdx          = 0;
	private String boardWriter       = null;
	private String boardTitle        = null;
	private String boardContents     = null;
	private String boardViewCount    = null;
	private int fileIdx           = 0;
	private String row           	= null;
	private String offset           = null;
	private String boardPublicFl = null; 
	private Date boardWriteDate = null;
	
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContents() {
		return boardContents;
	}
	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}
	public String getBoardViewCount() {
		return boardViewCount;
	}
	public void setBoardViewCount(String boardViewCount) {
		this.boardViewCount = boardViewCount;
	}
	public int getFileIdx() {
		return fileIdx;
	}
	public void setFileIdx(int fileIdx) {
		this.fileIdx = fileIdx;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public Date getBoardWriteDate() {
		return boardWriteDate;
	}
	
	public void setBoardWriteDate(Date boardWriterJoinDate) {
		this.boardWriteDate = boardWriterJoinDate;
	}
	
	public String getBoardPublicFl() {
		return boardPublicFl;
	}
	public void setBoardPublicFl(String boardPublicFl) {
		this.boardPublicFl = boardPublicFl;
	}	
	
	
}