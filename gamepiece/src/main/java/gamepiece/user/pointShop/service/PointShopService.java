package gamepiece.user.pointShop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.pointShop.domain.PointCategories;
import gamepiece.user.pointShop.domain.PointLog;
import gamepiece.user.pointShop.domain.PointShopLog;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface PointShopService {
	
	
	// 이모티콘 조회
	PageInfo<Point> findimoticon(Pageable pageable);
	// 아바타 조회
	PageInfo<Point> findavatar(Pageable pageable);
	// 아바타 액자 조회
	PageInfo<Point> findavatarframe(Pageable pageable);
	// 기타 조회
	PageInfo<Point> findetc(Pageable pageable);
	// 배경프로필 조회
	PageInfo<Point> findbackground(Pageable pageable);
	
	PageInfo<Point> findhistory(Pageable pageable, String id);
	
	PointLog getPointsHeld(String id);
	
	Point pointInfo(String itemCd);
	
	List<PointShopLog> purchaseList(String id);
	
	// 아이템 전체 조회
	List<Point> findAll();
	
	PointShopLog addPointShopLog(String id, String itemCd, int itemPrice);
	
	PointLog addPointLog(String id, String itemName, int itemPrice);
	
	// 아이템 카테고리 조회
	List<PointCategories> findCate();
}
