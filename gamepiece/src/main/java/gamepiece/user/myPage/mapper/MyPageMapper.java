package gamepiece.user.myPage.mapper;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.myPage.domain.MyPage;

@Mapper
public interface MyPageMapper {

	// 마이페이지 - 사용자정보조회 (정보수정)
	MyPage myPageUser(String id);

}
