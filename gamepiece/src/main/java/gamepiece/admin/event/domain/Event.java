package gamepiece.admin.event.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Event {

	private String ev_cd;
	private String ev_nm;
	private String ev_contents;
	private Date ev_start_ymd;
	private Date ev_end_ymd;
	private int ev_winners_num;
	private String admin_id;
	private Date reg_ymd;
	private String ev_img;
	
	public Event() {} 
	
	public Event(String ev_cd, String ev_nm, String ev_contents, Date ev_start_ymd, Date ev_end_ymd, int ev_winners_num, String admin_id, Date reg_ymd, String ev_img){
		super();
		this.ev_cd = ev_cd;
		this.ev_nm = ev_nm;
		this.ev_contents = ev_contents;
		this.ev_start_ymd = ev_start_ymd;
		this.ev_end_ymd = ev_end_ymd;
		this.ev_winners_num = ev_winners_num;
		this.admin_id = admin_id;
		this.reg_ymd = reg_ymd;
		this.ev_img = ev_img;
	}
}

