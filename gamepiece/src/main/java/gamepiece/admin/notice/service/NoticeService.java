package gamepiece.admin.notice.service;


import gamepiece.admin.notice.domain.Notice;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface NoticeService {
	
	
	//공지 검색조회
	PageInfo<Notice> getSearchList(String searchValue, Pageable pageable);
	
	
	//공지글 삭제
	int removeNotice(int noticeNum);
	
	//공지글 수정
	int modifyNotice(Notice notice);
	
	
	//공지글 추가
	int addNotice(Notice notice);
	
	//특정 공지글 조회
	Notice getNoticeInfo(int noticeNum);
	
	
	//게시글목록
	PageInfo<Notice> getNoticeList(Pageable pageable);

}
