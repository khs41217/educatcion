package com.itkey.sam.board.dto;

/**
 * @author LKM
 */
public class BoardDTO {
	
	private String boardIdx          = null;
	private String boardWriter       = null;
	private String boardTitle        = null;
	private String boardContents     = null;
	private String boardViewCount    = null;
	private String fileIdx           = null;
	private String row           	= null;
	private String offset           = null;
	
	public String getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(String boardIdx) {
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
	public String getFileIdx() {
		return fileIdx;
	}
	public void setFileIdx(String fileIdx) {
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
	
}