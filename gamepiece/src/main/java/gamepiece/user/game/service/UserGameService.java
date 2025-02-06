package gamepiece.user.game.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gamepiece.user.game.domain.UserGame;
import gamepiece.user.game.domain.UserReview;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface UserGameService {
	
	
	
	// 게임 목록 조회
	PageInfo<UserGame> getGameList(Pageable pageable);
	
	// 플랫폼 목록 조회
	ArrayList<String> getPlatformList();
	
	// 장르 목록 조회
	ArrayList<String> getGenreList();

	// 플랫폼 클릭 시 해당 게임 목록 조회
	PageInfo<UserGame> getGameListWithPlatform(Pageable pageable, String platformCode);
	
	Map<String, Object> getGameListApi(String searchValue, int currentPage);
	
	Map<String, Object> getGameDetailApi(String gameCode, String title);
	
	List<UserReview> getUserReview(String gameCode);
	
	String getLastReviewNo();
	
	void writeUserReview(UserReview userReview);
	
	
	void putGameInCart(UserGame userGame);
	
	List<UserGame> getUserCartList(String id);
	
	Integer cartTotalPrice(String id);
	
	void deleteGameCartList(String id);
	
	void deleteGameCartItem(String id, String gameCode);
	
	List<UserGame> getPaymentList();
	
	
}
