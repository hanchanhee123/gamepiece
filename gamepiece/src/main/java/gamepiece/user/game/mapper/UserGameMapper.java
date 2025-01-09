package gamepiece.user.game.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.game.domain.UserGame;

@Mapper
public interface UserGameMapper {

	// 사용자 게임 목록 조회
	List<UserGame> getGameList();
}
