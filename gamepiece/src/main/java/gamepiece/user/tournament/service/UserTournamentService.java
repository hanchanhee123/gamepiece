package gamepiece.user.tournament.service;

import java.util.List;

import gamepiece.user.tournament.domain.MatchGroup;

public interface UserTournamentService {
	public List<MatchGroup> getTournamentList(String gameName, String matchCate, String date);
}
