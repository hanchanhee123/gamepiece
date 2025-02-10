package gamepiece.user.game.domain;

import lombok.Data;

@Data
public class UserReview {
	
	private String reviewNo;
	private String gameCode;
	private String id;
	private String grade;
	private String reviewContents;
	private int reviewEmpathy;
	private int reviewDisempathy;
	private String reviewLogYmd;
	
	private int nextReviewNum;
	private String review;
	private String reviewNum;
	
	private boolean isDetail;
}
