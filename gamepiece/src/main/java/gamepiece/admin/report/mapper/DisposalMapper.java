package gamepiece.admin.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.report.domain.Disposal;
import gamepiece.admin.report.domain.Report;
import gamepiece.util.Pageable;

@Mapper
public interface DisposalMapper {

	
	//신고처분 조회
	Disposal getDisposalInfo(String reportNo);
}
