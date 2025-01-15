package gamepiece.user.pointShop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.pointShop.domain.PointCategories;
import gamepiece.user.pointShop.domain.PointLog;
import gamepiece.user.pointShop.domain.PointShopLog;
import gamepiece.util.Pageable;



@Mapper
public interface UserPointShopMapper {
	List<Point> findimoticon(Pageable pageable);
	List<Point> findavatar(Pageable pageable);
	List<Point> findavatarframe(Pageable pageable);
	List<Point> findetc(Pageable pageable);
	List<Point> findbackground(Pageable pageable);
	List<Point> findhistory(Pageable pageable);
	
	int addPointShopLog(PointShopLog pointShopLog);
	
	int addPointLog(PointLog pointLog);
	
	int getimoticonCount();
	
	int getframeCount();
	
	int getetcCount();
	
	int getbackCount();
	
	int getavatarCount();
	
	int gethistoryCount();
	
	PointLog getPointsHeld(String id);
	
	List<PointShopLog> purchaseList(String id);
	
	Point pointInfo(String itemCd);
	
	List<Point> findAll();
	
	List<PointCategories> findCate();
}
