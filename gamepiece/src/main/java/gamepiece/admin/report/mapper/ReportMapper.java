package gamepiece.admin.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.report.domain.Report;
import gamepiece.util.Pageable;

@Mapper
public interface ReportMapper {

	
	//신고행 세기
	int getCntReport();
	
	//신고 목록
	List<Report> getReportList(Pageable pageable);
}
