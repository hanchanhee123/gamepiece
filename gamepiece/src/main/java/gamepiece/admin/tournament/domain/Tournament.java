package gamepiece.admin.tournament.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Tournament {
	private String tournamentCode;
	private String gameCode;
	private String gameName;
	private String tournamentName;
	private String tournamentStartDate;
	private String tournamentEndDate;
	private String tournamentAddr;
	private String adminId;
}
