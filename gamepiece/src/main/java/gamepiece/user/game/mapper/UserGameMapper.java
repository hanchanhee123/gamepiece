package gamepiece.user.game.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.game.domain.UserGame;
import gamepiece.util.Pageable;

@Mapper
public interface UserGameMapper {

	// 사용자 게임 목록 조회
	List<UserGame> getGameList(Pageable pageable);
	
	// 게임 목록 행 개수 
	int getCntGameList();
	
	// 플랫폼에 따른 게임 목록 행 개수
	int getCntGameListWithPlatform();
	
	// 플랫폼 목록 조회
	ArrayList<String> getPlatformList();
	
	// 장르 목록 조회
	ArrayList<String> getGenreList();
	
	
	
	// 플랫폼 클릭 시 해당 게임 목록 조회
	List<UserGame> getGameListWithPlatform(Map<String, Object> paramMap);
	
	
	
	// 장바구니에 담기 버튼 클릭시 장바구니 테이블에 데이터 삽입
	int putGameInCart(UserGame userGame);
	
	// 장바구니 테이블 데이터 조회
	List<UserGame> getUserCartList(String id);
	
	// 장바구니 안 가격 총합
	Integer cartTotalPrice(String id);
	
	// 장바구니 목록 제거
	int deleteGameCartList(String id);
	
	// 장바구니 선택 제거
	int deleteGameCartItem(String id, String cartCode);
	
	// 결제 방법 목록 조회
	List<UserGame> getPaymentList();
}

