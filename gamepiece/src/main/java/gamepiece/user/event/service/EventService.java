package gamepiece.user.event.service;

import gamepiece.user.event.domain.Event;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;

public interface EventService {
	
	PageInfo<Event> getProgressEvent(Pageable pageable);
	
	String getEventsWithStatus(String evCd);
}
