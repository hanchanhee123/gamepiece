package gamepiece.user.user.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.user.user.domain.User;
import gamepiece.user.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
	
	private final UserService userService;

	@GetMapping("/login")
	public String login(@RequestParam(value="msg", required = false) String msg, Model model) {
		
		log.info(msg);
		if(msg != null) model.addAttribute("msg", msg);
		
		return "user/user/login";
	}
	
	@PostMapping("/loginPro")
	public String loginPro(@RequestParam(value = "id") String id,
						   @RequestParam(value = "userPswd") String userPswd,
						   RedirectAttributes reAttr, HttpSession session) {
		String viewName ="redirect:/login";
		
		Map<String, Object> resultMap = userService.checkUser(id, userPswd);
		boolean isMatched = (boolean) resultMap.get("isMatched");
		if(isMatched) {
			User userInfo = (User) resultMap.get("userInfo");
			
			session.setAttribute("SID", userInfo.getId());
			session.setAttribute("SNAME", userInfo.getUserNm());
			
			viewName = "redirect:/";
		}else {
			reAttr.addAttribute("msg", "사용자의 정보가 일치하지 않습니다.");
		}
		
		return viewName;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("/addUser")
	public String addUser() {
		
		return "user/user/addUser";
	}
	
	@PostMapping("/addUserPro")
	public String addUserPro(User user) {
		
		userService.addUser(user);
		
		return "user/user/login";
	}
	
	@PostMapping("/checkId")
	@ResponseBody
	public boolean checkId(@RequestParam(name="id") String id) {
		
		boolean isDuplicate = false;
		isDuplicate = userService.checkId(id);
		System.out.println(isDuplicate);
		
		return isDuplicate;
	}
	
	@GetMapping("/findUserId")
	public String findUserId() {
		
		return "user/user/findUserId";
	}
	
	@GetMapping("/findUserPswd")
	public String findUserPswd() {
		
		return "user/user/findUserPswd";
	}
}
