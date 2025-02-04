package gamepiece.admin.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.admin.user.domain.User;
import gamepiece.admin.user.mapper.AdminUserMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Service("adminUserService")
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final AdminUserMapper adminUserMapper;
	
	// 로그인 (회원 아이디, 비밀번호 확인)
	@Override
	public Map<String, Object> checkUser(String id, String userPswd) {

		boolean isMatched = false;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		User userInfo = adminUserMapper.checkUser(id);
		if(userInfo != null) {
			String checkPw = userInfo.getUserPswd();
			if(checkPw.equals(userPswd)) {
				isMatched = true;
				resultMap.put("userInfo", userInfo);
			}
		}
		resultMap.put("isMatched", isMatched);
		
		return resultMap;
	}
	
	// 전체 회원정보 조회
	@Override
	public PageInfo<User> getAllUserInfo(Pageable pageable) {
		
		int rowCnt = adminUserMapper.getCntAllUserInfo();
		
		List<User> allUserInfo = adminUserMapper.getAllUserInfo(pageable);
		
		return new PageInfo<>(allUserInfo, pageable, rowCnt);
	}
	
	// 회원 상세정보 조회
	@Override
	public User getUserInfo(String id) {
		
		return adminUserMapper.getUserInfo(id);
	}
	
	// 회원 상세정보 수정
	@Override
	public void modifyUserInfo(User user) {
		
		adminUserMapper.modifyUserInfo(user);
	}

	// 탈퇴 회원정보 조회
	@Override
	public PageInfo<User> getRemoveUserInfo(Pageable pageable) {

		int rowCnt = adminUserMapper.getCntRemoveUserInfo();
		
		List<User> removeUserInfo = adminUserMapper.getRemoveUserInfo(pageable);
		
		return new PageInfo<>(removeUserInfo, pageable, rowCnt);
	}

	// 휴면 회원정보 조회
	@Override
	public PageInfo<User> getDormancyUserInfo(Pageable pageable) {
		
		int rowCnt = adminUserMapper.getCntDormancyUserInfo();
		
		List<User> dormancyUserInfo = adminUserMapper.getDormancyUserInfo();
		
		return new PageInfo<>(dormancyUserInfo, pageable, rowCnt);
	}
	
	// 회원 로그인내역 조회
	@Override
	public PageInfo<User> getUserLoginLog(Pageable pageable) {
		
		int rowCnt = adminUserMapper.getCntUserLoginLog();
		
		List<User> userLoginlog = adminUserMapper.getUserLoginLog(pageable);
		
		return new PageInfo<>(userLoginlog, pageable, rowCnt);
	}

	// 회원 권한정보 조회
	@Override
	public List<Map<String, Object>> getAuthrtInfo() {
		
		return adminUserMapper.getAuthrtInfo();
	}

	@Override
	public void removeUser(String id) {
		
		adminUserMapper.removeUser(id);
	}
	
}
