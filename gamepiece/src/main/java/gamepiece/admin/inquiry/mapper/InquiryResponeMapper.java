package gamepiece.admin.inquiry.mapper;




import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.inquiry.domain.InquiryRespone;


@Mapper
public interface InquiryResponeMapper {
	
	//문의답변수정
	int modifyInquiryRespone(InquiryRespone inquiryRespone);

	//문의답변 작성
	int addInquiryRespone(InquiryRespone inquiryRespone);
	
	//문의답변 조회
	InquiryRespone getInquiryResponeInfo(String inquiryNum);
	

}
