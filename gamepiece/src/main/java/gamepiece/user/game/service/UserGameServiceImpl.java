package gamepiece.user.game.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.user.game.domain.UserGame;
import gamepiece.user.game.mapper.UserGameMapper;
import lombok.RequiredArgsConstructor;

@Service("userUserGameService")
@RequiredArgsConstructor
@Transactional
public class UserGameServiceImpl implements UserGameService {

	private final UserGameMapper userGameMapper;
	@Override
	public List<UserGame> getGameList() {
		
		List<UserGame> userGameList = userGameMapper.getGameList();
		
		return userGameList;
	}
}
