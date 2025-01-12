package gamepiece.user.pointShop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.pointShop.domain.PointCategories;
import gamepiece.user.pointShop.domain.PointLog;
import gamepiece.user.pointShop.mapper.UserPointShopMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;


@Service
public class PointShopServiceImpl implements PointShopService{
	private final UserPointShopMapper userpointshopMapper;
	
	public PointShopServiceImpl(UserPointShopMapper userpointshopMapper) {
		this.userpointshopMapper = userpointshopMapper;
	}
	
	@Override
	public PointLog getPointsHeld(String id) {
		 return userpointshopMapper.getPointsHeld(id);
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
