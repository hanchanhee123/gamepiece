package gamepiece.admin.point.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gamepiece.admin.point.domain.Point;
import gamepiece.admin.point.domain.PointCategories;
import gamepiece.admin.point.mapper.PointshopMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

@Service
public class PointServiceImpl implements PointService {
	
	private final PointshopMapper pointshopMapper;
	
	public PointServiceImpl(PointshopMapper pointshopMapper) {
		this.pointshopMapper = pointshopMapper;
	}
	
	
	@Override
	public void modifyItem(Point point) {
		pointshopMapper.modifyItem(point);
	}
	
	@Override
	public void removeItem(String ps_cd) {
		
		pointshopMapper.removeItem(ps_cd);
	}
	
	@Override
	public Point getItemInfoByItemName(String itemName) {
		
		return pointshopMapper.getItemInfoByItemName(itemName);
	}
	@Override
	public PageInfo<Point> findAll(Pageable pageable) {
		int rowCnt = pointshopMapper.getItemCount();
		List<Point> loginList = pointshopMapper.findAll(pageable);
		return new PageInfo<>(loginList, pageable, rowCnt);
	}

	@Override
	public List<PointCategories> findCate() {
		return pointshopMapper.findCate();
	}
	
	@Override
	public void addItem(Point point) {
		
		point.setAdminId("id01");
		pointshopMapper.addItem(point);
		
	}
}
