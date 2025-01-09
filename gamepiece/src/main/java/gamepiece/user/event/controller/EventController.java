package gamepiece.user.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gamepiece.user.event.domain.Event;
import gamepiece.user.event.service.EventService;
import gamepiece.util.Pageable;

@Controller("userEventController")
@RequestMapping("/event")
public class EventController {

	private final EventService eventService;
	
	public EventController(@Qualifier("userEventService") EventService eventService) {
		this.eventService = eventService;
	}
	
	
	@GetMapping("/progressEvent")
	public String getProgressEvent(Pageable pageable, Model model) {
		
		var pageInfo = eventService.getProgressEvent(pageable);
		
		List<Event> eventList = pageInfo.getContents();
		System.out.println(eventList);
		
		eventList.forEach(list -> {
			list.setEvStatus(eventService.getEventsWithStatus(list.getEvCd()));
		});
		
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		
		model.addAttribute("eventList", eventList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		
		return "user/event/progressEvent";
	}
}
