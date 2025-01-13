package gamepiece.admin.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.admin.board.domain.Board;
import gamepiece.admin.board.mapper.BoardMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{
	
	private final BoardMapper boardMapper;
	
	
	@Override
	public PageInfo<Board> getBoardsList(Pageable pageable) {
		
		
		
	
		
			int rowCnt = boardMapper.getCntBoard();
			List<Board> BoardList = boardMapper.getBoardsList(pageable);
			
	
		BoardList.forEach(boardInfo -> {
			String CategoryCode = boardInfo.getBoardCategory(); 
			switch (CategoryCode) {
				case "bbs_cate_01" -> {
					boardInfo.setBoardCategory("자유게시판");
				}
				case "bbs_cate_02" -> boardInfo.setBoardCategory("공략게시판");
				case "bbs_cate_03" -> boardInfo.setBoardCategory("정보게시판");
			
			}
		});
		
		
		return new PageInfo<>(BoardList, pageable, rowCnt);
	}






	@Override
	public Board getBoardInfo(String boardNum) {
		// TODO Auto-generated method stub
		return boardMapper.getBoardInfo(boardNum);
	}



	@Override
	public int removeBoard(String boardNum) {
		// TODO Auto-generated method stub
		return boardMapper.removeBoard(boardNum);
		
		
		
	}









	@Override
	public PageInfo<Board> getSearchList(String searchCate, String searchValue, Pageable pageable) {
	   Map<String, Object> searchMap = new HashMap<String, Object>();
	   
	
	   searchMap.put("searchCate", searchCate);
	   searchMap.put("searchValue", searchValue);
	   searchMap.put("offset", pageable.getOffset());
	   searchMap.put("rowPerPage", pageable.getRowPerPage());

	   
	   int rowCnt = boardMapper.getCntSearchBoard(searchMap);
	   List<Board> boardList = boardMapper.getBoardSearchList(searchMap);
	   boardList.forEach(boardInfo -> {
			String CategoryCode = boardInfo.getBoardCategory(); 
			switch (CategoryCode) {
				case "bbs_cate_01" -> {
					boardInfo.setBoardCategory("자유게시판");
				}
				case "bbs_cate_02" -> boardInfo.setBoardCategory("공략게시판");
				case "bbs_cate_03" -> boardInfo.setBoardCategory("정보게시판");
			
			}
		});

	   return new PageInfo<>(boardList, pageable, rowCnt);
	}
	    
	    
	    
	

	


	



	
	
}
