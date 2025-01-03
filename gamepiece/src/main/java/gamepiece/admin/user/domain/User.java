package gamepiece.admin.user.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {

	private String id;
	private String authrtCd;
	private String authrtNm;
	private String userPswd;
	private String userNm;
	private String userGender;
	private String userEmlAddr;
	private String userBrdt;
	private String userTelno;
	private String userNn;
	private LocalDate joinYmd;
	private LocalDate whdwlYmd;
	private String loginNo;
	private LocalDateTime lgnDt;
	private int inactiveDays;	// 미접일수
	
}