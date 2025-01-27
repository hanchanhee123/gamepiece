package gamepiece.user.board.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;


import gamepiece.user.board.domain.Board;
import gamepiece.user.board.domain.BoardComment;
import gamepiece.user.board.domain.BoardFiles;
import gamepiece.user.board.domain.Inquiry;
import gamepiece.user.board.domain.InquiryRespone;
import gamepiece.user.board.domain.Notice;
import gamepiece.user.board.domain.Report;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface BoardService {
	

	BoardFiles getBoardFileInfo(String boardNum);
	
	BoardFiles getFileInfo(String fileIdx);
	//게시판 파일 조회
	
	
	BoardFiles getNoticeNum(int noticeNum);
	
	BoardFiles getInquiryFile(String inquiryNum);
	
	BoardFiles getBoardFile(String boardNum);
	
    void addFilesWithInfo(MultipartFile[] files, List<BoardFiles> fileDtoList);
	
	
	void addFile(MultipartFile file); 
	
	void addFiles(MultipartFile[] files);

	void deleteFile(BoardFiles fileDto); 
	
	
	//공지 조회수 증가
	int addNoticeViewCount(int noticeNum);
	
	//게시판 조회수 증가
	int addViewCount(String boardNum);
	
	//댓글삭제
		int removeComment(String commentNum);
		
		//댓글수정
		int modifyComment(BoardComment boardComment);
	
	
	//공지 검색조회
	PageInfo<Notice> getNoticeSearchList(String searchValue, Pageable pageable);
	
	//문의 검색조회
	PageInfo<Inquiry> getInquirySearchList(String searchValue, Pageable pageable);
	
	
	//정보게시물 검색조회
	PageInfo<Board> getInfoSearchList(String searchValue, Pageable pageable);
	
	//자유게시물 검색조회
	PageInfo<Board> getFreeSearchList(String searchValue, Pageable pageable);
	
	//공략게시물 검색조회
	PageInfo<Board> getAttackSearchList(String searchValue, Pageable pageable);
	
	//게시물 검색조회
	PageInfo<Board> getSearchList(String searchValue, Pageable pageable);
	

	//특정게시물삭제
	int removeBoard(String boardNum);
	
	//특정게시물수정
	int modifyBoard(Board board);
	
	//신고하기
	int addReport(Report report);
	
	//문의답변 확인
	InquiryRespone getInquiryResponeInfo(String inquiryNum);
	
	//문의게시물 상세
	Inquiry getInquiryInfo(String inquiryNum);
	

	//공지글 상세
	Notice getNoticeInfo(int noticeNum);
	
	//덧글작성
	 int addComment(BoardComment boardComment);
	
	   //특정 게시물 덧글 조회 (페이징 처리)
	   PageInfo<BoardComment> getBoardCommentInfo(String boardNum, Pageable pageable);
	
	//특정게시물조회
	Board getBoardInfo(String boardNum);
		
	
	//문의글 작성
	int addInquiry(Inquiry inquiry);

	//게시물추가
	int addBoard(Board board);
	
	//전체게시글 목록
	PageInfo<Board> getAllBoardsList(Pageable pageable);
	
	//문의목록
	PageInfo<Inquiry> getInquiryList(Pageable pageable);

	//공지목록
	PageInfo<Notice> getNoticeList(Pageable pageable);
	
	//자유게시글목록
	PageInfo<Board> getFreeBoardsList(Pageable pageable);
	
	
	//공략게시글목록
	PageInfo<Board> getAttackBoardsList(Pageable pageable);
	
	//정보게시글목록
	PageInfo<Board> getInfoBoardsList(Pageable pageable);


	


}
