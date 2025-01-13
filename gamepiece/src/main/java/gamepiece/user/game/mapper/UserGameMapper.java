package gamepiece.user.game.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.game.domain.UserGame;
import gamepiece.util.Pageable;

@Mapper
public interface UserGameMapper {

	// 사용자 게임 목록 조회
	List<UserGame> getGameList(Pageable pageable);
	
	// 게임 목록 행 개수 
	int getCntGameList();
	
	// 플랫폼 목록 조회
	ArrayList<String> getPlatformList();
}
