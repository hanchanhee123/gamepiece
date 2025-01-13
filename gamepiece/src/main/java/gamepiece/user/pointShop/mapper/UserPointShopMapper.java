package gamepiece.user.pointShop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.pointShop.domain.PointCategories;
import gamepiece.user.pointShop.domain.PointLog;
import gamepiece.util.Pageable;



@Mapper
public interface UserPointShopMapper {
	List<Point> findimoticon(Pageable pageable);
	List<Point> findavatar(Pageable pageable);
	List<Point> findavatarframe(Pageable pageable);
	List<Point> findetc(Pageable pageable);
	List<Point> findbackground(Pageable pageable);
	
	int getimoticonCount();
	
	int getframeCount();
	
	int getetcCount();
	
	int getbackCount();
	
	int getavatarCount();
	
	PointLog getPointsHeld(String id);
	
	Point pointInfo(String itemCd);
	
	List<Point> findAll();
	
	List<PointCategories> findCate();
}
