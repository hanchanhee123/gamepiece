package gamepiece.user.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.user.event.domain.Event;
import gamepiece.user.event.mapper.UserEventMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@RequiredArgsConstructor
@Service("userEventService")
@Slf4j
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

	@Override
	public PageInfo<Event> getEndEvent(Pageable pageable) {

		int rowCnt = userEventMapper.getCntEventList();
		List<Event> eventList = userEventMapper.getEndEvent(pageable); 
		return new PageInfo<>(eventList, pageable, rowCnt);
	}
	
	@Override
	public PageInfo<Event> getWinnerList(Pageable pageable, String evCd) {

		int rowCnt = userEventMapper.getCntEventList();
		List<Event> eventList = userEventMapper.getWinnerList(pageable, evCd); 
		return new PageInfo<>(eventList, pageable, rowCnt);
	}

	@Override
	public List<Event> getEventWinner(String evCd) {

		return userEventMapper.getEventWinner(evCd);
	}

	@Override
	public List<Event> getWinnerInfoByEvCd(String evCd) {
		
		return userEventMapper.getWinnerInfoByEvCd(evCd);
	}
}
