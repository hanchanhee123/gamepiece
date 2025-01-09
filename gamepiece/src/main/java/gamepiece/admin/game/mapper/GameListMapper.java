package gamepiece.admin.game.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.game.domain.Game;
import gamepiece.util.Pageable;

@Mapper
public interface GameListMapper{
	
	// 게임목록 조회
	List<Game> getGameList(Pageable pageable);
	
	// 특정 게임 정보 조회
	Game getGameInfoByCode(String gameCode);
	
	// 게임정보 수정
	void modifyGame(Game game);
	
	// 플랫폼 목록 조회
	List<Map<String, Object>> getPlatform();
	
	
	int getCntGameList();
	
	// 게임 검색
	List<Game> getSearchList(Map<String, Object> searchMap);
	
	// 장르 목록 조회(임시)
	List<Game> getGenreList();
	
	// 장르를 통한 게임 목록 검색
	List<Game> getGameWithGenre(Map<String, Object> searchMap);
	
	// 플랫폼을 통한 게임 목록 검색
	List<Game> getGameWithPlatform(Map<String, Object> searchMap);

}


