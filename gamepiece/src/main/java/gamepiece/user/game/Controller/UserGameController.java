package gamepiece.user.game.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import gamepiece.user.game.domain.UserGame;
import gamepiece.user.game.domain.UserReview;
import gamepiece.user.game.service.UserGameService;
import gamepiece.user.user.service.UserService;
import gamepiece.util.Pageable;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
@Slf4j
public class UserGameController {

	private final UserGameService userGameService;
	private final UserService userService;
	
	
	// 사용자 게임 목록 조회
	// 사용자 스팀 게임 목록 조회
		@GetMapping("/steam")
		public String getUserGameListView(@RequestParam(value="currentPage", required=false, defaultValue = "1") int currentPage,
										  @RequestParam(value="searchValue", required=false, defaultValue = "") String searchValue,
										  @RequestParam(value="id", required=false, defaultValue = "") String id,
										  HttpSession session,
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
			id = (String) session.getAttribute("SID");
			
			model.addAttribute("id", id);
	        String avatar = userService.getUserAvatar(id);
	        model.addAttribute("avatar", avatar);
			
			
			 
			ArrayList<String> platformList = userGameService.getPlatformList();
			
			
			model.addAttribute("id", id);
			Map<String, Object> gameList = userGameService.getGameListApi(searchValue, currentPage);
			model.addAttribute("gameList", gameList);
			model.addAttribute("platformList", platformList);
			
			return "user/game/steamList";
		}
	
		/*
		 * @GetMapping("/platform") public String
		 * getUserGameListWithPlatform(@RequestParam(value="platformCode") String
		 * platformCode, Model model, Pageable pageable) { var pageInfo =
		 * userGameService.getGameListWithPlatform(pageable, platformCode);
		 * 
		 * 
		 * List<UserGame> gameList = pageInfo.getContents(); ArrayList<String>
		 * platformList = userGameService.getPlatformList(); ArrayList<String> genreList
		 * = userGameService.getGenreList();
		 * 
		 * int currentPage = pageInfo.getCurrentPage(); int startPageNum =
		 * pageInfo.getStartPageNum(); int endPageNum = pageInfo.getEndPageNum(); int
		 * lastPage = pageInfo.getLastPage();
		 * 
		 * model.addAttribute("currentPlatformCode", platformCode);
		 * model.addAttribute("userGameList", gameList);
		 * model.addAttribute("currentPage", currentPage);
		 * model.addAttribute("startPageNum", startPageNum);
		 * model.addAttribute("endPageNum", endPageNum); model.addAttribute("lastPage",
		 * lastPage); model.addAttribute("platformList", platformList);
		 * model.addAttribute("genreList", genreList); return "user/game/gameList"; }
		 */
	
	// 게임 상세 정보 화면
	@GetMapping("/steamDetail")
	public String getUserGameDetailApi(@RequestParam(value="gameCode") String gameCode,
									   @RequestParam(value="title", required=false, defaultValue = "" )String title,
									   Model model,
									   HttpSession session) {
		String id = (String) session.getAttribute("SID");
		
		model.addAttribute("id", id);
        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);
		
		Map<String, Object> gameDetail = userGameService.getGameDetailApi(gameCode, title);
		List<UserReview> userReview = userGameService.getUserReview(gameCode);
		String nextReviewNum = userGameService.getLastReviewNo();
		int nextReviewNumInt =Integer.parseInt(nextReviewNum.substring(3)) + 1 ; 
		
		
		
		model.addAttribute("nextReviewNumInt", nextReviewNumInt);
		model.addAttribute("gameDetail", gameDetail);
		model.addAttribute("userReview", userReview);
		return "user/game/steamDetail";
	}
	
	// 리뷰 작성
	@PostMapping("/writeReview")
	public String writeGameReview(UserReview userReview,
								  Model model) {
		
		userGameService.writeUserReview(userReview);
		
		
		
		return "user/game/steamDetail";
	}
	
	
	
	
	// 장바구니에 게임 담기
	@PostMapping("/gameCart")
	public String putGameInCart(@RequestParam(value="gameCode") String gameCode,
									  @RequestParam(value="title", required = false, defaultValue = "") String title,
									  @RequestParam(value="finalPrice") String finalPrice,
									  @RequestParam(value="isDetail") String isDetail,
									  @RequestParam(value="id") String id,
									  UserGame userGame,
									  Model model,
									  HttpSession session) {
		
		Map<String, Object> gameDetail = userGameService.getGameDetailApi(gameCode, title);
		List<UserReview> userReview = userGameService.getUserReview(gameCode);
		String nextReviewNum = userGameService.getLastReviewNo();
		int nextReviewNumInt =Integer.parseInt(nextReviewNum.substring(3)) + 1 ;
		
		id = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);
		
		
		
		userGameService.putGameInCart(userGame);
		
		model.addAttribute("id", id);
		model.addAttribute("nextReviewNumInt", nextReviewNumInt);
		model.addAttribute("gameDetail", gameDetail);
		model.addAttribute("userReview", userReview);
		return "user/game/steamDetail";
	}
	
	// 장바구니 화면
	@GetMapping("/gameCartView") 
	public String userGameCartList(@RequestParam(value="id") String id,
								   Model model, HttpSession session) {
		
		List<UserGame> cartList = userGameService.getUserCartList(id);
		
		int totalPrice = userGameService.cartTotalPrice(id);
		id = (String) session.getAttribute("SID");
		
		model.addAttribute("id", id);
        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);
		
		
		model.addAttribute("cartList", cartList);
		model.addAttribute("totalPrice", totalPrice);
		
		return "user/game/gameCartList";
	}
	
	// 장바구니 목록 삭제
	@GetMapping("/deleteGameCartList")
	public String deleteGameCartList(@RequestParam(value="id") String id,
									 Model model, HttpSession session) {
		
		userGameService.deleteGameCartList(id);
		return "user/game/gameCartList";
	}
	
	
	
	// 결제 진행 화면
	@GetMapping("/paymentView") 
	public String paymentView(@RequestParam(value="id") String id,
			Model model,
			HttpSession session) {
		
		
		id = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(id);
        model.addAttribute("avatar", avatar);
		
		List<UserGame> cartList = userGameService.getUserCartList(id);
		
		
		int totalPrice = userGameService.cartTotalPrice(id);
		
		List<UserGame> paymentList = userGameService.getPaymentList();
		
		
		
		model.addAttribute("cartList", cartList);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("paymentList", paymentList);
		
		return "user/game/paymentView";
	}
	
	
	
	
	
	
	
	
	
}
