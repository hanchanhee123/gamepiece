package gamepiece.admin.notice.domain;

import gamepiece.admin.board.domain.AdminBoardFiles;
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
	    private AdminBoardFiles fileInfo;

}
