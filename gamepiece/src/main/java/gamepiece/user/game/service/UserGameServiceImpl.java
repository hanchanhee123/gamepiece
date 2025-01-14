package gamepiece.user.game.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Override
	public ArrayList<String> getGenreList() {
		
		ArrayList<String> genreList = userGameMapper.getGenreList();
		
		return genreList;
	}
	
	
	
	@Override
	public PageInfo<UserGame> getGameListWithPlatform(Pageable pageable, String platformCode) {
		int rowCnt = userGameMapper.getCntGameListWithPlatform();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("platformCode", platformCode);
		paramMap.put("pageable", pageable);
		
		List<UserGame> userGameList = userGameMapper.getGameListWithPlatform(paramMap);
		
		return new PageInfo<>(userGameList, pageable, rowCnt);
	}
	
}
