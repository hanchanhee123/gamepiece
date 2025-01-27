package gamepiece.admin.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import gamepiece.admin.board.domain.AdminBoardFiles;



@Mapper
@Repository("adminBoardFileMapper")
public interface BoardFileMapper {
	
	AdminBoardFiles findByFileIdx(String fileIdx);
	int deleteFileByIdx(String fileIdx);
	AdminBoardFiles getFileInfoByIdx(String fileIdx);
	List<AdminBoardFiles> getFileList();
	int addfile(AdminBoardFiles fileInfo);
	void addfiles(List<AdminBoardFiles> fileDtoList);
	String getNextFileIdx();
	AdminBoardFiles findByBoardNum(String boardNum);
	  AdminBoardFiles findByInquiryNum(String inquiryNum);
	  AdminBoardFiles modifyFile(String boardNum);

}
