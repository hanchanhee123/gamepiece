package gamepiece.user.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.file.dto.FileDto;
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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller("userBoardController")
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

	private final BoardService boardService;
	private final UserService userService;
	private final FileService fileService;

	/*  
	 * 
	 * 			
	 * */
	
	
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
	
	
	
	@GetMapping("/remove")  // POST를 GET으로 변경
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

	@RequestMapping(value = "smarteditorMultiImageUpload")
	public void smarteditorMultiImageUpload(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 파일정보
			String sFileInfo = "";
			// 파일명을 받는다 - 일반 원본파일명
			String sFilename = request.getHeader("file-name");
			// 파일 확장자
			String sFilenameExt = sFilename.substring(sFilename.lastIndexOf(".") + 1);
			// 확장자를소문자로 변경
			sFilenameExt = sFilenameExt.toLowerCase();

			// 이미지 검증 배열변수
			String[] allowFileArr = { "jpg", "png", "bmp", "gif" };

			// 확장자 체크
			int nCnt = 0;
			for (int i = 0; i < allowFileArr.length; i++) {
				if (sFilenameExt.equals(allowFileArr[i])) {
					nCnt++;
				}
			}

			// 이미지가 아니라면
			if (nCnt == 0) {
				PrintWriter print = response.getWriter();
				print.print("NOTALLOW_" + sFilename);
				print.flush();
				print.close();
			} else {
				// 디렉토리 설정 및 업로드

				// 파일경로
				String filePath = "경로설정";
				File file = new File(filePath);

				if (!file.exists()) {
					file.mkdirs();
				}

				String sRealFileNm = "";
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
				String today = formatter.format(new java.util.Date());
				sRealFileNm = today + UUID.randomUUID().toString() + sFilename.substring(sFilename.lastIndexOf("."));
				String rlFileNm = filePath + sRealFileNm;

				///////////////// 서버에 파일쓰기 /////////////////
				InputStream inputStream = request.getInputStream();
				OutputStream outputStream = new FileOutputStream(rlFileNm);
				int numRead;
				byte bytes[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
				while ((numRead = inputStream.read(bytes, 0, bytes.length)) != -1) {
					outputStream.write(bytes, 0, numRead);
				}
				if (inputStream != null) {
					inputStream.close();
				}
				outputStream.flush();
				outputStream.close();

				///////////////// 이미지 /////////////////
				// 정보 출력
				sFileInfo += "&bNewLine=true";
				// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
				sFileInfo += "&sFileName=" + sFilename;
				sFileInfo += "&sFileURL=" + "경로설정" + sRealFileNm;
				PrintWriter printWriter = response.getWriter();
				printWriter.print(sFileInfo);
				printWriter.flush();
				printWriter.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
