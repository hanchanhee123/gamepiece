package gamepiece.admin.notice.controller;




import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import gamepiece.admin.notice.domain.Notice;
import gamepiece.admin.notice.domain.NoticeFiles;
import gamepiece.admin.notice.mapper.NoticeFilesMapper;
import gamepiece.admin.notice.service.NoticeService;
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
	private final NoticeFilesMapper noticeFilesMapper;
	
	
	
	
	@GetMapping("/display")
	public ResponseEntity<Resource> getImage(@RequestParam("fileIdx") String fileIdx) {
	    try {
	        AdminBoardFiles fileDto = boardFileMapper.getFileInfoByIdx(fileIdx);
	        if(fileDto == null) return ResponseEntity.notFound().build();

	        String fullPath;
	        String filePath = fileDto.getFilePath();
	        
	        // 공지사항과 일반게시판/문의 구분
	        if (filePath.endsWith("/image") || filePath.endsWith("/file")) {
	            // 공지사항
	            fullPath = fileRealPath + filePath + "/" + fileDto.getFileNewName();
	        } else {
	            // 일반게시판/문의
	            fullPath = fileRealPath + filePath;
	        }

	        Path path = Paths.get(fullPath);
	        Resource resource = new UrlResource(path.toUri());

	        if(!resource.exists()) {
	            return ResponseEntity.notFound().build();
	        }

	        // 이미지 타입 결정
	        String contentType = "image/" + extractExt(fileDto.getFileOriginalName());
	        
	        // 응답 헤더 최적화
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.parseMediaType(contentType));
	        headers.setCacheControl("public, max-age=31536000"); // 캐시 1년
	        headers.setContentLength(resource.contentLength());

	        return ResponseEntity.ok()
	                .headers(headers)
	                .body(resource);
	                
	    } catch (Exception e) {
	        log.error("이미지 로딩 중 오류 발생: ", e);
	        return ResponseEntity.internalServerError().build();
	    }
	}

	private String extractExt(String fileName) {
	    int dot = fileName.lastIndexOf('.');
	    return dot > -1 ? fileName.substring(dot + 1) : "";
	}
	
	
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
						Pageable pageable, HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("SID");
        model.addAttribute("adminId", id);
        
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
	   HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("SID");
        model.addAttribute("adminId", id);
		
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
	                          @RequestParam(required = false) String[] deletedFiles,
	                          RedirectAttributes rttr) {
	     try {
	        // 1. 게시글 수정
	        noticeService.modifyNotice(notice);

	        // 2. 삭제할 파일 처리
	        if (deletedFiles != null && deletedFiles.length > 0) {
	            for (String fileIdx : deletedFiles) {
	                // 파일 매핑만 삭제
	            	noticeFilesMapper.deleteFileMapping(notice.getNoticeNum(), fileIdx);
	                // 실제 파일도 삭제
	            	AdminBoardFiles fileInfo = boardFileMapper.findByFileIdx(fileIdx);
	                if (fileInfo != null) {
	                    noticeService.deleteFile(fileInfo);
	                }
	            }
	        }

	        // 3. 새 파일 추가
	        if (files != null && files.length > 0 && !files[0].isEmpty()) {
	            List<AdminBoardFiles> newFiles = boardFilesUtils.uploadFiles(files);
	            
	            // 3-1. 새 파일 정보 저장
	            boardFileMapper.addfiles(newFiles);
	            
	            // 3-2. 새 파일 매핑 추가
	            for (AdminBoardFiles file : newFiles) {
	            	NoticeFiles mapping = new NoticeFiles();
	                mapping.setNoticeNum(notice.getNoticeNum());
	                mapping.setFileIdx(file.getFileIdx());  // () 추가
	                noticeFilesMapper.addNoticeMapping(mapping);
	            }
	        }

	        rttr.addFlashAttribute("message", "게시글이 수정되었습니다.");
	        return "redirect:/admin/notice/list";

	    } catch (Exception e) {
	        e.printStackTrace();
	        rttr.addFlashAttribute("error", "게시글 수정 중 오류가 발생했습니다.");
	        return "redirect:/admin/notice/list";
	    }
	}
	
	
	
	
	
	@GetMapping("/modify")
	public String modifyNoticeView(@RequestParam(name="noticeNum") int noticeNum, HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("SID");
        model.addAttribute("adminId", id);
		
		Notice noticeInfo = noticeService.getNoticeInfo(noticeNum);
		
		List<NoticeFiles> noticeFiles = noticeService.getNoticeFiles(noticeNum);
		
		model.addAttribute("noticeInfo", noticeInfo);
		model.addAttribute("title", "공지수정");
		model.addAttribute("noticeFiles", noticeFiles);
		
		
		
		return "admin/notice/modifyNotice";	
	}
	
	@PostMapping("/write")
	public String addNotice(Notice notice, 
	                       @RequestParam(required = false) MultipartFile[] files,
	                       HttpSession session, 
	                       RedirectAttributes rttr) {
	    
	    String adminId = "id01";
	    notice.setAdminId(adminId);
	    
	    try {
	        // 1. 게시글 저장
	        int result = noticeService.addNotice(notice);
	        if (result <= 0) {
	            throw new RuntimeException("게시글 저장에 실패했습니다.");
	        }

	        // 2. 파일 업로드 및 매핑 처리
	        if (files != null && files.length > 0 && !files[0].isEmpty()) {
	            // 중복 파일 체크를 위한 Set
	            Set<String> processedFiles = new HashSet<>();
	            String lastFileIdx = boardFileMapper.getNextFileIdx();
	            int nextNum = 1;
	            
	            if (lastFileIdx != null) {
	                String numStr = lastFileIdx.substring(lastFileIdx.lastIndexOf("_") + 1);
	                nextNum = Integer.parseInt(numStr) + 1;
	            }

	            for (MultipartFile file : files) {
	                if (!file.isEmpty()) {
	                    // 이미 처리된 파일은 스킵
	                    String originalFileName = file.getOriginalFilename();
	                    if (!processedFiles.add(originalFileName)) {
	                        continue;
	                    }

	                    String fileIdx = String.format("file_%03d", nextNum++);
	                    AdminBoardFiles fileInfo = boardFilesUtils.storeFile(file, fileIdx);
	                    
	                    if (fileInfo != null) {
	                        // 파일 정보 저장
	                        boardFileMapper.addfile(fileInfo);
	                        
	                        // 게시글-파일 매핑 생성 (한 번만)
	                        NoticeFiles mapping = new NoticeFiles();
	                        mapping.setNoticeNum(notice.getNoticeNum());
	                        mapping.setFileIdx(fileInfo.getFileIdx());
	                        noticeFilesMapper.addNoticeMapping(mapping);
	                    }
	                }
	            }
	        }

	        rttr.addFlashAttribute("message", "게시글이 작성되었습니다.");
	        return "redirect:/admin/notice/list";
	        
	    } catch (Exception e) {
	        log.error("Error in addNotice: ", e);
	        rttr.addFlashAttribute("error", "게시글 작성 중 오류가 발생했습니다.");
	        return "redirect:/admin/notice/list";
	    }
	}
	@GetMapping("/write")
	public String addNoticeView(HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("SID");
        model.addAttribute("adminId", id);
		
		model.addAttribute("title", "공지작성");
	
		return "admin/notice/addNotice";
	}
	
	
	
	@GetMapping("/list")
	public String getNoticeList(Pageable pageable, HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("SID");
        model.addAttribute("adminId", id);
		
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
