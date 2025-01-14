package gamepiece.admin.board.service;




import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;


public interface BoardService {
	
	
	// 회원목록 검색조회
	PageInfo<gamepiece.admin.board.domain.Board> getSearchList(String searchCate, String searchValue, Pageable pageable);
	
	//특정게시물삭제
	int removeBoard(String boardNum);
	
	//게시물정보조회
	gamepiece.admin.board.domain.Board getBoardInfo(String boardNum);
	

	//게시글목록
	PageInfo<gamepiece.admin.board.domain.Board> getBoardsList(Pageable pageable);

	

	
	
}
