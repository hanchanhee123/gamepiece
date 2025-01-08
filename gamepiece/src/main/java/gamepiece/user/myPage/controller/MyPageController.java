package gamepiece.user.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class MyPageController {

	@GetMapping("/myPageUser")
	public String myPageUser() {
		
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
