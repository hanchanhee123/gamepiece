package gamepiece.user.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import gamepiece.user.board.domain.Inquiry;
import gamepiece.util.Pageable;

@Mapper
@Repository("userInquiryMapper")
public interface InquiryMapper {
	
	String getLastInquiryNum();
	
	//문의 검색행 세기
	int getCntSearchInquiry(Map<String, Object> searchMap);
	
	//문의 검색조회
	List<Inquiry> getInquirySearchList(Map<String, Object> searchMap);
	
	//문의게시물 상세
	Inquiry getInquiryInfo(String inquiryNum);
	
	//문의글 작성
	int addInquiry(Inquiry inquiry);
	

	//문의글 행세기
	int getCntInquiry();
	
	//문의글 조회
	List<Inquiry> getInquiryList(Pageable pageable);

}
