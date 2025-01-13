package gamepiece.admin.boardComment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.admin.boardComment.domain.BoardComment;
import gamepiece.admin.boardComment.mapper.BoardCommentMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardCommentServiceImpl implements BoardCommentService {

	
	private final BoardCommentMapper boardCommentMapper;
 	

	@Override
	public int addComment(BoardComment boardComment) {
		
		int result = boardCommentMapper.addComment(boardComment);
		
		return result;
	}

	@Override
	public BoardComment getCommentInfo(String commnetNum) {
	
		return boardCommentMapper.getCommentInfo(commnetNum);
	}

	@Override
	public int modifyComment(BoardComment boardComment) {

		return boardCommentMapper.modifyComment(boardComment);
	}

	@Override
	public int removeComment(String commentNum) {
		// TODO Auto-generated method stub
		return boardCommentMapper.removeComment(commentNum);
	}
	
	@Override
	public PageInfo<BoardComment> getCommentList(Pageable pageable) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("offset", pageable.getOffset());
	    paramMap.put("rowPerPage", pageable.getRowPerPage());
	    
	    List<BoardComment> commentList = boardCommentMapper.getCommentList(pageable);
	    int rowCnt = boardCommentMapper.getCntComment();

	    return new PageInfo<>(commentList, pageable, rowCnt);
	}

	@Override
	public PageInfo<BoardComment> getBoardCommentInfo(String boardNum, Pageable pageable) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("boardNum", boardNum);
	    paramMap.put("offset", pageable.getOffset());
	    paramMap.put("rowPerPage", pageable.getRowPerPage());
	    
	    List<BoardComment> commentList = boardCommentMapper.getBoardCommentInfo(paramMap);
	    int rowCnt = boardCommentMapper.getCntBoardComment(boardNum);
	    
	    return new PageInfo<>(commentList, pageable, rowCnt);
	}


}
