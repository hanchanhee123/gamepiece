package gamepiece.user.user.service;

import java.util.Map;

import gamepiece.user.user.domain.User;

public interface UserService {

	// 로그인 (회원 아이디, 비밀번호 확인)
	Map<String, Object> checkUser(String id, String userPswd);

	// 사용자 로그 확인 (오늘 날짜)
	int findLoginLog(String sid);
	
	// 로그인 로그 삽입
	void loginLog(String sid);
	
	// 로그인 로그 업데이트
	void modifyLoginLog(String sid);

	// 회원가입
	void addUser(User user);

	// 중복 아이디 체크
	boolean checkId(String id);

	// 아이디 찾기 로직
	String findUserIdPro(String userNm, String userEmlAddr, String userTelno);

	// 비밀번호 찾기 로직
	String findUserPswdPro(String id, String userNm, String userEmlAddr, String userTelno);

	// 회원 정보 수정
	void modifyUser(User user);
	
	// 회원 탈퇴
	void removeUser(String id);
	
	// 아바타
	String getUserAvatar(String id);

	// 배경프로필
	String getUserBackground(String id);

}
