package gamepiece.user.board.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import gamepiece.user.board.domain.BoardLikes;

@Mapper
public interface BoardLikeMapper {
	BoardLikes getBoardLikesByUser(Map<String, String> map);
    int addBoardLikes(BoardLikes boardLikes);
    
    // 삭제 메소드 추가
    int removeBoardLikes(@Param("boardNum") String boardNum, 
                        @Param("userId") String userId, 
                        @Param("likesType") String likesType);
}
