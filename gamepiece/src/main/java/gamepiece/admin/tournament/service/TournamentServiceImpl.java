package gamepiece.admin.tournament.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gamepiece.admin.tournament.domain.Tournament;
import gamepiece.admin.tournament.mapper.TournamentMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

@Service
public class TournamentServiceImpl implements TournamentService {
	
	private final TournamentMapper tournamentMapper;
	
	public TournamentServiceImpl(TournamentMapper tournamentMapper) {
		this.tournamentMapper = tournamentMapper;
	}

	@Override
	public PageInfo<Tournament> getTournamentList(Pageable pageable) {
		int rowCnt = tournamentMapper.getCntTournament();
		List<Tournament> tournamentList = tournamentMapper.getTournamentList(pageable);
		return new PageInfo<>(tournamentList, pageable, rowCnt);
	}

	@Override
	public List<Tournament> getGameList() {
		// TODO Auto-generated method stub
		return tournamentMapper.getGameList();
	}

	@Override
	public void addTournament(Tournament tournamentInfo) {
		tournamentInfo.setTournamentAddr("전주");
		tournamentInfo.setAdminId("id01");
		tournamentMapper.addTournament(tournamentInfo);
	}

	@Override
	public Tournament getTournament(String tournamentCode) {
		return tournamentMapper.getTournament(tournamentCode);
	}

	@Override
	public void modifyTournament(Tournament tournament) {
		tournamentMapper.modifyTournament(tournament);
	}
	
}
