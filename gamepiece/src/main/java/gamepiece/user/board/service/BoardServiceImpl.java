package gamepiece.user.board.service;







import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gamepiece.user.board.domain.Board;
import gamepiece.user.board.domain.BoardComment;
import gamepiece.user.board.domain.BoardCommentLikes;
import gamepiece.user.board.domain.BoardFiles;
import gamepiece.user.board.domain.BoardLikes;
import gamepiece.user.board.domain.Inquiry;
import gamepiece.user.board.domain.InquiryRespone;
import gamepiece.user.board.domain.Notice;
import gamepiece.user.board.domain.Report;
import gamepiece.user.board.mapper.AllBoardMapper;
import gamepiece.user.board.mapper.AttackBoardMapper;
import gamepiece.user.board.mapper.BoardCommentLikeMapper;
import gamepiece.user.board.mapper.BoardCommentMapper;
import gamepiece.user.board.mapper.BoardFileMapper;
import gamepiece.user.board.mapper.BoardLikeMapper;
import gamepiece.user.board.mapper.FreeBoardMapper;
import gamepiece.user.board.mapper.InfoBoardMapper;
import gamepiece.user.board.mapper.InquiryMapper;
import gamepiece.user.board.mapper.InquiryResponeMapper;
import gamepiece.user.board.mapper.NoticeMapper;
import gamepiece.user.board.mapper.ReportMapper;
import gamepiece.user.board.util.BoardFilesUtils;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("userBoardService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{
	
	

    @Value("${file.path}")
    private String fileRealPath;  
	
	private final AttackBoardMapper attackBoardMapper;
	private final FreeBoardMapper freeBoardMapper;
	private final InfoBoardMapper infoBoardMapper;
	private final NoticeMapper noticeMapper;
	private final InquiryMapper inquiryMapper;
	private final AllBoardMapper allBoardMapper;
	private final BoardCommentMapper boardCommentMapper;
	private final InquiryResponeMapper inquiryResponeMapper;
	private final ReportMapper reportMapper; 
	private final BoardFileMapper boardFileMapper; 
	private final BoardFilesUtils boardFilesUtils;
	private final BoardLikeMapper boardLikeMapper;
	private final BoardCommentLikeMapper boardCommentLikeMapper;

 
	
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



	@Override
	public PageInfo<BoardComment> getBoardCommentInfo(String boardNum, Pageable pageable) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("boardNum", boardNum);
	    paramMap.put("offset", pageable.getOffset());
	    paramMap.put("rowPerPage", pageable.getRowPerPage());

	    try {
	        List<BoardComment> commentList = boardCommentMapper.getBoardCommentInfo(paramMap);
	        int rowCnt = boardCommentMapper.getCntBoardComment(paramMap);
	        return new PageInfo<>(commentList, pageable, rowCnt);
	    } catch(Exception e) {
	        // 에러가 발생하면 빈 결과 반환
	        return new PageInfo<>(new ArrayList<>(), pageable, 0);
	    }
	}



	@Override
	public int addComment(BoardComment boardComment) {
		// TODO Auto-generated method stub
	
		int result = boardCommentMapper.addComment(boardComment);
		
		return result;
		
	}



	@Override
	public Notice getNoticeInfo(int noticeNum) {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeInfo(noticeNum);
	}



	@Override
	public Inquiry getInquiryInfo(String inquiryNum) {
		// TODO Auto-generated method stub
		return inquiryMapper.getInquiryInfo(inquiryNum);
	}



	@Override
	public InquiryRespone getInquiryResponeInfo(String inquiryNum) {
		// TODO Auto-generated method stub
		return inquiryResponeMapper.getInquiryResponeInfo(inquiryNum);
	}



	@Override
	public int addReport(Report report) {
		// TODO Auto-generated method stub
		
		return reportMapper.addReport(report);
	}


	@Override
	public int removeBoard(String boardNum) {
	    // 숫자만 들어온 경우 'bbs_' 접두사 추가
	    if (!boardNum.startsWith("bbs_")) {
	        boardNum = "bbs_" + boardNum;
	    }
	    return allBoardMapper.removeBoard(boardNum);
	}


	@Override
	public int modifyBoard(Board board) {
		// TODO Auto-generated method stub
	    if (board.getFileIdx() != null) {
	        return allBoardMapper.modifyBoard(board);
	    }
	    return 0;
	}



	@Override
	public PageInfo<Board> getSearchList(String searchValue, Pageable pageable) {
	    Map<String, Object> searchMap = new HashMap<String, Object>();
	    
	    searchMap.put("searchValue", searchValue);
	    searchMap.put("offset", pageable.getOffset());
	    searchMap.put("rowPerPage", pageable.getRowPerPage());

	    int rowCnt = allBoardMapper.getCntSearchBoard(searchMap);
	   
	    
	    List<Board> allboardList = allBoardMapper.getBoardSearchList(searchMap);
	
	    allboardList.forEach(allboardInfo -> {
		    String BoardNum = allboardInfo.getBoardNum();

		    String numberOnly = BoardNum.substring(BoardNum.indexOf("_") + 1);
		 
		    int allBoardNum = Integer.parseInt(numberOnly);
		  
		    allboardInfo.setBoardNum(String.valueOf(allBoardNum));
		});

	    allboardList.forEach(allboardInfo -> {
	        String CategoryCode = allboardInfo.getBoardCategory();
	        switch (CategoryCode) {
	            case "bbs_cate_01":
	                allboardInfo.setBoardCategory("자유게시판");
	                break;
	            case "bbs_cate_02":
	                allboardInfo.setBoardCategory("공략게시판");
	                break;
	            case "bbs_cate_03":
	                allboardInfo.setBoardCategory("정보게시판");
	                break;
	        }
	    });

	    return new PageInfo<>(allboardList, pageable, rowCnt);
	}



	@Override
	public PageInfo<Board> getAttackSearchList(String searchValue, Pageable pageable) {
	    Map<String, Object> searchMap = new HashMap<String, Object>();
		
		searchMap.put("searchValue", searchValue);
	    searchMap.put("offset", pageable.getOffset());
	    searchMap.put("rowPerPage", pageable.getRowPerPage());

	    int rowCnt = attackBoardMapper.getCntSearchAttackBoard(searchMap);
	   
	    
	    List<Board> attackBoardList = attackBoardMapper.getAttackBoardSearchList(searchMap);
	
	    attackBoardList.forEach(attackBoardInfo -> {
		    String BoardNum = attackBoardInfo.getBoardNum();

		    String numberOnly = BoardNum.substring(BoardNum.indexOf("_") + 1);
		 
		    int attackBoardNum = Integer.parseInt(numberOnly);
		  
		    attackBoardInfo.setBoardNum(String.valueOf(attackBoardNum));
		});



	    return new PageInfo<>(attackBoardList, pageable, rowCnt);

	
	}



	@Override
	public PageInfo<Board> getFreeSearchList(String searchValue, Pageable pageable) {
		
		  Map<String, Object> searchMap = new HashMap<String, Object>();
			
			searchMap.put("searchValue", searchValue);
		    searchMap.put("offset", pageable.getOffset());
		    searchMap.put("rowPerPage", pageable.getRowPerPage());

		    int rowCnt = freeBoardMapper.getCntSearchFreeBoard(searchMap);
		   
		    
		    List<Board> freeBoardList = freeBoardMapper.getFreeBoardSearchList(searchMap);
		
		    freeBoardList.forEach(freeBoardInfo -> {
			    String BoardNum = freeBoardInfo.getBoardNum();

			    String numberOnly = BoardNum.substring(BoardNum.indexOf("_") + 1);
			 
			    int freeBoardNum = Integer.parseInt(numberOnly);
			  
			    freeBoardInfo.setBoardNum(String.valueOf(freeBoardNum));
			});



		    return new PageInfo<>(freeBoardList, pageable, rowCnt);
	}



	@Override
	public PageInfo<Board> getInfoSearchList(String searchValue, Pageable pageable) {
	
		  Map<String, Object> searchMap = new HashMap<String, Object>();
			
			searchMap.put("searchValue", searchValue);
		    searchMap.put("offset", pageable.getOffset());
		    searchMap.put("rowPerPage", pageable.getRowPerPage());

		    int rowCnt = infoBoardMapper.getCntSearchInfoBoard(searchMap);
		   
		    
		    List<Board> infoBoardList = infoBoardMapper.getInfoBoardSearchList(searchMap);
		
		    infoBoardList.forEach(infoBoardInfo -> {
			    String BoardNum = infoBoardInfo.getBoardNum();

			    String numberOnly = BoardNum.substring(BoardNum.indexOf("_") + 1);
			 
			    int infoBoardNum = Integer.parseInt(numberOnly);
			  
			    infoBoardInfo.setBoardNum(String.valueOf(infoBoardNum));
			});



		    return new PageInfo<>(infoBoardList, pageable, rowCnt);
			
		
	}



	@Override
	public PageInfo<Inquiry> getInquirySearchList(String searchValue, Pageable pageable) {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		searchMap.put("searchValue", searchValue);
	    searchMap.put("offset", pageable.getOffset());
	    searchMap.put("rowPerPage", pageable.getRowPerPage());

	    int rowCnt = inquiryMapper.getCntSearchInquiry(searchMap);
	   
	    
	    List<Inquiry> inquiryList = inquiryMapper.getInquirySearchList(searchMap);
	
		inquiryList.forEach(inquiryInfo -> {
		    String inquiryNum = inquiryInfo.getInquiryNum();

		    String numberOnly = inquiryNum.substring(inquiryNum.indexOf("_") + 1);
		 
		    int inquiryNumCount = Integer.parseInt(numberOnly);
		  
		    inquiryInfo.setInquiryNum(String.valueOf(inquiryNumCount));
		});

	    
		return new PageInfo<>(inquiryList, pageable, rowCnt);
		
	
			
	}



	@Override
	public PageInfo<Notice> getNoticeSearchList(String searchValue, Pageable pageable) {
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		searchMap.put("searchValue", searchValue);
	    searchMap.put("offset", pageable.getOffset());
	    searchMap.put("rowPerPage", pageable.getRowPerPage());

	    int rowCnt = noticeMapper.getCntSearchNotice(searchMap);
	    
	    List<Notice> noticeList = noticeMapper.getNoticeSearchList(searchMap);
		
		
	    return new PageInfo<>(noticeList, pageable, rowCnt);
		
		
	}



	@Override
	public int removeComment(String commentNum) {
		// TODO Auto-generated method stub
		return boardCommentMapper.removeComment(commentNum);
	}



	@Override
	public int modifyComment(BoardComment boardComment) {
		// TODO Auto-generated method stub
		return boardCommentMapper.modifyComment(boardComment);
	}



	@Override
	public int addViewCount(String boardNum) {
		// TODO Auto-generated method stub
		return allBoardMapper.addViewCount(boardNum);
	}



	@Override
	public int addNoticeViewCount(int noticeNum) {
		// TODO Auto-generated method stub
		return noticeMapper.addViewCount(noticeNum);
	}

	@Override
	@Transactional
	public void deleteFile(BoardFiles fileDto) {
	    try {
	        // 1. boards 테이블의 file_idx를 null로 업데이트
	    	allBoardMapper.modifyFileIdxToNull(fileDto.getFileIdx());
	        
	        // 2. 실제 파일 삭제 시도
	        String path = fileDto.getFilePath();
	        boolean isDelete = boardFilesUtils.deleteFileByPath(path);
	        
	        // 3. files 테이블에서 레코드 삭제
	        if (isDelete) {
	            boardFileMapper.deleteFileByIdx(fileDto.getFileIdx());
	        } else {
	            // 파일 삭제 실패시에도 DB 레코드는 삭제 (이미 게시글에서 참조가 제거됨)
	            boardFileMapper.deleteFileByIdx(fileDto.getFileIdx());
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("파일 삭제 중 오류 발생", e);
	    }
	}
	@Override
	public void addFile(MultipartFile file) {
		BoardFiles fileInfo = boardFilesUtils.uploadFile(file);
		if(fileInfo != null) boardFileMapper.addfile(fileInfo); 
	}
	
	@Override
	public void addFiles(MultipartFile[] files) {
		List<BoardFiles> fileList = boardFilesUtils.uploadFiles(files);
		if(!fileList.isEmpty()) boardFileMapper.addfiles(fileList);
	}

	@Override
	@Transactional
	public void addFilesWithInfo(MultipartFile[] files, List<BoardFiles> fileDtoList) {
	    // 파일 정보 먼저 저장
	    boardFileMapper.addfiles(fileDtoList);
	    log.info("데이터베이스에 {} 개의 파일 정보 저장 완료", fileDtoList.size());
	    
	    // 실제 파일 저장 로직
	    for (int i = 0; i < files.length; i++) {
	        MultipartFile file = files[i];
	        BoardFiles fileDto = fileDtoList.get(i);
	        
	        try {
	            // 실제 파일 저장 경로 재확인
	            String uploadPath = fileRealPath + File.separator + "attachment" 
	                               + File.separator + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
	                               + File.separator + (file.getContentType().contains("image") ? "image" : "file");
	            
	            // 디렉토리 생성
	            File directory = new File(uploadPath);
	            if (!directory.exists()) {
	                directory.mkdirs();
	            }
	            
	            // 파일 저장
	            File dest = new File(directory, fileDto.getFileNewName());
	            
	            // FileCopyUtils 대신 InputStream과 OutputStream 사용
	            try (var inputStream = file.getInputStream();
	                 var outputStream = new FileOutputStream(dest)) {
	                byte[] buffer = new byte[8192];
	                int bytesRead;
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);
	                }
	                outputStream.flush();
	                
	                // 파일 크기 확인
	                if (dest.length() == 0) {
	                    throw new IOException("파일이 0바이트로 저장됨");
	                }
	                
	                log.info("파일 저장 완료: {}, 크기: {} bytes", dest.getAbsolutePath(), dest.length());
	            }
	        } catch (IOException e) {
	            log.error("파일 저장 중 오류 발생: {}", e.getMessage());
	            throw new RuntimeException("파일 저장 실패: " + e.getMessage(), e);
	        }
	    }
	}

	@Override
	public BoardFiles getBoardFile(String boardNum) {
		// TODO Auto-generated method stub
		return boardFileMapper.findByBoardNum(boardNum);
	}

	
	
	@Override
	public BoardFiles getFileInfo(String fileIdx) {
	    // 로깅 추가
	    log.info("Retrieving file info for fileIdx: {}", fileIdx);
	    
	    BoardFiles fileInfo = boardFileMapper.findByFileIdx(fileIdx);
	    
	    // 로깅 추가
	    if (fileInfo == null) {
	        log.warn("No file found for fileIdx: {}", fileIdx);
	    }
	    
	    return fileInfo;
	}

	@Override
	public BoardFiles getInquiryFile(String inquiryNum) {
		// TODO Auto-generated method stub
		return boardFileMapper.findByInquiryNum(inquiryNum);
	}

	@Override
	public BoardFiles getBoardFileInfo(String boardNum) {
		// TODO Auto-generated method stub
		return allBoardMapper.getBoardFile(boardNum);
	}



	@Override
	public BoardFiles getNoticeNum(int noticeNum) {
		// TODO Auto-generated method stub
		return boardFileMapper.findByNoticeNum(noticeNum);
	}



	@Override
	public int addLikeCount(String boardNum) {
		// TODO Auto-generated method stub
		return allBoardMapper.addLikeCount(boardNum);
	}



	@Override
	public int addDisLikeCount(String boardNum) {
		// TODO Auto-generated method stub
		return allBoardMapper.addDisLikeCount(boardNum);
	}



	@Override
	public int addCommentLikeCount(String commentNum) {
		// TODO Auto-generated method stub
		return boardCommentMapper.addCommentLikeCount(commentNum);
	}



	@Override
	public int addCommentDisLikeCount(String commentNum) {
		// TODO Auto-generated method stub
		return boardCommentMapper.addCommentDisLikeCount(commentNum);
	}


	public Map<String, Object> addBoardLike(String boardNum, String userId, String likesType) {
	    Map<String, Object> result = new HashMap<>();

	    // 이미 좋아요/싫어요를 했는지 확인
	    Map<String, String> params = new HashMap<>();
	    params.put("boardNum", boardNum);
	    params.put("userId", userId);

	    BoardLikes existingLike = boardLikeMapper.getBoardLikesByUser(params);  // 메서드명 수정

	    if (existingLike != null) {
	        result.put("success", false);
	        result.put("message", "이미 누르셨습니다");
	        return result;
	    }

	    // 새로운 좋아요/싫어요 추가
	    BoardLikes boardLikes = new BoardLikes();
	    boardLikes.setBoardNum(boardNum);
	    boardLikes.setUserId(userId);
	    boardLikes.setLikesType(likesType);

	    int addResult = boardLikeMapper.addBoardLikes(boardLikes);

	    // 좋아요/싫어요 카운트 증가
	    if ("좋아요".equals(likesType)) {
	        allBoardMapper.addLikeCount(boardNum);
	    } else {
	        allBoardMapper.addDisLikeCount(boardNum);
	    }

	    result.put("success", addResult > 0);
	    return result;
	}



	@Override
	public Map<String, Object> addCommentLike(String commentNum, String userId, String likesType) {
		 Map<String, Object> result = new HashMap<>();

		    // 이미 좋아요/싫어요를 했는지 확인
		    Map<String, String> params = new HashMap<>();
		    params.put("commentNum", commentNum);
		    params.put("userId", userId);
		
		    BoardCommentLikes commentLikeInfo = boardCommentLikeMapper.getCommentLikesByUser(params);  
		    
		    if (commentLikeInfo != null) {
		        result.put("success", false);
		        result.put("message", "이미 누르셨습니다");
		        return result;
		    }

		    BoardCommentLikes boardCommentLikes = new BoardCommentLikes();
		    boardCommentLikes.setCommentNum(commentNum);
		    boardCommentLikes.setUserId(userId);
		    boardCommentLikes.setLikesType(likesType);
		    
		    int addResult = boardCommentLikeMapper.addCommentLikes(boardCommentLikes);
		    
		    // 좋아요/싫어요 카운트 증가
		    if ("좋아요".equals(likesType)) {
		        boardCommentMapper.addCommentLikeCount(commentNum);
		    } else {
		       boardCommentMapper.addCommentDisLikeCount(commentNum);
		    }
		    
		    result.put("success", addResult > 0);
		return result;
	}



	
	
	}
	
	
	
	
	
	

	
	


