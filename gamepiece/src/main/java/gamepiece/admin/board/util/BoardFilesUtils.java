package gamepiece.admin.board.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import gamepiece.admin.board.domain.AdminBoardFiles;
import gamepiece.admin.board.mapper.BoardFileMapper;
import lombok.RequiredArgsConstructor;



@Component("adminBoardFilesUtils")
@RequiredArgsConstructor
public class BoardFilesUtils {
	
	@Value("${file.path}")
	private String fileRealPath;
	private final BoardFileMapper boardFileMapper;
	

	public AdminBoardFiles uploadFile(MultipartFile multipartFile) {
		
		AdminBoardFiles fileInfo = storeFile(multipartFile);
		
		return fileInfo;
	}

	public List<AdminBoardFiles> uploadFiles(MultipartFile[] multipartFiles) {
		List<AdminBoardFiles> fileList = new ArrayList<AdminBoardFiles>();
		AdminBoardFiles fileInfo;
		for(MultipartFile multipartFile : multipartFiles) {
			fileInfo = storeFile(multipartFile);
			if(fileInfo != null) fileList.add(fileInfo);
		}
		return fileList;
	}
	
	
	private AdminBoardFiles storeFile(MultipartFile multipartFile) {
		if(multipartFile.isEmpty()) return null;
		
		// 현재 날짜 구하기(Asia/Seoul)
		LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
		// 날짜 패턴(디렉토리)
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		// 콘텐츠타입 분류(디렉토리)
		String contentType = multipartFile.getContentType();
		
		if(contentType != null && contentType.indexOf("image") > -1) {
			contentType = "/image";
		}else {
			contentType = "/file";
		}
		
		String dbFilePath = fileRealPath + "/attachment/" + now.format(formatter) + contentType;
		String path = Paths.get(dbFilePath).toString();
		
		
		
		// 파일 명이 겹치지 않게 파일명 설정
    	String newFileName = "";
    	/*
    	String[] fileNameSplit = multipartFile.getOriginalFilename().split("\\.");

    	for(int i=0; i<fileNameSplit.length; i++) {
    		if(i == (fileNameSplit.length-1)) {
    			fileNameSplit[i] = "." + fileNameSplit[i];
    		}else {			    			
    			fileNameSplit[i] = fileNameSplit[i].replaceAll("\\s", "") + Long.toString(System.nanoTime());
    		}
    		resultFileName += fileNameSplit[i];
    	}
		*/
    	String[] fileNameSplit = multipartFile.getOriginalFilename().split("\\.");
    	newFileName = UUID.randomUUID().toString()+ "." + fileNameSplit[1];
    	
    	createFolder(path);
    	
    	byte[] bytes;			    	
    	Path uploadPath = Paths.get(path + "/" + newFileName);
    	
    	dbFilePath += ("/" + newFileName);
    	
    	AdminBoardFiles fileInfo = null;
    	
    	try {
            bytes = multipartFile.getBytes();
            Files.write(uploadPath, bytes);

            // 파일 인덱스를 BoardFileMapper에서 가져오도록 수정
            String fileIdx = boardFileMapper.getNextFileIdx();

            return AdminBoardFiles.builder()
                .fileIdx(fileIdx) // 파일 인덱스 추가
                .fileOriginalName(multipartFile.getOriginalFilename())
                .fileNewName(newFileName)
                .filePath(dbFilePath.replaceAll(fileRealPath, ""))
                .fileSize(multipartFile.getSize())
                .build();

        } catch (IOException e) {
            e.printStackTrace();
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





