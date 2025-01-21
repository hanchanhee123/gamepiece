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
	private String genreCode;
	private String genreName;
	private int reviewCount;
	private int reviewAvg;
	private String summation;
	private String adminId;
	private String regYmd;
	private String isDelete;
	
	
	private String urlSrc;
	private String title;
	private String released;
	private String finalPrice;
	private String disCountPrice;
	private String originalPrice;
	private String searchResult;
	
	private int currentPage;
	private int lastPage;
	private double totalAmount;
	private int startPageNum;
	private int endPageNum;
	
	private String searchValue;
	private String imgSrcSec;
	private String deepInfoText;
	
	private boolean isDetail;
	private String id;
	
	private boolean isCart;
}
