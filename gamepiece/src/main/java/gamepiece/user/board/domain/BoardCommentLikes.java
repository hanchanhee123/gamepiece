package gamepiece.user.board.domain;

import gamepiece.user.user.domain.User;
import lombok.Data;

@Data
public class BoardCommentLikes {
	
	
	    private String commentLikeNum;
	    private String commentNum;
	    private String userId;
	    private String likesType;
	    private String likeYmd;
	    private BoardComment commentInfo;
	    private User userInfo;

}
