package gamepiece.admin.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import gamepiece.admin.notice.domain.NoticeFiles;

@Mapper
public interface NoticeFilesMapper {
	
	List<NoticeFiles> getNoticeFiles(int noticeNum);
    List<NoticeFiles> getMappingsByNoticeNum(int noticeNum);
    int addNoticeMapping(NoticeFiles mapping);
    int removeNoticeMapping(String nfIdx);

    int deleteAllFileMapping(int noticeNum);
    int deleteFileMapping(@Param("noticeNum") int noticeNum, @Param("fileIdx") String fileIdx);
    int addFileMapping(@Param("noticeNum") int noticeNum, @Param("fileIdx") String fileIdx);

}
