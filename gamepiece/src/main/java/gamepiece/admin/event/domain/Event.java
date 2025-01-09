package gamepiece.admin.event.domain;


import java.time.LocalDateTime;

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
	private String evStartYmd;
	private String evEndYmd;
	private int evWinnersNum;
	private String adminId;
	private LocalDateTime regYmd;
	private String evImg;
	private String evpNo;
	private String id;
	private String evParticipateYmd;
	private String evWinnerYn;
	private String evStatus;

}


















