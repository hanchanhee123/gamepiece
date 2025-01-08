package gamepiece.admin.boardComment.domain;

import lombok.Data;

@Data
public class BoardComment {
	
	private String commnetNum; //덧글번호
	private String boardNum; //게시물번호
	private String commentUser; //덧글작성자
	private String commentDetail; //덧글내용
	private int commnetLikeCnt; //덧글좋아요
	private int commentDisLikeCnt; //덧글싫어요
	private String commentYmd; //덧글작성일자	
	
}
