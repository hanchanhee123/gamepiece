package gamepiece.admin.boardCategory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import gamepiece.admin.boardCategory.domain.BoardCategory;
import gamepiece.admin.boardCategory.mapper.BoardCategoryMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardCategoryServiceImpl implements BoardCategoryService  {
	
	private final BoardCategoryMapper boardCategoryMapper;

	@Override
	public PageInfo<BoardCategory> getBoardCategoryList(Pageable pageable) {
	
		int rowCnt = boardCategoryMapper.getCntBoardCategory();
		List<BoardCategory> categoryList = boardCategoryMapper.getBoardCategoryList(pageable);
		
		
		return new PageInfo<>(categoryList, pageable, rowCnt);
	
		
	}

	@Override
	public int addBoardCategory(BoardCategory boardCategory) {
		
		int result = boardCategoryMapper.addBoardCategory(boardCategory);

		return result;
	}

	@Override
	public BoardCategory getCategoryInfo(String categoryCode) {

		return boardCategoryMapper.getCategoryInfo(categoryCode);
	}

	@Override
	public int modifyCategory(BoardCategory boardCategory) {
		// TODO Auto-generated method stub
		return boardCategoryMapper.modifyCategory(boardCategory);
	}


}
