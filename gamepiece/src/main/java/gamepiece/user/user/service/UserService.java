package gamepiece.user.user.service;

import java.util.Map;

import gamepiece.user.user.domain.User;

public interface UserService {

	// 로그인 (회원 아이디, 비밀번호 확인)
	Map<String, Object> checkUser(String id, String userPswd);

	// 회원가입
	void addUser(User user);

	// 중복 아이디 체크
	boolean checkId(String id);
	
}
