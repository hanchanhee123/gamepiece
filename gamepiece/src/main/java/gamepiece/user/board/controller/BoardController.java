package gamepiece.user.board.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// Spring Framework
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.file.service.FileService;
import gamepiece.user.board.domain.Board;
import gamepiece.user.board.domain.BoardComment;
import gamepiece.user.board.domain.Inquiry;
import gamepiece.user.board.domain.InquiryRespone;
import gamepiece.user.board.domain.Notice;
import gamepiece.user.board.domain.Report;
import gamepiece.user.board.service.BoardService;
import gamepiece.user.user.service.UserService;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
// Jakarta EE
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
// Lombok
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
	private final UserService userService;
	private final FileService fileService;

	
	

	
	
	
	
	
	
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
	                          @RequestParam String boardNum,RedirectAttributes rttr) {
		
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
	        fileService.addFile(file);  // 파일은 저장되지만 반환값이 없음

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
			Pageable pageable, Model model) {
		
		PageInfo<Notice> pageInfo = boardService.getNoticeSearchList(searchValue, pageable);
		
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
			Pageable pageable, Model model) {
		
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
			Pageable pageable, Model model) {
		
		PageInfo<Board> pageInfo = boardService.getInfoSearchList(searchValue, pageable);
		
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
											Pageable pageable, Model model) {
		
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
											Pageable pageable, Model model) {
		
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
	public String boardsearchList(
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
	public String boardSearchList(
					
							        @RequestParam(value="searchValue", required = false) String searchValue,
							        Pageable pageable,
							        Model model) {
	        
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
	public String removeBoard(@RequestParam(name="boardNum") String boardNum, RedirectAttributes rttr) {
	    int result = boardService.removeBoard(boardNum);
	    
	    if(result > 0) {
	        rttr.addFlashAttribute("message", "게시글이 삭제되었습니다.");
	    } else {
	        rttr.addFlashAttribute("error", "게시글 삭제에 실패했습니다.");
	    }
	    
	    return "redirect:/board";
	}
	
	@PostMapping("/modify")
	public String modifyBoard(Board board, RedirectAttributes rttr) {
		int result = boardService.modifyBoard(board);
		
		  if(result > 0) {
		        rttr.addFlashAttribute("message", "게시글이 수정되었습니다.");
			 
		    } else {
		        rttr.addFlashAttribute("error", "게시글 수정에 실패했습니다.");
		       
		    }
		  
		  System.out.println("boardNum : " + board.getBoardNum());
			
			return "redirect:/board";
		  
	}
	
	
	
	@GetMapping("/modify")
	public String modifyBoardView(@RequestParam(name="boardNum") String boardNum ,Model model) {
		
			Board boardInfo = boardService.getBoardInfo(boardNum);
			
	
			
			
			model.addAttribute("boardInfo", boardInfo);
	
		
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
	public String inquiryView(@RequestParam(name="inquiryNum") String inquiryNum, Model model) {
		
		Inquiry inquiryInfo = boardService.getInquiryInfo(inquiryNum);
		InquiryRespone responeInfo = boardService.getInquiryResponeInfo(inquiryNum);
		
		model.addAttribute("inquiryInfo", inquiryInfo);
		model.addAttribute("responeInfo", responeInfo);
		
		
		
		 return "user/board/inquiryDetail";
	}
	
	
	
	@GetMapping("/notice/detail")
	public String noticeView(@RequestParam(name="noticeNum") int noticeNum, Model model) {
		
			Notice noticeInfo = boardService.getNoticeInfo(noticeNum);
		
		
		model.addAttribute("noticeInfo", noticeInfo);
		
		
		 return "user/board/noticeDetail";
	}
	
	
	@PostMapping("/addComment")
	public String addComment(BoardComment boardComment, HttpSession session, RedirectAttributes rttr) {
		
		String loginId = (String) session.getAttribute("SID");
		boardComment.setCommentUserId(loginId);
		
		
		boardService.addComment(boardComment);
		
		 return "redirect:/board/detail?boardNum=" + boardComment.getBoardNum();
		
		
	}
	
	

	@GetMapping("/detail")
	public String detailBoardView(@RequestParam(name="boardNum") String boardNum,
	                          Pageable pageable,
	                          Model model) {
	    Board boardInfo = boardService.getBoardInfo(boardNum);
	    

	

	    // 댓글 정보 조회 (페이징 처리)
	    PageInfo<BoardComment> pageInfo = boardService.getBoardCommentInfo(boardNum, pageable);
	    
	    // 모델에 데이터 추가
	    model.addAttribute("title", "게시글상세");
	    model.addAttribute("boardInfo", boardInfo);
	    model.addAttribute("commentList", pageInfo.getContents());
	    model.addAttribute("currentPage", pageInfo.getCurrentPage());
	    model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	    model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	    model.addAttribute("lastPage", pageInfo.getLastPage());
	    model.addAttribute("boardNum", boardNum);
	    
	    return "user/board/boardDetail";
	}
	


	@PostMapping("/inquiry/write")
	public String addInquiry(Inquiry inquiry, HttpSession session, RedirectAttributes rttr) {

		String loginId = (String) session.getAttribute("SID");
		inquiry.setInquiryUserId(loginId);

	

		int result = boardService.addInquiry(inquiry);

		if (result > 0) {
			rttr.addFlashAttribute("message", "게시글이 작성되었습니다.");
			return "redirect:/board/inquiry";
		} else {
			rttr.addFlashAttribute("message", "게시글이 작성실패하였습니다.");
			return "redirect:/board/inquiry";
		}

	}

	@GetMapping("/inquiry/write")
	public String addInquiryView(Model model) {

		return "user/board/addInquiry";
	}

	
	
	@PostMapping("/write")
	public String addBoard(Board board, HttpSession session, RedirectAttributes rttr) {

		String loginId = (String) session.getAttribute("SID");

		board.setBoardUserId(loginId);
	
	
		

		int result = boardService.addBoard(board);
		
		rttr.addFlashAttribute("message", "게시글이 작성되었습니다.");

	
			
	
		return "redirect:/board";
	}

	@GetMapping("/write")
	public String addBoardView(Model model) {

		return "user/board/addBoard";
	}

	@GetMapping("")
	public String allBoardListView(Pageable pageable, Model model) {

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
	public String inquiryListView(Pageable pageable, Model model) {

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
	public String NoticeListView(Pageable pageable, Model model) {

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
	public String FreeboardListView(Pageable pageable, Model model) {

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
	public String AttackboardListView(Pageable pageable, Model model) {

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
	public String InfoboardListView(Pageable pageable, Model model) {

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
