package gamepiece.admin.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.admin.board.domain.Board;
import gamepiece.admin.board.mapper.BoardMapper;
import gamepiece.admin.boardComment.domain.BoardComment;
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
	public int addBoard(Board board) {
		int result = boardMapper.addBoard(board);
		return result;
	}


	@Override
	public int getBoards(Board board) {
		int result = boardMapper.getBoards(board);
		return result;
		
	}


	@Override
	public Board getBoardInfo(String boardNum) {
		// TODO Auto-generated method stub
		return boardMapper.getBoardInfo(boardNum);
	}


	@Override
	public int modifyBoard(Board board) {
		
		return boardMapper.modifyBoard(board);
	}


	@Override
	public int removeBoard(String boardNum) {
		// TODO Auto-generated method stub
		return boardMapper.removeBoard(boardNum);
		
		
		
	}





	



	
	
}
