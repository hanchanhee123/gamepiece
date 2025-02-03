package gamepiece.user.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.board.domain.InquiryFiles;

@Mapper
public interface InquiryFilesMapper {
 
    List<InquiryFiles> getInquiryFiles(String inquiryNum);

    int addInquiryFileMapping(InquiryFiles mapping);
    int removeInquiryMapping(String inqFileIdx);

}