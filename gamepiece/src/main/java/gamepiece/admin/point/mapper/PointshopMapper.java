package gamepiece.admin.point.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.point.domain.Point;
import gamepiece.admin.point.domain.PointCategories;
import gamepiece.util.Pageable;

@Mapper
public interface PointshopMapper {
	Point getItemInfoByItemName(String itemName);
	
	List<Point> findAll(Pageable pageable);
	
	int getItemCount();
	
	List<PointCategories> findCate();
	
	int modifyItem(Point point);
	
	int addItem(Point point);
	
	int removeItem(String ps_cd);
	
}
