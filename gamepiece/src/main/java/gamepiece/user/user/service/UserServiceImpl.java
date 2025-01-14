package gamepiece.user.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.common.mapper.CommonMapper;
import gamepiece.user.user.domain.User;
import gamepiece.user.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserMapper userMapper;
	private final CommonMapper commonMapper;

	// 로그인 (회원 아이디, 비밀번호 확인)
	@Override
	public Map<String, Object> checkUser(String id, String userPswd) {

		boolean isMatched = false;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		User userInfo = userMapper.checkUser(id);
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
	
	// 사용자 로그 확인 (오늘 날짜)
	@Override
	public int findLoginLog(String sid) {
		
		return userMapper.findLoginLog(sid);
	}

	// 로그인 로그 삽입
	@Override
	public void loginLog(String sid) {
		
		String loginNo = commonMapper.getPrimaryKey("login_", "user_login_log", "login_no");
		System.out.println("user_login_log 생성된 기본키 : " + loginNo);
		userMapper.addloginLog(loginNo, sid);
	}
	
	// 로그인 로그 업데이트
	@Override
	public void modifyLoginLog(String sid) {
		
		userMapper.modifyLoginLog(sid);
	}
	
	// 회원가입
	@Override
	public void addUser(User user) {
		
		userMapper.addUser(user);
	}
	
	// 중복 아이디 체크
	@Override
	public boolean checkId(String id) {
		
		return userMapper.checkId(id);
	}

	// 아이디 찾기 로직
	@Override
	public String findUserIdPro(String userNm, String userEmlAddr, String userTelno) {

		return userMapper.findUserIdPro(userNm, userEmlAddr, userTelno);
	}
	
	// 비밀번호 찾기 로직
	@Override
	public String findUserPswdPro(String id, String userNm, String userEmlAddr, String userTelno) {

		return userMapper.findUserPswdPro(id, userNm, userEmlAddr, userTelno);
	}
	
	// 개인 정보 수정
	@Override
	public void modifyUserInfo(User user) {
		
		userMapper.modifyUserInfo(user);
	}
	
	// 아바타
	@Override
	public String getUserAvatar(String id) {

		return userMapper.getUserAvatar(id);
	}
	
	// 아바타액자
	@Override
	public String getUserAvatarFrame(String id) {

		return userMapper.getUserAvatarFrame(id);
	}

}
