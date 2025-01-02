package gamepiece.admin.game.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.game.domain.Game;

@Mapper
public interface GameListMapper{

	List<Game> getGameList();
	
	Game getGameInfoByCode(String gameCode);
}
