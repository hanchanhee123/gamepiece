package gamepiece.user.home.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gamepiece.user.event.domain.Event;
import gamepiece.user.event.service.EventService;
import gamepiece.user.user.service.UserService;
import gamepiece.util.Pageable;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserHomeController {
	
	public final UserService userService;
	private final EventService eventService;
	
	@GetMapping(value = {"","/"})
	public String getHomeView() {
		
		return "user/infomationpage";
	}
	@GetMapping(value = "/main")
	public String HomeView(Pageable pageable, HttpSession session,
			Model model) {
		
		String id = (String) session.getAttribute("SID");
		var pageInfo = eventService.getProgressEvent(pageable);
		
		String avatar = userService.getUserAvatar(id);
		
		List<Event> eventList = pageInfo.getContents();

		eventList.forEach(list -> {
			list.setEvStatus(eventService.getEventsWithStatus(list.getEvCd()));
		});
		
		model.addAttribute("avatar", avatar);
		model.addAttribute("userId", id);
		model.addAttribute("eventList", eventList);
		
		return "user/index";
	}
}
