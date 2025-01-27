package gamepiece.admin.board.controller;







import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.board.domain.AdminBoardFiles;
import gamepiece.admin.board.domain.Board;
import gamepiece.admin.board.mapper.BoardFileMapper;
import gamepiece.admin.board.service.BoardService;
import gamepiece.admin.boardCategory.domain.BoardCategory;
import gamepiece.admin.boardCategory.service.BoardCategoryService;
import gamepiece.admin.boardComment.service.BoardCommentService;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	
    @Value("${file.path}")
    private String fileRealPath;  

	private final BoardService boardService;
	private final BoardCategoryService boardCategoryService;
	private final BoardCommentService boardCommentService;
	private final BoardFileMapper boardFileMapper;
	
	
	
	
	
	@GetMapping("/download")
	public ResponseEntity<Object> downloadFile(@RequestParam String fileIdx,
	        HttpServletRequest request) {
		log.info("다운로드 요청된 fileIdx: {}", fileIdx);
	    try {
	        AdminBoardFiles fileDto = boardFileMapper.getFileInfoByIdx(fileIdx);
	        log.info("조회된 파일 정보: {}", fileDto);  // fileDto가 null인지 확인
	        if(fileDto == null) {
	            return ResponseEntity.notFound().build();
	        }
	        
	        
	        // contentType 분류해서 경로 구성
	        String fileType = fileDto.getFileNewName().toLowerCase().matches(".*\\.(jpg|jpeg|png|gif|bmp)$") 
	                       ? "/image" : "/file";
	        
	        // 전체 경로 구성
	        String fullPath = fileRealPath + fileDto.getFilePath() 
	                       + fileType + "/" + fileDto.getFileNewName();
	        
	        log.info("다운로드 시도 경로: {}", fullPath);
	        
	        File file = new File(fullPath);
	        if (!file.exists()) {
	            log.error("파일이 존재하지 않음: {}", fullPath);
	            return ResponseEntity.notFound().build();
	        }
	        
	        log.info("파일 크기: {} bytes", file.length());
	        
	        Path path = Paths.get(file.getAbsolutePath());
	        Resource resource = new UrlResource(path.toUri());

	        String contentType = request.getServletContext()
	                .getMimeType(resource.getFile().getAbsolutePath());
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" +
	                                URLEncoder.encode(fileDto.getFileOriginalName(),"UTF-8") +
	                                "\";")
	                .body(resource);
	        
	    } catch (Exception e) {
	        log.error("파일 다운로드 중 오류 발생: {}", e.getMessage());
	        return ResponseEntity.internalServerError().build();
	    }
	}
	
	
	
	@PostMapping("/searchList")
	public String boardsearchList(
	        @RequestParam(value="searchCate", required = false, defaultValue = "all") String searchCate,
	        @RequestParam(value="searchValue") String searchValue, 
	        Pageable pageable, 
	        Model model) {

	
		
	    PageInfo<Board> pageInfo = boardService.getSearchList(searchCate, searchValue, pageable);
	    
	    
	    

	    model.addAttribute("title", "게시판 목록");
	    model.addAttribute("boardList", pageInfo.getContents());
	    model.addAttribute("currentPage", pageInfo.getCurrentPage());
	    model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	    model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	    model.addAttribute("lastPage", pageInfo.getLastPage());
	    

	    model.addAttribute("searchCate", searchCate);
	    model.addAttribute("searchValue", searchValue);
	
	    return "admin/board/boardsList";
	}
	
	
	@GetMapping("/searchList")
	public String boardSearchList(
							        @RequestParam(value="searchCate", required = false, defaultValue = "all") String searchCate,
							        @RequestParam(value="searchValue", required = false) String searchValue,
							        Pageable pageable,
							        Model model) {
	        
	    PageInfo<Board> pageInfo = boardService.getSearchList(searchCate, searchValue, pageable);
	
	    model.addAttribute("title", "게시판 목록");
	    model.addAttribute("boardList", pageInfo.getContents());
	    model.addAttribute("currentPage", pageInfo.getCurrentPage());
	    model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	    model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	    model.addAttribute("lastPage", pageInfo.getLastPage());
	    
	
	    model.addAttribute("searchCate", searchCate);
	    model.addAttribute("searchValue", searchValue);
	    
	    return "admin/board/boardsList";
	}
	
	
	
	
	@PostMapping("/remove")
	public String removeBoard(@RequestParam("boardNum") String boardNum, RedirectAttributes rttr) {
	    int result = boardService.removeBoard(boardNum);

	  
	        rttr.addFlashAttribute("message", "게시글이 삭제되었습니다.");
	   
	    return "redirect:/admin/board/list";
	}
	
	
	@GetMapping("/detail")
	public String detailBoardView(@RequestParam(name="boardNum") String boardNum,
	                            Pageable pageable,
	                            Model model) {

	   Board boardInfo = boardService.getBoardInfo(boardNum);
	   List<BoardCategory> categoryList = boardCategoryService.getBoardCategoryList();

	   // 특정 게시물의 덧글 목록 조회 (페이징 처리)
	   var pageInfo = boardCommentService.getBoardCommentInfo(boardNum, pageable);
	   
	   
	   AdminBoardFiles boardFile = boardService.getBoardFile(boardNum);

	   model.addAttribute("title", "게시글상세");
	   model.addAttribute("boardInfo", boardInfo);
	   model.addAttribute("categoryList", categoryList);
	   model.addAttribute("commentList", pageInfo.getContents());
	   model.addAttribute("currentPage", pageInfo.getCurrentPage());
	   model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	   model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	   model.addAttribute("lastPage", pageInfo.getLastPage());
	   model.addAttribute("boardNum", boardNum);  // 페이징 처리시 필요
	   model.addAttribute("boardFile", boardFile);
	   	
	   return "admin/board/boardDetail";
	}
	
	
	
	
	@GetMapping("/list")
	public String BoardList(Pageable pageable, Model model) {        
	  
	    var pageInfo = boardService.getBoardsList(pageable);   

	    List<Board> boardList = pageInfo.getContents();
	    int currentPage = pageInfo.getCurrentPage();
	    int startPageNum = pageInfo.getStartPageNum();
	    int endPageNum = pageInfo.getEndPageNum();
	    int lastPage = pageInfo.getLastPage();

	    model.addAttribute("title", "게시판 목록");
	    model.addAttribute("boardList", boardList);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("startPageNum", startPageNum);
	    model.addAttribute("endPageNum", endPageNum);
	    model.addAttribute("lastPage", lastPage);
	    
	    return "admin/board/boardsList";
	}
	
	
	

	

	
	
	
}


