package gamepiece.admin.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.user.domain.User;

@Mapper
public interface UserMapper {

	public List<User> getAllUserInfo();
	
	public User getUserInfo(String id);
	
	public List<User> getRemoveUserInfo();

	public List<User> getUserLoginLog();

	public List<User> getDormancyUserInfo();

}
