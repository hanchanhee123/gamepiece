package gamepiece.admin.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.user.domain.User;
import gamepiece.util.Pageable;

@Mapper
public interface AdminUserMapper {
	
	// 로그인 (회원 아이디-관리자 확인)
	public User checkUser(String id);
	
	// 전체 회원정보 조회
	public List<User> getAllUserInfo(Pageable pageable);
	
	// 전체 회원정보 총 갯수
	int getCntAllUserInfo();
	
	// 회원 상세정보 조회
	public User getUserInfo(String id);
	
	// 회원 상세정보 수정
	public void modifyUserInfo(User user);
	
	// 탈퇴 회원정보 조회
	public List<User> getRemoveUserInfo(Pageable pageable);
	
	// 탈퇴 회원정보 총 갯수
	int getCntRemoveUserInfo();

	// 휴면 회원정보 조회
	public List<User> getDormancyUserInfo();

	// 휴면 회원정보 총 갯수
	public int getCntDormancyUserInfo();
	
	// 회원 로그인내역 조회
	public List<User> getUserLoginLog(Pageable pageable);

	// 회원 로그인내역 총 갯수
	public int getCntUserLoginLog();
	
	// 회원 권한정보 조회
	public List<Map<String, Object>> getAuthrtInfo();

	// 회원 탈퇴
	public void removeUser(String id);

}
