package gamepiece.user.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserHomeController {
	
	@GetMapping(value = {"","/"})
	public String getHomeView() {
		
		return "user/index";
	}
}
