package gamepiece.admin.board.util;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import gamepiece.admin.board.domain.AdminBoardFiles;
import gamepiece.admin.board.mapper.BoardFileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Component("adminBoardFilesUtils")
@RequiredArgsConstructor
@Slf4j
public class BoardFilesUtils {
	
	@Value("${file.path}")
	private String fileRealPath;
	private final BoardFileMapper boardFileMapper;
	

	   
	public AdminBoardFiles uploadFile(MultipartFile file) {
	    if (file == null || file.isEmpty()) {
	        return null;
	    }
	    
	    try {
	        String lastFileIdx = boardFileMapper.getNextFileIdx();
	        int nextNum = 1;
	        
	        if (lastFileIdx != null) {
	            String numStr = lastFileIdx.substring(lastFileIdx.lastIndexOf("_") + 1);
	            nextNum = Integer.parseInt(numStr) + 1;
	        }
	        
	        String fileIdx = String.format("file_%03d", nextNum);
	        return storeFile(file, fileIdx);
	        
	    } catch (Exception e) {
	        log.error("Error in uploadFile: ", e);
	        return null;
	    }
	}
    public List<AdminBoardFiles> uploadFiles(MultipartFile[] multipartFiles) {
        List<AdminBoardFiles> fileList = new ArrayList<>();
        
        if (multipartFiles == null || multipartFiles.length == 0 || multipartFiles[0].isEmpty()) {
            return fileList;
        }
        
        // Set을 사용하여 중복 파일 체크
        Set<String> processedFiles = new HashSet<>();
        String lastFileIdx = boardFileMapper.getNextFileIdx();
        int nextNum = 1;
        
        if (lastFileIdx != null) {
            String numStr = lastFileIdx.substring(lastFileIdx.lastIndexOf("_") + 1);
            nextNum = Integer.parseInt(numStr) + 1;
        }
        
        for (MultipartFile file : multipartFiles) {
            if (file != null && !file.isEmpty()) {
                // 원본 파일명으로 중복 체크
                String originalFileName = file.getOriginalFilename();
                if (!processedFiles.add(originalFileName)) {
                    continue;  // 이미 처리된 파일은 건너뛰기
                }
                
                String fileIdx = String.format("file_%03d", nextNum++);
                AdminBoardFiles fileInfo = storeFile(file, fileIdx);
                if (fileInfo != null) {
                    fileList.add(fileInfo);
                }
            }
        }
        
        return fileList;
    }

    private int getNextFileNumber() {
        String lastFileIdx = boardFileMapper.getNextFileIdx();
        if (lastFileIdx == null) {
            return 1;
        }
        String numStr = lastFileIdx.substring(lastFileIdx.lastIndexOf("_") + 1);
        return Integer.parseInt(numStr) + 1;
    }
	  private String getOSFilePath() {
	        String rootPath = "/";
	        String os = System.getProperty("os.name").toLowerCase();
	        
	        if(os.contains("win")) {
	            rootPath = "C:";
	        }
	        
	        return rootPath;
	    }
	
	  
		private String extractExt(String originFileName) {
		    int index = originFileName.lastIndexOf(".");
		    return index > -1 ? originFileName.substring(index + 1) : "";
		}
		
	  
		public AdminBoardFiles storeFile(MultipartFile multipartFile, String fileIdx) {
		    if (multipartFile == null || multipartFile.isEmpty()) {
		        return null;
		    }
		    
		    try {
		        String rootPath = getOSFilePath();
		        String originFileName = multipartFile.getOriginalFilename();
		        
		        // UUID로 새 파일명 생성
		        String newFileName = UUID.randomUUID().toString() + "." + extractExt(originFileName);
		        
		        // 날짜 폴더명 생성
		        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		        String relativePath = "/attachment/" + today + 
		            (multipartFile.getContentType() != null && 
		             multipartFile.getContentType().contains("image") ? "/image" : "");
		        
		        // 전체 경로 생성
		        String fullPath = rootPath + fileRealPath + relativePath;
		        File dir = new File(fullPath);
		        if(!dir.exists()) {
		            dir.mkdirs();
		        }
		        
		        AdminBoardFiles boardFile = AdminBoardFiles.builder()
		                .fileIdx(fileIdx)
		                .fileOriginalName(originFileName)
		                .fileNewName(newFileName)
		                .filePath(relativePath)
		                .fileSize(multipartFile.getSize())
		                .build();
		        
		        File saveFile = new File(dir, newFileName);
		        multipartFile.transferTo(saveFile);
		        
		        return boardFile;
		    } catch(IOException e) {
		        log.error("File storage error: ", e);
		        return null;
		    }
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
		File file = new File(path);
		
		Path deletePath = Paths.get(file.getAbsolutePath());
		
		try {
			Files.deleteIfExists(deletePath);
			isDelete = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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





