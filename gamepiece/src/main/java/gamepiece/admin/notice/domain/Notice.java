package gamepiece.admin.notice.domain;

import lombok.Data;

@Data
public class Notice {

	private int noticeNum; //공지사항 번호
	private String noticeTitle; //공지제목
	private String noticeContent; //공지내용
	private String noticeImg; //공지이미지
	private int ViewCount; //공지조회수
	private String noticeWriter; //공지작성자
	private String adminId = "id01";
	private String noticeYmd; //공지작성일
	
	

}
