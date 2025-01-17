package gamepiece.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import gamepiece.user.board.domain.Board;
import gamepiece.util.Pageable;

@Mapper
@Repository("userBoardMapper")
public interface AllBoardMapper {
	
	
	//특정게시물삭제
	int removeBoard(String boardUserId);
	
	//특정게시물수정
	int modifyBoard(Board board);
	
	//특정게시물조회
	Board getBoardInfo(String boardNum);
	
	
	//게시물추가
	int addBoard(Board board);
	
	//게시물 행 세기
	int getCntBoard();
	
	//전체게시글 목록
	List<Board> getAllBoardsList(Pageable pageable);

}
