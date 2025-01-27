package gamepiece.admin.event.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import gamepiece.admin.event.domain.Event;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface EventService {


	// 이벤트목록
	PageInfo<Event> getEventList(Pageable pageable);
	
	List<Event> getEventParticipant(String evCd);

	List<Event> getEventDetail(String evCd);
	
	/* void addEventWinnerList(String evCd, Event event); */

	// 특정
	Event getEventInfoById(String evCd);

	//특정 회원 수정
	void modifyEvent(Event event, MultipartFile files);
	
	String getEventsWithStatus(String evCd);

	List<Event> getEventWinner(String evCd);

	void removeEvent(String evCd);

	PageInfo<Event> searchList(String searchValue, String searchCate, Pageable pageable);

	PageInfo<Event> getEventWinnerList(Pageable pageable);

	List<Event> getEventsList();

	List<Event> getWinnerListInfo(String evCd);
	
	void addEventWinnerList(Event event);

	List<Event> EventWinnerListDetail(String evCd);

	Event getEventWinnerListInfoInfoById(String evCd);

	void modifyEventWinnerList(Event event);

	void removeEventWinnerList(String evCd);

	List<Event> selectEventWinners(String evCd, int evWinnersNum);

	void updateWinners(Event event);

	int countWinner(String evCd, int evWinnersNum);

	PageInfo<Event> searchWinnerList(String searchValue, String searchCate, Pageable pageable);

	void addEvent(Event event, MultipartFile files);
	
}