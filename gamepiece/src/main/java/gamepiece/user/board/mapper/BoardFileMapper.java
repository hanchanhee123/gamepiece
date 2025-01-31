package gamepiece.user.board.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.board.domain.BoardFiles;

@Mapper
public interface BoardFileMapper {
	
	BoardFiles findByFileIdx(String fileIdx);
	int deleteFileByIdx(String fileIdx);
	BoardFiles getFileInfoByIdx(String fileIdx);
	BoardFiles getFilesInfoByIdx(String fileIdx);
	List<BoardFiles> getFileList();
	int addfile(BoardFiles fileInfo);
	void addfiles(List<BoardFiles> fileDtoList);
	String getNextFileIdx();
		BoardFiles findByNoticeNum(int noticeNum);  
	  BoardFiles findByBoardNum(String boardNum);
	  BoardFiles findByInquiryNum(String inquiryNum);
	   
	 
}
