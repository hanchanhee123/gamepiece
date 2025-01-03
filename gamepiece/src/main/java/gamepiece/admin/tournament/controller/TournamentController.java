package gamepiece.admin.tournament.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamepiece.admin.tournament.domain.Tournament;
import gamepiece.admin.tournament.service.TournamentService;

@Controller
@RequestMapping("/admin/tournament")
public class TournamentController {
	private final TournamentService tournamentService;
	
	public TournamentController(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}
	
	@GetMapping("/tournamentList")
	public String getTournamentList(Model model) {
		
		model.addAttribute("title", "대회 일정 목록");
		model.addAttribute("tournamentList", tournamentService.getTournamentList());
		
		return "admin/tournament/tournamentList";	
	}
	
	@GetMapping("/addTournament")
	public String addTournament(Model model) {
		
		model.addAttribute("title", "대회 추가");
		model.addAttribute("gameList", tournamentService.getGameList());
		
		return "admin/tournament/addTournament";
	}
	
	@PostMapping("/addTournament")
	public String tournamentAdd(Tournament tournamentInfo) {
		tournamentService.addTournament(tournamentInfo);
		return "redirect:/admin/tournament/tournamentList";
	}
	
	@GetMapping("/modifyTournament")
	public String modifyTournament(@RequestParam(name="tournament") String tournamentCode,
									Model model) {
		model.addAttribute("title", "대회수정");
		model.addAttribute("gameList", tournamentService.getGameList());
		model.addAttribute("tournamentInfo", tournamentService.getTournament(tournamentCode));
		
		return "admin/tournament/modifyTournament";
	}
	
	@PostMapping("/modifyTournament")
	public String tournamentModify(Tournament tournament) {
		System.out.println(tournament);
		tournamentService.modifyTournament(tournament);
		return"redirect:/admin/tournament/tournamentList";
	}
}
