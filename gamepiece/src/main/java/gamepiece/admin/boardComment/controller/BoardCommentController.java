package gamepiece.admin.boardComment.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import gamepiece.admin.boardComment.domain.BoardComment;
import gamepiece.admin.boardComment.service.BoardCommentService;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/boardComment")
@RequiredArgsConstructor
public class BoardCommentController {
	
		private final BoardCommentService boardCommentService;
		
	


		@PostMapping("/remove")
		public String removeBoard(@RequestParam(name="commentNum") String commentNum, RedirectAttributes rttr) {
		    int result = boardCommentService.removeComment(commentNum);

		 
		        rttr.addFlashAttribute("message", "댓글이 삭제되었습니다.");
		  
		    return "redirect:/admin/boardComment/list";
		}
		
		
		
		@PostMapping("/modify")
		public String modifyCategory(BoardComment boardComment, RedirectAttributes rttr) {
					int result = boardCommentService.modifyComment(boardComment);
		    
		    if(result > 0) {
		        rttr.addFlashAttribute("message", "게시글이 수정되었습니다.");
		      
		    } 
		    return "redirect:/admin/boardComment/list";
		}
			
		
		
		
		
		
		
		
		@GetMapping("/modify")
		public String modifyCategoryView(@RequestParam(name="commentNum") String commentNum, Model model) {
			
			BoardComment commentInfo = boardCommentService.getCommentInfo(commentNum);
			
			
			model.addAttribute("title", "댓글수정");
			model.addAttribute("commentInfo", commentInfo);
			
			
			return "admin/boardComment/modifyBoardComment";
		}
		
		
		@PostMapping("/write")
		public String addComment(BoardComment boardComment) {
			
			
			boardCommentService.addComment(boardComment);
			
			return "redirect:/admin/boardComment/list";
		}
		
		
		
		@GetMapping("/write")
		public String addCommentView(Model model) {
			
			model.addAttribute("title", "댓글작성");
			
			return "admin/boardComment/addBoardComment";
		}
		
		
		
		@GetMapping("/list")
		public String BoardCommentList(Pageable pageable,Model model) {
			
			 var pageInfo = boardCommentService.getCommentList(pageable);
			 
			 List<BoardComment> commentList = pageInfo.getContents();
			    int currentPage = pageInfo.getCurrentPage();
			    int startPageNum = pageInfo.getStartPageNum();
			    int endPageNum = pageInfo.getEndPageNum();
			    int lastPage = pageInfo.getLastPage();

			
			    model.addAttribute("title", "댓글내역");
			    model.addAttribute("commentList", commentList);
				  model.addAttribute("currentPage", currentPage);
				    model.addAttribute("startPageNum", startPageNum);
				    model.addAttribute("endPageNum", endPageNum);
				    model.addAttribute("lastPage", lastPage);
			
			
			return "admin/boardComment/BoardCommentList";
		}


}
