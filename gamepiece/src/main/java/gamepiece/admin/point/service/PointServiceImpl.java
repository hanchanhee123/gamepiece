package gamepiece.admin.point.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import gamepiece.admin.point.domain.Point;
import gamepiece.admin.point.domain.PointCategories;
import gamepiece.admin.point.mapper.PointshopMapper;
import gamepiece.common.mapper.CommonMapper;
import gamepiece.file.dto.FileDto;
import gamepiece.file.mapper.FileMapper;
import gamepiece.file.util.FilesUtils;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
	
	private final PointshopMapper pointshopMapper;
	private final FileMapper fileMapper;
	private final CommonMapper commonMapper;
	private final FilesUtils fileUtils;
	
	
	@Override
	public PageInfo<Point> searchList(String searchCate, String searchValue, Pageable pageable) {
		String cate = "";
		switch (searchCate) {
			case "avatar" 		-> cate = "cate_02";
			case "frame" 		-> cate = "cate_03";
			case "imoticon" 	-> cate = "cate_01";
			case "background" 	-> cate = "cate_05";
			case "etc" 			-> cate = "cate_04";
		
		}
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		int rowCnt = pointshopMapper.getItemCount();
		searchMap.put("searchCate", cate);
		searchMap.put("searchValue", searchValue);
		searchMap.put("pageable", pageable);
		
	
		List<Point> loginList = pointshopMapper.getSearchList(searchMap);
		log.info("searchMap: {}", searchMap);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}
	
	@Override
	public void modifyItem(Point point, MultipartFile files) {
		if(files != null && !files.isEmpty()) {			
			FileDto newFileInfo = fileUtils.uploadFile(files);
			if(newFileInfo != null) {
				String fileIdx = point.getFileIdx();
				if(fileIdx != null && !"".equals(fileIdx)) {					
					FileDto fileInfo = fileMapper.getFileInfoByIdx(fileIdx);
					boolean isDelete = fileUtils.deleteFileByPath(fileInfo.getFilePath());
					if(isDelete) {
						newFileInfo.setFileIdx(fileIdx);
						fileMapper.modifyfile(newFileInfo);
					}
				}else {
					fileIdx = commonMapper.getPrimaryKey("file_", "files", "file_idx");
					newFileInfo.setFileIdx(fileIdx);
					fileMapper.addfile(newFileInfo);
				}
				
			}
		}
		pointshopMapper.modifyItem(point);
	}
	@Override
	public void inactiveItem(String ps_cd) {
		pointshopMapper.inactiveItem(ps_cd);
		
	}
	
	@Override
	public void removeItem(String ps_cd) {
		
		pointshopMapper.removeItem(ps_cd);
	}
	
	@Override
	public void logcount(String ps_cd) {
		pointshopMapper.logCount(ps_cd); 
	}
	
	@Override
	public Point getItemInfoByItemCd(String itemCd) {
		
		return pointshopMapper.getItemInfoByItemCd(itemCd);
	}
	@Override
	public PageInfo<Point> findAll(Pageable pageable) {
		int rowCnt = pointshopMapper.getItemCount();
		List<Point> loginList = pointshopMapper.findAll(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}

	@Override
	public List<PointCategories> findCate() {
		return pointshopMapper.findCate();
	}
	
	@Override
	public void addItem(Point point, MultipartFile files) {
		
	    FileDto fileInfo = fileUtils.uploadFile(files);
        if(fileInfo != null) {
            String fileIdx = commonMapper.getPrimaryKey("file_", "files", "file_idx");
            fileInfo.setFileIdx(fileIdx);
            fileMapper.addfile(fileInfo);
            String itemCd = commonMapper.getPrimaryKey("ps_", "point_shop", "ps_cd");
            point.setAdminId("id01");
            point.setItemCd(itemCd);
            point.setFileIdx(fileIdx);
            pointshopMapper.addItem(point);
        }
	}
}
