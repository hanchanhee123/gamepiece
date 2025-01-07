package gamepiece.user.tournament.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("userTournamentController")
@RequestMapping("/tournament")
public class TournamentController {

	@GetMapping("/list")
	public String getTournamentList(Model model) {
		model.addAttribute("title", "대회리스트");
		
		return "user/tournament/tournamentList";
	}
	
}
