package gamepiece.user.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gamepiece.user.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserHomeController {
	
	public final UserService userService;
	
	@GetMapping(value = {"","/"})
	public String getHomeView() {
		
		return "user/infomationpage";
	}
	@GetMapping(value = "/main")
	public String HomeView(HttpSession session,
			Model model) {
		
		String id = (String) session.getAttribute("SID");
		
		String avatar = userService.getUserAvatar(id);
		
		model.addAttribute("avatar", avatar);
		model.addAttribute("userId", id);
		
		return "user/index";
	}
}
