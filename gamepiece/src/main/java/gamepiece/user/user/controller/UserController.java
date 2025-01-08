package gamepiece.user.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/myPageUser")
	public String myPageUser() {
		
		return "/user/user/myPageUser";
	}
	
	@GetMapping("/myPageGame")
	public String myPageGame() {
		
		return "/user/user/myPageGame";
	}
	
	@GetMapping("/myPageCommunity")
	public String myPageCommunity() {
		
		return "/user/user/myPageCommunity";
	}
	
	@GetMapping("/myPageEvent")
	public String myPageEvent() {
		
		return "/user/user/myPageEvent";
	}
}
