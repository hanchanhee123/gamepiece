package gamepiece.user.board.domain;

import gamepiece.user.user.domain.User;
import lombok.Data;

@Data
public class BoardLikes {
	
	
	    private String likeNum;
	    private String boardNum;
	    private String userId;
	    private String likesType;
	    private String likeYmd;
	    private Board boardInfo;
	    private User userInfo;

}
