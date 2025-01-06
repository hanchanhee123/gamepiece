package gamepiece.admin.user.service;

import java.util.List;
import java.util.Map;

import gamepiece.admin.user.domain.User;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface UserService {
	
	// 전체 회원정보 조회
	public PageInfo<User> getAllUserInfo(Pageable pageable);
	
	// 회원 상세정보 조회
	public User getUserInfo(String id);
	
	// 회원 상세정보 수정
	public void modifyUserInfo(User user);

	// 탈퇴 회원정보 조회
	public PageInfo<User> getRemoveUserInfo(Pageable pageable);
	
	// 휴면 회원정보 조회
	public PageInfo<User> getDormancyUserInfo(Pageable pageable);
	
	// 회원 로그인내역 조회
	public PageInfo<User> getUserLoginLog(Pageable pageable);

	// 회원 권한정보 조회
	List<Map<String, Object>> getAuthrtInfo();

	// 회원 탈퇴
	public void removeUser(String id);
	
}
