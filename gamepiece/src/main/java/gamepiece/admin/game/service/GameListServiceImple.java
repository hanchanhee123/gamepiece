package gamepiece.admin.game.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gamepiece.admin.game.domain.Game;
import gamepiece.admin.game.mapper.GameListMapper;

@Service
public class GameListServiceImple implements GameListService{
	private final GameListMapper gameListMapper;
	
	public GameListServiceImple(GameListMapper gameListMapper) {
		this.gameListMapper = gameListMapper;
	}
	
	@Override
	public List<Game> getGameList() {
		// TODO Auto-generated method stub
		return gameListMapper.getGameList();
	}
	
	
}
