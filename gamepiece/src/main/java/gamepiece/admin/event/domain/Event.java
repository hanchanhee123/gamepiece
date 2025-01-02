package gamepiece.admin.event.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Event {

	private String evCd;
	private String evNm;
	private String evContents;
	private LocalDate evStartYmd;
	private LocalDate evEndYmd;
	private int evWinnersNum;
	private String adminId;
	private LocalDateTime regYmd;
	private String evImg;
	private String evpNo;
	private String id;
	private LocalDateTime evParticipateYmd;
	private String evAgreement;

}


















