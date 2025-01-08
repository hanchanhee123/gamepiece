package gamepiece.admin.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import gamepiece.util.Pageable;
import gamepiece.admin.board.domain.Board;
import gamepiece.admin.board.service.BoardService;
import gamepiece.admin.boardCategory.domain.BoardCategory;
import gamepiece.admin.boardCategory.service.BoardCategoryService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final BoardCategoryService boardCategoryService;
	
	@PostMapping("/remove")
	public String removeBoard(@RequestParam("boardNum") String boardNum, RedirectAttributes rttr) {
	    int result = boardService.removeBoard(boardNum);

	    if(result > 0) {
	        rttr.addFlashAttribute("message", "게시글이 삭제되었습니다.");
	    } else {
	        rttr.addFlashAttribute("error", "게시글 삭제에 실패했습니다.");
	    }
	    return "redirect:/admin/board/list";
	}
	
	
	
	
	
	
	@PostMapping("/modify")
	public String modifyBoard(Board board, RedirectAttributes rttr) {
	    int result = boardService.modifyBoard(board);
	    
	    if(result > 0) {
	        rttr.addFlashAttribute("message", "게시글이 수정되었습니다.");
	      
	    } 
	    return "redirect:/admin/board/list";
	}
	@GetMapping("/modify")
	public String modifyBoardView(@RequestParam(name="boardNum") String boardNum, Pageable pageable, Model model) {
		

		List<BoardCategory> categoryList = (List<BoardCategory>) boardCategoryService.getBoardCategoryList(pageable);
		Board boardInfo = boardService.getBoardInfo(boardNum);
		
		model.addAttribute("title","게시글수정");
	
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("boardInfo", boardInfo);
		
		
		return "admin/board/modifyBoard";
		
	}
	
	
	
	@PostMapping("/write")
	public String addBoard(Board board) {
		
		boardService.addBoard(board);
		
		return "redirect:/admin/board/list";
	}

	
	@GetMapping("/write")
	public String addBoardView(Pageable pageable,Model model) {
		
		
		List<BoardCategory> categoryList = (List<BoardCategory>) boardCategoryService.getBoardCategoryList(pageable);
		
		model.addAttribute("title", "게시물작성");

		model.addAttribute("categoryList", categoryList);
		return "admin/board/addBoard";
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


