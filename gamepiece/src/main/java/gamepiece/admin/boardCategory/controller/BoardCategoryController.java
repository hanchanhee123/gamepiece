package gamepiece.admin.boardCategory.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.boardCategory.domain.BoardCategory;
import gamepiece.admin.boardCategory.service.BoardCategoryService;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;




@Controller
@RequestMapping("/admin/boardCategory")
@RequiredArgsConstructor
public class BoardCategoryController {
	
	private final BoardCategoryService boardCategoryService;
	
	
	@PostMapping("/remove")
	public String removeBoard(@RequestParam(name="categoryCode") String categoryCode, RedirectAttributes rttr) {
	    int result = boardCategoryService.removeCategory(categoryCode);

	    
	        rttr.addFlashAttribute("message", "게시글이 삭제되었습니다.");
	   
	    return "redirect:/admin/boardCategory/list";
	}
	

	@PostMapping("/modify")
	public String modifyCategory(BoardCategory boardCategory, RedirectAttributes rttr) {
				int result = boardCategoryService.modifyCategory(boardCategory);
	    
	    if(result > 0) {
	        rttr.addFlashAttribute("message", "게시글이 수정되었습니다.");
	      
	    } 
	    return "redirect:/admin/boardCategory/list";
	}
		
		
		
	
	
	

	@GetMapping("/modify")
	public String modifyCategoryView(@RequestParam(name="categoryCode") String categoryCode, Model model) {
		
		BoardCategory categoryInfo = boardCategoryService.getCategoryInfo(categoryCode);
		
		
		model.addAttribute("title", "카테고리수정");
		model.addAttribute("categoryInfo", categoryInfo);
		
		
		return "admin/boardCategory/modifyBoardCategory";
	}
	

	@PostMapping("/write")
	public String addCategory(BoardCategory boardCategory) {
			
		boardCategoryService.addBoardCategory(boardCategory);
		
		return "redirect:/admin/boardCategory/list";
		
	}
	
	
	@GetMapping("/write")
	public String addCategoryView(Model model) {
		
		
		model.addAttribute("title", "카테고리추가");
		return "admin/boardCategory/addBoardCategory";
	}
	
	
	
	@GetMapping("/list")
	public String getBoardCategoryList(Pageable pageable, Model model) {
		
	
		
		
		var pageInfo = boardCategoryService.getBoardCategoryList(pageable);
	
		List<BoardCategory> categoryList = pageInfo.getContents();
	    int currentPage = pageInfo.getCurrentPage();
	    int startPageNum = pageInfo.getStartPageNum();
	    int endPageNum = pageInfo.getEndPageNum();
	    int lastPage = pageInfo.getLastPage();


		
		model.addAttribute("title","카테고리목록");
		model.addAttribute("categoryList", categoryList);
		 model.addAttribute("currentPage", currentPage);
		    model.addAttribute("startPageNum", startPageNum);
		    model.addAttribute("endPageNum", endPageNum);
		    model.addAttribute("lastPage", lastPage);
		
		return "admin/boardCategory/boardCategoryList";
		
		
	}
	
	
	
	
	
	
	

}
