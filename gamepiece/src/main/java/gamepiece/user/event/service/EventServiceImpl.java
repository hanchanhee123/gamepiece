package gamepiece.user.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.user.event.domain.Event;
import gamepiece.user.event.mapper.UserEventMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service("userEventService")
public class EventServiceImpl implements EventService {

	private final UserEventMapper userEventMapper;

	@Override
	public PageInfo<Event> getProgressEvent(Pageable pageable) {

		int rowCnt = userEventMapper.getCntEventList();
		List<Event> eventList = userEventMapper.getProgressEvent(pageable); 
		return new PageInfo<>(eventList, pageable, rowCnt);
	}

	@Override
	public String getEventsWithStatus(String evCd) {

		return userEventMapper.getEventListWithStatus(evCd);
	}

	
}
