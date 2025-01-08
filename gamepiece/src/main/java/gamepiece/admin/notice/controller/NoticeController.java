package gamepiece.admin.notice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.inquiry.domain.Inquiry;
import gamepiece.admin.notice.domain.Notice;
import gamepiece.admin.notice.service.NoticeService;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService noticeService;
	
	
	@PostMapping("/remove")
	public String removeInquiry(@RequestParam(name="noticeNum") int noticeNum, RedirectAttributes rttr) {
	    int result = noticeService.removeNotice(noticeNum);

	    if(result > 0) {
	        rttr.addFlashAttribute("message", "문의글 삭제되었습니다.");
	    } else {
	        rttr.addFlashAttribute("error", "문의글 삭제에 실패했습니다.");
	    }
	    return "redirect:/admin/notice/list";
	}
	
	@PostMapping("/modify")
	public String modifyInquiry(Notice notice, RedirectAttributes rttr) {
	    int result = noticeService.modifyNotice(notice);
	    
	    if(result > 0) {
	        rttr.addFlashAttribute("message", "게시글이 수정되었습니다.");
		 
	    } else {
	        rttr.addFlashAttribute("error", "게시글 수정에 실패했습니다.");
	       
	    }
	    return "redirect:/admin/notice/list";

	}
	
	@GetMapping("/modify")
	public String modifyNoticeView(@RequestParam(name="noticeNum") int noticeNum, Model model) {
		
		Notice noticeInfo = noticeService.getNoticeInfo(noticeNum);
		
		
		model.addAttribute("noticeInfo", noticeInfo);
		model.addAttribute("title", "공지수정");
		
		
		
		return "admin/notice/modifyNotice";
		
		
		
	}
	
	
	@PostMapping("/write")
	public String addNotice(Notice notice) {
		
		noticeService.addNotice(notice);
		
		
		return "redirect:/admin/notice/list";
	}
	
	
	
	@GetMapping("/write")
	public String addNoticeView(Model model) {
		

		
		model.addAttribute("title", "공지작성");
	
		return "admin/notice/addNotice";
	}
	
	
	
	@GetMapping("/list")
	public String noticeList(Pageable pageable, Model model) {
		
		var pageInfo = noticeService.getNoticeList(pageable);

	    List<Notice> noticeList = pageInfo.getContents();
	    int currentPage = pageInfo.getCurrentPage();
	    int startPageNum = pageInfo.getStartPageNum();
	    int endPageNum = pageInfo.getEndPageNum();
	    int lastPage = pageInfo.getLastPage();

	    model.addAttribute("title", "공지사항");
	    model.addAttribute("noticeList", noticeList);
	    model.addAttribute("currentPage", currentPage);
	    model.addAttribute("startPageNum", startPageNum);
	    model.addAttribute("endPageNum", endPageNum);
	    model.addAttribute("lastPage", lastPage);
	  
		
		
		
		return "admin/notice/noticeList";
	}
	
	
	
	
	
	
}
