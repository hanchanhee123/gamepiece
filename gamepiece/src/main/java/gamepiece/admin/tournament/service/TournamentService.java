package gamepiece.admin.tournament.service;

import java.util.List;

import gamepiece.admin.tournament.domain.Tournament;

public interface TournamentService {
	// 대회 목록 리스트 조회
	public List<Tournament> getTournamentList();
	// 대회 게임 카테고리 조회
	public List<Tournament> getGameList();
	// 대회 추가
	public void addTournament(Tournament tournamentInfo);
	// 대회 수정
	public Tournament getTournament(String tournamentCode);
	public void modifyTournament(Tournament tournament);
}
