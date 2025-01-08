package gamepiece.admin.boardComment.service;

import java.util.List;

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
	public PageInfo<BoardComment> getCommentList(Pageable pageable) {

		int rowCnt = boardCommentMapper.getCntComment();
		List<BoardComment> commentList = boardCommentMapper.getCommentList(pageable);
		
		return new PageInfo<>(commentList, pageable, rowCnt);
		
	}

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

}
