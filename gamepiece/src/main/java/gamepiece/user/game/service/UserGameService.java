package gamepiece.user.game.service;

import java.util.ArrayList;

import gamepiece.user.game.domain.UserGame;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface UserGameService {
	
	PageInfo<UserGame> getGameList(Pageable pageable);
	
	ArrayList<String> getPlatformList();

}
