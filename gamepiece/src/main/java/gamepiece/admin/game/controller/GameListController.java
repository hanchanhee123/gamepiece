package gamepiece.admin.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.game.domain.Game;
import gamepiece.admin.game.service.GameListService;

@Controller
@RequestMapping("/admin/game")
public class GameListController {

	private final GameListService gameListService;
	
	public GameListController(GameListService gameListService) {
		this.gameListService = gameListService;
	}
	
	// 게임 목록 화면으로 이동
	@GetMapping("/gameList")
	public String gameListView(Model model) {
		model.addAttribute("title", "게임목록");
		System.out.println(gameListService.getGameList());
		model.addAttribute("gameList", gameListService.getGameList());
		return "admin/game/gameList";
	}
	
	// 게임 수정 화면으로 이동
	@GetMapping("/modify")
	public String modifyGameListView(@RequestParam(name="gameCode") String gameCode, Model model) {
		var gameInfo = gameListService.getGameInfoByGameCode(gameCode);
		var platform = gameListService.getPlatform();
		model.addAttribute("title", "게임수정");
		model.addAttribute("gameInfo", gameInfo);
		model.addAttribute("gameList", gameListService.getGameList());
		model.addAttribute("platform", platform);
		return "admin/game/modifyGame";
	}
	
	// 특정 게임 정보 수정
	@PostMapping("/modify")
	public String modifyGame(Game game, RedirectAttributes reAttr) {
		System.out.println(game);
		gameListService.modifyGame(game);
		reAttr.addAttribute("gameCode", game.getGameCode());
		return "redirect:/admin/game/gameList";
	}
	
	
}
