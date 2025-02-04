package gamepiece.admin.report.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.admin.report.domain.Disposal;
import gamepiece.admin.report.domain.Report;
import gamepiece.admin.report.mapper.DisposalMapper;
import gamepiece.admin.report.mapper.ReportMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService{
	
		private final ReportMapper reportmapper;
		private final DisposalMapper disposalMapper;

		@Override
		public PageInfo<Report> getReportList(Pageable pageable) {
		
			int rowCnt = reportmapper.getCntReport();
			List<Report> reportList = reportmapper.getReportList(pageable);
			
			
			return new PageInfo<>(reportList, pageable, rowCnt);
			
		}

		@Override
		public Disposal getDisposalInfo(String reportNo) {

			return disposalMapper.getDisposalInfo(reportNo);
		}

		@Override
		public int modifyReportReview(List<String> reportNumbers) {
	
			return reportmapper.modifyReportReview(reportNumbers);
		}

		@Override
		public int addDisposal(Disposal disposal) {
		
		    if (disposal.getReportNo() == null || disposal.getReportNo().isEmpty()) {
		        throw new IllegalArgumentException("ReportNo cannot be null or empty");
		    }

		  
		    System.out.println("Adding Disposal: " + disposal);

		  
		    return disposalMapper.addDisposal(disposal);
		}

		@Override
		public int modifyReportClear(Report report) {

			
			return reportmapper.modifyReportClear(report);
		}
	
		
		
		

}
