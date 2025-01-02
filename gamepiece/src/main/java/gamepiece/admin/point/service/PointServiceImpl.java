package gamepiece.admin.point.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gamepiece.admin.point.domain.Point;
import gamepiece.admin.point.domain.PointCategories;
import gamepiece.admin.point.mapper.PointshopMapper;

@Service
public class PointServiceImpl implements PointService {
	
	private final PointshopMapper pointshopMapper;
	
	public PointServiceImpl(PointshopMapper pointshopMapper) {
		this.pointshopMapper = pointshopMapper;
	}
	
	@Override
	public Point getItemInfoByName(String itemName) {
		
		return pointshopMapper.getItemInfoByName(itemName);
	}
	
	public List<Point> findAll() {
		return pointshopMapper.findAll();
	}

	@Override
	public List<PointCategories> findCate() {
		return pointshopMapper.findCate();
	}
	
	public List<Point> addPointShop() {
		return pointshopMapper.addPointShop();
	}
}
