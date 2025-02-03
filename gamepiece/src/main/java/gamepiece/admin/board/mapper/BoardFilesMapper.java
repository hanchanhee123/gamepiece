package gamepiece.admin.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import gamepiece.admin.board.domain.AdminBoardsFiles;



@Mapper
@Repository("adminBoardFilesMapper")
public interface BoardFilesMapper {
	
	  List<AdminBoardsFiles> getBoardFiles(String boardNum);
	   

}
