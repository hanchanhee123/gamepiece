package gamepiece.admin.report.mapper;


import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.report.domain.Disposal;

@Mapper
public interface DisposalMapper {

	
	//처분작성
	int addDisposal(Disposal disposal);
	
	//신고처분 조회
	Disposal getDisposalInfo(String reportNo);
}
