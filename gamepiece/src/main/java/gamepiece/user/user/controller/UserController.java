package gamepiece.user.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/login")
	public String login() {
		
		return "/user/user/login";
	}
	
	@GetMapping("/addUser")
	public String addUser() {
		
		return "/user/user/addUser";
	}
	
	@GetMapping("/findUserId")
	public String findUserId() {
		
		return "/user/user/findUserId";
	}
	
	@GetMapping("/findUserPw")
	public String findUserPw() {
		
		return "/user/user/findUserPw";
	}
}
