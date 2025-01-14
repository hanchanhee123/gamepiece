package gamepiece.user.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.user.domain.User;

@Mapper
public interface UserMapper {
	
	// 로그인 (회원 아이디, 비밀번호 확인)
	User checkUser(String id);
	
	// 사용자 로그 확인 (오늘 날짜)
	int findLoginLog(String id);

	// 로그인 로그 삽입
	void addloginLog(String loginNo, String id);
	
	// 로그인 로그 업데이트
	void modifyLoginLog(String id);

	// 회원가입
	int addUser(User user);

	// 중복 아이디 체크
	boolean checkId(String id);

	// 아이디 찾기 로직
	String findUserIdPro(String userNm, String userEmlAddr, String userTelno);

	// 비밀번호 찾기 로직
	String findUserPswdPro(String id, String userNm, String userEmlAddr, String userTelno);

	// 개인 정보 수정
	void modifyUserInfo(User user);

	// 아바타
	String getUserAvatar(String id);

	// 아바타액자
	String getUserAvatarFrame(String id);

}
