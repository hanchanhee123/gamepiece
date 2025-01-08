package gamepiece.user.pointShop.service;

import java.util.List;

import gamepiece.user.pointShop.domain.PointCategories;
import gamepiece.user.pointShop.domain.Point;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface PointShopService {
	
	// 아이템 목록 조회
	PageInfo<Point> findAll(Pageable pageable);
	
	// 아이템 카테고리 조회
	List<PointCategories> findCate();
}
