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
	
	void addPointShop(Point point);
	
	void modifyItem(String itemName);
}
