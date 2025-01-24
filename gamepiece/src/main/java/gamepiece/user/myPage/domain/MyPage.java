package gamepiece.user.myPage.domain;

import java.util.List;

import gamepiece.user.pointShop.domain.PointLog;
import lombok.Data;

@Data
public class MyPage {
	
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
	
	private List<PointLog> pointLog;
	
}
