package gamepiece.admin.user.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.user.domain.User;
import gamepiece.admin.user.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// 전체 회원정보 조회
	@GetMapping("/allUserInfo")
	public String getAllUserInfo(Model model) {
		
		List<User> allUserInfo = userService.getAllUserInfo();
		
		model.addAttribute("allUserInfo", allUserInfo);
		System.out.println("전체 회원정보 조회 : " + allUserInfo);
		
		return "admin/user/allUserInfo";
	}
	
	// 회원 상세정보 조회
	@GetMapping("/userInfo")
	public String getUserInfo(@RequestParam(name="id") String id, Model model) {
		
		User userInfo = userService.getUserInfo(id);
		var authrtInfo = userService.getAuthrtInfo();
		
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("authrtInfo", authrtInfo);
		System.out.println("회원 상세정보 조회 : " + userInfo);
		System.out.println("회원 권한 : " + authrtInfo);
		
		return "admin/user/userInfo";
	}
	
	// 회원 상세정보 수정
	@GetMapping("/modifyUserInfo")
	public String getModifyUserInfo(User user, RedirectAttributes reAttr) {
		
		userService.modifyUserInfo(user);
		
		reAttr.addAttribute("id", user.getId());
		
		return "redirect:/admin/user/allUserInfo";
	}
	
	// 탈퇴 회원정보 조회
	@GetMapping("/removeUserInfo")
	public String getRemoveUserInfo(Model model) {
		List<User> removeUserInfo = userService.getRemoveUserInfo();
		
		model.addAttribute("removeUserInfo", removeUserInfo);
		System.out.println("탈퇴 회원정보 조회 : " + removeUserInfo);
		
		return "admin/user/removeUserInfo";
	}
	
	// 휴면 회원정보 조회
	@GetMapping("/dormancyUserInfo")
	public String getDormancyUserInfo(Model model) {
		
		List<User> dormancyUserInfo = userService.getDormancyUserInfo();
		
		model.addAttribute("dormancyUserInfo", dormancyUserInfo);
		System.out.println("휴면 회원정보 조회 : " + dormancyUserInfo);
		
		return "admin/user/dormancyUserInfo";
	}
	
	// 회원 로그인내역 조회
	@GetMapping("/userLoginlog")
	public String getUserLoginLog(Model model) {
		
		List<User> userLoginlog = userService.getUserLoginLog();
		
		model.addAttribute("userLoginlog", userLoginlog);
		System.out.println("회원 로그인내역 조회 : " + userLoginlog);
		
		return "admin/user/userLoginlog";
	}
}
