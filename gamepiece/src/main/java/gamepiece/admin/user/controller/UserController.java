package gamepiece.admin.user.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamepiece.admin.user.domain.User;
import gamepiece.admin.user.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/allUserInfo")
	public String getAllUserInfo(Model model) {
		
		List<User> allUserInfo = userService.getAllUserInfo();
		
		model.addAttribute("allUserInfo", allUserInfo);
		System.out.println("전체 회원정보 조회 : " + allUserInfo);
		
		return "admin/user/allUserInfo";
	}
	
	@GetMapping("/UserInfo")
	public String getUserInfo(@RequestParam String id, Model model) {
		
		User userInfo = userService.getUserInfo(id);
		
		model.addAttribute("userInfo", userInfo);
		System.out.println("회원 상세정보 조회 : " + userInfo);
		
		return "admin/user/userInfo";
	}
	
	@GetMapping("/removeUserInfo")
	public String getRemoveUserInfo(Model model) {
		
		return "admin/user/removeUserInfo";
	}
	
	@GetMapping("/dormancyUserInfo")
	public String getDormancyUserInfo(Model model) {
		
		return "admin/user/dormancyUserInfo";
	}
	
	@GetMapping("/userLoginlog")
	public String getUserLoginLog(Model model) {
		
		return "admin/user/userLoginlog";
	}
}
