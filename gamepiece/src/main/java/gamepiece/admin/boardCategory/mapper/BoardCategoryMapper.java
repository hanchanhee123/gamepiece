package gamepiece.admin.boardCategory.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.board.domain.Board;
import gamepiece.admin.boardCategory.domain.BoardCategory;
import gamepiece.util.Pageable;

@Mapper
public interface BoardCategoryMapper {
	
	//카테고리삭제
	int removeCategory(String categoryCode);
	
	//카테고리 수정
	int modifyCategory(BoardCategory boardCategory);
	
	//특정 카테고리 조회
	BoardCategory getCategoryInfo(String categoryCode);
	
	//카테고리 추가
	int addBoardCategory(BoardCategory boardCategory);
	
	//카테고리 행 세기
	 int getCntBoardCategory();
	
	//카테고리목록
	List<BoardCategory> getBoardCategoryList(Pageable pageable);
	
	//카테고리리스트
	List<BoardCategory> BoardCategoryList();


}
