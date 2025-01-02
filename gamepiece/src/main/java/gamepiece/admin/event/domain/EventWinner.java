package gamepiece.admin.event.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EventWinner {

	private String ewNo;
	private String evpNo;
	private String id;
	private int winningPricePoint;
	private Date winningYmd;
}
