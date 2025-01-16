package gamepiece.user.myPage.service;

import java.util.List;

import gamepiece.user.board.domain.Board;
import gamepiece.user.board.domain.Inquiry;
import gamepiece.user.event.domain.Event;
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

	// 마이페이지 - 보유 이모티콘
	List<Point> myPageEmoticon(String id);

	// 마이페이지 - 내 게시글
	List<Board> myPageBoard(String id);

	// 마이페이지 - 내 문의글
	List<Inquiry> myPageInquiry(String id);
	
	// 마이페이지 - 참여한 이벤트
	List<Event> myPageEvent(String id);
	
	// 마이페이지 - 내 게시글에 대한 댓글 수
//	int myPageBoardComments(String id);

}
