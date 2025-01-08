package gamepiece.admin.inquiry.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.inquiry.domain.Inquiry;
import gamepiece.util.Pageable;

@Mapper
public interface InquiryMapper {
	
	//문의글 삭제
	int removeInquiry(String inquiryNum);
	
	//문의글 행 세기
	int getCntInquiry();
	
	//특정게시물 수정
	int modifyInquiry(Inquiry inquiry);
	
	//특정게시물 조회
	Inquiry getInquiryInfo(String inquiryNum);
	
	//문의글 추가(작성)
	int addInquiry(Inquiry inquiry);
	
	//문의글 목록
	List<Inquiry> getInquiryList(Pageable pageable);

}
