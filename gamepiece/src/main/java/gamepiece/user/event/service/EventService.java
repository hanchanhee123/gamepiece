package gamepiece.user.event.service;

import java.util.List;

import gamepiece.user.event.domain.Event;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface EventService {
	
	PageInfo<Event> getProgressEvent(Pageable pageable);
	
	PageInfo<Event> getEndEvent(Pageable pageable);
	
	String getEventsWithStatus(String evCd);

	PageInfo<Event> getWinnerList(Pageable pageable, String evCd);

	List<Event> getEventWinner(String evCd);
	
	List<Event> getWinnerInfoByEvCd(String evCd);

	List<Event> eventDetail(String evCd);

	List<Event> getParticipations(String evCd);

}
