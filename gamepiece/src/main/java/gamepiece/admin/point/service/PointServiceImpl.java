package gamepiece.admin.point.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.admin.point.domain.Point;
import gamepiece.admin.point.domain.PointCategories;
import gamepiece.admin.point.mapper.PointshopMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PointServiceImpl implements PointService {
	
	private final PointshopMapper pointshopMapper;
	
	public PointServiceImpl(PointshopMapper pointshopMapper) {
		this.pointshopMapper = pointshopMapper;
	}
	
	@Override
	public List<Point> searchList(String searchCate, String searchValue, Pageable pageable) {
		String cate = "";
		switch (searchCate) {
			case "avatar" 		-> cate = "cate_02";
			case "frame" 		-> cate = "cate_03";
			case "imoticon" 	-> cate = "cate_01";
			case "background" 	-> cate = "cate_05";
			case "etc" 			-> cate = "cate_04";
		
		}
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		//int rowCnt = pointshopMapper.getItemCount();
		searchMap.put("searchCate", cate);
		searchMap.put("searchValue", searchValue);
		searchMap.put("pageable", pageable);
		
		
		log.info("searchMap: {}", searchMap);
		List<Point> ItemList = pointshopMapper.getSearchList(searchMap);
		return ItemList;
	}
	
	@Override
	public void modifyItem(Point point) {
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
	public Point getItemInfoByItemName(String itemName) {
		
		return pointshopMapper.getItemInfoByItemName(itemName);
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
	public void addItem(Point point) {
		
		point.setAdminId("id01");
		pointshopMapper.addItem(point);
		
	}
}
