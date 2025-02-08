package gamepiece.admin.tournament.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamepiece.admin.tournament.domain.Tournament;
import gamepiece.admin.tournament.service.TournamentService;
import gamepiece.util.Pageable;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/tournament")
public class TournamentController {
	private final TournamentService tournamentService;
	
	public TournamentController(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}
	
	@GetMapping("/tournamentList")
	public String getTournamentList(Pageable pageable, HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("SID");
        model.addAttribute("adminId", id);
		
		var pageInfo = tournamentService.getTournamentList(pageable);
		
		log.info("contents : {}, currentPage : {}", pageInfo.getContents(), pageInfo.getCurrentPage());
		
		model.addAttribute("title", "대회 일정 목록");
		model.addAttribute("tournamentList", pageInfo.getContents());
		model.addAttribute("currentPage", pageInfo.getCurrentPage());
		model.addAttribute("lastPage", pageInfo.getLastPage());
		model.addAttribute("startPageNum", pageInfo.getStartPageNum());
		model.addAttribute("endPageNum", pageInfo.getEndPageNum());
		
		return "admin/tournament/tournamentList";	
	}
	
	@GetMapping("/addTournament")
	public String addTournament(HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("SID");
        model.addAttribute("adminId", id);
		
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
									HttpSession session, Model model) {
		String id = (String) session.getAttribute("SID");
        model.addAttribute("adminId", id);
		
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
