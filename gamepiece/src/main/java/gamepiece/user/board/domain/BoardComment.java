package gamepiece.user.board.domain;

import gamepiece.user.user.domain.Avatar;
import gamepiece.user.user.domain.User;
import lombok.Data;


@Data
public class BoardComment {
		
	private String commentNum; //덧글번호
	private String boardNum; //게시판번호
	private String commentUserId; //덧글유저Id
	private String commentDetail; //덧글내용
	private int commentLikeCnt; //덧글좋아요
	private int commentDisLikeCnt; //덧글싫어요
	private String commentYmd; //덧글작성일자	
	private Board boardInfo; //게시물정보
	private User userInfo; //유저정보
	private Avatar avatarInfo; //아바타정보
	 private String avatarFilePath;


}
