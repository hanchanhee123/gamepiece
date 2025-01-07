package gamepiece.admin.game.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.game.domain.Game;
import gamepiece.util.Pageable;

@Mapper
public interface GameListMapper{

	List<Game> getGameList(Pageable pageable);
	
	Game getGameInfoByCode(String gameCode);
	
	void modifyGame(Game game);
	
	List<Map<String, Object>> getPlatform();
	
	int getCntGameList();
}


