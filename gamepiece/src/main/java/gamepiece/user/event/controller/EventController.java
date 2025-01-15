package gamepiece.user.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gamepiece.user.event.domain.Event;
import gamepiece.user.event.service.EventService;
import gamepiece.util.Pageable;
import lombok.extern.slf4j.Slf4j;

@Controller("userEventController")
@RequestMapping("/event")
@Slf4j
public class EventController {

	private final EventService eventService;
	
	public EventController(@Qualifier("userEventService") EventService eventService) {
		this.eventService = eventService;
	}
	
	
	@GetMapping("/progressEvent")
	public String getProgressEvent(Pageable pageable, Model model) {
		
		var pageInfo = eventService.getProgressEvent(pageable);
		
		List<Event> eventList = pageInfo.getContents();
		System.out.println(pageable);
		
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
	
	@GetMapping("/endEvent")
	public String getEndEvent(Pageable pageable, Model model) {
		
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
		
		return "user/event/endEvent";
	}
	
	@GetMapping("/winnerList")
	public String getWinnerList(Pageable pageable, Model model) {
		/*
		 * if (evCd == null || evCd.isEmpty()) { evCd = "DEFAULT_EVENT"; // 기본값
		 * log.warn("Event Code was null, using default: {}", evCd); }
		 */

	    var pageInfo = eventService.getProgressEvent(pageable);
		/* List<Event> getEventWinner = eventService.getEventWinner(evCd); */
	    
	    List<Event> eventList = pageInfo.getContents();
	    eventList.forEach(list -> {
	        list.setEvStatus(eventService.getEventsWithStatus(list.getEvCd()));
	    });

	    model.addAttribute("eventList", eventList);
		/* model.addAttribute("getEventWinner", getEventWinner); */
	    model.addAttribute("currentPage", pageInfo.getCurrentPage());
	    model.addAttribute("startPageNum", pageInfo.getStartPageNum());
	    model.addAttribute("endPageNum", pageInfo.getEndPageNum());
	    model.addAttribute("lastPage", pageInfo.getLastPage());

	    return "user/event/winnerList";
	}
	
	@PostMapping("/winner/detail")
	@ResponseBody
	public List<Event> getWinnerInfoByEvCd(@RequestParam("evCd") String evCd) {
		log.info("Event evCd: {}", evCd);
		List<Event> eventList = eventService.getWinnerInfoByEvCd(evCd);
		
		log.info("eventList {}",eventList);
		return eventList;
	}
	
	@GetMapping("/eventDetail")
	public String eventDetail(@RequestParam("evCd") String evCd, Model model) {
		
		
		List<Event> eventDetail = eventService.eventDetail(evCd);
		List<Event> getParticipations = eventService.getParticipations(evCd);
		
		model.addAttribute("eventDetail", eventDetail);
		model.addAttribute("getParticipations", getParticipations);
		
		return "user/event/eventDetail";
	}
}
