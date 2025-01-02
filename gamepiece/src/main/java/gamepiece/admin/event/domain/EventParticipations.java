package gamepiece.admin.event.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EventParticipations {

	private String evp_no;
	private String ev_cd;
	private Date ev_participate_ymd;
	private String ev_agreement;
}
