package gamepiece.admin.game.service;

import java.util.List;
import java.util.Map;

import gamepiece.admin.game.domain.Game;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;


public interface GameListService {
	
	// 게임 목록 조회
	PageInfo<Game> getGameList(Pageable pageable);
	
	// 특정 게임 정보 조회
	Game getGameInfoByGameCode(String gameCode);
	
	// 특정 게임 정보 수정
	void modifyGame(Game game);
	
	// 플랫폼 조회
	List<Map<String, Object>> getPlatform();
	
	// 게임 검색
	List<Game> searchList(String searchValue);
	
	// 장르 검색(임시)
	List<Game> getGenreList();
}
