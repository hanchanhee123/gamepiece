package gamepiece.user.board.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import gamepiece.user.board.domain.BoardCommentLikes;


@Mapper
public interface BoardCommentLikeMapper {
	
    // 좋아요/싫어요 추가
    public int addCommentLikes(BoardCommentLikes commentLikes);

    // 좋아요/싫어요 확인 
    public BoardCommentLikes getCommentLikesByUser(Map<String, String> map);
    

    // 삭제 메소드 추가
    int removeCommentLikes(@Param("commentNum") String commentNum, 
                        @Param("userId") String userId, 
                        @Param("likesType") String likesType);

}
