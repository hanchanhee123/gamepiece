package gamepiece.admin.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.notice.domain.Notice;
import gamepiece.util.Pageable;

@Mapper
public interface NoticeMapper {
	
	

	//공지 검색행 세기
	int getCntSearchNotice(Map<String, Object> searchMap);
	
	//공지 검색조회
	List<Notice> getNoticeSearchList(Map<String, Object> searchMap);
	
	
	//공지글 삭제
	int removeNotice(int noticeNum);

	//공지글 수정
	int modifyNotice(Notice notice);
	
	//공지글 추가
	int addNotice(Notice notice);
	
	//특정 공지글 조회
	Notice getNoticeInfo(int noticeNum);
	
	//공지 행세기
	int getCntNotice();
	
	//공지목록
	List<Notice> getNoticeList(Pageable pageable);
	

}
