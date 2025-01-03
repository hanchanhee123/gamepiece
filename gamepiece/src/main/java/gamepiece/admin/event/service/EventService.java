package gamepiece.admin.event.service;

import java.util.List;

import gamepiece.admin.event.domain.Event;

public interface EventService {


	// 이벤트목록
	List<Event> getEventList();
	
	List<Event> getEventParticipant(String evCd);

	List<Event> getEventDetail(String evCd);

	void addEvent(Event event);

	// 특정
	Event getEventInfoById(String evCd);

	//특정 회원 수정
	void modifyEvent(Event event);
	
	String getEventsWithStatus(String evCd);

	List<Event> getEventWinner(String evCd);
}