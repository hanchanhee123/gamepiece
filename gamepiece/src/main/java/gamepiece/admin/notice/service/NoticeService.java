package gamepiece.admin.notice.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import gamepiece.admin.board.domain.AdminBoardFiles;
import gamepiece.admin.notice.domain.Notice;
import gamepiece.admin.notice.domain.NoticeFiles;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface NoticeService {
	
	
	 List<NoticeFiles> getNoticeFiles(int noticeNum);
	  
	    void addNoticeFileMapping(int noticeNum, String fileIdx);
	    
	    void deleteBoardFileMapping(int noticeNum, String fileIdx);
	    
	    void deleteAllNoticeBoardFiles(int noticeNum);
	    
	    void updateNoticeFiles(int noticeNum, List<MultipartFile> newFiles);
	    
	    
	
	void addNoticeWithFiles(Notice notice, MultipartFile[] files);
	
	AdminBoardFiles getNoticeFile(int noticeNum);
	
	
	  void addFilesWithInfo(MultipartFile[] files, List<AdminBoardFiles> fileDtoList);
		
		
		void addFile(MultipartFile file); 
		
		void addFiles(MultipartFile[] files);

		void deleteFile(AdminBoardFiles fileDto); 
	
	
	
	//공지 검색조회
	PageInfo<Notice> getSearchList(String searchValue, Pageable pageable);
	
	
	//공지글 삭제
	int removeNotice(int noticeNum);
	
	//공지글 수정
	int modifyNotice(Notice notice);
	
	
	//공지글 추가
	int addNotice(Notice notice);
	
	//특정 공지글 조회
	Notice getNoticeInfo(int noticeNum);
	
	
	//게시글목록
	PageInfo<Notice> getNoticeList(Pageable pageable);

}
