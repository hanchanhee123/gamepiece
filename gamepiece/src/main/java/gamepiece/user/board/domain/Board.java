package gamepiece.user.board.domain;




import gamepiece.user.user.domain.User;
import lombok.Data;

@Data
public class Board {

	
	private String boardNum; //게시판번호
	private String boardCategory; //게시판카테고리 
	private String boardTitle; //게시판제목
	private String boardContent; //게시판내용
	private String boardUserid; //게시판작성자Id
	private int boardLikeCnt; //게시판좋아요수
	private int boardDisLikeCnt; //게시판싫어요수
	private int boardViewCnt; //게시판조회수
	private String boardYmd; //게시판작성일자
	private User userInfo; //유저정보

	
	
	
}
