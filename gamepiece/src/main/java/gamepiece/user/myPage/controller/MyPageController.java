package gamepiece.user.myPage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import gamepiece.user.myPage.domain.MyPage;
import gamepiece.user.myPage.service.MyPageService;
import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.pointShop.service.PointShopService;
import gamepiece.user.user.service.UserService;
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
		
		String avatarFrame = userService.getUserAvatarFrame(id);
		model.addAttribute("avatarFrame", avatarFrame);
		
		return "/user/myPage/myPageUser";
	}
	
	// 아바타조회
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
	
	// 아바타액자조회
	@GetMapping("/avatarFrame")
	@ResponseBody
	public List<Point> getAvatarFrame(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
		return myPageService.getAvatarFrame(id);
	}
	
	// 아바타액자 저장
	@PostMapping("/saveAvatarFrame")
	@ResponseBody
	public String saveAvatarFrame(@RequestBody Map<String, String> request, HttpSession session) {

		String id = (String) session.getAttribute("SID");
		String selectAvatarFrame = request.get("selectAvatarFrame");
		
		log.info("selectAvatarFrame : {}", selectAvatarFrame);
		
		if (id == null || selectAvatarFrame == null) {
	        return "잘못된 요청";
	    }

		myPageService.saveAvatarFrame(id, selectAvatarFrame);

		return "아바타 저장 성공";
	}
	
	@GetMapping("/myPageGame")
	public String myPageGame(Model model, HttpSession session) {
		
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
		
		return "/user/myPage/myPageGame";
	}
	
	@GetMapping("/myPageWishlist")
	public String myPageWishlist(Model model, HttpSession session) {
		
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
		
		return "/user/myPage/myPageWishlist";
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
		
		return "/user/myPage/myPageReview";
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
		
		return "/user/myPage/myPageRefundPayment";
	}
	
	@GetMapping("/myPageCommunity")
	public String myPageCommunity(Model model, HttpSession session) {
		
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
		
		return "/user/myPage/myPageCommunity";
	}
	
	@GetMapping("/myPageEmoticon")
	public String myPageEmoticon(Model model, HttpSession session) {
		
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
		
		
		return "/user/myPage/myPageEmoticon";
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
		
		return "/user/myPage/myPageBoard";
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
		
		return "/user/myPage/myPageInquiry";
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
		
		return "/user/myPage/myPageEvent";
	}
}
