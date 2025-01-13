package gamepiece.user.tournament.domain;

import java.util.List;

import lombok.Data;

@Data
public class MatchGroup {
	private String groupName;
	
	private List<MatchInfo> schedules;
}
