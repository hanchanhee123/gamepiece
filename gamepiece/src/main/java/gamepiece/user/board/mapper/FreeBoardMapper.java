package gamepiece.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.board.domain.Board;
import gamepiece.util.Pageable;

@Mapper
public interface FreeBoardMapper {
	
	
	
	//게시물 행 세기
	int getCntBoard();
	
	//자유게시글 목록
	List<Board> getFreeBoardsList(Pageable pageable);

}
