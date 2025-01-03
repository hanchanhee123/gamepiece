package gamepiece.admin.event.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EventParticipation {

	private String evpNo;
	private String evCd;
	private String id;
	private String evParticipateYmd;
	private String evWinnerYn;
}
