package gamepiece.user.game.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamepiece.user.game.domain.UserGame;
import gamepiece.user.game.service.UserGameService;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
@Slf4j
public class UserGameController {

	private final UserGameService userGameService;
	
	// 사용자 게임 목록 조회
	// 사용자 스팀 게임 목록 조회
		@GetMapping("/steam")
		public String getUserGameListView(@RequestParam(value="currentPage", required=false, defaultValue = "1") int currentPage,
										  @RequestParam(value="searchValue", required=false, defaultValue = "") String searchValue,
										  Model model) {
		
			
			
			/*
			 * var pageInfo = userGameService.getGameList(pageable);
			 * 
			 * 
			 * List<UserGame> gameList = pageInfo.getContents();
			 * 
			 * 
			 * 
			 * 
			 * 
			 * int currentPage = pageInfo.getCurrentPage(); int startPageNum =
			 * pageInfo.getStartPageNum(); int endPageNum = pageInfo.getEndPageNum(); int
			 * lastPage = pageInfo.getLastPage();
			 * 
			 * model.addAttribute("userGameList", gameList);
			 * model.addAttribute("currentPage", currentPage);
			 * model.addAttribute("startPageNum", startPageNum);
			 * model.addAttribute("endPageNum", endPageNum); 
			 * model.addAttribute("lastPage", lastPage); 
			 * model.addAttribute("platformList", platformList);
			 * model.addAttribute("genreList", genreList); 
			 * return "user/game/gameList";
			 */
			
			
			 
			ArrayList<String> platformList = userGameService.getPlatformList();
			
			
			Map<String, Object> gameList = userGameService.getGameListApi(searchValue, currentPage);
			model.addAttribute("gameList", gameList);
			model.addAttribute("platformList", platformList);
			
			return "user/game/steamList";
		}
	
	@GetMapping("/platform")
	public String getUserGameListWithPlatform(@RequestParam(value="platformCode") String platformCode, Model model, Pageable pageable) {
		var pageInfo = userGameService.getGameListWithPlatform(pageable, platformCode);
		
		
		List<UserGame> gameList = pageInfo.getContents();
		ArrayList<String> platformList = userGameService.getPlatformList();
		ArrayList<String> genreList = userGameService.getGenreList();
		
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		
		model.addAttribute("currentPlatformCode", platformCode);
		model.addAttribute("userGameList", gameList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("platformList", platformList);
		model.addAttribute("genreList", genreList);
		return "user/game/gameList";
	}
	
	@GetMapping("/steamDetail")
	public String getUserGameDetailApi(@RequestParam(value="gameCode") String gameCode,
									   @RequestParam(value="title", required=false, defaultValue = "" )String title,
									   Model model) {
		Map<String, Object> gameDetail = userGameService.getGameDetailApi(gameCode, title);
		model.addAttribute("gameDetail", gameDetail);
		return "user/game/steamDetail";
	}
	
	
	
	
	
	
	
	
}
