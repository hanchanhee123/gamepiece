package gamepiece.user.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import gamepiece.user.board.domain.Board;
import gamepiece.user.board.domain.BoardFiles;
import gamepiece.util.Pageable;

@Mapper
@Repository("userBoardMapper")
public interface AllBoardMapper {
	
	
    void modifyFileIdxToNull(String fileIdx);
	
	//좋아요 증가
	int addLikeCount(String boardNum);
	
	//싫어요 증가
	int addDisLikeCount(String boardNum);
	

	//게시판 파일 조회
	BoardFiles getBoardFile(String boardNum);
	
	//게시판 조회수 증가
	int addViewCount(String boardNum);
	
	//게시판 검색행 세기
	int getCntSearchBoard(Map<String, Object> searchMap);

	//게시판 검색조회
	List<Board> getBoardSearchList(Map<String, Object> searchMap);
	
	
	//특정게시물삭제
	int removeBoard(String boardNum);
	
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
