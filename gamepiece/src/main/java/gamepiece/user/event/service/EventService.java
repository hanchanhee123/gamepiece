package gamepiece.user.event.service;

import gamepiece.user.event.domain.Event;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface EventService {
	
	PageInfo<Event> getProgressEvent(Pageable pageable);
	
	PageInfo<Event> getEndEvent(Pageable pageable);
	
	String getEventsWithStatus(String evCd);

	PageInfo<Event> getWinnerList(Pageable pageable);
}
