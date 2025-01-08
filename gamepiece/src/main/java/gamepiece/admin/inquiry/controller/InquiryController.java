package gamepiece.admin.inquiry.controller;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.inquiry.domain.Inquiry;
import gamepiece.admin.inquiry.service.InquiryService;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/inquiry")
@RequiredArgsConstructor
public class InquiryController {
	
	
	private final InquiryService inquiryService;
	
	
	
	@PostMapping("/remove")
	public String removeInquiry(@RequestParam("inquiryNum") String inquiryNum, RedirectAttributes rttr) {
	    int result = inquiryService.removeInquiry(inquiryNum);

	    if(result > 0) {
	        rttr.addFlashAttribute("message", "문의글 삭제되었습니다.");
	    } else {
	        rttr.addFlashAttribute("error", "문의글 삭제에 실패했습니다.");
	    }
	    return "redirect:/admin/inquiry/list";
	}
	
  
	@PostMapping("/modify")
	public String modifyInquiry(Inquiry inquiry, RedirectAttributes rttr) {
	    int result = inquiryService.modifyInquiry(inquiry);
	    
	    
	
	    
	    if(result > 0) {
	        rttr.addFlashAttribute("message", "게시글이 수정되었습니다.");
		 
	    } else {
	        rttr.addFlashAttribute("error", "게시글 수정에 실패했습니다.");
	       
	    }
	    return "redirect:/admin/inquiry/list";

	}
	
	@GetMapping("/modify")
	public String modifyInquiryView(@RequestParam(name="inquiryNum") String inquiryNum, Model model) {
		Inquiry inquiryInfo = inquiryService.getInquiryInfo(inquiryNum);
		
		
		model.addAttribute("title","문의글수정");
		model.addAttribute("inquiryInfo", inquiryInfo);
	
		
		return "admin/inquiry/modifyInquiry";
	}
	

	@PostMapping("/write")
	public String addInquiry(Inquiry inquiry) {
		
		
		inquiryService.addInquiry(inquiry);
		
		return "redirect:/admin/inquiry/list";
	}
	
	
	
	 
	@GetMapping("/write")
	public String addInquiryView(Model model) {
		

		
		model.addAttribute("title", "문의글작성");
	
		return "admin/inquiry/addInquiry";
	}
	
	
	
	
	
	
	
	@GetMapping("/list")
	public String inquiryList(Pageable pageable, Model model) {
		
	    var pageInfo = inquiryService.getInquiryList(pageable);  
	    
	    List<Inquiry> inquiryList = pageInfo.getContents();
	    int currentPage = pageInfo.getCurrentPage();
	    int startPageNum = pageInfo.getStartPageNum();
	    int endPageNum = pageInfo.getEndPageNum();
	    int lastPage = pageInfo.getLastPage();

		
		model.addAttribute("title", "문의내역");
		model.addAttribute("inquiryList", inquiryList);
		  model.addAttribute("currentPage", currentPage);
		    model.addAttribute("startPageNum", startPageNum);
		    model.addAttribute("endPageNum", endPageNum);
		    model.addAttribute("lastPage", lastPage);
		    
		
		return "admin/inquiry/inquiryList";
		
	}

}
