package gamepiece.admin.inquiry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.admin.inquiry.domain.Inquiry;
import gamepiece.admin.inquiry.mapper.InquiryMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional

@Slf4j
public class InquiryServiceImpl implements InquiryService{

	private final InquiryMapper inquiryMapper;
	
	
	  @Autowired
	    public InquiryServiceImpl(InquiryMapper inquiryMapper) {
	        this.inquiryMapper = inquiryMapper;
	    }
	
	
	
	
	

	@Override
	public int addInquiry(Inquiry inquiry) {
		int result = inquiryMapper.addInquiry(inquiry);
		return result;
	}

	@Override
	public Inquiry getInquiryInfo(String inquiryNum) {
	
		return inquiryMapper.getInquiryInfo(inquiryNum);
	}

	@Override
	public int modifyInquiry(Inquiry inquiry) {
	
		return inquiryMapper.modifyInquiry(inquiry);
	}





	@Override
	public PageInfo<Inquiry> getInquiryList(Pageable pageable) {
		
		int rowCnt = inquiryMapper.getCntInquiry();
		List<Inquiry> inquiryList = inquiryMapper.getInquiryList(pageable);
		
		return new PageInfo<>(inquiryList, pageable, rowCnt);
	}






	@Override
	public int removeInquiry(String inquiryNum) {
		// TODO Auto-generated method stub
		return inquiryMapper.removeInquiry(inquiryNum);
	}
	


}
