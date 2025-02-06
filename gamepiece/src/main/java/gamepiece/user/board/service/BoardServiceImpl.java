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
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gamepiece.user.board.domain.Board;
import gamepiece.user.board.domain.BoardComment;
import gamepiece.user.board.domain.BoardCommentLikes;
import gamepiece.user.board.domain.BoardFiles;
import gamepiece.user.board.domain.BoardLikes;
import gamepiece.user.board.domain.BoardsFiles;
import gamepiece.user.board.domain.Inquiry;
import gamepiece.user.board.domain.InquiryFiles;
import gamepiece.user.board.domain.InquiryRespone;
import gamepiece.user.board.domain.Notice;
import gamepiece.user.board.domain.NoticeFiles;
import gamepiece.user.board.domain.Report;
import gamepiece.user.board.mapper.AllBoardMapper;
import gamepiece.user.board.mapper.AttackBoardMapper;
import gamepiece.user.board.mapper.BoardCommentLikeMapper;
import gamepiece.user.board.mapper.BoardCommentMapper;
import gamepiece.user.board.mapper.BoardFileMapper;
import gamepiece.user.board.mapper.BoardFilesMapper;
import gamepiece.user.board.mapper.BoardLikeMapper;
import gamepiece.user.board.mapper.FreeBoardMapper;
import gamepiece.user.board.mapper.InfoBoardMapper;
import gamepiece.user.board.mapper.InquiryFilesMapper;
import gamepiece.user.board.mapper.InquiryMapper;
import gamepiece.user.board.mapper.InquiryResponeMapper;
import gamepiece.user.board.mapper.NoticeFileMapper;
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
	private final BoardFilesMapper boardFilesMapper;
	private final InquiryFilesMapper inquiryFilesMapper;
	private final NoticeFileMapper noticeFileMapper;
 
	
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

	public String generateInquiryNum() {
        // 마지막 inquiry 번호를 가져와서 +1
        String lastNum = inquiryMapper.getLastInquiryNum();
        if (lastNum == null) {
            return "inq_01";
        }
        
        // 번호 부분만 추출해서 증가
        int num = Integer.parseInt(lastNum.replace("inq_", "")) + 1;
        return String.format("inq_%02d", num);
    }
    
	  public String getLatestInquiryNum() {
	        String lastNum = inquiryMapper.getLastInquiryNum();
	        if (lastNum == null) {
	            return "inq_01";
	        }
	        return lastNum;
	    }

	@Override
	public int addInquiry(Inquiry inquiry) {
		
		 String newInquiryNum = generateInquiryNum();
	        inquiry.setInquiryNum(newInquiryNum);
	
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
	    return allBoardMapper.modifyBoard(board);
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
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Map<String, Object> addBoardLike(String boardNum, String userId, String likesType) {
	    Map<String, Object> result = new HashMap<>();
	    
	    // 현재 클릭한 타입의 반대 타입
	    String oppositeType = "좋아요".equals(likesType) ? "싫어요" : "좋아요";
	    
	    // 반대 타입의 좋아요/싫어요가 있는지 확인
	    Map<String, String> oppositeParams = new HashMap<>();
	    oppositeParams.put("boardNum", boardNum);
	    oppositeParams.put("userId", userId);
	    oppositeParams.put("likesType", oppositeType);
	    
	    // 반대 타입이 있으면 삭제
	    BoardLikes oppositeLike = boardLikeMapper.getBoardLikesByUser(oppositeParams);
	    if (oppositeLike != null) {
	        boardLikeMapper.removeBoardLikes(boardNum, userId, oppositeType);
	        if ("좋아요".equals(oppositeType)) {
	            allBoardMapper.cancelLikeCount(boardNum);
	        } else {
	            allBoardMapper.cancelDisLikeCount(boardNum);
	        }
	    }
	    
	    // 현재 타입 처리
	    Map<String, String> currentParams = new HashMap<>();
	    currentParams.put("boardNum", boardNum);
	    currentParams.put("userId", userId);
	    currentParams.put("likesType", likesType);
	    
	    BoardLikes currentLike = boardLikeMapper.getBoardLikesByUser(currentParams);
	    
	    if (currentLike != null) {
	        // 취소
	        boardLikeMapper.removeBoardLikes(boardNum, userId, likesType);
	        if ("좋아요".equals(oppositeType)) {
	            allBoardMapper.cancelLikeCount(boardNum);
	        } else {
	            allBoardMapper.cancelDisLikeCount(boardNum);
	        }
	        result.put("action", "remove");
	    } else {
	        // 추가
	        BoardLikes boardLikes = new BoardLikes();
	        boardLikes.setBoardNum(boardNum);
	        boardLikes.setUserId(userId);
	        boardLikes.setLikesType(likesType);
	        boardLikeMapper.addBoardLikes(boardLikes);
	        
	        if ("좋아요".equals(likesType)) {
	            allBoardMapper.addLikeCount(boardNum);
	        } else {
	            allBoardMapper.addDisLikeCount(boardNum);
	        }
	        result.put("action", "add");
	    }
	    
	    result.put("success", true);
	    return result;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Map<String, Object> addCommentLike(String commentNum, String userId, String likesType) {
	    Map<String, Object> result = new HashMap<>();
	    
	    // 현재 클릭한 타입의 반대 타입
	    String oppositeType = "좋아요".equals(likesType) ? "싫어요" : "좋아요";
	    
	    // 반대 타입의 좋아요/싫어요가 있는지 확인
	    Map<String, String> oppositeParams = new HashMap<>();
	    oppositeParams.put("commentNum", commentNum);
	    oppositeParams.put("userId", userId);
	    oppositeParams.put("likesType", oppositeType);
	    
	    // 반대 타입이 있으면 삭제
	    BoardCommentLikes oppositeLike = boardCommentLikeMapper.getCommentLikesByUser(oppositeParams);
	    if (oppositeLike != null) {
	        boardCommentLikeMapper.removeCommentLikes(commentNum, userId, oppositeType);
	        if ("좋아요".equals(oppositeType)) {
	            boardCommentMapper.cancelCommentLikeCount(commentNum);
	        } else {
	            boardCommentMapper.cancelCommentDisLikeCount(commentNum);
	        }
	    }
	    
	    // 현재 타입 처리
	    Map<String, String> currentParams = new HashMap<>();
	    currentParams.put("commentNum", commentNum);
	    currentParams.put("userId", userId);
	    currentParams.put("likesType", likesType);
	    
	    BoardCommentLikes currentLike = boardCommentLikeMapper.getCommentLikesByUser(currentParams);
	    
	    if (currentLike != null) {
	        // 취소
	        boardCommentLikeMapper.removeCommentLikes(commentNum, userId, likesType);
	        if ("좋아요".equals(likesType)) {
	            boardCommentMapper.cancelCommentLikeCount(commentNum);
	        } else {
	            boardCommentMapper.cancelCommentDisLikeCount(commentNum);
	        }
	        result.put("action", "remove");
	    } else {
	        // 추가
	        BoardCommentLikes boardCommentLikes = new BoardCommentLikes();
	        boardCommentLikes.setCommentNum(commentNum);
	        boardCommentLikes.setUserId(userId);
	        boardCommentLikes.setLikesType(likesType);
	        boardCommentLikeMapper.addCommentLikes(boardCommentLikes);
	        
	        if ("좋아요".equals(likesType)) {
	            boardCommentMapper.addCommentLikeCount(commentNum);
	        } else {
	            boardCommentMapper.addCommentDisLikeCount(commentNum);
	        }
	        result.put("action", "add");
	    }
	    
	    result.put("success", true);
	    return result;
	}



	 @Override
	    public List<BoardsFiles> getBoardFiles(String boardNum) {
	        return boardFilesMapper.getBoardFiles(boardNum);
	    }

	    @Override
	    public void addBoardFileMapping(String boardNum, String fileIdx) {
	        BoardsFiles mapping = new BoardsFiles();
	        mapping.setBoardNum(boardNum);
	        mapping.setFileIdx(fileIdx);
	        boardFilesMapper.insertMapping(mapping);
	    }

	    @Override
	    public void deleteBoardFileMapping(String boardNum, String fileIdx) {
	        boardFilesMapper.deleteFileMapping(boardNum, fileIdx);
	    }

	    @Override
	    public void deleteAllBoardFiles(String boardNum) {
	        boardFilesMapper.deleteAllFileMapping(boardNum);
	    }

	    @Override
	    @Transactional
	    public void updateBoardFiles(String boardNum, List<MultipartFile> newFiles) {
	        // 1. 기존 파일 매핑 모두 삭제
	        deleteAllBoardFiles(boardNum);

	        // 2. 새 파일들 저장 및 매핑
	        for(MultipartFile file : newFiles) {
	            if(!file.isEmpty()) {
	                BoardFiles fileInfo = saveFile(file); // 파일 저장 메서드 (기존 로직 사용)
	                addBoardFileMapping(boardNum, fileInfo.getFileIdx());
	            }
	        }
	    }



	    @Transactional
	    public BoardFiles saveFile(MultipartFile file) {
	        try {
	            String fileIdx = boardFileMapper.getNextFileIdx();
	            String originalFileName = file.getOriginalFilename();
	            String newFileName = UUID.randomUUID().toString() + "_" + originalFileName;
	            String filePath = "/attachment/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	            
	            BoardFiles boardFile = BoardFiles.builder()
	                    .fileIdx(fileIdx)
	                    .fileOriginalName(originalFileName)
	                    .fileNewName(newFileName)
	                    .filePath(filePath)
	                    .fileSize(file.getSize())
	                    .build();

	            // 파일 저장 로직
	            File saveFile = new File(filePath);
	            if (!saveFile.exists()) {
	                saveFile.mkdirs();
	            }
	            file.transferTo(new File(filePath + File.separator + newFileName));

	            return boardFile;
	        } catch (Exception e) {
	            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
	        }
	    }



		@Override
		public List<InquiryFiles> getInquiryFiles(String inquiryNum) {
			// TODO Auto-generated method stub
			return inquiryFilesMapper.getInquiryFiles(inquiryNum);
		}



		@Override
		@Transactional
		public void addInquiryWithFiles(Inquiry inquiry, MultipartFile[] files) {
		    try {
		        // 1. 문의글 번호 생성 및 저장
		        String newInquiryNum = generateInquiryNum();
		        inquiry.setInquiryNum(newInquiryNum);
		        int result = inquiryMapper.addInquiry(inquiry);
		        
		        if (result <= 0) {
		            throw new RuntimeException("문의글 저장에 실패했습니다.");
		        }

		        // 2. 파일 처리
		        if (files != null && files.length > 0 && !files[0].isEmpty()) {
		            // 파일 업로드 및 files 테이블에 저장
		            List<BoardFiles> uploadedFiles = boardFilesUtils.uploadFiles(files);
		            if (!uploadedFiles.isEmpty()) {
		                // files 테이블에 저장
		                boardFileMapper.addfiles(uploadedFiles);

		                // 3. 매핑 정보 저장
		                for (BoardFiles file : uploadedFiles) {
		                    InquiryFiles mapping = InquiryFiles.builder()
		                            .inquiryNum(newInquiryNum)
		                            .fileIdx(file.getFileIdx())
		                            .fileInfo(file)
		                            .build();
		                    
		                    inquiryFilesMapper.addInquiryFileMapping(mapping);
		                }
		            }
		        }
		    } catch (Exception e) {
		        log.error("문의글 및 파일 저장 중 오류 발생: ", e);
		        throw new RuntimeException("문의글 및 파일 저장 중 오류가 발생했습니다.", e);
		    }
		}

	

		@Override
		@Transactional
		public void deleteInquiryFile(String inquiryNum, String fileIdx) {
		    try {
		        // 1. 매핑 정보 삭제
		        inquiryFilesMapper.removeInquiryMapping(fileIdx);
		        
		        // 2. 실제 파일 삭제
		        BoardFiles fileInfo = boardFileMapper.getFileInfoByIdx(fileIdx);
		        if (fileInfo != null) {
		            String filePath = fileInfo.getFilePath();
		            boolean isDeleted = boardFilesUtils.deleteFileByPath(filePath);
		            
		            // 3. files 테이블에서 레코드 삭제
		            if (isDeleted) {
		                boardFileMapper.deleteFileByIdx(fileIdx);
		            } else {
		                log.warn("파일 삭제 실패: {}", filePath);
		            }
		        }
		    } catch (Exception e) {
		        log.error("문의글 파일 삭제 중 오류 발생: ", e);
		        throw new RuntimeException("파일 삭제 중 오류가 발생했습니다.", e);
		    }
		}

		@Override
		public Inquiry getInquiryWithFiles(String inquiryNum) {
		    // 1. 문의글 정보 조회
		    Inquiry inquiry = inquiryMapper.getInquiryInfo(inquiryNum);
		    if (inquiry != null) {
		        // 2. 첨부 파일 정보 조회
		        List<InquiryFiles> files = inquiryFilesMapper.getInquiryFiles(inquiryNum);
		        inquiry.setInquiryFiles(files);  
		    }
		    return inquiry;
		}



		@Override
		public List<NoticeFiles> getNoticeFiles(int noticeNum) {
			// TODO Auto-generated method stub
			return noticeFileMapper.getNoticeFiles(noticeNum);
		}



		}



	
	
	
	
	
	
	
	
	
	

	
	


