package gamepiece.user.game.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gamepiece.user.game.domain.UserGame;
import gamepiece.user.game.service.UserGameService;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/game")
@RequiredArgsConstructor
public class UserGameController {

	private final UserGameService userGameService;
	
	@GetMapping("/gameList")
	public String getUserGameListView(Model model, Pageable pageable) {
		
		List<UserGame> userGameList = userGameService.getGameList();
		
		model.addAttribute("userGameList", userGameList);
		return "user/game/gameList";
	}
	
}
