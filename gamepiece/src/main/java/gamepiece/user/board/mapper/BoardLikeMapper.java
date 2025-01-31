package gamepiece.user.board.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.board.domain.BoardLikes;

@Mapper
public interface BoardLikeMapper {
	
    // 좋아요/싫어요 추가
    public int addBoardLikes(BoardLikes boardLikes);

    // 좋아요/싫어요 확인 
    public BoardLikes getBoardLikesByUser(Map<String, String> map);

}
