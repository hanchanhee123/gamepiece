package gamepiece.admin.boardCategory.domain;


import lombok.Data;

@Data
public class BoardCategory {
	

	private String categoryCode;
	private String categoryName;	
	private String adminId = "id01";
	private String categoryYmd;
	private Integer boardCount;

}
