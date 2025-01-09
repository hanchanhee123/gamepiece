package gamepiece.user.game.domain;

import lombok.Data;

@Data
public class UserGame {

	private String gameCode;
	private String gameName;
	private String platformCode;
	private String platformName;
	private String developer;
	private String gameEnrollmentYmd;
	private String detailId;
	private String imgId;
	private String videoId;
	private String description;
	private int price;
	private String genre;
	private int reviewCount;
	private int reviewAvg;
	private String summation;
	private String adminId;
	private String regYmd;
	private String isDelete;
	
}
