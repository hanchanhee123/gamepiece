package gamepiece.user.pointShop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.pointShop.domain.PointCategories;
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
	public PageInfo<Point> findimoticon(Pageable pageable) {
		int rowCnt = userpointshopMapper.getItemCount();
		List<Point> loginList = userpointshopMapper.findimoticon(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}
	@Override
	public PageInfo<Point> findavatar(Pageable pageable) {
		int rowCnt = userpointshopMapper.getItemCount();
		List<Point> loginList = userpointshopMapper.findavatar(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}
	@Override
	public PageInfo<Point> findavatarframe(Pageable pageable) {
		int rowCnt = userpointshopMapper.getItemCount();
		List<Point> loginList = userpointshopMapper.findavatarframe(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}
	@Override
	public PageInfo<Point> findetc(Pageable pageable) {
		int rowCnt = userpointshopMapper.getItemCount();
		List<Point> loginList = userpointshopMapper.findetc(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}
	@Override
	public PageInfo<Point> findbackground(Pageable pageable) {
		int rowCnt = userpointshopMapper.getItemCount();
		List<Point> loginList = userpointshopMapper.findbackground(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}
	
	
	
	@Override
	public List<PointCategories> findCate() {
		
		return userpointshopMapper.findCate();
	}
}
