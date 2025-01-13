package gamepiece.admin.boardComment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.boardComment.domain.BoardComment;
import gamepiece.util.Pageable;

@Mapper
public interface BoardCommentMapper {
	
	
	
	//특정 게시판 덧글 세기
	int getCntBoardComment(String boardNum);
	
	
	//덧글삭제
	int removeComment(String commentNum);	

	//덧글수정
	int modifyComment(BoardComment boardComment);
	
	
	
	//특정 게시물 덧글 조회
	List<BoardComment> getBoardCommentInfo(Map<String, Object> paramMap); 
	
	//특정행 조회
	BoardComment getCommentInfo(String commnetNum);
	
	
	//덧글작성
	int addComment(BoardComment boardComment);
	
	//덧글세기
	int getCntComment();
	
	
	//덧글목록
	List<BoardComment> getCommentList(Pageable pageable);

}
