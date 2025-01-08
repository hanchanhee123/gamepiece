package gamepiece.admin.inquiry.service;

import java.util.List;

import gamepiece.admin.board.domain.Board;
import gamepiece.admin.inquiry.domain.Inquiry;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface InquiryService {
	
	
	//문의글 삭제
		int removeInquiry(String inquiryNum);
	
	//특정게시물 수정
		int modifyInquiry(Inquiry inquiry);
		
	
	//특정게시물 조회
	Inquiry getInquiryInfo(String inquiryNum);
	
	//문의글 추가(작성)
	int addInquiry(Inquiry inquiry);
	
	//문의글 목록
	PageInfo<Inquiry> getInquiryList(Pageable pageable);

}
