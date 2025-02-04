package gamepiece.admin.point.domain;



import lombok.Data;


@Data
public class Point {
	

	private String itemCd;
	private String itemCate;
	private String cateName;
	private String itemName;
	private String filePath;
	private int itemPrice;
	private String adminId;
	private String regYmd;
	private String isDelete;
	
	private String fileIdx;
	
}
