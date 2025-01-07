package gamepiece.admin.tournament.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.tournament.domain.Tournament;
import gamepiece.util.Pageable;

@Mapper
public interface TournamentMapper {
	public List<Tournament> getTournamentList(Pageable pageable);

	public List<Tournament> getGameList(); 
	
	public Tournament getTournament(String tournamentCode);
	
	public void addTournament(Tournament tournamentInfo);
	
	public void modifyTournament(Tournament tournament);
	
	public void removeTournament(Tournament tournament);
	
	public int getCntTournament();
}
