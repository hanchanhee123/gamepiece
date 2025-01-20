package gamepiece.user.myPage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import gamepiece.user.board.domain.Board;
import gamepiece.user.board.domain.Inquiry;
import gamepiece.user.event.domain.Event;
import gamepiece.user.myPage.domain.MyPage;
import gamepiece.user.myPage.service.MyPageService;
import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.pointShop.service.PointShopService;
import gamepiece.user.user.service.UserService;
import gamepiece.util.Pageable;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
	
	private final MyPageService myPageService;
	private final UserService userService;
	private final PointShopService pointshopService;

	@GetMapping("/myPageUser")
	public String myPageUser(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
		MyPage myPageUser = myPageService.myPageUser(id);
		model.addAttribute("myPageUser", myPageUser);
		
		String avatar = userService.getUserAvatar(id);
		model.addAttribute("avatar", avatar);
		
		String background = userService.getUserBackground(id);
		model.addAttribute("background", background);
		
		return "user/myPage/myPageUser";
	}
	
	// 아바타 조회
	@GetMapping("/avatar")
	@ResponseBody
	public List<Point> getAvatar(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
		return myPageService.getAvatar(id);
	}
	
	// 아바타 저장
	@PostMapping("/saveAvatar")
	@ResponseBody
	public String saveAvatar(@RequestBody Map<String, String> request, HttpSession session) {

		String id = (String) session.getAttribute("SID");
		String selectAvatar = request.get("selectAvatar");
		
		log.info("selectAvatar : {}", selectAvatar);
		
		if (id == null || selectAvatar == null) {
	        return "잘못된 요청";
	    }

		myPageService.saveAvatar(id, selectAvatar);

		return "아바타 저장 성공";
	}
	
	// 배경프로필 조회
	@GetMapping("/background")
	@ResponseBody
	public List<Point> getBackground(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
		return myPageService.getBackground(id);
	}
	
	// 배경프로필 저장
	@PostMapping("/saveBackground")
	@ResponseBody
	public String saveBackground(@RequestBody Map<String, String> request, HttpSession session) {

		String id = (String) session.getAttribute("SID");
		String selectBackground = request.get("selectBackground");
		
		log.info("selectBackground : {}", selectBackground);
		
		if (id == null || selectBackground == null) {
	        return "잘못된 요청";
	    }

		myPageService.saveBackground(id, selectBackground);

		return "아바타 저장 성공";
	}
	
	@GetMapping("/myPageReview")
	public String myPageReview(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
		String avatar = userService.getUserAvatar(id);
		model.addAttribute("avatar", avatar);
		
		MyPage myPageUser = myPageService.myPageUser(id);
		model.addAttribute("myPageUserName", myPageUser.getUserNm());
		
		var userPoint = pointshopService.getPointsHeld(id);
		model.addAttribute("myPageUserPoint", userPoint.getTotalPoint());
		
		List<MyPage> myPagePointLog = myPageService.myPagePointLog(id);
		model.addAttribute("myPagePointLog", myPagePointLog);
		log.info("myPagePointLog : {}", myPagePointLog);
		
//		List<Board> myPageReview = myPageService.myPageReview(id);
//		model.addAttribute("myPageReview", myPageReview);
//		log.info("myPageReview : {}", myPageReview);
		
		return "user/myPage/myPageReview";
	}
	
	@GetMapping("/myPageRefundPayment")
	public String myPageRefundPayment(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
		String avatar = userService.getUserAvatar(id);
		model.addAttribute("avatar", avatar);
		
		MyPage myPageUser = myPageService.myPageUser(id);
		model.addAttribute("myPageUserName", myPageUser.getUserNm());
		
		var userPoint = pointshopService.getPointsHeld(id);
		model.addAttribute("myPageUserPoint", userPoint.getTotalPoint());
		
		List<MyPage> myPagePointLog = myPageService.myPagePointLog(id);
		model.addAttribute("myPagePointLog", myPagePointLog);
		log.info("myPagePointLog : {}", myPagePointLog);
		
		return "user/myPage/myPageRefundPayment";
	}

	@GetMapping("/myPageEmoticon")
	public String myPageEmoticon(Model model, HttpSession session, Pageable pageable) {
		
		String id = (String) session.getAttribute("SID");
		
		String avatar = userService.getUserAvatar(id);
		model.addAttribute("avatar", avatar);
		
		MyPage myPageUser = myPageService.myPageUser(id);
		model.addAttribute("myPageUserName", myPageUser.getUserNm());
		
		var userPoint = pointshopService.getPointsHeld(id);
		model.addAttribute("myPageUserPoint", userPoint.getTotalPoint());
		
		List<MyPage> myPagePointLog = myPageService.myPagePointLog(id);
		model.addAttribute("myPagePointLog", myPagePointLog);
		log.info("myPagePointLog : {}", myPagePointLog);
		
		List<Point> myPageEmoticon = myPageService.myPageEmoticon(id);
		model.addAttribute("myPageEmoticon", myPageEmoticon);
		log.info("myPageEmoticon : {}", myPageEmoticon);
		
		return "user/myPage/myPageEmoticon";
	}
	
	@GetMapping("/myPageBoard")
	public String myPageBoard(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
		String avatar = userService.getUserAvatar(id);
		model.addAttribute("avatar", avatar);
		
		MyPage myPageUser = myPageService.myPageUser(id);
		model.addAttribute("myPageUserName", myPageUser.getUserNm());
		
		var userPoint = pointshopService.getPointsHeld(id);
		model.addAttribute("myPageUserPoint", userPoint.getTotalPoint());
		
		List<MyPage> myPagePointLog = myPageService.myPagePointLog(id);
		model.addAttribute("myPagePointLog", myPagePointLog);
		log.info("myPagePointLog : {}", myPagePointLog);
		
		List<Board> myPageBoard = myPageService.myPageBoard(id);
		model.addAttribute("myPageBoard", myPageBoard);
		log.info("myPageBoard : {}", myPageBoard);
//		int myPageBoardComments = myPageService.myPageBoardComments(id);
//		model.addAttribute("myPageBoardComments", myPageBoardComments);
		
		return "user/myPage/myPageBoard";
	}
	
	@GetMapping("/myPageInquiry")
	public String myPageInquiry(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
		String avatar = userService.getUserAvatar(id);
		model.addAttribute("avatar", avatar);
		
		MyPage myPageUser = myPageService.myPageUser(id);
		model.addAttribute("myPageUserName", myPageUser.getUserNm());
		
		var userPoint = pointshopService.getPointsHeld(id);
		model.addAttribute("myPageUserPoint", userPoint.getTotalPoint());
		
		List<MyPage> myPagePointLog = myPageService.myPagePointLog(id);
		model.addAttribute("myPagePointLog", myPagePointLog);
		log.info("myPagePointLog : {}", myPagePointLog);
		
		List<Inquiry> myPageInquiry = myPageService.myPageInquiry(id);
		model.addAttribute("myPageInquiry", myPageInquiry);
		log.info("myPageInquiry : {}", myPageInquiry);
		
		return "user/myPage/myPageInquiry";
	}
	
	@GetMapping("/myPageEvent")
	public String myPageEvent(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
		String avatar = userService.getUserAvatar(id);
		model.addAttribute("avatar", avatar);
		
		MyPage myPageUser = myPageService.myPageUser(id);
		model.addAttribute("myPageUserName", myPageUser.getUserNm());
		
		var userPoint = pointshopService.getPointsHeld(id);
		model.addAttribute("myPageUserPoint", userPoint.getTotalPoint());
		
		List<MyPage> myPagePointLog = myPageService.myPagePointLog(id);
		model.addAttribute("myPagePointLog", myPagePointLog);
		log.info("myPagePointLog : {}", myPagePointLog);
		
		List<Event> myPageEvent = myPageService.myPageEvent(id);
		model.addAttribute("myPageEvent", myPageEvent);
		log.info("myPageEvent : {}", myPageEvent);
		
		return "user/myPage/myPageEvent";
	}
}
