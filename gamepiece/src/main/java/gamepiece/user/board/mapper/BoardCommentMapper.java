package gamepiece.user.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import gamepiece.user.board.domain.BoardComment;


@Mapper
@Repository("userBoardCommentMapper")
public interface BoardCommentMapper {
	

	
	//덧글 좋아요 취소
	int cancelCommentLikeCount(String commentNum);
	
	//덧글 싫어요 취소
	int cancelCommentDisLikeCount(String commentNum);
	
	
	//덧글 좋아요 증가
	int addCommentLikeCount(String commentNum);
	
	//덧글 싫어요 증가
	int addCommentDisLikeCount(String commentNum);
	
	
	//댓글삭제
	int removeComment(String commentNum);
	
	//댓글수정
	int modifyComment(BoardComment boardComment);
	
	//게시물 작성
	int addComment(BoardComment boardComment);
	
	//특정 게시물 덧글세기
	int getCntBoardComment(Map<String, Object> searchMap);
	
	//특정 게시물 덧글 조회
	List<BoardComment> getBoardCommentInfo(Map<String, Object> paramMap); 
	


}
