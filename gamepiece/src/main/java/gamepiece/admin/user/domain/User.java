package gamepiece.admin.user.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

	private String id;
	private String authrt_cd;
	private String authrt_nm;
	private String user_pw;
	private String user_nm;
	private String user_gender;
	private String user_eml_addr;
	private Date user_brdt;
	private String user_telno;
	private String user_nn;
	private Date join_ymd;
	private Date whdwl_ymd;
	
}
