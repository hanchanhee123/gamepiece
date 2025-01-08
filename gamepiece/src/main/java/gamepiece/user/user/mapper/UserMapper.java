package gamepiece.user.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.user.domain.User;

@Mapper
public interface UserMapper {
	
	User checkUser(String id);

}
