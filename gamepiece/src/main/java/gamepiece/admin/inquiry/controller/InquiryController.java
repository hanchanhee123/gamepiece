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
import gamepiece.admin.inquiry.domain.InquiryRespone;
import gamepiece.admin.inquiry.service.InquiryService;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/inquiry")
@RequiredArgsConstructor
public class InquiryController {
	
	
	private final InquiryService inquiryService;
	
	
	
	@PostMapping("/searchList")
	public String postInquirySearchList(@RequestParam(value="searchValue") String searchValue,Pageable pageable,
	   Model model) {

	   PageInfo<Inquiry> pageInfo = inquiryService.getSearchList(searchValue, pageable);

	   model.addAttribute("title", "문의사항 목록");
	   model.addAttribute("inquiryList", pageInfo.getContents());
	   model.addAttribute("currentPage", pageInfo.getCurrentPage());
	   model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	   model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	   model.addAttribute("lastPage", pageInfo.getLastPage());
	   model.addAttribute("searchValue", searchValue);

	   return "admin/inquiry/inquiryList";
	}

	@GetMapping("/searchList")
	public String inquirySearchList( @RequestParam(value="searchValue", required = false) String searchValue,
	   Pageable pageable,
	   Model model) {

	   PageInfo<Inquiry> pageInfo = inquiryService.getSearchList(searchValue, pageable);

	   model.addAttribute("title", "문의사항 목록");
	   model.addAttribute("inquiryList", pageInfo.getContents());
	   model.addAttribute("currentPage", pageInfo.getCurrentPage());
	   model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	   model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	   model.addAttribute("lastPage", pageInfo.getLastPage());
	   model.addAttribute("searchValue", searchValue);

	   return "admin/inquiry/inquiryList";
	}
	
	

	
	@PostMapping("/respone/add")
	public String addInquiryResponse(InquiryRespone inquiryRespone, RedirectAttributes rttr) {
	    inquiryService.addInquiryRespone(inquiryRespone);
	    rttr.addFlashAttribute("message", "답변이 입력되었습니다.");
	    
	    return "redirect:/admin/inquiry/detail?inquiryNum=" + inquiryRespone.getInquiryNum();
	}

	@PostMapping("/respone/modify") 
	public String modifyInquiryResponse(InquiryRespone inquiryRespone, RedirectAttributes rttr) {
	    inquiryService.modifyInquiryRespone(inquiryRespone);
	    rttr.addFlashAttribute("message", "답변이 수정되었습니다.");
	    
	    return "redirect:/admin/inquiry/detail?inquiryNum=" + inquiryRespone.getInquiryNum();
	}
	
	

	
	@GetMapping("/detail")
	public String inquiryDetailView(@RequestParam(name="inquiryNum") String inquiryNum, Model model) {
		Inquiry inquiryInfo = inquiryService.getInquiryInfo(inquiryNum);
		InquiryRespone responeInfo = inquiryService.getInquiryResponeInfo(inquiryNum);
		
		
		model.addAttribute("title","문의글상세");
		model.addAttribute("inquiryInfo", inquiryInfo);
		model.addAttribute("responeInfo", responeInfo);
	
		
		return "admin/inquiry/inquiryDetail";
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
