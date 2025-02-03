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
public class BoardsFiles {
    private String bfIdx;        // boards_files 테이블의 PK
    private String boardNum;        // 게시판 번호
    private String fileIdx;      // 파일 번호
    private BoardFiles fileInfo; 
}