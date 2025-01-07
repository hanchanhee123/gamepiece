package gamepiece.admin.tournament.service;

import java.util.List;

import gamepiece.admin.tournament.domain.Tournament;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface TournamentService {
	// 대회 목록 리스트 조회
	public PageInfo<Tournament> getTournamentList(Pageable pageable);
	// 대회 게임 카테고리 조회
	public List<Tournament> getGameList();
	// 대회 추가
	public void addTournament(Tournament tournamentInfo);
	// 대회 수정
	public Tournament getTournament(String tournamentCode);
	public void modifyTournament(Tournament tournament);
}
