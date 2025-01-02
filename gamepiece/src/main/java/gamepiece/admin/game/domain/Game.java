package gamepiece.admin.game.domain;

public class Game {
	
	private String gameName;
	private int gamePrice;
	private String gameGerne;
	private String gamePlatform;
	
	
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public int getGamePrice() {
		return gamePrice;
	}
	public void setGamePrice(int gamePrice) {
		this.gamePrice = gamePrice;
	}
	public String getGameGerne() {
		return gameGerne;
	}
	public void setGameGerne(String gameGerne) {
		this.gameGerne = gameGerne;
	}
	public String getGamePlatform() {
		return gamePlatform;
	}
	public void setGamePlatform(String gamePlatform) {
		this.gamePlatform = gamePlatform;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Game [gameName=");
		builder.append(gameName);
		builder.append(", gamePrice=");
		builder.append(gamePrice);
		builder.append(", gameGerne=");
		builder.append(gameGerne);
		builder.append(", gamePlatform=");
		builder.append(gamePlatform);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
