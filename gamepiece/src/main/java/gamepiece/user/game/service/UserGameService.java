package gamepiece.user.game.service;

import java.util.ArrayList;

import gamepiece.user.game.domain.UserGame;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface UserGameService {
	
	// 게임 목록 조회
	PageInfo<UserGame> getGameList(Pageable pageable);
	
	// 플랫폼 목록 조회
	ArrayList<String> getPlatformList();

	// 플랫폼 클릭 시 해당 게임 목록 조회
	PageInfo<UserGame> getGameListWithPlatform(Pageable pageable, String platformCode);
}
