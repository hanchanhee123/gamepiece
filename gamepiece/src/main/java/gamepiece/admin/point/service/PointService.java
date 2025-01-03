package gamepiece.admin.point.service;

import java.util.List;

import gamepiece.admin.point.domain.Point;
import gamepiece.admin.point.domain.PointCategories;

public interface PointService {
	//특정 아이템 조회
	Point getItemInfoByName(String itemName);
	
	// 아이템 목록 조회
	List<Point> findAll();
	
	// 아이템 카테고리 조회
	List<PointCategories> findCate();
	
	void addItem(Point point);
	
	void modifyItem(Point point);
	
}
