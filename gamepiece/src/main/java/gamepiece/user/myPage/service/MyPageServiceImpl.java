package gamepiece.user.myPage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.user.myPage.domain.MyPage;
import gamepiece.user.myPage.mapper.MyPageMapper;
import gamepiece.user.pointShop.domain.Point;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

	private final MyPageMapper myPageMapper;
	
	// 마이페이지 - 사용자정보조회 (정보수정)
	@Override
	public MyPage myPageUser(String id) {
		
		return myPageMapper.myPageUser(id);
	}

	// 마이페이지 - 사용자정보조회 (포인트 내역)
	@Override
	public List<MyPage> myPagePointLog(String id) {
		
		return myPageMapper.myPagePointLog(id);
	}

	// 마이페이지 - 아바타
	@Override
	public List<Point> getAvatar(String id) {

		return myPageMapper.getAvatar(id);
	}

}
