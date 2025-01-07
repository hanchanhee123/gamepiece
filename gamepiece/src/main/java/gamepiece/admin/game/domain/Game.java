package gamepiece.admin.game.domain;

public class Game {
	
	
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
	
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getPlatformCode() {
		return platformCode;
	}
	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getGameEnrollmentYmd() {
		return gameEnrollmentYmd;
	}
	public void setGameEnrollmentYmd(String gameEnrollmentYmd) {
		this.gameEnrollmentYmd = gameEnrollmentYmd;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public int getReviewAvg() {
		return reviewAvg;
	}
	public void setReviewAvg(int reviewAvg) {
		this.reviewAvg = reviewAvg;
	}
	public String getSummation() {
		return summation;
	}
	public void setSummation(String summation) {
		this.summation = summation;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getRegYmd() {
		return regYmd;
	}
	public void setRegYmd(String regYmd) {
		this.regYmd = regYmd;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Game [gameCode=");
		builder.append(gameCode);
		builder.append(", gameName=");
		builder.append(gameName);
		builder.append(", platformCode=");
		builder.append(platformCode);
		builder.append(", platformName=");
		builder.append(platformName);
		builder.append(", developer=");
		builder.append(developer);
		builder.append(", gameEnrollmentYmd=");
		builder.append(gameEnrollmentYmd);
		builder.append(", detailId=");
		builder.append(detailId);
		builder.append(", imgId=");
		builder.append(imgId);
		builder.append(", videoId=");
		builder.append(videoId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", price=");
		builder.append(price);
		builder.append(", reviewCount=");
		builder.append(reviewCount);
		builder.append(", reviewAvg=");
		builder.append(reviewAvg);
		builder.append(", summation=");
		builder.append(summation);
		builder.append(", adminId=");
		builder.append(adminId);
		builder.append(", regYmd=");
		builder.append(regYmd);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
