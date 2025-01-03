package gamepiece.admin.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.user.domain.User;

@Mapper
public interface UserMapper {

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
	public List<Map<String, Object>> getAuthrtInfo();


}
