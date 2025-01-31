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
public class BoardFiles {
	
	private String fileIdx;
	private String fileOriginalName;
	private String fileNewName;
	private String filePath;
	private Long fileSize;
	private Board boardInfo;
	private Inquiry inquiryInfo;
	private Notice noticeInfo;
	

}
