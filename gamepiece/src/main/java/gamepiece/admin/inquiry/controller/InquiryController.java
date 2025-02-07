package gamepiece.admin.inquiry.controller;





import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.board.domain.AdminBoardFiles;
import gamepiece.admin.board.mapper.BoardFileMapper;
import gamepiece.admin.inquiry.domain.Inquiry;
import gamepiece.admin.inquiry.domain.InquiryFiles;
import gamepiece.admin.inquiry.domain.InquiryRespone;
import gamepiece.admin.inquiry.service.InquiryService;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/inquiry")
@RequiredArgsConstructor
@Slf4j
public class InquiryController {
	
	
	@Value("${file.path}")
    private String fileRealPath;  
	
	private final InquiryService inquiryService;
	private final BoardFileMapper boardFileMapper;
	
	
	

	
	@GetMapping("/download")
	public ResponseEntity<Object> downloadFile(@RequestParam String fileIdx,
	        HttpServletRequest request) {
		log.info("다운로드 요청된 fileIdx: {}", fileIdx);
	    try {
	        AdminBoardFiles fileDto = boardFileMapper.getFileInfoByIdx(fileIdx);
	        log.info("조회된 파일 정보: {}", fileDto);  // fileDto가 null인지 확인
	        if(fileDto == null) {
	            return ResponseEntity.notFound().build();
	        }
	        
	        // contentType 분류해서 경로 구성
	        String fileType = fileDto.getFileNewName().toLowerCase().matches(".*\\.(jpg|jpeg|png|gif|bmp)$") 
	                       ? "/image" : "/file";
	        
	        // 전체 경로 구성
	        String fullPath = fileRealPath + fileDto.getFilePath() 
	                       + fileType + "/" + fileDto.getFileNewName();
									        
	        log.info("다운로드 시도 경로: {}", fullPath);
	        
	        File file = new File(fullPath);
	        if (!file.exists()) {
	            log.error("파일이 존재하지 않음: {}", fullPath);
	            return ResponseEntity.notFound().build();
	        }
	        
	        log.info("파일 크기: {} bytes", file.length());
	        
	        Path path = Paths.get(file.getAbsolutePath());
	        Resource resource = new UrlResource(path.toUri());

	        String contentType = request.getServletContext()
	                .getMimeType(resource.getFile().getAbsolutePath());
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" +
	                                URLEncoder.encode(fileDto.getFileOriginalName(),"UTF-8") +
	                                "\";")
	                .body(resource);
	        
	    } catch (Exception e) {
	        log.error("파일 다운로드 중 오류 발생: {}", e.getMessage());
	        return ResponseEntity.internalServerError().build();
	    }
	}
	
	
	
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
	   HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("SID");
        model.addAttribute("adminId", id);

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
	public String inquiryDetailView(@RequestParam(name="inquiryNum") String inquiryNum, HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("SID");
        model.addAttribute("adminId", id);
		
		Inquiry inquiryInfo = inquiryService.getInquiryInfo(inquiryNum);
		InquiryRespone responeInfo = inquiryService.getInquiryResponeInfo(inquiryNum);
		
		List<InquiryFiles> inquiryFiles = inquiryService.getInquiryFiles(inquiryNum);
		
		model.addAttribute("title","문의글상세");
		model.addAttribute("inquiryInfo", inquiryInfo);
		model.addAttribute("responeInfo", responeInfo);
		model.addAttribute("inquiryFiles", inquiryFiles);
	
		
		return "admin/inquiry/inquiryDetail";
	}
	


	
	
	
	
	
	@GetMapping("/list")
	public String getInquiryList(Pageable pageable, HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("SID");
        model.addAttribute("adminId", id);	
		
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
