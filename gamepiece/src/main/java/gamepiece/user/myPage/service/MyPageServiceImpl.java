package gamepiece.user.myPage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.common.mapper.CommonMapper;
import gamepiece.user.board.domain.Board;
import gamepiece.user.board.domain.Inquiry;
import gamepiece.user.event.domain.Event;
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
	
	// 배경프로필 조회
	@Override
	public List<Point> getBackground(String id) {

		return myPageMapper.getBackground(id);
	}
	
	// 배경프로필 저장
	@Override
	public void saveBackground(String id, String selectBackground) {
		
		String backgroundNo = commonMapper.getPrimaryKey("b_", "background", "background_no");
		System.out.println("background 생성된 기본키 : " + backgroundNo);
		
		// 기존 배경프로필
		myPageMapper.updateBackground(id);

		// 새로운 배경프로필
		myPageMapper.insertBackground(backgroundNo, id, selectBackground);
	}
	
	// 마이페이지 - 보유 이모티콘
	@Override
	public List<Point> myPageEmoticon(String id) {

		return myPageMapper.myPageEmoticon(id);
	}
	
	// 마이페이지 - 내 리뷰
	@Override
	public List<Board> myPageReview(String id) {

		return myPageMapper.myPageReview(id);
	}
	
	// 마이페이지 - 내 게시글
	@Override
	public List<Board> myPageBoard(String id) {

		return myPageMapper.myPageBoard(id);
	}
	
	// 마이페이지 - 내 문의글
	@Override
	public List<Inquiry> myPageInquiry(String id) {

		return myPageMapper.myPageInquiry(id);
	}
	
	// 참여한 이벤트
	@Override
	public List<Event> myPageEvent(String id) {

		return myPageMapper.myPageEvent(id);
	}
	
	// 마이페이지 - 내 게시글에 대한 댓글 수
//	@Override
//	public int myPageBoardComments(String id) {
//		
//		return myPageMapper.myPageBoardComments(id);
//	}
	
}
