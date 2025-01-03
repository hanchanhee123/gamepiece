package gamepiece.admin.game.service;

import java.util.List;
import java.util.Map;

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
	
	@Override
	public Game getGameInfoByGameCode(String gameCode) {
		return gameListMapper.getGameInfoByCode(gameCode);
	}
	
	@Override
	public void modifyGame(Game game) {
		gameListMapper.modifyGame(game);
		
	}
	
	@Override
	public List<Map<String, Object>> getPlatform() {
		// TODO Auto-generated method stub
		return gameListMapper.getPlatform();
	}
	
	
}
