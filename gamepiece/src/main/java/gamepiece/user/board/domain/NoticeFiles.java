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
public class NoticeFiles {
	private String nfIdx;        
    private int noticeNum;       
    private String fileIdx;     
    private BoardFiles fileInfo;

}
