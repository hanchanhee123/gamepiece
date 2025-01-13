package gamepiece.admin.report.domain;

import lombok.Data;

@Data
public class Report {

	private String reportNo; //신고번호
	private String reportUser; //신고인
	private String reportedUser;//피신고인
	private String reportContent; //신고내용
	private String reportProgress; //진행상황
	private String reportYmd; //신고일
	
	
	
}
