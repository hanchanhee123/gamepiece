package gamepiece.user.myPage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.common.mapper.CommonMapper;
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
	private final CommonMapper commonMapper;
	
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

	// 아바타 조회
	@Override
	public List<Point> getAvatar(String id) {

		return myPageMapper.getAvatar(id);
	}

	// 아바타 저장
	@Override
	public void saveAvatar(String id, String selectAvatar) {
		
		String avatarNo = commonMapper.getPrimaryKey("a_", "avatar", "avatar_no");
		System.out.println("avatar 생성된 기본키 : " + avatarNo);
		
		// 기존 아바타
		myPageMapper.updateAvatar(id);

		// 새로운 아바타
		myPageMapper.insertAvatar(avatarNo, id, selectAvatar);
	}
	
	// 아바타액자 조회
	@Override
	public List<Point> getAvatarFrame(String id) {

		return myPageMapper.getAvatarFrame(id);
	}

	@Override
	public void saveAvatarFrame(String id, String selectAvatarFrame) {
		
		String avatarFrameNo = commonMapper.getPrimaryKey("af_", "avatar_frame", "avatarframe_no");
		System.out.println("avatar_frame 생성된 기본키 : " + avatarFrameNo);
		
		// 기존 아바타 액자
		myPageMapper.updateAvatarFrame(id);

		// 새로운 아바타 액자
		myPageMapper.insertAvatarFrame(avatarFrameNo, id, selectAvatarFrame);
		
	}
	
}
