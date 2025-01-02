package gamepiece.admin.event.service;

import java.util.List;

import gamepiece.admin.event.domain.Event;

public interface EventService {


	// 이벤트목록
	List<Event> getEventList();
}
