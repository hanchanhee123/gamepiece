package gamepiece.admin.board.service;

import gamepiece.admin.board.domain.Board;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;


public interface BoardService {
	
	
	//특정게시물삭제
	int removeBoard(String boardNum);
	
	//특정게시물수정
	int modifyBoard(Board board);
	
	//게시물정보조회
	Board getBoardInfo(String boardNum);
	
	//게시물수
	int getBoards(Board board);

	//게시글 추가
	int addBoard(Board board);
	
	//게시글목록
	PageInfo<Board> getBoardsList(Pageable pageable);
	
	
}
