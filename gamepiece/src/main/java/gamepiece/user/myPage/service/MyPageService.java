package gamepiece.user.myPage.service;

import java.util.List;

import gamepiece.user.myPage.domain.MyPage;
import gamepiece.user.pointShop.domain.Point;

public interface MyPageService {

	// 마이페이지 - 사용자정보조회 (정보수정)
	MyPage myPageUser(String id);

	// 마이페이지 - 사용자정보조회 (포인트 내역)
	List<MyPage> myPagePointLog(String id);

	// 아바타 조회
	List<Point> getAvatar(String id);

	// 아바타 저장
	void saveAvatar(String id, String selectAvatar);
	
	// 아바타액자 조회
	List<Point> getAvatarFrame(String id);

	// 아바타액자 저장
	void saveAvatarFrame(String id, String selectAvatarFrame);

}
