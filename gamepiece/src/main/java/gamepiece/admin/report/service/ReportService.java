package gamepiece.admin.report.service;


import gamepiece.admin.report.domain.Disposal;
import gamepiece.admin.report.domain.Report;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface ReportService {
	
	
	//신고처분 조회
	Disposal getDisposalInfo(String reportNo);
	
	//신고목록
	PageInfo<Report> getInquiryList(Pageable pageable);

}
