package gamepiece.admin.event.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EventWinning {

	private String ew_no;
	private String evp_no;
	private String id;
	private int winning_price_point;
	private Date winning_ymd;
}
