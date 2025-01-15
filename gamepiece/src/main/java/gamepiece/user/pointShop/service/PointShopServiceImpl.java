package gamepiece.user.pointShop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.common.mapper.CommonMapper;
import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.pointShop.domain.PointCategories;
import gamepiece.user.pointShop.domain.PointLog;
import gamepiece.user.pointShop.domain.PointShopLog;
import gamepiece.user.pointShop.mapper.UserPointShopMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PointShopServiceImpl implements PointShopService{
	private final UserPointShopMapper userpointshopMapper;
	
	@Override
	public List<PointShopLog> purchaseList(String id) {
		List<PointShopLog> purchaseList = userpointshopMapper.purchaseList(id);
		
		return purchaseList;
	}
	
	@Override
	public PageInfo<Point> findhistory(Pageable pageable) {
		int rowCnt = userpointshopMapper.gethistoryCount();
		List<Point> historyList = userpointshopMapper.findhistory(pageable);
		return new PageInfo<>(historyList, pageable, rowCnt);
	}
	
	@Override
	public PointLog addPointLog(String id, String itemName, int itemPrice) {
		PointLog pointLog = new PointLog();
		String content = "포인트샵 아이템 구매";
		pointLog.setId(id);
		pointLog.setPointReceive(itemPrice);
		pointLog.setPrc(content);
		userpointshopMapper.addPointLog(pointLog);
		
		return pointLog;
	}
	
	@Override
	public PointShopLog addPointShopLog(String id, String itemCd, int itemPrice) {
		PointShopLog pointShopLog = new PointShopLog();
		pointShopLog.setId(id);
		pointShopLog.setPsCd(itemCd);
		pointShopLog.setUsedPoint(itemPrice);
		userpointshopMapper.addPointShopLog(pointShopLog);
		log.info("insert 후 pointShopLog : {}", pointShopLog);
		return pointShopLog;
	}
	
	@Override
	public PointLog getPointsHeld(String id) {
		 return userpointshopMapper.getPointsHeld(id);
	}
	
	@Override
	public Point pointInfo(String itemCd) {
		return userpointshopMapper.pointInfo(itemCd);
	}
	
	@Override	
	public List<Point> findAll() {
		
		return userpointshopMapper.findAll();
	}
	
	@Override
	public PageInfo<Point> findimoticon(Pageable pageable) {
		int rowCnt = userpointshopMapper.getimoticonCount();
		List<Point> loginList = userpointshopMapper.findimoticon(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}
	@Override
	public PageInfo<Point> findavatar(Pageable pageable) {
		int rowCnt = userpointshopMapper.getavatarCount();
		List<Point> loginList = userpointshopMapper.findavatar(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}
	@Override
	public PageInfo<Point> findavatarframe(Pageable pageable) {
		int rowCnt = userpointshopMapper.getframeCount();
		List<Point> loginList = userpointshopMapper.findavatarframe(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}
	@Override
	public PageInfo<Point> findetc(Pageable pageable) {
		int rowCnt = userpointshopMapper.getetcCount();
		List<Point> loginList = userpointshopMapper.findetc(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}
	@Override
	public PageInfo<Point> findbackground(Pageable pageable) {
		int rowCnt = userpointshopMapper.getbackCount();
		List<Point> loginList = userpointshopMapper.findbackground(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}
	
	
	
	@Override
	public List<PointCategories> findCate() {
		
		return userpointshopMapper.findCate();
	}
}
