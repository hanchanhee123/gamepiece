package gamepiece.admin.tournament.service;

import java.util.List;

import gamepiece.admin.tournament.domain.Tournament;

public interface TournamentService {
	public List<Tournament> getTournamentList();
	public List<Tournament> getGameList();
	public Tournament getTournamentInfo(Tournament tournamentInfo, String tournamentStartDateStr, String tournamentEndDateStr);
	public void addTournament(Tournament tournamentInfo);
}
