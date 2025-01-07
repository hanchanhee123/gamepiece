package gamepiece.user.event.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gamepiece.user.event.service.EventService;
import lombok.RequiredArgsConstructor;

@Controller("user.eventController")
@RequiredArgsConstructor
@RequestMapping("/user/event")
public class EventController {

	private final EventService eventService;
	
	
	
	@GetMapping("/progressEvent")
	public String progressEvent(Model model) {
		
		return "user/event/progressEvent";
	}
}
