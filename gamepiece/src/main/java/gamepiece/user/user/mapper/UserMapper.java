package gamepiece.user.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.user.domain.User;

@Mapper
public interface UserMapper {
	
	// 로그인 (회원 아이디, 비밀번호 확인)
	User checkUser(String id);

	// 회원가입
	int addUser(User user);

	// 중복 아이디 체크
	boolean checkId(String id);

}
