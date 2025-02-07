package gamepiece.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import gamepiece.user.board.domain.NoticeFiles;



@Mapper
@Repository("userNoticeFileMapper")
public interface NoticeFileMapper {
	
	
    List<NoticeFiles> getNoticeFiles(int noticeNum);

}
