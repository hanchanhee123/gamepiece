package gamepiece.admin.user.service;

import java.util.List;

import gamepiece.admin.user.domain.User;

public interface UserService {
	
	public List<User> getAllUserInfo();
	
	public User getUserInfo(String id);
	
}
