package gamepiece.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import gamepiece.user.board.domain.Inquiry;
import gamepiece.util.Pageable;

@Mapper
@Repository("userInquiryMapper")
public interface InquiryMapper {
	
	
	//문의글 작성
	int addInquiry(Inquiry inquiry);
	

	//문의글 행세기
	int getCntInquiry();
	
	//문의글 조회
	List<Inquiry> getInquiryList(Pageable pageable);

}
