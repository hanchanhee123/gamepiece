package gamepiece.user.tournament.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tournament")
public class UserTournamentController {
	
	@GetMapping("/list")
	public String getUserTournamentList(Model model, @RequestParam(value = "tournament", defaultValue = "all") String tournament) {
		
		model.addAttribute("title", "전체 대회리스트");
		model.addAttribute("tournament", tournament);
		
		return "user/tournament/tournamentList";
	}
	
	
}
