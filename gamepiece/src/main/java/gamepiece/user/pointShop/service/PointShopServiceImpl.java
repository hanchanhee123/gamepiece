package gamepiece.user.pointShop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.pointShop.mapper.UserPointShopMapper;


@Service
public class PointShopServiceImpl implements PointShopService{
	private final UserPointShopMapper pointshopMapper;
	
	public PointShopServiceImpl(UserPointShopMapper pointshopMapper) {
		this.pointshopMapper = pointshopMapper;
	}
	
	public List<Point> findAll() {
		return pointshopMapper.findAll();
	}
}
