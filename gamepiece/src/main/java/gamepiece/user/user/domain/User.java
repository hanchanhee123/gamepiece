package gamepiece.user.user.domain;


import lombok.Data;

@Data
public class User {

	private String id;
	private String authrtCd;
	private String userPswd;
	private String userNm;
	private String userGender;
	private String userEmlAddr;
	private String userBrdt;
	private String userTelno;
	private String userNn;
	private String joinYmd;
	private String whdwlYmd;
	private String isDelete;
	
}
