package gamepiece.user.myPage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.myPage.domain.MyPage;
import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.user.domain.Avatar;

@Mapper
public interface MyPageMapper {

	// 마이페이지 - 사용자정보조회 (정보수정)
	MyPage myPageUser(String id);

	// 마이페이지 - 사용자정보조회 (포인트 내역)
	List<MyPage> myPagePointLog(String id);

	// 아바타 조회
	List<Point> getAvatar(String id);

	// 기존 아바타
	void updateAvatar(String id);

	// 새로운 아바타
	void insertAvatar(String avatarNo, String id, String selectAvatar);

	// 마이페이지 - 아바타URL
	Avatar getUserAvatar(String id);

	// 아바타액자 조회
	List<Point> getAvatarFrame(String id);

	// 기존 아바타액자
	void updateAvatarFrame(String id);

	// 새로운 아바타액자
	void insertAvatarFrame(String avatarFrameNo, String id, String selectAvatarFrame);

}
