package gamepiece.user.board.util;



import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import gamepiece.user.board.domain.BoardFiles;
import gamepiece.user.board.domain.Inquiry;
import gamepiece.user.board.domain.InquiryFiles;
import gamepiece.user.board.mapper.BoardFileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Component("userBoardFilesUtils")
@RequiredArgsConstructor
@Slf4j
public class BoardFilesUtils {
	
	@Value("${file.path}")
	private String fileRealPath;
	private final BoardFileMapper boardFileMapper;
	

	public BoardFiles uploadFile(MultipartFile multipartFile) {
	    String fileIdx = generateFileIdx(0);  // 단일 파일은 index 0으로 처리
	    return storeFile(multipartFile, fileIdx);
	}

	public List<BoardFiles> uploadFiles(MultipartFile[] multipartFiles) {
	    List<BoardFiles> fileList = new ArrayList<BoardFiles>();
	    int index = 0;
	    
	    for(MultipartFile multipartFile : multipartFiles) {
	        String fileIdx = generateFileIdx(index++);  // 각 파일마다 새로운 fileIdx 생성
	        BoardFiles fileInfo = storeFile(multipartFile, fileIdx);
	        if(fileInfo != null) fileList.add(fileInfo);
	    }
	    return fileList;
	}
	
	
	  // inquiryFiles를 위한 새로운 메서드 추가
    public List<BoardFiles> uploadInquiryFiles(MultipartFile[] multipartFiles) {
        List<BoardFiles> fileList = new ArrayList<>();
        int index = 0;
        
        for(MultipartFile multipartFile : multipartFiles) {
            String fileIdx = generateFileIdx(index++);
            BoardFiles fileInfo = storeFile(multipartFile, fileIdx);
            if(fileInfo != null) {
                // inquiry 관련 정보 설정
                fileInfo.setInquiryInfo(new Inquiry()); // 필요한 경우
                fileList.add(fileInfo);
            }
        }
        return fileList;
    }
    
    // 매핑 생성을 위한 헬퍼 메서드
    public InquiryFiles createInquiryFileMapping(String inquiryNum, BoardFiles boardFile) {
        return InquiryFiles.builder()
                .inquiryNum(inquiryNum)
                .fileIdx(boardFile.getFileIdx())
                .fileInfo(boardFile)
                .build();
    }

	private String generateFileIdx(int index) {
	    try {
	        // 현재 가장 큰 file_idx 값을 가져옴
	        String lastFileIdx = boardFileMapper.getNextFileIdx();
	        int nextNum = 1;
	        
	        if (lastFileIdx != null) {
	            // 마지막 file_idx에서 숫자 부분만 추출
	            String numStr = lastFileIdx.substring(lastFileIdx.lastIndexOf("_") + 1);
	            nextNum = Integer.parseInt(numStr) + 1 + index;
	        }
	        
	        // 새로운 file_idx 생성
	        return String.format("file_%03d", nextNum);
	    } catch (Exception e) {
	        log.error("Error generating file index: ", e);
	        // 예외 발생 시 현재 시간을 이용하여 유니크한 값 생성
	        return String.format("file_%03d", 
	            (int)(System.currentTimeMillis() % 1000) + index);
	    }
	}
	
	   private String getOSFilePath() {
	        String rootPath = "/";
	        String os = System.getProperty("os.name").toLowerCase();
	        
	        if(os.contains("win")) {
	            rootPath = "C:";
	        }
	        
	        return rootPath;
	    }
	
	   private BoardFiles storeFile(MultipartFile multipartFile, String fileIdx) {
	        try {
	            String rootPath = getOSFilePath();
	            
	            // 원본 파일명 추출
	            String originFileName = multipartFile.getOriginalFilename();
	            
	            // 새 파일명 생성 (UUID)
	            String uuid = UUID.randomUUID().toString();
	            String newFileName = uuid + "." + extractExt(originFileName);
	            
	            // 날짜 폴더명 생성
	            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	            
	            // 상대 경로 생성
	            String relativePath = "/attachment/" + today + 
	                (multipartFile.getContentType() != null && 
	                 multipartFile.getContentType().contains("image") ? "/image" : "");
	            
	            // 전체 경로 생성
	            String fullPath = rootPath + fileRealPath + relativePath;
	            File dir = new File(fullPath);
	            if(!dir.exists()) {
	                boolean created = dir.mkdirs();
	                log.info("Directory created: {}, success: {}", dir.getAbsolutePath(), created);
	            }
	            
	            // 파일 정보 객체 생성
	            BoardFiles boardFile = BoardFiles.builder()
	                    .fileIdx(fileIdx)
	                    .fileOriginalName(originFileName)
	                    .fileNewName(newFileName)
	                    .filePath(relativePath + "/" + newFileName)
	                    .fileSize(multipartFile.getSize())
	                    .build();
	            
	            // 실제 파일 저장
	            File saveFile = new File(dir, newFileName);
	            multipartFile.transferTo(saveFile);
	            
	            return boardFile;
	            
	        } catch(IOException e) {
	            log.error("File storage error: ", e);
	            return null;
	        }
	    }
	

	private String extractExt(String originFileName) {
	    int index = originFileName.lastIndexOf(".");
	    return index > -1 ? originFileName.substring(index + 1) : "";
	}
	
	
	
	
	private void createFolder(String path){
 		File isFile = new File(path);		
 		if(!isFile.isDirectory()){
 			isFile.mkdirs();			
 		}
 		if(!isFile.canWrite()){
 			isFile.setWritable(true);
 		}
 		if(!isFile.canRead()){
 			isFile.setReadable(true);
 		}
 	}

	public boolean deleteFileByPath(String path) {
	    path = fileRealPath + path;
	    boolean isDelete = false;
	    
	    try {
	        File file = new File(path);
	        if (file.exists()) {
	            // 실제 파일만 삭제하고 디렉토리는 건드리지 않음
	            if (file.isFile()) {
	                isDelete = file.delete();
	            } else {
	                // 디렉토리인 경우는 true 반환 (에러로 처리하지 않음)
	                isDelete = true;
	            }
	        } else {
	            // 파일이 없는 경우도 true 반환 (이미 삭제된 것으로 간주)
	            isDelete = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        isDelete = false;
	    }
	    
	    return isDelete;
	}
	
	 public static String formatFileSize(long bytes) {
	        if (bytes < 1024) return bytes + " B";
	        int exp = (int) (Math.log(bytes) / Math.log(1024));
	        String pre = "KMGTPE".charAt(exp-1) + "";
	        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
	    }
	 
	  
	
	
}





