package gamepiece.user.user.controller;

import java.util.HashMap;
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

	// 로그인창으로 이동
	@GetMapping("/login")
	public String login(@RequestParam(value="msg", required = false) String msg, Model model) {
		
		log.info(msg);
		if(msg != null) model.addAttribute("msg", msg);
		
		return "user/user/login";
	}
	
	// 로그인 로직
	@PostMapping("/loginPro")
	public String loginPro(@RequestParam(value = "id") String id,
						   @RequestParam(value = "userPswd") String userPswd,
						   RedirectAttributes reAttr, HttpSession session) {
		String viewName ="redirect:/login";
		
		Map<String, Object> resultMap = userService.checkUser(id, userPswd);
		boolean isMatched = (boolean) resultMap.get("isMatched");
		if(isMatched) {
			User userInfo = (User) resultMap.get("userInfo");
			
			log.info("userInfo : {}", userInfo);
			
			session.setAttribute("SID", userInfo.getId());
			session.setAttribute("SNAME", userInfo.getUserNm());
			
			viewName = "redirect:/";
			
			String sid = (String) session.getAttribute("SID");
			
			// 사용자 로그 확인 (오늘 날짜)
			int findLoginLog = userService.findLoginLog(sid);
			
			if(findLoginLog > 0) {
				// 로그인 로그 업데이트
				userService.modifyLoginLog(sid);
			} else {
				// 로그인 로그 삽입
				userService.loginLog(sid);
			}
			
		}else {
			reAttr.addAttribute("msg", "사용자의 정보가 일치하지 않습니다.");
		}
		
		return viewName;
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 회원가입
	@GetMapping("/addUser")
	public String addUser() {
		
		return "user/user/addUser";
	}
	
	// 회원가입 로직
	@PostMapping("/addUserPro")
	public String addUserPro(User user) {
		
		userService.addUser(user);
		
		return "user/user/login";
	}
	
	// 중복 아이디 체크
	@PostMapping("/checkId")
	@ResponseBody
	public boolean checkId(@RequestParam(name="id") String id) {
		
		boolean isDuplicate = false;
		isDuplicate = userService.checkId(id);
		System.out.println(isDuplicate);
		
		return isDuplicate;
	}
	
	// 아이디 찾기
	@GetMapping("/findUserId")
	public String findUserId() {
		
		return "user/user/findUserId";
	}
	
	// 아이디 찾기 로직
	@PostMapping("/findUserIdPro")
	@ResponseBody
	public Map<String, String> findUserIdPro(@RequestParam(name="userNm") String userNm,
								@RequestParam(name="userEmlAddr") String userEmlAddr,
								@RequestParam(name="userTelno") String userTelno) {
		
		String findUserId = userService.findUserIdPro(userNm, userEmlAddr, userTelno);
		
		Map<String, String> response = new HashMap<>();
		if(findUserId != null) {
			response.put("status", "success");
	        response.put("findUserId", findUserId);
	        log.info(findUserId);
	    } else {
	        response.put("status", "fail");
	        response.put("message", "아이디를 찾을 수 없습니다.");
	    }
		
		return response;
	}
	
	// 비밀번호 찾기
	@GetMapping("/findUserPswd")
	public String findUserPswd() {
		
		return "user/user/findUserPswd";
	}
	
	// 비밀번호 찾기 로직
	@PostMapping("/findUserPswdPro")
	@ResponseBody
	public Map<String, String> findUserPswdPro(@RequestParam(name="id") String id,
								@RequestParam(name="userNm") String userNm,
								@RequestParam(name="userEmlAddr") String userEmlAddr,
								@RequestParam(name="userTelno") String userTelno) {
		
		String findUserPswd = userService.findUserPswdPro(id, userNm, userEmlAddr, userTelno);
		
		Map<String, String> response = new HashMap<>();
		if(findUserPswd != null) {
			response.put("status", "success");
	        response.put("findUserPswd", findUserPswd);
	        log.info(findUserPswd);
	    } else {
	        response.put("status", "fail");
	        response.put("message", "비밀번호를 찾을 수 없습니다.");
	    }
		
		return response;
	}
	
	// 회원 정보 수정
	@PostMapping("/modifyUser")
	public String modifyUser(User user, Model model) {
		
		userService.modifyUser(user);
		
		return "redirect:/myPageUser";
	}
	
	// 회원 탈퇴
	@GetMapping("/removeUser")
	public String removeUser(HttpSession session) {
		
		String id = (String) session.getAttribute("SID");
		
		userService.removeUser(id);
		
		session.invalidate();
		
		return "redirect:/";
	}
	
}
