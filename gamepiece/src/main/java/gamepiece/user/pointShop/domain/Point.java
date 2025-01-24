package gamepiece.user.pointShop.domain;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
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
	
	
	
}
