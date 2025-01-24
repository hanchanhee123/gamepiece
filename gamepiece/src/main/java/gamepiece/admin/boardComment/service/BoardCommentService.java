package gamepiece.admin.boardComment.service;



import gamepiece.admin.boardComment.domain.BoardComment;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface BoardCommentService {
	
	
	
	 //덧글삭제
	   int removeComment(String commentNum);

	   //덧글수정
	   int modifyComment(BoardComment boardComment);

	   //특정행 조회
	   BoardComment getCommentInfo(String commentNum);

	   //특정 게시물 덧글 조회 (페이징 처리)
	   PageInfo<BoardComment> getBoardCommentInfo(String boardNum, Pageable pageable);

	   //덧글작성
	   int addComment(BoardComment boardComment);

	   //덧글목록 
	   PageInfo<BoardComment> getCommentList(Pageable pageable);
}
