package gamepiece.user.myPage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.myPage.domain.MyPage;

@Mapper
public interface MyPageMapper {

	// 마이페이지 - 사용자정보조회 (정보수정)
	MyPage myPageUser(String id);

	// 마이페이지 - 사용자정보조회 (포인트 내역)
	List<MyPage> myPagePointLog(String id);

}
