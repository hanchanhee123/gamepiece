package gamepiece.user.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.user.event.domain.Event;
import gamepiece.user.event.mapper.UserEventMapper;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service("userEventService")
public class EventServiceImpl implements EventService {

	private final UserEventMapper userEventMapper;
	
	@Override
	public List<Event> progressEvent() {
		
		return userEventMapper.progressEvent();
	}

}
