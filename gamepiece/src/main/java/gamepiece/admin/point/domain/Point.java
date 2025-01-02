package gamepiece.admin.point.domain;

import java.util.Date;

import lombok.Data;


@Data
public class Point {
	

	private String itemCd;
	private String itemCate;
	private String itemName;
	private String itemImgurl;
	private int itemPrice;
	private String adminId;
	private Date regYmd;
	
}
