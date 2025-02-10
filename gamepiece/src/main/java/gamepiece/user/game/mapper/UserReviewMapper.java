package gamepiece.user.game.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.game.domain.UserReview;

@Mapper
public interface UserReviewMapper {

	// 리뷰 조회 
	List<UserReview> getUserReview(String gameCode);
	
	// 리뷰 마지막 번호 조회
	int getLastReviewNum();
	
	int writeUserReview(UserReview userReview);
	
}
