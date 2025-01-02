package gamepiece.admin.game.service;

import java.util.List;



import gamepiece.admin.game.domain.Game;


public interface GameListService {
	
	// 게임 목록 조회
	List<Game> getGameList();
	
	// 게임수정
	Game getGameInfoByGameCode(String gameCode);
}
