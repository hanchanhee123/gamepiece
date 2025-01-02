package gamepiece.admin.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamepiece.admin.user.domain.User;
import gamepiece.admin.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	// 전체 회원정보 조회
	public List<User> getAllUserInfo() {
		
		return userMapper.getAllUserInfo();
	}
	
	// 회원 상세정보 조회
	public User getUserInfo(String id) {
		
		return userMapper.getUserInfo(id);
	}
	
	// 회원 상세정보 수정
	public void modifyUserInfo(User user) {
		
		userMapper.modifyUserInfo(user);
	}

	// 탈퇴 회원정보 조회
	public List<User> getRemoveUserInfo() {

		return userMapper.getRemoveUserInfo();
	}

	// 휴면 회원정보 조회
	public List<User> getDormancyUserInfo() {
		
		return userMapper.getDormancyUserInfo();
	}
	
	// 회원 로그인내역 조회
	public List<User> getUserLoginLog() {
		
		return userMapper.getUserLoginLog();
	}

	// 회원 권한정보 조회
	public List<Map<String, Object>> getAuthrtInfo() {
		
		return userMapper.getAuthrtInfo();
	}

}
