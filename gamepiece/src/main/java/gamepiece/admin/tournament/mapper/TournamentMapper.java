package gamepiece.admin.tournament.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.tournament.domain.Tournament;

@Mapper
public interface TournamentMapper {
	public List<Tournament> getTournamentList();

	public List<Tournament> getGameList(); 
	
	public Tournament getTournament(String tournamentCode);
	
	public void addTournament(Tournament tournamentInfo);
	
	public void modifyTournament(Tournament tournament);
	
	public void removeTournament(Tournament tournament);
}
