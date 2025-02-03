package gamepiece.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import gamepiece.user.board.domain.BoardsFiles;

@Mapper
public interface BoardFilesMapper {
 
    List<BoardsFiles> getBoardFiles(String boardNum);
    List<BoardsFiles> getMappingsByBoardNum(String boardNum);
    int insertMapping(BoardsFiles mapping);
    int deleteMapping(String bfIdx);

    int deleteAllFileMapping(String boardNum);
    int deleteFileMapping(@Param("boardNum") String boardNum, @Param("fileIdx") String fileIdx);
    int addFileMapping(@Param("boardNum") String boardNum, @Param("fileIdx") String fileIdx);
}