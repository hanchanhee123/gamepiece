package gamepiece.user.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.user.event.domain.Event;
import gamepiece.user.event.mapper.EventMapper;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service("user.eventService")
public class EventServiceImpl implements EventService {

	private final EventMapper eventMapper;
	
	@Override
	public List<Event> progressEvent() {
		
		return eventMapper.progressEvent();
	}

}
