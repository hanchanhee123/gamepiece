package gamepiece.user.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import gamepiece.user.board.domain.Notice;
import gamepiece.util.Pageable;

@Mapper
@Repository("userNoticeMapper")
public interface NoticeMapper {
	
	
	
	
	//공지 조회수 증가
	int addViewCount(int noticeNum);
	
	
	//공지 검색행 세기
	int getCntSearchNotice(Map<String, Object> searchMap);
		
	//공지 검색조회
	List<Notice> getNoticeSearchList(Map<String, Object> searchMap);
	
	//공지글 상세
	Notice getNoticeInfo(int noticeNum);
	
	//게시물 행 세기
	int getCntNotice();
	
	//공지글 목록
	List<Notice> getNoticeList(Pageable pageable);

}
