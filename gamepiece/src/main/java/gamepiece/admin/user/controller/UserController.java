package gamepiece.admin.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.user.domain.User;
import gamepiece.admin.user.service.UserService;
import gamepiece.util.Pageable;
import lombok.extern.slf4j.Slf4j;

@Controller("adminUserController")
@Slf4j
@RequestMapping("/admin/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(@Qualifier("adminUserService") UserService userService) {
		this.userService = userService;
	}

	// 전체 회원정보 조회
	@GetMapping("/allUserInfo")
	public String getAllUserInfo(Pageable pageable, Model model) {
		
		var pageInfo = userService.getAllUserInfo(pageable);
		
		List<User> allUserInfo = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		log.info("allUserInfo : {}", allUserInfo);
		
		model.addAttribute("allUserInfo", allUserInfo);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		
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
	public String getRemoveUserInfo(Pageable pageable, Model model) {
		
		var pageInfo = userService.getRemoveUserInfo(pageable);
		
		List<User> removeUserInfo = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		log.info("removeUserInfo : {}", removeUserInfo);
		
		model.addAttribute("removeUserInfo", removeUserInfo);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);

		var authrtInfo = userService.getAuthrtInfo();
		model.addAttribute("authrtInfo", authrtInfo);
		
		return "admin/user/removeUserInfo";
	}
	
	// 휴면 회원정보 조회
	@GetMapping("/dormancyUserInfo")
	public String getDormancyUserInfo(Pageable pageable, Model model) {
		
		var pageInfo = userService.getDormancyUserInfo(pageable);
		
		List<User> dormancyUserInfo = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		log.info("dormancyUserInfo : {}", dormancyUserInfo);
		
		model.addAttribute("dormancyUserInfo", dormancyUserInfo);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		
		return "admin/user/dormancyUserInfo";
	}
	
	// 회원 로그인내역 조회
	@GetMapping("/userLoginlog")
	public String getUserLoginLog(Pageable pageable, Model model) {
		
		var pageInfo = userService.getUserLoginLog(pageable);
		
		List<User> userLoginlog = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		log.info("userLoginlog : {}", userLoginlog);
		
		model.addAttribute("userLoginlog", userLoginlog);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		
		log.info("pageInfo : {}", pageInfo);
		
		return "admin/user/userLoginlog";
	}
	
	@GetMapping("/removeUser")
	public String removeUser(String id) {
		
		userService.removeUser(id);
		
		return "redirect:/admin/user/allUserInfo";
	}
}
