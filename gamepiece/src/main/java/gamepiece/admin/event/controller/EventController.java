package gamepiece.admin.event.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.event.domain.Event;
import gamepiece.admin.event.service.EventService;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/event")
public class EventController {
	
	private final EventService eventService;
	
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@GetMapping("/eventList")
	public String getEventList(Pageable pageable, Model model) {
			
		var pageInfo = eventService.getEventList(pageable);
		
		List<Event> eventList = pageInfo.getContents();
		
		eventList.forEach(list -> {
			list.setEvStatus(eventService.getEventsWithStatus(list.getEvCd()));
		});
		
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		
		model.addAttribute("title", "이벤트목록");
		model.addAttribute("eventList", eventList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
	
		return "admin/event/eventList";
	}
	
	@GetMapping("/eventWinnerList")
	public String getEventWinnerList(Pageable pageable, Model model) {
		
		var pageInfo = eventService.getEventWinnerList(pageable);
		
		List<Event> eventWinnerList = pageInfo.getContents();
		
		eventWinnerList.forEach(list -> {
			list.setEvStatus(eventService.getEventsWithStatus(list.getEvCd()));
		});
		
		log.info("list : {}", eventWinnerList);
		
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		
		model.addAttribute("title", "이벤트목록");
		model.addAttribute("eventWinnerList", eventWinnerList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		
		return "admin/event/eventWinnerList";
	}
	
	@GetMapping("/addEvent")
	public String AddEvent(Model model) {
		
		model.addAttribute("title", "이벤트목록 추가");
		
		return "admin/event/addEvent";
	}
	
	@GetMapping("/addEventWinnerList")
	public String addEventWinnerList(Model model) {
		
		List<Event> eventList = eventService.getEventsList();
		
		model.addAttribute("title", "이벤트당첨자리스트 추가");
		model.addAttribute("eventList", eventList);
		
		log.info("eventList {}",eventList);
		
		return "admin/event/addEventWinnerList";
	}
	
	@PostMapping("/winner/winners")
	@ResponseBody
	public List<Event> getWinnerListInfo(@RequestParam("evCd") String evCd){
		
		log.info("Event evCd: {}", evCd);
		
		List<Event> addEventList = eventService.getWinnerListInfo(evCd);
		
		log.info("eventList {}",addEventList);
		
		return addEventList;
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
	
	@GetMapping("/eventWinner")
	public String EventWinner(@RequestParam String evCd, Model model) {
		
		List<Event> eventDetail = eventService.getEventDetail(evCd);
		List<Event> getEventWinner = eventService.getEventWinner(evCd);
		
		log.info("getEventWinner : {}", getEventWinner);
		
		model.addAttribute("title", "이벤트 당첨자");
		model.addAttribute("eventDetail", eventDetail);
		model.addAttribute("getEventWinner", getEventWinner);
		return "admin/event/eventWinner";
	}
	
	@PostMapping("/listWrite")
	public String addEventWinnerList(Event event) {
		log.info("evCd : {}", event.getEvCd());
		
		eventService.addEventWinnerList(event);
		
		return "redirect:/admin/event/eventWinnerList";
		
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
		
		/* var eventList = eventService.getEventList(); */
		Event eventInfo = eventService.getEventInfoById(evCd);
		
		model.addAttribute("title", "회원수정");
		/* model.addAttribute("eventList", eventList); */
		model.addAttribute("eventInfo", eventInfo);
		
		return "admin/event/modifyEvent";
	}
	
	@GetMapping("/remove")
	public String removeEvent(String evCd) {
		
		eventService.removeEvent(evCd);
		
		return "redirect:/admin/event/eventList";
	}
	
	@PostMapping("/searchList")
	public String searchListView(@RequestParam(value="searchValue") String searchValue,
								 @RequestParam(value="searchCate", required=false, defaultValue="name") String searchCate,
								 Model model,
								 Pageable pageable) {
		PageInfo<Event> pageInfo = eventService.searchList(searchValue, searchCate, pageable);
		
		
		log.info("searchCate:{}, searchValue:{}", searchCate,searchValue);
		
		
		List<Event> eventList = pageInfo.getContents();
		eventList.forEach(list -> {
			list.setEvStatus(eventService.getEventsWithStatus(list.getEvCd()));
		});
		
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("searchCate", searchCate);
		model.addAttribute("eventList", eventList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		
		return "admin/event/eventList";
	}
}




