package gamepiece.user.myPage.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gamepiece.user.myPage.domain.MyPage;
import gamepiece.user.myPage.service.MyPageService;
import gamepiece.user.pointShop.service.PointShopService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
	
	private final MyPageService myPageService;
	private final PointShopService pointshopService;

	@GetMapping("/myPageUser")
	public String myPageUser(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
		MyPage myPageUser = myPageService.myPageUser(id);
		model.addAttribute("myPageUser", myPageUser);
		
		return "/user/myPage/myPageUser";
	}
	
	@GetMapping("/myPageGame")
	public String myPageGame(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
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
