package gamepiece.admin.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.report.domain.Report;
import gamepiece.util.Pageable;

@Mapper
public interface ReportMapper {

	
	// 검토 → 처리완료
	int modifyReportClear(Report report);
	
	// 요청접수 → 검토 중으로 설정
	int modifyReportReview(List<String> reportNumbers);
	
	//신고행 세기
	int getCntReport();
	
	//신고 목록
	List<Report> getReportList(Pageable pageable);
}
