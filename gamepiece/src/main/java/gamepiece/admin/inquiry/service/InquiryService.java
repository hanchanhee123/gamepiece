package gamepiece.admin.inquiry.service;


import java.util.List;

import gamepiece.admin.board.domain.AdminBoardFiles;
import gamepiece.admin.inquiry.domain.Inquiry;
import gamepiece.admin.inquiry.domain.InquiryFiles;
import gamepiece.admin.inquiry.domain.InquiryRespone;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface InquiryService {
	
	
	List<InquiryFiles> getInquiryFiles(String inquiryNum);
	
	
	//파일조회
	AdminBoardFiles getInquiryFile(String inquiryNum);
	
	
	//문의 검색조회
	PageInfo<Inquiry> getSearchList(String searchValue, Pageable pageable);

	//문의답변수정
	int modifyInquiryRespone(InquiryRespone inquiryRespone);

	//문의답변 작성
	int addInquiryRespone(InquiryRespone inquiryRespone);
	
	
	//특정게시물에 답변조회
	InquiryRespone getInquiryResponeInfo(String inquiryNum);
		
	//특정게시물 조회
	Inquiry getInquiryInfo(String inquiryNum);
	

	
	//문의글 목록
	PageInfo<Inquiry> getInquiryList(Pageable pageable);

}
