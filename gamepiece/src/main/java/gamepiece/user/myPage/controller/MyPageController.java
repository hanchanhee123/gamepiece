package gamepiece.user.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gamepiece.user.myPage.domain.MyPage;
import gamepiece.user.myPage.service.MyPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
	
	private final MyPageService myPageService;

	@GetMapping("/myPageUser")
	public String myPageUser(Model model, HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
		MyPage myPageUser = myPageService.myPageUser(id);
		model.addAttribute("myPageUser", myPageUser);
		
		return "/user/myPage/myPageUser";
	}
	
	@GetMapping("/myPageGame")
	public String myPageGame() {
		
		return "/user/myPage/myPageGame";
	}
	
	@GetMapping("/myPageWishlist")
	public String myPageWishlist() {
		
		return "/user/myPage/myPageWishlist";
	}
	
	@GetMapping("/myPageReview")
	public String myPageReview() {
		
		return "/user/myPage/myPageReview";
	}
	
	@GetMapping("/myPageRefundPayment")
	public String myPageRefundPayment() {
		
		return "/user/myPage/myPageRefundPayment";
	}
	
	@GetMapping("/myPageCommunity")
	public String myPageCommunity() {
		
		return "/user/myPage/myPageCommunity";
	}
	
	@GetMapping("/myPageEmoticon")
	public String myPageEmoticon() {
		
		return "/user/myPage/myPageEmoticon";
	}
	
	@GetMapping("/myPageBoard")
	public String myPageBoard() {
		
		return "/user/myPage/myPageBoard";
	}
	
	@GetMapping("/myPageInquiry")
	public String myPageInquiry() {
		
		return "/user/myPage/myPageInquiry";
	}
	
	@GetMapping("/myPageEvent")
	public String myPageEvent() {
		
		return "/user/myPage/myPageEvent";
	}
}
