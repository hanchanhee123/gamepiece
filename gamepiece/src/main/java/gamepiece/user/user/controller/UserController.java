package gamepiece.user.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("user.userController")
@RequestMapping("/user")
public class UserController {

	@GetMapping("/myPageUser")
	public String myPageUser() {
		
		return "/user/user/myPageGame";
	}
}
