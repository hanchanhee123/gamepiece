package gamepiece.user.event.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gamepiece.user.event.service.EventService;
import lombok.RequiredArgsConstructor;

@Controller("userEventController")
@RequestMapping("/user/event")
public class EventController {

	private final EventService eventService;
	
	public EventController(@Qualifier("userEventService") EventService eventService) {
		this.eventService = eventService;
	}
	
	
	@GetMapping("/progressEvent")
	public String progressEvent(Model model) {
		
		return "user/event/progressEvent";
	}
}
