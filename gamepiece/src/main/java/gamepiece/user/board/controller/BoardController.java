package gamepiece.user.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.user.board.domain.Board;
import gamepiece.user.board.domain.Inquiry;
import gamepiece.user.board.domain.Notice;
import gamepiece.user.board.service.BoardService;
import gamepiece.util.Pageable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller("userBoardController")
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService boardService;
	
	
	@GetMapping("/detail")
	public String boardDetailView(@RequestParam(name="boardNum") String boardNum, Model model) {
		
			Board boardInfo = boardService.getBoardInfo(boardNum);
			
			model.addAttribute("boardInfo",boardInfo);
		
		
		return "user/board/boardDetail";
	}
	
	@PostMapping("/inquiry/write")
	public String addInquiry(Inquiry inquiry, RedirectAttributes rttr) {
		
		 String content = inquiry.getInquiryContent().replaceAll("<[^>]*>", "");
		 inquiry.setInquiryContent(content);
		 
		 
		int result = boardService.addInquiry(inquiry);
		
		 if(result > 0) {
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
	public String addBoard(Board board, RedirectAttributes rttr) {
		
		 String content = board.getBoardContent().replaceAll("<[^>]*>", "");
		 board.setBoardContent(content);
		 
		 
		int result = boardService.addBoard(board);
		
		 if(result > 0) {
			  rttr.addFlashAttribute("message", "문의글이 작성되었습니다.");
		        return "redirect:/board/inquiry";
		    } else {
				  rttr.addFlashAttribute("message", "문의글이 작성실패하였습니다.");
		        return "redirect:/board/inquiry";
		    }
			
	
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
	
	@RequestMapping(value="smarteditorMultiImageUpload")
	public void smarteditorMultiImageUpload(HttpServletRequest request, HttpServletResponse response){
		try {
			//파일정보
			String sFileInfo = "";
			//파일명을 받는다 - 일반 원본파일명
			String sFilename = request.getHeader("file-name");
			//파일 확장자
			String sFilenameExt = sFilename.substring(sFilename.lastIndexOf(".")+1);
			//확장자를소문자로 변경
			sFilenameExt = sFilenameExt.toLowerCase();
				
			//이미지 검증 배열변수
			String[] allowFileArr = {"jpg","png","bmp","gif"};

			//확장자 체크
			int nCnt = 0;
			for(int i=0; i<allowFileArr.length; i++) {
				if(sFilenameExt.equals(allowFileArr[i])){
					nCnt++;
				}
			}

			//이미지가 아니라면
			if(nCnt == 0) {
				PrintWriter print = response.getWriter();
				print.print("NOTALLOW_"+sFilename);
				print.flush();
				print.close();
			} else {
				//디렉토리 설정 및 업로드	
				
				//파일경로
				String filePath = "경로설정";
				File file = new File(filePath);
				
				if(!file.exists()) {
					file.mkdirs();
				}
				
				String sRealFileNm = "";
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
				String today= formatter.format(new java.util.Date());
				sRealFileNm = today+UUID.randomUUID().toString() + sFilename.substring(sFilename.lastIndexOf("."));
				String rlFileNm = filePath + sRealFileNm;
				
				///////////////// 서버에 파일쓰기 ///////////////// 
				InputStream inputStream = request.getInputStream();
				OutputStream outputStream=new FileOutputStream(rlFileNm);
				int numRead;
				byte bytes[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
				while((numRead = inputStream.read(bytes,0,bytes.length)) != -1){
					outputStream.write(bytes,0,numRead);
				}
				if(inputStream != null) {
					inputStream.close();
				}
				outputStream.flush();
				outputStream.close();
				
				///////////////// 이미지 /////////////////
				// 정보 출력
				sFileInfo += "&bNewLine=true";
				// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
				sFileInfo += "&sFileName="+ sFilename;
				sFileInfo += "&sFileURL="+"경로설정"+sRealFileNm;
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
