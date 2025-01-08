package gamepiece.admin.game.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import gamepiece.admin.game.domain.Game;
import gamepiece.admin.game.mapper.GameListMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

@Service
public class GameListServiceImple implements GameListService{
	private final GameListMapper gameListMapper;
	
	
	public GameListServiceImple(GameListMapper gameListMapper) {
		this.gameListMapper = gameListMapper;
	}
	
	@Override
	public PageInfo<Game> getGameList(Pageable pageable) {
		
		int rowCnt = gameListMapper.getCntGameList();
		List<Game> gameList = gameListMapper.getGameList(pageable);
		
		return new PageInfo<>(gameList, pageable, rowCnt);
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
	
	@Override
	public List<Game> searchList(String searchValue) {
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchValue", searchValue);
		
		List<Game> gameList = gameListMapper.getSearchList(searchMap);
		return gameList;
	}
	
	@Override
	public List<Game> getGenreList() {
		
		return gameListMapper.getGenreList();
	}
}
