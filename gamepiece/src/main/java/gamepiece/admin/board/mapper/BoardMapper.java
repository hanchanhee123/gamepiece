package gamepiece.admin.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.board.domain.Board;
import gamepiece.util.Pageable;

@Mapper
public interface BoardMapper {
	
	//게시물 행 세기
	int getCntBoard();
	
	//특정게시물삭제
	int removeBoard(String boardNum);
	
	//특정게시물수정
	int modifyBoard(Board board);
	
	//특정게시물조회
	Board getBoardInfo(String boardNum);
	
	//게시판 수
	 int getBoards(Board board);
	
	//게시물추가
	int addBoard(Board board);
	
	//게시글목록
	List<Board> getBoardsList(Pageable pageable);

}
