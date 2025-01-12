package gamepiece.user.tournament.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties({"gameId",
					   "title",
					   "replayVideoId",
					   "matchPredictable",
					   "matchTalk",
					   "esportsLoungeId",
					   "videos",
					   "teamMatch",
					   "playerMatch",
					   "homePlayer",
					   "awayPlayer",
					   "relay"
					  })
@Data
public class MatchInfo {
	private String leagueId;
	private String topLeagueId;
	private String gameCode;
	private String stadium;
	private String startDate;
	private String awayScore;
	private String homeScore;
	private String weeks;
	private String days;
	private String winner;
	private String matchStatus;
	private String maxMatchCount;
	private String currentMatchSet;
	private String loungeId;
	private String date;
	private String time;

	private TeamInfo homeTeam;
	private TeamInfo awayTeam;
	
	public void setStartDate(Long startDate) {
		Timestamp timestamp = new Timestamp(startDate);
		LocalDateTime localDateTime = timestamp.toLocalDateTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String startDateFormat = localDateTime.format(formatter);
		this.startDate = startDateFormat;
	}
}
