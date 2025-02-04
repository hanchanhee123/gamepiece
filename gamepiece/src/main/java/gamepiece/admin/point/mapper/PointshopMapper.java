package gamepiece.admin.point.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.point.domain.Point;
import gamepiece.admin.point.domain.PointCategories;
import gamepiece.util.Pageable;

@Mapper
public interface PointshopMapper {
	Point getItemInfoByItemCd(String itemCd);
	
	List<Point> findAll(Pageable pageable);
	
	int getItemCount();
	
	List<PointCategories> findCate();
	
	int modifyItem(Point point);
	
	int addItem(Point point);
	
	int removeItem(String ps_cd);
	
	int inactiveItem(String ps_cd);
	
	int logCount(String ps_cd);
	
	List<Point> getSearchList(Map<String, Object> searchMap);
	
}
