package gamepiece.admin.report.domain;

import lombok.Data;

@Data
public class Disposal {
	
	private String disposalCode; //처분코드
	private String reportNo; //신고번호
	private String disposalContent; //처분명
	private String disposalYmd; //처분날짜
	private String disposalCriteria; //처분기준
	private String disposalStartYmd; //처분시작날짜
	private String disposalEndYmd; //처분종료날짜
	private String adminId; //담당자Id
	private String disposalRegdate;//등록일자
}
