package gamepiece.admin.report.controller;





import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.report.domain.Disposal;
import gamepiece.admin.report.domain.Report;
import gamepiece.admin.report.service.ReportService;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/report")
@RequiredArgsConstructor
@Slf4j
public class ReportController {
	
		private final ReportService reportService;
		
		
		
	
	
		
		@PostMapping("/disposal/write")
		public String addDisposal(Disposal disposal, RedirectAttributes rttr) {
		
		    
		    // 1. 처분 정보 추가
		    int result = reportService.addDisposal(disposal);
		    
		    // 2. 신고 상태 '처리 완료'로 변경
		    Report report = new Report();
		    report.setReportNo(disposal.getReportNo());
		    reportService.modifyReportClear(report);
		    
		
		    return "redirect:/admin/report/list";
		}
		
		@GetMapping("/disposal/write")
		public String addDisposalView(@RequestParam("reportNo") String reportNo, Model model) {
			
		    model.addAttribute("reportNo", reportNo);
			model.addAttribute("title", "처분작성");
			

			return "admin/report/addDisposal";
		}
		
		
		
		@PostMapping("/reportReview")
		public String reportReview(@RequestParam String reportNumbers, RedirectAttributes rttr) {
		    // 로그 추가
	
		    
		    List<String> reportNumbersList = Arrays.asList(reportNumbers.split(","));
		    // 변환된 리스트 확인
		
		    
		    int result = reportService.modifyReportReview(reportNumbersList);
		    // 결과값 확인
		
		    
		
		        rttr.addFlashAttribute("message", "접수가 되었습니다");
		    
		    return "redirect:/admin/report/list";
		}
		
		
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
