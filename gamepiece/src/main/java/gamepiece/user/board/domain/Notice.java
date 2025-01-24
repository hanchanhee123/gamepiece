package gamepiece.user.board.domain;

import gamepiece.user.user.domain.User;
import lombok.Data;

@Data
public class Notice {
	
	private int noticeNum; //공지사항 번호
	private String noticeTitle; //공지제목
	private String noticeContent; //공지내용
	private String noticeImg; //공지이미지
	private int viewCount; //공지조회수
	private String adminId; 
	private String noticeYmd; //공지작성일
	private User userInfo; 

}
