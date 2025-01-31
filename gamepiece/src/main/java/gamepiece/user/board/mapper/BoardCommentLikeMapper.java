package gamepiece.user.board.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.board.domain.BoardCommentLikes;


@Mapper
public interface BoardCommentLikeMapper {
	
    // 좋아요/싫어요 추가
    public int addCommentLikes(BoardCommentLikes commentLikes);

    // 좋아요/싫어요 확인 
    public BoardCommentLikes getCommentLikesByUser(Map<String, String> map);

}
