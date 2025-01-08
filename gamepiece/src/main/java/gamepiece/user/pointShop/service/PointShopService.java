package gamepiece.user.pointShop.service;

import java.util.List;

import gamepiece.user.pointShop.domain.PointCategories;
import gamepiece.user.pointShop.domain.Point;
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
	
	
	// 아이템 카테고리 조회
	List<PointCategories> findCate();
}
