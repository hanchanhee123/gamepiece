package gamepiece.admin.tournament.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.tournament.domain.Tournament;

@Mapper
public interface TournamentMapper {
	public List<Tournament> getTournamentList();

	public List<Tournament> getGameList(); 
	
	public void addTournament(Tournament tournamentInfo);
}
