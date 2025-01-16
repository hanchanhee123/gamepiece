package gamepiece.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import gamepiece.user.board.domain.Notice;
import gamepiece.util.Pageable;

@Mapper
@Repository("userNoticeMapper")
public interface NoticeMapper {
	
	
	//공지글 상세
	Notice getNoticeInfo(int noticeNum);
	
	//게시물 행 세기
	int getCntNotice();
	
	//공지글 목록
	List<Notice> getNoticeList(Pageable pageable);

}
