package gamepiece.admin.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.admin.user.domain.User;
import gamepiece.admin.user.mapper.UserMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserMapper userMapper;
	
	// 전체 회원정보 조회
	@Override
	public PageInfo<User> getAllUserInfo(Pageable pageable) {
		
		int rowCnt = userMapper.getCntAllUserInfo();
		
		List<User> allUserInfo = userMapper.getAllUserInfo(pageable);
		
		return new PageInfo<>(allUserInfo, pageable, rowCnt);
	}
	
	// 회원 상세정보 조회
	@Override
	public User getUserInfo(String id) {
		
		return userMapper.getUserInfo(id);
	}
	
	// 회원 상세정보 수정
	@Override
	public void modifyUserInfo(User user) {
		
		userMapper.modifyUserInfo(user);
	}

	// 탈퇴 회원정보 조회
	@Override
	public PageInfo<User> getRemoveUserInfo(Pageable pageable) {

		int rowCnt = userMapper.getCntRemoveUserInfo();
		
		List<User> removeUserInfo = userMapper.getRemoveUserInfo(pageable);
		
		return new PageInfo<>(removeUserInfo, pageable, rowCnt);
	}

	// 휴면 회원정보 조회
	@Override
	public PageInfo<User> getDormancyUserInfo(Pageable pageable) {
		
		int rowCnt = userMapper.getCntDormancyUserInfo();
		
		List<User> dormancyUserInfo = userMapper.getDormancyUserInfo();
		
		return new PageInfo<>(dormancyUserInfo, pageable, rowCnt);
	}
	
	// 회원 로그인내역 조회
	@Override
	public PageInfo<User> getUserLoginLog(Pageable pageable) {
		
		int rowCnt = userMapper.getCntUserLoginLog();
		
		List<User> userLoginlog = userMapper.getUserLoginLog(pageable);
		
		return new PageInfo<>(userLoginlog, pageable, rowCnt);
	}

	// 회원 권한정보 조회
	@Override
	public List<Map<String, Object>> getAuthrtInfo() {
		
		return userMapper.getAuthrtInfo();
	}

	@Override
	public void removeUser(String id) {
		
		userMapper.removeUser(id);
	}
	
}
