package gamepiece.admin.user.service;

import java.util.List;
import java.util.Map;

import gamepiece.admin.user.domain.User;

public interface UserService {
	
	// 전체 회원정보 조회
	public List<User> getAllUserInfo();
	
	// 회원 상세정보 조회
	public User getUserInfo(String id);
	
	// 회원 상세정보 수정
	public void modifyUserInfo(User user);

	// 탈퇴 회원정보 조회
	public List<User> getRemoveUserInfo();
	
	// 휴면 회원정보 조회
	public List<User> getDormancyUserInfo();
	
	// 회원 로그인내역 조회
	public List<User> getUserLoginLog();

	// 회원 권한정보 조회
	List<Map<String, Object>> getAuthrtInfo();

	// 회원 탈퇴
	public void removeUser(String id);
	
}
