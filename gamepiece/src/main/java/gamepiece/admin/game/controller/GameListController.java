package gamepiece.admin.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamepiece.admin.game.service.GameListService;

@Controller
@RequestMapping("/admin/game")
public class GameListController {

	private final GameListService gameListService;
	
	public GameListController(GameListService gameListService) {
		this.gameListService = gameListService;
	}
	
	// 게임 목록 화면
	@GetMapping("/gameList")
	public String gameListView(Model model) {
		model.addAttribute("title", "게임목록");
		model.addAttribute("gameList", gameListService.getGameList());
		return "admin/game/gameList";
	}
	
	@GetMapping("/modify")
	public String modifyGameListView(@RequestParam(name="gameCode") String gameCode, Model model) {
		var gameInfo = gameListService.getGameInfoByGameCode(gameCode);
		model.addAttribute("title", "게임수정");
		model.addAttribute("gameInfo", gameInfo);
		model.addAttribute("gameList", gameListService.getGameList());
		return "admin/game/modifyGame";
	}
	
}
