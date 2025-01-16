package gamepiece.user.board.mapper;



import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import gamepiece.user.board.domain.InquiryRespone;



@Mapper
@Repository("userInquiryResponeMapper")
public interface InquiryResponeMapper {
	
	
	//문의답변 확인
	InquiryRespone getInquiryResponeInfo(String inquiryNum);

}
