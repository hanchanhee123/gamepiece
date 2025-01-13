package gamepiece.admin.inquiry.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.admin.inquiry.domain.Inquiry;
import gamepiece.admin.inquiry.domain.InquiryRespone;
import gamepiece.admin.inquiry.mapper.InquiryMapper;
import gamepiece.admin.inquiry.mapper.InquiryResponeMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InquiryServiceImpl implements InquiryService{

	private final InquiryMapper inquiryMapper;
	private final InquiryResponeMapper inquiryResponeMapper;
	
	
	
	

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
	public PageInfo<Inquiry> getInquiryList(Pageable pageable) {
		
		int rowCnt = inquiryMapper.getCntInquiry();
		List<Inquiry> inquiryList = inquiryMapper.getInquiryList(pageable);
		
		return new PageInfo<>(inquiryList, pageable, rowCnt);
	}



	@Override
	public InquiryRespone getInquiryResponeInfo(String inquiryNum) {
		// TODO Auto-generated method stub
		return inquiryResponeMapper.getInquiryResponeInfo(inquiryNum);
	}

	@Override
	public int addInquiryRespone(InquiryRespone inquiryRespone) {
		// TODO Auto-generated method stub
		
		int result = inquiryResponeMapper.addInquiryRespone(inquiryRespone);
		return result;
	}

	@Override
	public int modifyInquiryRespone(InquiryRespone inquiryRespone) {
		
		int result = inquiryResponeMapper.modifyInquiryRespone(inquiryRespone);
		
		return result;
	}

	@Override
	public PageInfo<Inquiry> getSearchList(String searchValue, Pageable pageable) {
		 Map<String, Object> searchMap = new HashMap<String, Object>();
		 
		   searchMap.put("searchValue", searchValue);
		   searchMap.put("offset", pageable.getOffset());
		   searchMap.put("rowPerPage", pageable.getRowPerPage());

		   int rowCnt = inquiryMapper.getCntSearchInquiry(searchMap);
		   List<Inquiry> inquiryList = inquiryMapper.getInquirySearchList(searchMap);
		 
		   return new PageInfo<>(inquiryList, pageable, rowCnt);
	}

	




	
	


}
