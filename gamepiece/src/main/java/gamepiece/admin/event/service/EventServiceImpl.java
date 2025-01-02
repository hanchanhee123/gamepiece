package gamepiece.admin.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamepiece.admin.event.domain.Event;
import gamepiece.admin.event.mapper.EventMapper;

@Service("eventService")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventMapper eventMapper;
	public List<Event> getEventList(){
	return eventMapper.getEventList();
	
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
}
