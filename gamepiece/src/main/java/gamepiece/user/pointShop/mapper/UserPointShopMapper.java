package gamepiece.user.pointShop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.pointShop.domain.PointCategories;
import gamepiece.user.pointShop.domain.Point;
import gamepiece.util.Pageable;



@Mapper
public interface UserPointShopMapper {
	List<Point> findimoticon(Pageable pageable);
	List<Point> findavatar(Pageable pageable);
	List<Point> findavatarframe(Pageable pageable);
	List<Point> findetc(Pageable pageable);
	List<Point> findbackground(Pageable pageable);
	
	int getItemCount();
	
	List<PointCategories> findCate();
}
