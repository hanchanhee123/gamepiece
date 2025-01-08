package gamepiece.user.pointShop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.pointShop.domain.PointCategories;
import gamepiece.user.pointShop.domain.Point;
import gamepiece.util.Pageable;



@Mapper
public interface UserPointShopMapper {
	List<Point> findAll(Pageable pageable);
	
	int getItemCount();
	
	List<PointCategories> findCate();
}
