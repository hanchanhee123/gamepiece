package gamepiece.user.game.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.game.domain.UserGame;
import gamepiece.util.Pageable;

@Mapper
public interface UserGameMapper {

	// 사용자 게임 목록 조회
	List<UserGame> getGameList(Pageable pageable);
	
	// 게임 목록 행 개수 
	int getCntGameList();
	
	// 플랫폼에 따른 게임 목록 행 개수
	int getCntGameListWithPlatform();
	
	// 플랫폼 목록 조회
	ArrayList<String> getPlatformList();
	
	// 장르 목록 조회
	ArrayList<String> getGenreList();
	
	
	
	// 플랫폼 클릭 시 해당 게임 목록 조회
	List<UserGame> getGameListWithPlatform(Map<String, Object> paramMap);
	
	
	
}

