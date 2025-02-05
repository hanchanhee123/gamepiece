package gamepiece.admin.user.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.user.service.UserService;
import gamepiece.admin.user.domain.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class LoginController {

	private final UserService userService;
	
	// 로그인창으로 이동
	@GetMapping("/login")
	public String login(@RequestParam(value="msg", required = false) String msg, Model model) {
		
		log.info("admin login 화면 이동 msg : {}", msg);
		if(msg != null) model.addAttribute("msg", msg);
		
		return "admin/user/login";
	}
	
	// 로그인 로직
	@PostMapping("/loginPro")
	public String loginPro(@RequestParam(value = "id") String id,
						   @RequestParam(value = "userPswd") String userPswd,
						   RedirectAttributes reAttr, HttpSession session) {
		String viewName ="redirect:/admin/login";
		
		Map<String, Object> resultMap = userService.checkUser(id, userPswd);
		boolean isMatched = (boolean) resultMap.get("isMatched");
		if(isMatched) {
			User userInfo = (User) resultMap.get("userInfo");
			
			log.info("userInfo : {}", userInfo);
			
			session.setAttribute("SID", userInfo.getId());
			session.setAttribute("SNAME", userInfo.getUserNm());
			session.setAttribute("SGRD", userInfo.getAuthrtCd());
			
			viewName = "redirect:/admin/user/allUserInfo";
			
			String sid = (String) session.getAttribute("SID");
			
		}else {
			reAttr.addAttribute("msg", "사용자의 정보가 일치하지 않거나, 관리자 권한이 아닙니다.");
		}
		
		return viewName;
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/admin/login";
	}
	
	
}
