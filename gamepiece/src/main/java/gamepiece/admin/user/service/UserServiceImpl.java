package gamepiece.admin.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamepiece.admin.user.domain.User;
import gamepiece.admin.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	public List<User> getAllUserInfo() {
		
		return userMapper.getAllUserInfo();
	}
	
	public User getUserInfo(String id) {
		
		return userMapper.getUserInfo(id);
	}

}
