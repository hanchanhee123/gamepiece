package gamepiece.admin.event.service;

import java.util.List;

import gamepiece.admin.event.domain.Event;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface EventService {


	// 이벤트목록
	PageInfo<Event> getEventList(Pageable pageable);
	
	List<Event> getEventParticipant(String evCd);

	List<Event> getEventDetail(String evCd);

	void addEvent(Event event);
	
	/* void addEventWinnerList(String evCd, Event event); */

	// 특정
	Event getEventInfoById(String evCd);

	//특정 회원 수정
	void modifyEvent(Event event);
	
	String getEventsWithStatus(String evCd);

	List<Event> getEventWinner(String evCd);

	void removeEvent(String evCd);

	PageInfo<Event> searchList(String searchValue, String searchCate, Pageable pageable);

	PageInfo<Event> getEventWinnerList(Pageable pageable);

	List<Event> getEventsList();

	List<Event> getWinnerListInfo(String evCd);
	
	void addEventWinnerList(Event event);
	
}