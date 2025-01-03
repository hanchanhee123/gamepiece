package gamepiece.admin.game.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.game.domain.Game;

@Mapper
public interface GameListMapper{

	List<Game> getGameList();
	
	Game getGameInfoByCode(String gameCode);
	
	void modifyGame(Game game);
	
	List<Map<String, Object>> getPlatform();
}


