package gamepiece.user.board.service;




import gamepiece.user.board.domain.Board;
import gamepiece.user.board.domain.Inquiry;
import gamepiece.user.board.domain.Notice;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface BoardService {
	
	
	
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
