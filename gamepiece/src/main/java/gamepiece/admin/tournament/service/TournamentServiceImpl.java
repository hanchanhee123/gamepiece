package gamepiece.admin.tournament.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gamepiece.admin.tournament.domain.Tournament;
import gamepiece.admin.tournament.mapper.TournamentMapper;

@Service
public class TournamentServiceImpl implements TournamentService {
	
	private final TournamentMapper tournamentMapper;
	
	public TournamentServiceImpl(TournamentMapper tournamentMapper) {
		this.tournamentMapper = tournamentMapper;
	}

	@Override
	public List<Tournament> getTournamentList() {
		// TODO Auto-generated method stub
		return tournamentMapper.getTournamentList();
	}

	@Override
	public List<Tournament> getGameList() {
		// TODO Auto-generated method stub
		return tournamentMapper.getGameList();
	}
	
}
