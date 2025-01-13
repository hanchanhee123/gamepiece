package gamepiece.user.game.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	@GetMapping("/gameList")
	public String getUserGameListView(Model model, Pageable pageable) {
	
		var pageInfo = userGameService.getGameList(pageable);
		
		
		List<UserGame> gameList = pageInfo.getContents();
		ArrayList<String> platformList = userGameService.getPlatformList();
		log.info("platformList : {}", platformList);
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		
		model.addAttribute("userGameList", gameList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("platformList", platformList);
		return "user/game/gameList";
	}
	
	@GetMapping("/platform")
	public String getUserGameListWithPlatform(@RequestParam(value="platformCode") String platformCode, Model model, Pageable pageable) {
		var pageInfo = userGameService.getGameListWithPlatform(pageable, platformCode);
		
		
		List<UserGame> gameList = pageInfo.getContents();
		ArrayList<String> platformList = userGameService.getPlatformList();
		log.info("platformList : {}", platformList);
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		
		model.addAttribute("userGameList", gameList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("platformList", platformList);
		
		return "user/game/gameList";
	}
	
	
	
	
	
	
}
