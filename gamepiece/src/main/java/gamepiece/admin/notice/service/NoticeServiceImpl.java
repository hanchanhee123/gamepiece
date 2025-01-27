package gamepiece.admin.notice.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gamepiece.admin.board.domain.AdminBoardFiles;
import gamepiece.admin.board.mapper.BoardFileMapper;
import gamepiece.admin.board.util.BoardFilesUtils;
import gamepiece.admin.notice.domain.Notice;
import gamepiece.admin.notice.mapper.NoticeMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {
	

    @Value("${file.path}")
    private String fileRealPath;  
	
	
	private final NoticeMapper noticeMapper;
	private final BoardFileMapper boardFileMapper;
	private final BoardFilesUtils boardFilesUtils;
	
	
	@Override
	public PageInfo<Notice> getNoticeList(Pageable pageable) {
	
		int rowCnt = noticeMapper.getCntNotice();
		List<Notice> noticeList = noticeMapper.getNoticeList(pageable);
		
		
		
		return new PageInfo<>(noticeList, pageable, rowCnt);
	
	}


	@Override
	public Notice getNoticeInfo(int noticeNum) {
		
		return noticeMapper.getNoticeInfo(noticeNum);
	}


	@Override
	public int addNotice(Notice notice) {
		
		
		return noticeMapper.addNotice(notice);
		
	}


	@Override
	public int modifyNotice(Notice notice) {
		
		   if (notice.getFileIdx() != null) {
		        return noticeMapper.modifyNotice(notice);
		    }
		    return 0;
		}



	@Override
	public int removeNotice(int noticeNum) {

		return noticeMapper.removeNotice(noticeNum);
	}


	@Override
	public PageInfo<Notice> getSearchList(String searchValue, Pageable pageable) {
		 Map<String, Object> searchMap = new HashMap<String, Object>();
		 

		   searchMap.put("searchValue", searchValue);
		   searchMap.put("offset", pageable.getOffset());
		   searchMap.put("rowPerPage", pageable.getRowPerPage());
		   
		   int rowCnt = noticeMapper.getCntSearchNotice(searchMap);
		   List<Notice> noticeList = noticeMapper.getNoticeSearchList(searchMap);
		 
		   return new PageInfo<>(noticeList, pageable, rowCnt);
	}

	@Override
	@Transactional
	public void addFilesWithInfo(MultipartFile[] files, List<AdminBoardFiles> fileDtoList) {
	    if (fileDtoList.isEmpty()) {
	        log.warn("파일 정보 리스트가 비어있습니다.");
	        return;
	    }

	    for (int i = 0; i < files.length && i < fileDtoList.size(); i++) {
	        MultipartFile file = files[i];
	        AdminBoardFiles fileDto = fileDtoList.get(i);

	        boardFileMapper.addfile(fileDto);
	        log.info("데이터베이스에 파일 정보 저장 완료: {}", fileDto.getFileIdx());

	        try {
	            // 실제 파일 저장 경로 로깅
	            log.info("=== 파일 업로드 경로 정보 ===");
	            log.info("fileRealPath: {}", fileRealPath);
	            log.info("업로드 기본 경로: {}", fileRealPath + "/attachment");
	            log.info("날짜 경로: {}", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
	            log.info("파일 타입: {}", file.getContentType().contains("image") ? "image" : "file");

	            String uploadDir = fileRealPath + "/attachment";
	            String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	            String typePath = file.getContentType().contains("image") ? "image" : "file";

	            Path uploadPath = Paths.get(uploadDir, datePath, typePath);
	            log.info("최종 업로드 경로: {}", uploadPath.toAbsolutePath());

	            Files.createDirectories(uploadPath);

	            Path filePath = uploadPath.resolve(fileDto.getFileNewName());
	            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	            long fileSize = Files.size(filePath);
	            if (fileSize == 0) {
	                throw new IOException("파일이 0바이트로 저장됨");
	            }

	            log.info("파일 저장 완료: {}, 크기: {} bytes", filePath.toAbsolutePath(), fileSize);
	            
	        } catch (IOException e) {
	            log.error("파일 저장 중 오류 발생: {}", e.getMessage());
	            throw new RuntimeException("파일 저장 실패: " + e.getMessage(), e);
	        }
	    }
	}


	@Override
	public void addFile(MultipartFile file) {
		AdminBoardFiles fileInfo = boardFilesUtils.uploadFile(file);
		if(fileInfo != null) boardFileMapper.addfile(fileInfo); 
		
	}


	@Override
	public void addFiles(MultipartFile[] files) {
		List<AdminBoardFiles> fileList = boardFilesUtils.uploadFiles(files);
		if(!fileList.isEmpty()) boardFileMapper.addfiles(fileList);
		
	}


	@Override
	public void deleteFile(AdminBoardFiles fileDto) {
		String path = fileDto.getFilePath();
		Boolean isDelete = boardFilesUtils.deleteFileByPath(path);
		if(isDelete) boardFileMapper.deleteFileByIdx(fileDto.getFileIdx());
		
	}


	@Override
	public AdminBoardFiles getNoticeFile(int noticeNum) {
		// TODO Auto-generated method stub
		return boardFileMapper.findByNoticeNum(noticeNum);
	}

}
