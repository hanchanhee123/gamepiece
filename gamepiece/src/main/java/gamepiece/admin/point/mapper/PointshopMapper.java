package gamepiece.admin.point.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.point.domain.Point;
import gamepiece.admin.point.domain.PointCategories;

@Mapper
public interface PointshopMapper {
	Point getItemInfoByName(String itemName);
	
	List<Point> findAll();
	
	List<PointCategories> findCate();
	
	int modifyItem(Point point);
	
	int addItem(Point point);
	
	int removeItem(String ps_cd);
}
