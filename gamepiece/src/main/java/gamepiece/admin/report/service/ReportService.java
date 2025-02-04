package gamepiece.admin.report.service;


import java.util.List;

import gamepiece.admin.report.domain.Disposal;
import gamepiece.admin.report.domain.Report;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface ReportService {
	

	// 검토 → 처리완료
	int modifyReportClear(Report report);
	
	//처분작성
	int addDisposal(Disposal disposal);
	
	// 요청접수 → 검토 중으로 설정
	int modifyReportReview(List<String> reportNumbers);
	
	//신고처분 조회
	Disposal getDisposalInfo(String reportNo);
	
	//신고목록
	PageInfo<Report> getReportList(Pageable pageable);

}
