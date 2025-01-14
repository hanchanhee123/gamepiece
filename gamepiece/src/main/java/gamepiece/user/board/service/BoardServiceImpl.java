package gamepiece.user.board.service;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.user.board.domain.Board;
import gamepiece.user.board.domain.Inquiry;
import gamepiece.user.board.domain.Notice;
import gamepiece.user.board.mapper.AllBoardMapper;
import gamepiece.user.board.mapper.AttackBoardMapper;
import gamepiece.user.board.mapper.FreeBoardMapper;
import gamepiece.user.board.mapper.InfoBoardMapper;
import gamepiece.user.board.mapper.InquiryMapper;
import gamepiece.user.board.mapper.NoticeMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("userBoardService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{
	
	private final AttackBoardMapper attackBoardMapper;
	private final FreeBoardMapper freeBoardMapper;
	private final InfoBoardMapper infoBoardMapper;
	private final NoticeMapper noticeMapper;
	private final InquiryMapper inquiryMapper;
	private final AllBoardMapper allBoardMapper;
	
	
	
	@Override
	public PageInfo<Board> getFreeBoardsList(Pageable pageable) {
		
		int rowCnt = freeBoardMapper.getCntBoard();
		List<Board> freeBoardList = freeBoardMapper.getFreeBoardsList(pageable);
		freeBoardList.forEach(freeBoardInfo -> {
		    String boardNum = freeBoardInfo.getBoardNum();
	
		    String numberOnly = boardNum.substring(boardNum.indexOf("_") + 1);
		 
		    int freeBoardNum = Integer.parseInt(numberOnly);
		    
		    freeBoardInfo.setBoardNum(String.valueOf(freeBoardNum));
		    
		 
		});
		
		return new PageInfo<>(freeBoardList, pageable, rowCnt);
		
		
	}
	
	 
	
	@Override
	public PageInfo<Board> getAttackBoardsList(Pageable pageable) {
		// TODO Auto-generated method stub
		int rowCnt = attackBoardMapper.getCntBoard();
		List<Board> attackBoardList = attackBoardMapper.getAttackBoardsList(pageable);
		attackBoardList.forEach(attackBoardInfo -> {
		    String boardNum = attackBoardInfo.getBoardNum();
	
		    String numberOnly = boardNum.substring(boardNum.indexOf("_") + 1);
		 
		    int attackBoardNum = Integer.parseInt(numberOnly);
		    
		    attackBoardInfo.setBoardNum(String.valueOf(attackBoardNum));
		 
		});
		
		return new PageInfo<>(attackBoardList, pageable, rowCnt);
	}
	
	
	
	@Override
	public PageInfo<Board> getInfoBoardsList(Pageable pageable) {
		// TODO Auto-generated method stub
		int rowCnt = infoBoardMapper.getCntBoard();
		List<Board> infoBoardList = infoBoardMapper.getInfoBoardsList(pageable);
		
		infoBoardList.forEach(infoBoardInfo -> {
		    String boardNum = infoBoardInfo.getBoardNum();

		    String numberOnly = boardNum.substring(boardNum.indexOf("_") + 1);
		 
		    int infoBoardNum = Integer.parseInt(numberOnly);
		  
		    infoBoardInfo.setBoardNum(String.valueOf(infoBoardNum));
		});
		
		return new PageInfo<>(infoBoardList, pageable, rowCnt);
	}



	@Override
	public PageInfo<Notice> getNoticeList(Pageable pageable) {
		
		int rowCnt = noticeMapper.getCntNotice();
		List<Notice> noticeList = noticeMapper.getNoticeList(pageable);
		
		
		
		return new PageInfo<>(noticeList, pageable, rowCnt);
	}



	@Override
	public PageInfo<Inquiry> getInquiryList(Pageable pageable) {
		
	
		int rowCnt = inquiryMapper.getCntInquiry();
		List<Inquiry> inquiryList = inquiryMapper.getInquiryList(pageable);
		
		inquiryList.forEach(inquiryInfo -> {
		    String inquiryNum = inquiryInfo.getInquiryNum();

		    String numberOnly = inquiryNum.substring(inquiryNum.indexOf("_") + 1);
		 
		    int inquiryNumCount = Integer.parseInt(numberOnly);
		  
		    inquiryInfo.setInquiryNum(String.valueOf(inquiryNumCount));
		});
		
		return new PageInfo<>(inquiryList, pageable, rowCnt);
		
	}



	@Override
	public PageInfo<Board> getAllBoardsList(Pageable pageable) {

		int rowCnt = allBoardMapper.getCntBoard();
		List<Board> allboardList = allBoardMapper.getAllBoardsList(pageable);
		
		allboardList.forEach(allboardInfo -> {
		    String BoardNum = allboardInfo.getBoardNum();

		    String numberOnly = BoardNum.substring(BoardNum.indexOf("_") + 1);
		 
		    int allBoardNum = Integer.parseInt(numberOnly);
		  
		    allboardInfo.setBoardNum(String.valueOf(allBoardNum));
		});
		
		allboardList.forEach(allboardInfo -> {
			String CategoryCode = allboardInfo.getBoardCategory(); 
			switch (CategoryCode) {
				case "bbs_cate_01" -> {
					allboardInfo.setBoardCategory("자유게시판");
				}
				case "bbs_cate_02" -> allboardInfo.setBoardCategory("공략게시판");
				case "bbs_cate_03" -> allboardInfo.setBoardCategory("정보게시판");
			
			}
		});
		
		
		
		return new PageInfo<>(allboardList, pageable, rowCnt);
		
		
		
	}



	@Override
	public int addBoard(Board board) {
	
	    return allBoardMapper.addBoard(board);
	}



	@Override
	public int addInquiry(Inquiry inquiry) {
	
		return inquiryMapper.addInquiry(inquiry);
	}



	@Override
	public Board getBoardInfo(String boardNum) {
		// TODO Auto-generated method stub
		return allBoardMapper.getBoardInfo(boardNum);
	}
	
	
	

	}
	
	
	
	
	
	

	
	


