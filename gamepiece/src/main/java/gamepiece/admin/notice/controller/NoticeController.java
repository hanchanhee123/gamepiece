package gamepiece.admin.notice.controller;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.board.domain.AdminBoardFiles;
import gamepiece.admin.board.mapper.BoardFileMapper;
import gamepiece.admin.board.util.BoardFilesUtils;
import gamepiece.admin.inquiry.controller.InquiryController;
import gamepiece.admin.notice.domain.Notice;
import gamepiece.admin.notice.service.NoticeService;
import gamepiece.user.board.domain.BoardFiles;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {
	
	
	
	@Value("${file.path}")
    private String fileRealPath;  
	
	private final NoticeService noticeService;
	private final BoardFileMapper boardFileMapper;
	private final BoardFilesUtils boardFilesUtils;
	
	
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
	
	
	
	@PostMapping("/uploadFiles")
	public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile[] files) {
	    // 파일 업로드
		 try {
		        if (files != null && files.length > 0) {
		        	noticeService.addFiles(files);
		            return ResponseEntity.ok("파일 업로드 성공");
		        }
		        return ResponseEntity.ok("업로드할 파일이 없습니다.");
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                           .body("파일 업로드 실패: " + e.getMessage());
		    }
	}
	
	
	
	@PostMapping("/searchList")
	public String postNoticeSearchList(@RequestParam(value="searchValue") String searchValue,
						Pageable pageable, Model model) {

	   PageInfo<Notice> pageInfo = noticeService.getSearchList(searchValue, pageable);

	   model.addAttribute("title", "공지사항 목록");
	   model.addAttribute("noticeList", pageInfo.getContents());
	   model.addAttribute("currentPage", pageInfo.getCurrentPage());
	   model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	   model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	   model.addAttribute("lastPage", pageInfo.getLastPage());
	   model.addAttribute("searchValue", searchValue);

	   return "admin/notice/noticeList";
	}

	@GetMapping("/searchList")
	public String noticeSearchList(
	   @RequestParam(value="searchValue", required = false) String searchValue,
	   Pageable pageable,
	   Model model) {

	   PageInfo<Notice> pageInfo = noticeService.getSearchList(searchValue, pageable);

	   model.addAttribute("title", "공지사항 목록");
	   model.addAttribute("noticeList", pageInfo.getContents());
	   model.addAttribute("currentPage", pageInfo.getCurrentPage());
	   model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	   model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	   model.addAttribute("lastPage", pageInfo.getLastPage());
	   model.addAttribute("searchValue", searchValue);

	   return "admin/notice/noticeList";
	}
	
	
	
	
	@PostMapping("/remove")
	public String removeNotice(@RequestParam(name="noticeNum") int noticeNum, RedirectAttributes rttr) {
	    int result = noticeService.removeNotice(noticeNum);

	
	        rttr.addFlashAttribute("message", "문의글 삭제되었습니다.");
	   
	    return "redirect:/admin/notice/list";
	}
	
	
	
	
	@PostMapping("/modify")
	@Transactional
	public String modifyNotice(Notice notice, 
	                          @RequestParam(required = false) MultipartFile[] files,
	                          RedirectAttributes rttr) {
	    try {
	        // 새 파일이 업로드된 경우
	        if (files != null && files.length > 0 && !files[0].isEmpty()) {
	            // 기존 파일 조회 및 삭제
	            AdminBoardFiles oldFile = noticeService.getNoticeFile(notice.getNoticeNum());
	            if (oldFile != null) {
	                noticeService.deleteFile(oldFile);
	            }

	            // 새 파일 정보 생성
	            String fileIdx = boardFileMapper.getNextFileIdx();
	            AdminBoardFiles newFile = new AdminBoardFiles();
	            newFile.setFileIdx(fileIdx);
	            newFile.setFileNewName(UUID.randomUUID().toString() + "_" + files[0].getOriginalFilename());
	            newFile.setFileOriginalName(files[0].getOriginalFilename());
	            newFile.setFilePath("/attachment/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
	            newFile.setFileSize(files[0].getSize());

	            // 파일 저장
	            boardFileMapper.addfile(newFile);
	            notice.setFileIdx(fileIdx);
	        }

	        // 게시글 수정
	        int result = noticeService.modifyNotice(notice);
	        if (result > 0) {
	            rttr.addFlashAttribute("message", "수정되었습니다.");
	        }

	        return "redirect:/admin/notice/list";
	    } catch (Exception e) {
	        e.printStackTrace();
	        rttr.addFlashAttribute("error", "수정 중 오류가 발생했습니다.");
	        return "redirect:/admin/notice/list";
	    }
	}
	
	
	
	
	
	@GetMapping("/modify")
	public String modifyNoticeView(@RequestParam(name="noticeNum") int noticeNum, Model model) {
		
		Notice noticeInfo = noticeService.getNoticeInfo(noticeNum);
		
		 AdminBoardFiles boardFile = noticeService.getNoticeFile(noticeNum);
		
		model.addAttribute("noticeInfo", noticeInfo);
		model.addAttribute("title", "공지수정");
		model.addAttribute("boardFile", boardFile);
		
		
		
		return "admin/notice/modifyNotice";	
	}
	
	@PostMapping("/write")
	public String addNotice(Notice notice, @RequestParam(required = false) MultipartFile[] files,
	                       HttpSession session,
	                       RedirectAttributes rttr) {
	    String adminId = "id01";
	    notice.setAdminId(adminId);

	    if (files != null && files.length > 0 && !files[0].isEmpty()) {
	        List<AdminBoardFiles> fileDtoList = new ArrayList<>();
	        synchronized (this) {
	            MultipartFile file = files[0];  // 첫 번째 파일만 처리
	            AdminBoardFiles noticeFiles = new AdminBoardFiles();

	            String fileIdx = boardFileMapper.getNextFileIdx();
	            noticeFiles.setFileIdx(fileIdx);
	            noticeFiles.setFileNewName(UUID.randomUUID().toString() + "_" + file.getOriginalFilename());
	            noticeFiles.setFileOriginalName(file.getOriginalFilename());
	            noticeFiles.setFilePath("/attachment/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
	            noticeFiles.setFileSize(file.getSize());

	            fileDtoList.add(noticeFiles);
	            notice.setFileIdx(fileIdx);

	            noticeService.addFilesWithInfo(new MultipartFile[]{file}, fileDtoList);
	        }
	    }

	    noticeService.addNotice(notice);
	    return "redirect:/admin/notice/list";
	}
	
	
	@GetMapping("/write")
	public String addNoticeView(Model model) {
		

		
		model.addAttribute("title", "공지작성");
	
		return "admin/notice/addNotice";
	}
	
	
	
	@GetMapping("/list")
	public String getNoticeList(Pageable pageable, Model model) {
		
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
