package gamepiece.admin.point.domain;

import java.util.Date;

import lombok.Data;


@Data
public class PointCategories {
	private String cateCode;
	private String cateName;
	private String adminId;
	private Date regYmd;
}
