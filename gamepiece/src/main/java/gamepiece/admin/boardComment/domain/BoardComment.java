package gamepiece.admin.boardComment.domain;



import gamepiece.admin.board.domain.Board;
import gamepiece.admin.user.domain.User;
import lombok.Data;

@Data
public class BoardComment {
	
	private String commentNum; //덧글번호
	private String boardNum; //게시판번호
	private String commentDetail; //덧글내용
	private int commnetLikeCnt; //덧글좋아요
	private int commentDisLikeCnt; //덧글싫어요
	private String commentYmd; //덧글작성일자	
	private Board boardInfo; //게시물정보
	private User userInfo; //유저정보
	private String isDelete; //삭제여부
}
