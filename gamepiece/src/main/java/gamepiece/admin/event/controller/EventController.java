package gamepiece.admin.event.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gamepiece.admin.event.domain.Event;
import gamepiece.admin.event.service.EventService;

@Controller
@RequestMapping("/admin/event")
public class EventController {
	
	private final EventService eventService;
	
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@GetMapping("/eventList")
	public String getEventsList(Model model) {
			
		List<Event> eventList = eventService.getEventList();
		
		model.addAttribute("title", "이벤트목록");
		model.addAttribute("eventList", eventList);
		return "admin/event/eventList";
	}
	
	@GetMapping("/addEvent")
	public String AddEvent(Model model) {
		
		model.addAttribute("title", "이벤트목록 추가");
		
		return "admin/event/addEvent";
	}
	
	@GetMapping("/modifyEvent")
	public String ModifyEvent(Model model) {
		
		model.addAttribute("title", "이벤트목록 수정");
		
		return "admin/event/modifyEvent";
	}
	
	@GetMapping("/removeEvent")
	public String RemoveEvent(Model model) {
		
		model.addAttribute("title", "이벤트목록 삭제");
		
		return "admin/event/removeEvent";
	}
	
	@GetMapping("/eventWinnerList")
	public String getWinnerList(Model model) {
		
		model.addAttribute("title", "이벤트당첨자 목록");
		
		return "admin/event/eventWinnerList";
	}
	
	@GetMapping("/addEventWinner")
	public String addWinner(Model model) {
		
		model.addAttribute("title", "이벤트당첨자 추가");
		
		return "admin/event/addEventWinner";
	}

	@GetMapping("/modifyEventWinner")
	public String modifyWinner(Model model) {
		
		model.addAttribute("title", "이벤트당첨자 수정");
		
		return "admin/event/modifyEventWinner";
	}

	@GetMapping("/removeEventWinner")
	public String removeWinner(Model model) {
		
		model.addAttribute("title", "이벤트당첨자 삭제");
		
		return "admin/event/removeEventWinner";
	}
}

