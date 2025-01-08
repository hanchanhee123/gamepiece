package gamepiece.user.user.service;

import java.util.Map;

public interface UserService {

	// 로그인 (회원 아이디, 비밀번호 확인)
	Map<String, Object> checkUser(String id, String userPswd);
	
}
