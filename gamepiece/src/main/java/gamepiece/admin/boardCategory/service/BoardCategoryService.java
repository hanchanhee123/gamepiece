package gamepiece.admin.boardCategory.service;




import java.util.List;

import gamepiece.admin.boardCategory.domain.BoardCategory;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface BoardCategoryService {
	
	//카테고리삭제
	int removeCategory(String categoryCode);
	
	//카테고리 수정
	int modifyCategory(BoardCategory boardCategory);
	
	//특정 카테고리 조회
	BoardCategory getCategoryInfo(String categoryCode);

	//카테고리 추가
	int addBoardCategory(BoardCategory boardCategory);
	
	PageInfo<BoardCategory> getBoardCategoryList(Pageable pageable);
	
	List<BoardCategory> getBoardCategoryList();
	

}
