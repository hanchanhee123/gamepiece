package gamepiece.admin.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.admin.event.domain.Event;
import gamepiece.admin.event.mapper.EventMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service("eventService")
public class EventServiceImpl implements EventService {

	
	private final EventMapper eventMapper;
	
	@Override
	public PageInfo<Event> getEventList(Pageable pageable){
	
		int rowCnt = eventMapper.getCntEventList();
		List<Event> eventList = eventMapper.getEventList(pageable); 
		return new PageInfo<>(eventList, pageable, rowCnt);
	
	}
	
	public List<Event> getEventParticipant(String evCd){
		return eventMapper.getEventParticipant(evCd);
	}
	
	public List<Event> getEventDetail(String evCd){
		return eventMapper.getEventDetail(evCd);
	}

	@Override
	public void addEvent(Event event) {
		event.setAdminId("id01");
		eventMapper.addEvent(event);
		
	}

	@Override
	public Event getEventInfoById(String evCd) {

		return eventMapper.getEventInfoById(evCd);
	}

	@Override
	public void modifyEvent(Event event) {
	
		eventMapper.modifyEvent(event);	
	}

	@Override
	public String getEventsWithStatus(String evCd) {
		return eventMapper.getEventListWithStatus(evCd);
	}

	@Override
	public List<Event> getEventWinner(String evCd) {
		
		return eventMapper.getEventWinner(evCd);
	}
}
