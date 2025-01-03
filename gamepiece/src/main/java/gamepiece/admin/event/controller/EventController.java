package gamepiece.admin.event.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/removeEvent")
	public String RemoveEvent(Model model) {
		
		model.addAttribute("title", "이벤트목록 삭제");
		
		return "admin/event/removeEvent";
	}
	
	@GetMapping("/eventDetail")
	public String EventDetail(@RequestParam String evCd, Model model) {
		
		List<Event> eventDetail = eventService.getEventDetail(evCd);
		List<Event> eventParticipant = eventService.getEventParticipant(evCd);
		
		model.addAttribute("title", "이벤트 상세");
		model.addAttribute("eventDetail", eventDetail);
		model.addAttribute("eventParticipant", eventParticipant);
		return "admin/event/eventDetail";
	}
	
	@GetMapping("/eventWinnerList")
	public String getWinnerList(Model model) {
		
		List<Event> eventList = eventService.getEventList();
		
		model.addAttribute("title", "이벤트당첨자 목록");
		model.addAttribute("eventList", eventList);
		
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
	
	@PostMapping("/write")
	public String addEvent(Event event) {
		
		eventService.addEvent(event);
		
		return "redirect:/admin/event/eventList";
		
	}
	
	@PostMapping("/modify")
	public String modifyEvent(Event event,
							   RedirectAttributes reAttr) {
		
		eventService.modifyEvent(event);
		
		reAttr.addAttribute("evCd", event.getEvCd());
		
		return "redirect:/admin/event/eventList";
	}
	
	@GetMapping("/modify")
	public String modifyEventView(@RequestParam(name="evCd") String evCd, Model model) {
		
		var eventList = eventService.getEventList();
		Event eventInfo = eventService.getEventInfoById(evCd);
		
		model.addAttribute("title", "회원수정");
		model.addAttribute("eventList", eventList);
		model.addAttribute("eventInfo", eventInfo);
		
		return "admin/event/modifyEvent";
	}
}




