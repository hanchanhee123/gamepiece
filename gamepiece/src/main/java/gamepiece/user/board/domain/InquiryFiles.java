package gamepiece.user.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InquiryFiles {
    private String inqFileIdx;        
    private String inquiryNum;       
    private String fileIdx;     
    private BoardFiles fileInfo;
}