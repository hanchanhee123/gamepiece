package gamepiece.user.board.domain;

import gamepiece.user.user.domain.User;
import lombok.Data;

@Data
public class InquiryRespone {
	
	
	private String responeNum; //답변번호
	private String inquiryNum; //해당문의번호
	private String responeContent; //답변내용
	private String responeYmd; //답변일
	private String adminId; //담당자id
	private User userInfo;
	

}
