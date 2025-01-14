package gamepiece.user.board.domain;

import gamepiece.user.user.domain.User;
import lombok.Data;

@Data
public class Inquiry {
	
	
	private String inquiryNum; //문의번호
	private String inquiryUserId; //문의작성자ID
	private String inquiryTitle; //문의제목
	private String inquiryContent; //문의내용
	private String inquiryYmd; //문의작성일자
	private User userInfo; //유저정보
	private InquiryRespone responeInfo; //문의답변정보

}
