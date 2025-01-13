package gamepiece.user.game.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.user.game.domain.UserGame;
import gamepiece.user.game.mapper.UserGameMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Service("userUserGameService")
@RequiredArgsConstructor
@Transactional
public class UserGameServiceImpl implements UserGameService {

	private final UserGameMapper userGameMapper;
	@Override
	public PageInfo<UserGame> getGameList(Pageable pageable) {
		
		int rowCnt = userGameMapper.getCntGameList();
		
		List<UserGame> userGameList = userGameMapper.getGameList(pageable);
		
		return new PageInfo<>(userGameList,pageable, rowCnt);
	}
	
	@Override
	public ArrayList<String> getPlatformList() {
		
		ArrayList<String> platformList = userGameMapper.getPlatformList();
		return platformList;
	}
}
