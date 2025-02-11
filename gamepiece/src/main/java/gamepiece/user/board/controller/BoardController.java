package gamepiece.user.board.controller;




import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.board.domain.AdminBoardFiles;
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
import gamepiece.user.board.mapper.BoardCommentLikeMapper;
import gamepiece.user.board.mapper.BoardFileMapper;
import gamepiece.user.board.mapper.BoardFilesMapper;
import gamepiece.user.board.mapper.BoardLikeMapper;
import gamepiece.user.board.mapper.InquiryFilesMapper;
import gamepiece.user.board.mapper.NoticeFileMapper;
import gamepiece.user.board.service.BoardService;
import gamepiece.user.board.util.BoardFilesUtils;
import gamepiece.user.user.service.UserService;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Controller("userBoardController")
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

	
    @Value("${file.path}")
    private String fileRealPath;  
	
	private final BoardService boardService;

	
	private final BoardLikeMapper boardLikeMapper; 
	private final BoardCommentLikeMapper boardCommentLikeMapper;
	private final BoardFileMapper boardFileMapper;
	private final BoardFilesUtils boardFilesUtils;
	private final gamepiece.admin.board.mapper.BoardFileMapper adminBoardFileMapper;
	private final BoardFilesMapper boardFilesMapper;
	private final InquiryFilesMapper inquiryFilesMapper;
	private final NoticeFileMapper noticeFileMapper;
	private final UserService userService;

	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam String fileIdx,
	                                         HttpServletRequest request
	                                         , HttpSession session, Model model) {
		

		 String id = (String) session.getAttribute("SID");

	        String avatar = userService.getUserAvatar(id);
	        model.addAttribute("avatar", avatar);
		
		
	    try {
	        AdminBoardFiles fileDto = adminBoardFileMapper.getFileInfoByIdx(fileIdx);
	        if(fileDto == null) {
	            return ResponseEntity.notFound().build();
	        }

	        String fullPath;
	        String filePath = fileDto.getFilePath();
	        
	        // 공지사항과 일반게시판/문의 구분
	        if (filePath.endsWith("/image") || filePath.endsWith("/file")) {
	            // 공지사항: /attachment/20250205/image + 파일명
	            fullPath = fileRealPath + filePath + "/" + fileDto.getFileNewName();
	        } else {
	            // 일반게시판/문의: /attachment/20250205/image/파일명.jpg
	            fullPath = fileRealPath + filePath;  // 파일명이 이미 경로에 포함되어 있음
	        }

	        log.info("=== 파일 다운로드 디버깅 정보 ===");
	        log.info("파일 ID: {}", fileIdx);
	        log.info("원본 파일 경로: {}", filePath);
	        log.info("파일명: {}", fileDto.getFileNewName());
	        log.info("최종 시도 경로: {}", fullPath);

	        Path path = Paths.get(fullPath);
	        Resource resource = new UrlResource(path.toUri());

	        if(!resource.exists()) {
	            log.error("파일을 찾을 수 없음: {}", fullPath);
	            return ResponseEntity.notFound().build();
	        }

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.setContentDisposition(ContentDisposition.attachment()
	                .filename(fileDto.getFileOriginalName(), StandardCharsets.UTF_8)
	                .build());
	        
	        return ResponseEntity.ok()
	                .headers(headers)
	                .body(resource);
	                
	    } catch (Exception e) {
	        log.error("파일 다운로드 중 오류 발생: ", e);
	        return ResponseEntity.internalServerError().build();
	    }
	}
	
	@PostMapping("/uploadFiles")
	public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile[] files) {
	    // 파일 업로드
		 try {
		        if (files != null && files.length > 0) {
		            boardService.addFiles(files);
		            return ResponseEntity.ok("파일 업로드 성공");
		        }
		        return ResponseEntity.ok("업로드할 파일이 없습니다.");
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                           .body("파일 업로드 실패: " + e.getMessage());
		    }
	}
	
	
	
	@PostMapping("/modifycomment")
	public String modifyComment(@RequestParam String commentNum,
	                            @RequestParam String commentDetail,
	                            @RequestParam String boardNum) {
		
		
		
	    BoardComment boardComment = new BoardComment();
	    boardComment.setCommentNum(commentNum);
	    boardComment.setCommentDetail(commentDetail);
	    boardComment.setBoardNum(boardNum);

	    int result = boardService.modifyComment(boardComment);

	    return "redirect:/board/detail?boardNum=" + boardNum;
	}
	
	
	@GetMapping("/removecomment")
	public String removeComment(@RequestParam String commentNum, 
	                          @RequestParam String boardNum,RedirectAttributes rttr , HttpSession session,
	                          Model model) {
		

		 String id = (String) session.getAttribute("SID");

	        String avatar = userService.getUserAvatar(id);
	        model.addAttribute("avatar", avatar);
		
		int result = boardService.removeComment(commentNum);
		rttr.addFlashAttribute("commentDel","덧글이 삭제되었습니다");
		
	    boardService.removeComment(commentNum);  
	    return "redirect:/board/detail?boardNum=" + boardNum;
	}

	
	
	
	
	@PostMapping("/uploadImage")
	public ResponseEntity<Map<String, Object>> uploadEditorImage(@RequestPart(name = "Filedata") MultipartFile file) {
	    Map<String, Object> result = new HashMap<>();
	    

	    try {
	        // 파일 저장 처리
	        boardService.addFile(file);  // 파일은 저장되지만 반환값이 없음

	        // 저장된 파일의 경로와 이름을 직접 설정 (가정)
	        String savedFilePath = "/attachment/" + file.getOriginalFilename(); // 예시
	        String originalFileName = file.getOriginalFilename();

	        result.put("url", savedFilePath);
	        result.put("originalFileName", originalFileName);
	        result.put("success", true);
	        return ResponseEntity.ok(result);

	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("success", false);
	        result.put("error", e.getMessage());
	        return ResponseEntity.internalServerError().body(result);
	    }
	}
	
	
	
	
	@PostMapping("/noticeList")
	public String noticeBoardSearch(@RequestParam(value="searchValue") String searchValue,
			Pageable pageable,
			Model model) {
		
		PageInfo<Notice> pageInfo =  boardService.getNoticeSearchList(searchValue, pageable);
		
		
		model.addAttribute("noticeList", pageInfo.getContents());
		model.addAttribute("currentPage", pageInfo.getCurrentPage());
		model.addAttribute("startPageNum", pageInfo.getStartPageNum());
		model.addAttribute("endPageNum", pageInfo.getEndPageNum());
		model.addAttribute("lastPage", pageInfo.getLastPage());
		model.addAttribute("searchValue", searchValue);
		
		
		
		return "user/board/noticeList";  
	}
	
	
	@GetMapping("/noticeList")
	public String noticeSearchList(@RequestParam(value="searchValue", required = false) String searchValue,
			Pageable pageable, Model model, HttpSession session) {
		
		PageInfo<Notice> pageInfo = boardService.getNoticeSearchList(searchValue, pageable);
		
		 String id = (String) session.getAttribute("SID");

	        String avatar = userService.getUserAvatar(id);
	        model.addAttribute("avatar", avatar);
		
		model.addAttribute("noticeList", pageInfo.getContents());
		model.addAttribute("currentPage", pageInfo.getCurrentPage());
		model.addAttribute("startPageNum", pageInfo.getStartPageNum());
		model.addAttribute("endPageNum", pageInfo.getEndPageNum());
		model.addAttribute("lastPage", pageInfo.getLastPage());
		model.addAttribute("searchValue", searchValue);
		
		return "user/board/noticeList";
	}
	
	
	
	
	
	
	
	@PostMapping("/inquiryList")
	public String inquirySearch(@RequestParam(value="searchValue") String searchValue,
			Pageable pageable,
			Model model) {
		
		PageInfo<Inquiry> pageInfo =  boardService.getInquirySearchList(searchValue, pageable);
		
		
		model.addAttribute("inquiryList", pageInfo.getContents());
		model.addAttribute("currentPage", pageInfo.getCurrentPage());
		model.addAttribute("startPageNum", pageInfo.getStartPageNum());
		model.addAttribute("endPageNum", pageInfo.getEndPageNum());
		model.addAttribute("lastPage", pageInfo.getLastPage());
		model.addAttribute("searchValue", searchValue);
		
		
		
		return "user/board/inquiryList";  
	}
	
	
	@GetMapping("/inquiryList")
	public String inquirySearchList(@RequestParam(value="searchValue", required = false) String searchValue,
			Pageable pageable, Model model, HttpSession session) {
		
		 String id = (String) session.getAttribute("SID");

	        String avatar = userService.getUserAvatar(id);
	        model.addAttribute("avatar", avatar);
		
		PageInfo<Inquiry> pageInfo = boardService.getInquirySearchList(searchValue, pageable);
		
		model.addAttribute("inquiryList", pageInfo.getContents());
		model.addAttribute("currentPage", pageInfo.getCurrentPage());
		model.addAttribute("startPageNum", pageInfo.getStartPageNum());
		model.addAttribute("endPageNum", pageInfo.getEndPageNum());
		model.addAttribute("lastPage", pageInfo.getLastPage());
		
		
		
		model.addAttribute("searchValue", searchValue);
		
		return "user/board/inquiryList";
	}
	
	
	
	@PostMapping("/infosearchList")
	public String infoBoardSearch(@RequestParam(value="searchValue") String searchValue,
			Pageable pageable,
			Model model) {
		
		
		
		PageInfo<Board> pageInfo = boardService.getInfoSearchList(searchValue, pageable);
		
		
		model.addAttribute("infoBoardList", pageInfo.getContents());
		model.addAttribute("currentPage", pageInfo.getCurrentPage());
		model.addAttribute("startPageNum", pageInfo.getStartPageNum());
		model.addAttribute("endPageNum", pageInfo.getEndPageNum());
		model.addAttribute("lastPage", pageInfo.getLastPage());
		model.addAttribute("searchValue", searchValue);
		
		
		
		return "user/board/InfoboardList";  
	}
	
	
	@GetMapping("/infosearchList")
	public String infoBoardSearchList(@RequestParam(value="searchValue", required = false) String searchValue,
			Pageable pageable, Model model, HttpSession session) {
		
		PageInfo<Board> pageInfo = boardService.getInfoSearchList(searchValue, pageable);
		
		 String id = (String) session.getAttribute("SID");

	        String avatar = userService.getUserAvatar(id);
	        model.addAttribute("avatar", avatar);
		
		model.addAttribute("infoBoardList", pageInfo.getContents());
		model.addAttribute("currentPage", pageInfo.getCurrentPage());
		model.addAttribute("startPageNum", pageInfo.getStartPageNum());
		model.addAttribute("endPageNum", pageInfo.getEndPageNum());
		model.addAttribute("lastPage", pageInfo.getLastPage());
		
		
		
		model.addAttribute("searchValue", searchValue);
		
		return "user/board/InfoboardList";
	}
	
	
	
	
	
	/* 정보게시글 */
	
	@PostMapping("/freesearchList")
	public String freeBoardSearch(@RequestParam(value="searchValue") String searchValue,
		    							Pageable pageable,
		    										Model model) {

		    PageInfo<Board> pageInfo = boardService.getFreeSearchList(searchValue, pageable);
		    
	
		    model.addAttribute("freeBoardList", pageInfo.getContents());
		    model.addAttribute("currentPage", pageInfo.getCurrentPage());
		    model.addAttribute("startPageNum", pageInfo.getStartPageNum());
		    model.addAttribute("endPageNum", pageInfo.getEndPageNum());
		    model.addAttribute("lastPage", pageInfo.getLastPage());
		    model.addAttribute("searchValue", searchValue);



		    return "user/board/FreeboardList";  
		}
	
	
	@GetMapping("/freesearchList")
	public String freeBoardSearchList(@RequestParam(value="searchValue", required = false) String searchValue,
											Pageable pageable, Model model, HttpSession session) {
		

		 String id = (String) session.getAttribute("SID");

	        String avatar = userService.getUserAvatar(id);
	        model.addAttribute("avatar", avatar);
		
	    PageInfo<Board> pageInfo = boardService.getFreeSearchList(searchValue, pageable);

	    model.addAttribute("freeBoardList", pageInfo.getContents());
	    model.addAttribute("currentPage", pageInfo.getCurrentPage());
	    model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	    model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	    model.addAttribute("lastPage", pageInfo.getLastPage());
	    
	

	    model.addAttribute("searchValue", searchValue);
	    
		return "user/board/FreeboardList";
	}
	
	
	
	
	
	
	
	
	@PostMapping("/attacksearchList")
	public String attackBoardSearch(@RequestParam(value="searchValue") String searchValue,
		    							Pageable pageable,
		    										Model model) {

		    PageInfo<Board> pageInfo = boardService.getAttackSearchList(searchValue, pageable);
		    
	
		    model.addAttribute("attackBoardList", pageInfo.getContents());
		    model.addAttribute("currentPage", pageInfo.getCurrentPage());
		    model.addAttribute("startPageNum", pageInfo.getStartPageNum());
		    model.addAttribute("endPageNum", pageInfo.getEndPageNum());
		    model.addAttribute("lastPage", pageInfo.getLastPage());
		    model.addAttribute("searchValue", searchValue);



		    return "user/board/AttackboardList";  
		}
	
	
	@GetMapping("/attacksearchList")
	public String attackBoardSearchList(@RequestParam(value="searchValue", required = false) String searchValue,
											Pageable pageable, Model model,  HttpSession session) {
		
		 String id = (String) session.getAttribute("SID");

	        String avatar = userService.getUserAvatar(id);
	        model.addAttribute("avatar", avatar);
		
	    PageInfo<Board> pageInfo = boardService.getAttackSearchList(searchValue, pageable);
	

	    model.addAttribute("attackBoardList", pageInfo.getContents());
	    model.addAttribute("currentPage", pageInfo.getCurrentPage());
	    model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	    model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	    model.addAttribute("lastPage", pageInfo.getLastPage());
	    
	

	    model.addAttribute("searchValue", searchValue);
	    
		return "user/board/AttackboardList";
	}
	
	


	
	
	@PostMapping("/allsearchList")
	public String allBoardsearch(
		    @RequestParam(value="searchValue") String searchValue,
		    Pageable pageable,
		    Model model) {

		    PageInfo<Board> pageInfo = boardService.getSearchList(searchValue, pageable);
		    
	
		    model.addAttribute("allBoardList", pageInfo.getContents());
		    model.addAttribute("currentPage", pageInfo.getCurrentPage());
		    model.addAttribute("startPageNum", pageInfo.getStartPageNum());
		    model.addAttribute("endPageNum", pageInfo.getEndPageNum());
		    model.addAttribute("lastPage", pageInfo.getLastPage());
		    model.addAttribute("searchValue", searchValue);



		    return "user/board/allBoardList";  // 이 경로가 맞는지 확인
		}
	
	
	@GetMapping("/allsearchList")
	public String allBoardSearchList(
					
							        @RequestParam(value="searchValue", required = false) String searchValue,
							        Pageable pageable,
							        Model model,
							        HttpSession session) {
		
		  String id = (String) session.getAttribute("SID");

	        String avatar = userService.getUserAvatar(id);
	        model.addAttribute("avatar", avatar);
	        
	    PageInfo<Board> pageInfo = boardService.getSearchList(searchValue, pageable);
	

	    model.addAttribute("allboardList", pageInfo.getContents());
	    model.addAttribute("currentPage", pageInfo.getCurrentPage());
	    model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	    model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	    model.addAttribute("lastPage", pageInfo.getLastPage());
	    
	

	    model.addAttribute("searchValue", searchValue);
	    
		return "user/board/allBoardList";
	}
	
	
	
	@GetMapping("/remove")  
	public String removeBoard(@RequestParam(name="boardNum") String boardNum, RedirectAttributes rttr, HttpSession session, Model model ) {
		
		  String id = (String) session.getAttribute("SID");

	        String avatar = userService.getUserAvatar(id);
	        model.addAttribute("avatar", avatar);
	    int result = boardService.removeBoard(boardNum);
	    
	    if(result > 0) {
	        rttr.addFlashAttribute("message", "게시글이 삭제되었습니다.");
	    } else {
	        rttr.addFlashAttribute("error", "게시글 삭제에 실패했습니다.");
	    }
	    
	    return "redirect:/board";
	}
	
	@PostMapping("/modify")
	@Transactional
	public String modifyBoard(Board board,
	                         @RequestParam(required = false) MultipartFile[] files,
	                         @RequestParam(required = false) String[] deletedFiles,
	                         RedirectAttributes rttr) {
	    try {
	        // 1. 게시글 수정
	        boardService.modifyBoard(board);

	        // 2. 삭제할 파일 처리
	        if (deletedFiles != null && deletedFiles.length > 0) {
	            for (String fileIdx : deletedFiles) {
	                // 파일 매핑만 삭제
	                boardFilesMapper.deleteFileMapping(board.getBoardNum(), fileIdx);
	                // 실제 파일도 삭제
	                BoardFiles fileInfo = boardFileMapper.findByFileIdx(fileIdx);
	                if (fileInfo != null) {
	                    boardService.deleteFile(fileInfo);
	                }
	            }
	        }

	        // 3. 새 파일 추가
	        if (files != null && files.length > 0 && !files[0].isEmpty()) {
	            List<BoardFiles> newFiles = boardFilesUtils.uploadFiles(files);
	            
	            // 3-1. 새 파일 정보 저장
	            boardFileMapper.addfiles(newFiles);
	            
	            // 3-2. 새 파일 매핑 추가
	            for (BoardFiles file : newFiles) {
	                BoardsFiles mapping = new BoardsFiles();
	                mapping.setBoardNum(board.getBoardNum());
	                mapping.setFileIdx(file.getFileIdx());  // () 추가
	                boardFilesMapper.insertMapping(mapping);
	            }
	        }

	        rttr.addFlashAttribute("message", "게시글이 수정되었습니다.");
	        return "redirect:/board/detail?boardNum=" + board.getBoardNum();

	    } catch (Exception e) {
	        e.printStackTrace();
	        rttr.addFlashAttribute("error", "게시글 수정 중 오류가 발생했습니다.");
	        return "redirect:/board";
	    }
	}
	
	@GetMapping("/modify")
	public String modifyBoardView(@RequestParam(name="boardNum") String boardNum, Model model, HttpSession session ) {
		
		  String id = (String) session.getAttribute("SID");

	        String avatar = userService.getUserAvatar(id);
	        model.addAttribute("avatar", avatar);
	    Board boardInfo = boardService.getBoardInfo(boardNum);
	    
	    List<BoardsFiles> boardFiles = boardFilesMapper.getBoardFiles(boardNum);
	    
	    model.addAttribute("boardInfo", boardInfo);
	    model.addAttribute("boardFiles", boardFiles);   // 파일 정보 추가

	    return "user/board/modifyBoard";
	}
		  
		  	
	
	

	
	@PostMapping("/report")
	public String addReport(Report report, HttpSession session) {
	    String loginId = (String) session.getAttribute("SID");
	    report.setReportUser(loginId);
	    
	    // 신고 처리 로직
	    boardService.addReport(report);
	    
	    // 처리 완료 후 디테일 페이지로 리다이렉트
	    return "redirect:/board/detail?boardNum=" + report.getBoardNum();
	}

	
	
	@GetMapping("/inquiry/detail")
	public String inquiryView(@RequestParam(name="inquiryNum") String inquiryNum, Model model , HttpSession session) {
		
		   String id = (String) session.getAttribute("SID");

	        String avatar = userService.getUserAvatar(id);
	        model.addAttribute("avatar", avatar);
		
		Inquiry inquiryInfo = boardService.getInquiryInfo(inquiryNum);
		InquiryRespone responeInfo = boardService.getInquiryResponeInfo(inquiryNum);
		
		  String writerAvatar = userService.getUserAvatar(inquiryInfo.getInquiryUserId());
	        model.addAttribute("writerAvatar", writerAvatar); 
	        
	   
		
		List<InquiryFiles> inquiryFiles = boardService.getInquiryFiles(inquiryNum);
		
		model.addAttribute("inquiryInfo", inquiryInfo);
		model.addAttribute("responeInfo", responeInfo);
		model.addAttribute("inquiryFiles", inquiryFiles);
		
		
		 return "user/board/inquiryDetail";
	}
	
	
	
	@GetMapping("/notice/detail")
	public String noticeView(@RequestParam(name="noticeNum") int noticeNum, Model model , HttpSession session) {
		
		
	    String id = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);
        
	
        
        

		 int updateResult = boardService.addNoticeViewCount(noticeNum);
		
			Notice noticeInfo = boardService.getNoticeInfo(noticeNum);
		
		    List<NoticeFiles> noticeFiles = noticeFileMapper.getNoticeFiles(noticeNum);
			
			
			  String writerAvatar = userService.getUserAvatar(noticeInfo.getAdminId());
		        model.addAttribute("writerAvatar", writerAvatar); 
	        
			
		
		model.addAttribute("noticeInfo", noticeInfo);
		model.addAttribute("noticeFiles", noticeFiles);
		
		 return "user/board/noticeDetail";
	}
	
	
	@PostMapping("/addComment")
	public String addComment(BoardComment boardComment, HttpSession session, RedirectAttributes rttr) {
		
		String loginId = (String) session.getAttribute("SID");

		boardComment.setCommentUserId(loginId);
		
		
		boardService.addComment(boardComment);
		
		 return "redirect:/board/detail?boardNum=" + boardComment.getBoardNum();
		
		
	}
	
	@PostMapping("/like")
	@ResponseBody
	public Map<String, Object> handleBoardLike(@RequestParam String boardNum, HttpSession session) {
	    String userId = (String) session.getAttribute("SID");
	    if(userId == null) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", false);
	        response.put("message", "로그인이 필요합니다.");
	        return response;
	    }
	    
	    Map<String, Object> result = boardService.addBoardLike(boardNum, userId, "좋아요");
	    log.info("좋아요 처리 결과: {}", result);
	    return result;
	}

	@PostMapping("/dislike")
	@ResponseBody
	public Map<String, Object> handleBoardDislike(@RequestParam String boardNum, HttpSession session) {
	    String userId = (String) session.getAttribute("SID");
	    if(userId == null) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", false);
	        response.put("message", "로그인이 필요합니다.");
	        return response;
	    }

	    Map<String, Object> result = boardService.addBoardLike(boardNum, userId, "싫어요");
	    log.info("싫어요 처리 결과: {}", result);
	    return result;
	}
	@PostMapping("/comment/like")
	@ResponseBody
	public Map<String, Object> addCommentLike(@RequestParam String commentNum, HttpSession session) {
		 String userId = (String) session.getAttribute("SID");
	    return boardService.addCommentLike(commentNum, userId, "좋아요");
	}

	@PostMapping("/comment/dislike")
	@ResponseBody
	public Map<String, Object> addCommentDislike(@RequestParam String commentNum, HttpSession session) {
		 String userId = (String) session.getAttribute("SID");
		 return boardService.addCommentLike(commentNum, userId, "싫어요");
	}
	
	@GetMapping("/detail")
	public String detailBoardView(@RequestParam(name="boardNum") String boardNum,
	                            Pageable pageable, HttpSession session,
	                            Model model) {
	    int updateResult = boardService.addViewCount(boardNum);
	    log.info("조회수 증가 결과: {}", updateResult);

	    Board boardInfo = boardService.getBoardInfo(boardNum);
	    List<BoardsFiles> boardFiles = boardFilesMapper.getBoardFiles(boardNum);
	    PageInfo<BoardComment> pageInfo = boardService.getBoardCommentInfo(boardNum, pageable);

	    // 로그인한 사용자의 좋아요/싫어요 상태 확인
	    String userId = (String) session.getAttribute("SID");
	    String id = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);
	    
        
        String writerAvatar = userService.getUserAvatar(boardInfo.getBoardUserId());
        model.addAttribute("writerAvatar", writerAvatar); 
        
        
        List<BoardComment> comments = pageInfo.getContents();
        
	     // 여기에 아바타 정보 추가
	        for (BoardComment comment : comments) {
	            String commentAvatar = userService.getUserAvatar(comment.getCommentUserId());
	            comment.setAvatarFilePath(commentAvatar);
	        }

        
	    if (userId != null) {
	        // 게시글 좋아요/싫어요 상태 확인
	    	  Map<String, String> likeParams = new HashMap<>();
	          likeParams.put("boardNum", boardNum);
	          likeParams.put("userId", userId);
	          likeParams.put("likesType", "좋아요");
	          BoardLikes likeStatus = boardLikeMapper.getBoardLikesByUser(likeParams);
	          model.addAttribute("boardLikeStatus", likeStatus);

	          // 게시글 싫어요 상태 확인
	          Map<String, String> dislikeParams = new HashMap<>();
	          dislikeParams.put("boardNum", boardNum);
	          dislikeParams.put("userId", userId);
	          dislikeParams.put("likesType", "싫어요");
	          BoardLikes dislikeStatus = boardLikeMapper.getBoardLikesByUser(dislikeParams);
	          model.addAttribute("boardDislikeStatus", dislikeStatus);
	     // 댓글 좋아요/싫어요 상태 확인
	      
	        Map<String, Map<String, BoardCommentLikes>> commentLikeStatuses = new HashMap<>(); 
	        
	        for (BoardComment comment : comments) {
	            Map<String, BoardCommentLikes> commentStatus = new HashMap<>();
	            
	            Map<String, String> commentLikeParams = new HashMap<>();
	            commentLikeParams.put("commentNum", comment.getCommentNum());
	            commentLikeParams.put("userId", userId);
	            commentLikeParams.put("likesType", "좋아요");
	            BoardCommentLikes commentLikeStatus = boardCommentLikeMapper.getCommentLikesByUser(commentLikeParams);
	            
	            Map<String, String> commentDislikeParams = new HashMap<>();
	            commentDislikeParams.put("commentNum", comment.getCommentNum());
	            commentDislikeParams.put("userId", userId);
	            commentDislikeParams.put("likesType", "싫어요");
	            BoardCommentLikes commentDislikeStatus = boardCommentLikeMapper.getCommentLikesByUser(commentDislikeParams);
	            
	            commentStatus.put("like", commentLikeStatus);
	            commentStatus.put("dislike", commentDislikeStatus);
	            commentLikeStatuses.put(comment.getCommentNum(), commentStatus);
	        }

	        model.addAttribute("boardLikeStatus", likeStatus);
	        model.addAttribute("boardDislikeStatus", dislikeStatus);
	        model.addAttribute("commentLikeStatuses", commentLikeStatuses);
	    }

	    // 기존 모델 속성 추가
	    model.addAttribute("title", "게시글상세");
	    model.addAttribute("boardInfo", boardInfo);
	    model.addAttribute("boardFiles", boardFiles); 
	    model.addAttribute("commentList", pageInfo.getContents());
	    model.addAttribute("currentPage", pageInfo.getCurrentPage());
	    model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	    model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	    model.addAttribute("lastPage", pageInfo.getLastPage());
	    model.addAttribute("boardNum", boardNum);
	    log.info("댓글 목록: {}", pageInfo.getContents());
	    return "user/board/boardDetail";
	}

	@PostMapping("/inquiry/write")
	@Transactional
	public String addInquiry(Inquiry inquiry,
	                        @RequestParam(required = false) MultipartFile[] files,
	                        HttpSession session,
	                        RedirectAttributes rttr) {
	    String loginId = (String) session.getAttribute("SID");
	    inquiry.setInquiryUserId(loginId);

	    try {
	        // 1. inquiry 저장
	        int result = boardService.addInquiry(inquiry);
	        
	        if(result > 0) {
	            // 2. 파일이 있는 경우 처리
	            if (files != null && files.length > 0 && !files[0].isEmpty()) {
	                // 파일 업로드 및 files 테이블에 저장
	                List<BoardFiles> fileList = boardFilesUtils.uploadFiles(files);
	                boardFileMapper.addfiles(fileList);

	                // 3. inquiry_files 매핑 테이블에 저장
	                for (BoardFiles file : fileList) {
	                    InquiryFiles mapping = boardFilesUtils.createInquiryFileMapping(inquiry.getInquiryNum(), file);
	                    inquiryFilesMapper.addInquiryFileMapping(mapping);
	                }
	            }

	            rttr.addFlashAttribute("message", "문의글이 등록되었습니다.");
	            return "redirect:/board/inquiry";
	        }
	        throw new RuntimeException("문의글 저장에 실패했습니다.");

	    } catch (Exception e) {
	        log.error("문의글 작성 중 오류 발생: ", e);
	        rttr.addFlashAttribute("error", "문의글 작성 중 오류가 발생했습니다.");
	        return "redirect:/board/inquiry/write";
	    }
	}
	
	@GetMapping("/inquiry/write")
	public String addInquiryView(Model model , HttpSession session) {
		String id = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);

		return "user/board/addInquiry";
	}

	@PostMapping("/write")
	public String addBoard(Board board,
	                      @RequestParam(required = false) MultipartFile[] files,
	                      HttpSession session,
	                      RedirectAttributes rttr) {
	    String loginId = (String) session.getAttribute("SID");
	    board.setBoardUserId(loginId);

	    try {
	        // 1. 게시글 먼저 저장
	        int result = boardService.addBoard(board);
	        
	        // 2. 파일 업로드 및 매핑 처리
	        if (files != null && files.length > 0 && !files[0].isEmpty()) {
	            List<BoardFiles> fileList = boardFilesUtils.uploadFiles(files);
	            boardFileMapper.addfiles(fileList);
	            
	            // 게시글-파일 매핑 생성
	            for (BoardFiles file : fileList) {
	                BoardsFiles mapping = new BoardsFiles();
	                
	                // boardNum이 null이 아닌지 확인하고 처리
	                if (board.getBoardNum() != null) {
	                    // bbs_ prefix가 있는지 확인
	                    String boardNum = board.getBoardNum().startsWith("bbs_") ? 
	                        board.getBoardNum() : 
	                        "bbs_" + board.getBoardNum();
	                    mapping.setBoardNum(boardNum);
	                } else {
	                    // 로그 추가
	                    log.error("게시글 번호가 null입니다.");
	                    throw new RuntimeException("게시글 번호가 null입니다.");
	                }
	                
	                mapping.setFileIdx(file.getFileIdx());
	                boardFilesMapper.insertMapping(mapping);
	            }
	        }

	        rttr.addFlashAttribute("message", "게시글이 작성되었습니다.");
	        return "redirect:/board";

	    } catch (Exception e) {
	        e.printStackTrace();
	        rttr.addFlashAttribute("error", "게시글 작성 중 오류가 발생했습니다.");
	        return "redirect:/board/write";
	    }
	}
	
	@GetMapping("/write")
	public String addBoardView(Model model, HttpSession session) {

		String id = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);
		
		
		return "user/board/addBoard";
	}
	
	

	@GetMapping("")
	public String getAllBoardListView(Pageable pageable, Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);
		
		var pageInfo = boardService.getAllBoardsList(pageable);
		List<Board> allBoardList = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		
		

		model.addAttribute("allBoardList", allBoardList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);

		return "user/board/allBoardList";

	}

	@GetMapping("/inquiry")
	public String getInquiryListView(Pageable pageable, Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);
		

		var pageInfo = boardService.getInquiryList(pageable);
		List<Inquiry> inquiryList = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();

		model.addAttribute("inquiryList", inquiryList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);

		return "user/board/inquiryList";
	}

	@GetMapping("/notice")
	public String NoticeListView(Pageable pageable, Model model , HttpSession session) {
		
		String id = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);
		

		var pageInfo = boardService.getNoticeList(pageable);
		List<Notice> noticeList = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();

		model.addAttribute("noticeList", noticeList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);

		return "user/board/noticeList";
	}

	@GetMapping("/free")
	public String getFreeboardListView(Pageable pageable, Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);
		

		var pageInfo = boardService.getFreeBoardsList(pageable);
		List<Board> freeBoardList = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();

		model.addAttribute("freeBoardList", freeBoardList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);

		return "user/board/FreeboardList";

	}

	@GetMapping("/attack")
	public String getAttackboardListView(Pageable pageable, Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);

		var pageInfo = boardService.getAttackBoardsList(pageable);
		List<Board> attackBoardList = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();

		model.addAttribute("attackBoardList", attackBoardList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);

		return "user/board/AttackboardList";

	}

	@GetMapping("/info")
	public String getInfoboardListView(Pageable pageable, Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);

		var pageInfo = boardService.getInfoBoardsList(pageable);
		List<Board> infoBoardList = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();

		model.addAttribute("infoBoardList", infoBoardList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);

		return "user/board/InfoboardList";

	}

	@PostMapping("/smarteditorMultiImageUpload")
	public void smarteditorMultiImageUpload(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        // 최대 허용 파일 개수
	        int maxFiles = 10;
	        // 업로드된 파일들의 정보를 저장할 리스트
	        List<Map<String, String>> uploadedFiles = new ArrayList<>();
	        
	        String contentType = request.getContentType();
	        if (contentType.startsWith("multipart/")) {
	            Collection<Part> parts = request.getParts();
	            int fileCount = 0;
	            
	            for (Part part : parts) {
	                if (part.getContentType() != null && fileCount < maxFiles) {
	                    // 파일명 추출
	                    String fileName = getFileName(part);
	                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	                    
	                    // 날짜 기반 경로 생성
	                    LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
	                    String datePath = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	                    String dbFilePath = "/attachment/" + datePath + "/image";
	                    String uploadPath = this.fileRealPath + dbFilePath;
	                    
	                    // 디렉토리 생성
	                    File dir = new File(uploadPath);
	                    if (!dir.exists()) {
	                        dir.mkdirs();
	                    }
	                    
	                    // 고유 파일명 생성
	                    String newFileName = UUID.randomUUID().toString() + "." + fileExt;
	                    String fullPath = uploadPath + "/" + newFileName;
	                    
	                    // 파일 저장
	                    try (InputStream input = part.getInputStream();
	                         FileOutputStream output = new FileOutputStream(fullPath)) {
	                        byte[] buffer = new byte[8192];
	                        int bytesRead;
	                        while ((bytesRead = input.read(buffer)) != -1) {
	                            output.write(buffer, 0, bytesRead);
	                        }
	                    }
	                    
	                    // 파일 정보 저장
	                    Map<String, String> fileInfo = new HashMap<>();
	                    fileInfo.put("originalName", fileName);
	                    fileInfo.put("url", "/attachment/" + datePath + "/image/" + newFileName);
	                    uploadedFiles.add(fileInfo);
	                    
	                    fileCount++;
	                }
	            }
	        }
	        
	        // 응답 생성
	        StringBuilder responseData = new StringBuilder();
	        for (int i = 0; i < uploadedFiles.size(); i++) {
	            Map<String, String> fileInfo = uploadedFiles.get(i);
	            responseData.append("&bNewLine=true");
	            responseData.append("&sFileName=").append(fileInfo.get("originalName"));
	            responseData.append("&sFileURL=").append(fileInfo.get("url"));
	            if (i < uploadedFiles.size() - 1) {
	                responseData.append("|");
	            }
	        }
	        
	        // 클라이언트에 응답
	        PrintWriter printWriter = response.getWriter();
	        printWriter.print(responseData.toString());
	        printWriter.flush();
	        printWriter.close();
	        
	    } catch (Exception e) {
	        // 에러 처리
	        e.printStackTrace();
	        try {
	            PrintWriter print = response.getWriter();
	            print.print("ERROR_" + e.getMessage());
	            print.flush();
	            print.close();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	}

	// 파일명 추출 유틸리티 메서드
	private String getFileName(Part part) {
	    String contentDisp = part.getHeader("content-disposition");
	    String[] items = contentDisp.split(";");
	    for (String s : items) {
	        if (s.trim().startsWith("filename")) {
	            return s.substring(s.indexOf("=") + 2, s.length() - 1);
	        }
	    }
	    return "";
	}
}
