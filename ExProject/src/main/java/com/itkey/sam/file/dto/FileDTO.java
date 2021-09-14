package com.itkey.sam.file.dto;

public class FileDTO {
	private int fileIdx;
	private String fileOriginalName;
	private String fileChangedName;
	private String filePath;

	public int getFileIdx() {
		return fileIdx;
	}
	public void setFileIdx(int fileIdx) {
		this.fileIdx = fileIdx;
	}
	public String getFileOriginalName() {
		return fileOriginalName;
	}
	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}
	public String getFileChangedName() {
		return fileChangedName;
	}
	public void setFileChangedName(String fileChangedName) {
		this.fileChangedName = fileChangedName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String toString() {
		return "FileDTO [fileOriginalName=" + fileOriginalName + ", fileChangedName=" + fileChangedName + ", FilePath="
				+ filePath + "]";
	}
	
	
	
}
