package gamepiece.admin.board.service;

import java.util.List;

import gamepiece.admin.board.domain.Board;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;


public interface BoardService {
	
	
	// 회원목록 검색조회
	PageInfo<Board> getSearchList(String searchCate, String searchValue, Pageable pageable);
	
	//특정게시물삭제
	int removeBoard(String boardNum);
	
	//게시물정보조회
	Board getBoardInfo(String boardNum);
	

	//게시글목록
	PageInfo<Board> getBoardsList(Pageable pageable);

	

	
	
}
