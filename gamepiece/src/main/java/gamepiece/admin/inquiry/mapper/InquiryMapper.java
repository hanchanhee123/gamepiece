package gamepiece.admin.inquiry.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.inquiry.domain.Inquiry;
import gamepiece.admin.inquiry.domain.InquiryRespone;
import gamepiece.util.Pageable;

@Mapper
public interface InquiryMapper {
	

	//문의 검색행 세기
	int getCntSearchInquiry(Map<String, Object> searchMap);
	
	//문의 검색조회
	List<Inquiry> getInquirySearchList(Map<String, Object> searchMap);
	
	//문의답변 조회
	InquiryRespone getInquiryResponeInfo(String inquiryNum);
	

	
	//문의글 행 세기
	int getCntInquiry();

	
	//특정게시물 조회
	Inquiry getInquiryInfo(String inquiryNum);
	
	//문의글 추가(작성)
	int addInquiry(Inquiry inquiry);
	
	//문의글 목록
	List<Inquiry> getInquiryList(Pageable pageable);

}
