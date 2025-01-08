package gamepiece.admin.boardCategory.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamepiece.admin.boardCategory.domain.BoardCategory;
import gamepiece.admin.boardCategory.service.BoardCategoryService;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;




@Controller
@RequestMapping("/admin/boardCategory")
@RequiredArgsConstructor
public class BoardCategoryController {
	
	private final BoardCategoryService boardCategoryService;
	
	

	

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
	public String BoardCategoryList(Pageable pageable, Model model) {
		
	
		
		
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
