package gamepiece.admin.game.service;

import java.util.List;
import java.util.Map;

import gamepiece.admin.game.domain.Game;


public interface GameListService {
	
	// 게임 목록 조회
	List<Game> getGameList();
	
	// 특정 게임 정보 조회
	Game getGameInfoByGameCode(String gameCode);
	
	// 특정 게임 정보 수정
	void modifyGame(Game game);
	
	List<Map<String, Object>> getPlatform();
	
}
