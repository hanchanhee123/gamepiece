package gamepiece.admin.report.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamepiece.admin.report.domain.Disposal;
import gamepiece.admin.report.domain.Report;
import gamepiece.admin.report.service.ReportService;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin/report")
@RequiredArgsConstructor
public class ReportController {
	
		private final ReportService reportService;
		
		
	
		
		
		
		@GetMapping("/detail")
		public String disposalInfo(@RequestParam(name="reportNo") String reportNo,Model model) {
			
			Disposal disposalInfo = reportService.getDisposalInfo(reportNo);
			
			System.out.println("disposalCriteria: " + disposalInfo.getDisposalCriteria());
		model.addAttribute("title", "처분상세");	
		model.addAttribute("disposalInfo", disposalInfo);
		
			return "admin/report/disposalDetail";
		}
		
		
		
	
	@GetMapping("/list")
	public String reportList(Pageable pageable, Model model) {
		
		var pageInfo = reportService.getInquiryList(pageable);
		
		List<Report> reportList = pageInfo.getContents();
	    int currentPage = pageInfo.getCurrentPage();
	    int startPageNum = pageInfo.getStartPageNum();
	    int endPageNum = pageInfo.getEndPageNum();
	    int lastPage = pageInfo.getLastPage();
	    
		model.addAttribute("title", "신고내역");
		model.addAttribute("reportList", reportList);
		  model.addAttribute("currentPage", currentPage);
		    model.addAttribute("startPageNum", startPageNum);
		    model.addAttribute("endPageNum", endPageNum);
		    model.addAttribute("lastPage", lastPage);
		    
		
		return "admin/report/reportList";
		
		
		
	}

}
