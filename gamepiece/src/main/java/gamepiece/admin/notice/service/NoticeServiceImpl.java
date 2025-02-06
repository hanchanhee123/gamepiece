package gamepiece.admin.notice.service;



import java.io.File;
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
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gamepiece.admin.board.domain.AdminBoardFiles;
import gamepiece.admin.board.mapper.BoardFileMapper;
import gamepiece.admin.board.util.BoardFilesUtils;
import gamepiece.admin.notice.domain.Notice;
import gamepiece.admin.notice.domain.NoticeFiles;
import gamepiece.admin.notice.mapper.NoticeFilesMapper;
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
	private final NoticeFilesMapper noticeFilesMapper;
	
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

	@Override
	@Transactional
	public void addNoticeWithFiles(Notice notice, MultipartFile[] files) {
	    try {
	        if (files != null && files.length > 0 && !files[0].isEmpty()) {
	            String rootPath = getOSFilePath(); // OS에 따른 루트 경로 가져오기
	            
	            // 파일 인덱스 시작값 한 번만 조회
	            String lastFileIdx = boardFileMapper.getNextFileIdx();
	            int startNum = 1;
	            
	            if (lastFileIdx != null) {
	                String numStr = lastFileIdx.substring(lastFileIdx.lastIndexOf("_") + 1);
	                startNum = Integer.parseInt(numStr) + 1;
	            }
	            
	            // 각 파일 처리
	            for (int i = 0; i < files.length; i++) {
	                MultipartFile file = files[i];
	                if (!file.isEmpty()) {
	                    // 새로운 fileIdx 생성
	                    String fileIdx = String.format("file_%03d", startNum + i);
	                    
	                    // 날짜 폴더명 생성
	                    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	                    String relativePath = "/attachment/" + today + 
	                        (file.getContentType().contains("image") ? "/image" : "");
	                    
	                    // 전체 경로 생성 및 디렉토리 생성
	                    String fullPath = rootPath + fileRealPath + relativePath;
	                    File dir = new File(fullPath);
	                    if (!dir.exists()) {
	                        dir.mkdirs();
	                    }
	                    
	                    // 새 파일명 생성
	                    String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	                    
	                    // 파일 정보 생성
	                    AdminBoardFiles boardFile = AdminBoardFiles.builder()
	                            .fileIdx(fileIdx)
	                            .fileOriginalName(file.getOriginalFilename())
	                            .fileNewName(newFileName)
	                            .filePath(relativePath)
	                            .fileSize(file.getSize())
	                            .build();
	                    
	                    // 실제 파일 저장
	                    File saveFile = new File(dir, newFileName);
	                    file.transferTo(saveFile);
	                    
	                    // DB에 파일 정보 저장
	                    boardFileMapper.addfile(boardFile);
	                    
	                    // 게시글-파일 매핑 생성
	                    NoticeFiles mapping = new NoticeFiles();
	                    mapping.setNoticeNum(notice.getNoticeNum());
	                    mapping.setFileIdx(fileIdx);
	                    noticeFilesMapper.addNoticeMapping(mapping);
	                }
	            }
	        }
	    } catch (Exception e) {
	        log.error("파일 처리 중 오류 발생: ", e);
	        throw new RuntimeException("파일 처리 중 오류가 발생했습니다.", e);
	    }
	}

	// OS에 따른 루트 경로 가져오기
	private String getOSFilePath() {
	    String rootPath = "/";
	    String os = System.getProperty("os.name").toLowerCase();
	    
	    if(os.contains("win")) {
	        rootPath = "C:";
	    }
	    return rootPath;
	}
	@Override
	public List<NoticeFiles> getNoticeFiles(int noticeNum) {
		// TODO Auto-generated method stub
		return noticeFilesMapper.getNoticeFiles(noticeNum);
	}


	@Override
	public void addNoticeFileMapping(int noticeNum, String fileIdx) {
		// TODO Auto-generated method stub
		NoticeFiles mapping = new NoticeFiles();
		mapping.setNoticeNum(noticeNum);
		mapping.setFileIdx(fileIdx);
		noticeFilesMapper.addNoticeMapping(mapping);
	}


	@Override
	public void deleteBoardFileMapping(int noticeNum, String fileIdx) {
		
		noticeFilesMapper.addFileMapping(noticeNum, fileIdx);
		
	}


	@Override
	public void deleteAllNoticeBoardFiles(int noticeNum) {

			noticeFilesMapper.deleteAllFileMapping(noticeNum);
		
	}


	@Override
	public void updateNoticeFiles(int noticeNum, List<MultipartFile> newFiles) {
	
		deleteAllNoticeBoardFiles(noticeNum);
		
		 for(MultipartFile file : newFiles) {
	            if(!file.isEmpty()) {
	            	AdminBoardFiles fileInfo = saveFile(file); // 파일 저장 메서드 (기존 로직 사용)
	                addNoticeFileMapping(noticeNum, fileInfo.getFileIdx());
	            }
	        }
		
	}
	

    @Transactional
    public AdminBoardFiles saveFile(MultipartFile file) {
        try {
            String fileIdx = boardFileMapper.getNextFileIdx();
            String originalFileName = file.getOriginalFilename();
            String newFileName = UUID.randomUUID().toString() + "_" + originalFileName;
            String filePath = "/attachment/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            
            AdminBoardFiles boardFile = AdminBoardFiles.builder()
                    .fileIdx(fileIdx)
                    .fileOriginalName(originalFileName)
                    .fileNewName(newFileName)
                    .filePath(filePath)
                    .fileSize(file.getSize())
                    .build();

            // 파일 저장 로직
            File saveFile = new File(filePath);
            if (!saveFile.exists()) {
                saveFile.mkdirs();
            }
            file.transferTo(new File(filePath + File.separator + newFileName));

            return boardFile;
        } catch (Exception e) {
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }
	
	
	
	
	
	
	
}
