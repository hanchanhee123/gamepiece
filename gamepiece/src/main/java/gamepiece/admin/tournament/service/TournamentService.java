package gamepiece.admin.tournament.service;

import java.util.List;

import gamepiece.admin.tournament.domain.Tournament;

public interface TournamentService {
	public List<Tournament> getTournamentList();
	public List<Tournament> getGameList();
}
