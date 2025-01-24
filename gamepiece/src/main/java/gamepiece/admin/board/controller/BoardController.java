package gamepiece.admin.board.controller;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.board.domain.Board;
import gamepiece.admin.board.service.BoardService;
import gamepiece.admin.boardCategory.domain.BoardCategory;
import gamepiece.admin.boardCategory.service.BoardCategoryService;
import gamepiece.admin.boardComment.domain.BoardComment;
import gamepiece.admin.boardComment.service.BoardCommentService;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

	private final BoardService boardService;
	private final BoardCategoryService boardCategoryService;
	private final BoardCommentService boardCommentService;
	
	
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

	   model.addAttribute("title", "게시글상세");
	   model.addAttribute("boardInfo", boardInfo);
	   model.addAttribute("categoryList", categoryList);
	   model.addAttribute("commentList", pageInfo.getContents());
	   model.addAttribute("currentPage", pageInfo.getCurrentPage());
	   model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	   model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	   model.addAttribute("lastPage", pageInfo.getLastPage());
	   model.addAttribute("boardNum", boardNum);  // 페이징 처리시 필요

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


