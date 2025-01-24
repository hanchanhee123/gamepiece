package gamepiece.user.myPage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.board.domain.Board;
import gamepiece.user.board.domain.Inquiry;
import gamepiece.user.event.domain.Event;
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

	// 배경프로필 조회
	List<Point> getBackground(String id);

	// 기존 배경프로필
	void updateBackground(String id);
	
	// 새로운 배경프로필
	void insertBackground(String backgroundNo, String id, String selectBackground);

	// 마이페이지 - 아바타URL
	Avatar getUserAvatar(String id);

	// 마이페이지 - 보유 이모티콘
	List<Point> myPageEmoticon(String id);

	// 마이페이지 - 내 리뷰
	List<Board> myPageReview(String id);
	
	// 마이페이지 - 내 게시글
	List<Board> myPageBoard(String id);

	// 마이페이지 - 내 문의글
	List<Inquiry> myPageInquiry(String id);
	
	// 마이페이지 - 참여한 이벤트
	List<Event> myPageEvent(String id);

	// 마이페이지 - 내 게시글에 대한 댓글 수
//	int myPageBoardComments(String id);

}
