package gamepiece.user.pointShop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.pointShop.domain.Point;



@Mapper
public interface UserPointShopMapper {
	List<Point> findAll();
}
