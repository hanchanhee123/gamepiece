package gamepiece.admin.game.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.game.domain.Game;
import gamepiece.admin.game.service.GameListService;
import gamepiece.util.Pageable;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/game")
@Slf4j
public class GameListController {

	private final GameListService gameListService;
	
	public GameListController(GameListService gameListService) {
		this.gameListService = gameListService;
	}
	
	// 게임 목록 화면으로 이동
	@GetMapping("/gameList")
	public String gameListView(Model model, Pageable pageable) {
		
		var pageInfo = gameListService.getGameList(pageable);
		List<Game> gameList = pageInfo.getContents();
		
		
		int currentPage = pageInfo.getCurrentPage();
		int lastPage = pageInfo.getLastPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		
	
		
		List<Map<String, Object>> platformList = gameListService.getPlatform();
		
		List<Game> genreList = gameListService.getGenreList();
		
		model.addAttribute("title", "게임목록");
		model.addAttribute("gameList", gameList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		
		model.addAttribute("platformList", platformList);
		model.addAttribute("genreList", genreList);
		
		
		
		return "admin/game/gameList";
	}
	
	// 게임 수정 화면으로 이동
	@GetMapping("/modify")
	public String modifyGameListView(@RequestParam(name="gameCode") String gameCode, Model model, Pageable pageable) {
		var gameInfo = gameListService.getGameInfoByGameCode(gameCode);
		var platform = gameListService.getPlatform();
		model.addAttribute("title", "게임수정");
		model.addAttribute("gameInfo", gameInfo);
		model.addAttribute("gameList", gameListService.getGameList(pageable));
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
	
	
	// 게임 검색
	@PostMapping("/searchList")
	public String searchListView(@RequestParam(value="searchValue") String searchValue, Model model, Pageable pageable) {
		
		List<Game> gameList = gameListService.searchList(searchValue);
		var pageInfo = gameListService.getGameList(pageable);
		
		int currentPage = pageInfo.getCurrentPage();
		int lastPage = pageInfo.getLastPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		
		model.addAttribute("gameList", gameList);
		model.addAttribute("searchValue", searchValue);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		return "admin/game/gameList";
	}
	
	@PostMapping("/searchGenre")
	public String searchGameWithGenre(@RequestParam(value="searchGenre") String searchGenre, Model model, Pageable pageable) {
		
		List<Game> gameList = gameListService.searchGameWithGenre(searchGenre);
		var pageInfo = gameListService.getGameList(pageable);
		List<Game> genreList = gameListService.getGenreList();
		log.info("genreList : {}", genreList);
		
		int currentPage = pageInfo.getCurrentPage();
		int lastPage = pageInfo.getLastPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		
		model.addAttribute("gameList", gameList);
		model.addAttribute("searchGenre", searchGenre);
		model.addAttribute("genreList", genreList);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		
		return "admin/game/gameList";
	}
	
	@PostMapping("/searchPlatform")
	public String searchGameWithPlatform(@RequestParam(value="searchPlatform") String searchPlatform, Model model, Pageable pageable) {
		
		
		List<Game> gameList = gameListService.searchGameWithPlatform(searchPlatform);
		var pageInfo = gameListService.getGameList(pageable);
		List<Map<String, Object>> platformList = gameListService.getPlatform();
		
		log.info("gameList : {}", gameList);
		
		
		int currentPage = pageInfo.getCurrentPage();
		int lastPage = pageInfo.getLastPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		
		model.addAttribute("gameList", gameList);
		model.addAttribute("searchPlatform", searchPlatform);
		model.addAttribute("platformList", platformList);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		
		return "admin/game/gameList";
	}
	
	
	
	
	
	
	
	
	
}
