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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.user.event.domain.Event;
import gamepiece.user.event.service.EventService;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller("userEventController")
@RequestMapping("/event")
@Slf4j
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;

	@GetMapping("/progressEvent")
	public String getProgressEvent(Pageable pageable, Model model) {

		var pageInfo = eventService.getProgressEvent(pageable);

		List<Event> eventList = pageInfo.getContents();

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
	
	@GetMapping("/searchList")
	public String getSearchListView(@RequestParam(value = "searchValue") String searchValue, Model model,
			Pageable pageable) {

		PageInfo<Event> pageInfo = eventService.searchList(searchValue, pageable);

		List<Event> eventList = pageInfo.getContents();

		eventList.forEach(list -> {
			list.setEvStatus(eventService.getEventsWithStatus(list.getEvCd()));
		});

		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();

		model.addAttribute("searchValue", searchValue);
		model.addAttribute("eventList", eventList);
		model.addAttribute("search", "searching");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);

		return "user/event/progressEvent";

	}
	
	@PostMapping("/searchList")
	public String searchListView(@RequestParam(value = "searchValue") String searchValue, Model model,
			Pageable pageable) {

		PageInfo<Event> pageInfo = eventService.searchList(searchValue, pageable);

		List<Event> eventList = pageInfo.getContents();

		eventList.forEach(list -> {
			list.setEvStatus(eventService.getEventsWithStatus(list.getEvCd()));
		});

		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();

		model.addAttribute("searchValue", searchValue);
		model.addAttribute("eventList", eventList);
		model.addAttribute("search", "searching");
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

		var pageInfo = eventService.getEventWinnerList(pageable);

		List<Event> eventList = pageInfo.getContents();

		log.info("event : {}", eventList);
		
		model.addAttribute("eventList", eventList);
		model.addAttribute("currentPage", pageInfo.getCurrentPage());
		model.addAttribute("startPageNum", pageInfo.getStartPageNum());
		model.addAttribute("endPageNum", pageInfo.getEndPageNum());
		model.addAttribute("lastPage", pageInfo.getLastPage());

		return "user/event/winnerList";
	}

	@GetMapping("/searchWinnerList")
	public String getSearchwinnerListView(@RequestParam(value="searchValue") String searchValue, Model model,
			Pageable pageable) {
	
		PageInfo<Event> pageInfo = eventService.searchWinnerList(searchValue, pageable);
		
		List<Event> eventWinnerList = pageInfo.getContents();
		
		log.info("event : {}", eventWinnerList);
		
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("eventList", eventWinnerList);
		model.addAttribute("search", "searching");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		
		
		return "user/event/winnerList";

	}
	
	@PostMapping("/searchWinnerList")
	public String searchwinnerListView(@RequestParam(value="searchValue") String searchValue, Model model,
			Pageable pageable) {
	
		PageInfo<Event> pageInfo = eventService.searchWinnerList(searchValue, pageable);
		
		List<Event> eventWinnerList = pageInfo.getContents();
		
		log.info("event : {}", eventWinnerList);
		
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("eventList", eventWinnerList);
		model.addAttribute("search", "searching");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		
		
		return "user/event/winnerList";

	}
	
	@PostMapping("/winner/detail")
	@ResponseBody
	public List<Event> getWinnerInfoByEvCd(@RequestParam("evCd") String evCd) {

		List<Event> eventList = eventService.getWinnerInfoByEvCd(evCd);

		return eventList;
	}

	@GetMapping("/eventDetail")
	public String eventDetail(@RequestParam("evCd") String evCd, String evStatus, Model model, HttpSession session) {

		String loginId = (String) session.getAttribute("SID");

		List<Event> eventDetail = eventService.eventDetail(evCd);

		model.addAttribute("eventDetail", eventDetail);
		model.addAttribute("loginId", loginId);
		model.addAttribute("status", evStatus);

		return "user/event/eventDetail";
	}

	@PostMapping("/eventDetail")
	@ResponseBody
	public int getEventDetail(@RequestParam("evCd") String evCd, Model model, HttpSession session) {

		String loginId = (String) session.getAttribute("SID");

		List<Event> eventDetail = eventService.eventDetail(evCd);
		int getParticipations = eventService.getParticipations(evCd, loginId);

		model.addAttribute("eventDetail", eventDetail);
		model.addAttribute("getParticipations", getParticipations);

		return getParticipations;
	}

	@GetMapping("/insertParticipant")
	public String insertParticipant(Event event, Model model, RedirectAttributes reAttr) {

		eventService.insertParticipant(event);

		reAttr.addAttribute("evCd", event.getEvCd());
		reAttr.addAttribute("evStatus", event.getEvStatus());

		return "redirect:/event/eventDetail";
	}

}
