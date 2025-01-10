package gamepiece.admin.point.service;

import java.util.List;
import gamepiece.admin.point.domain.Point;
import gamepiece.admin.point.domain.PointCategories;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface PointService {
	//특정 아이템 조회
	Point getItemInfoByItemName(String itemName);
	
	// 아이템 목록 조회
	PageInfo<Point> findAll(Pageable pageable);
	
	// 아이템 카테고리 조회
	List<PointCategories> findCate();
	
	void addItem(Point point);
	
	void modifyItem(Point point);
	
	void removeItem(String ps_cd);
	
	List<Point> searchList(String searchCate, String searchValue, Pageable pageable);
	
}
