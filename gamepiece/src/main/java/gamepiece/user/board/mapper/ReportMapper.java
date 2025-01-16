package gamepiece.user.board.mapper;



import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import gamepiece.user.board.domain.Report;



@Mapper
@Repository("userRepoetMapper")
public interface ReportMapper {
	
	
	//신고하기
	int addReport(Report report);


}
