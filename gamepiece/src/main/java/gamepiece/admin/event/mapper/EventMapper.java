package gamepiece.admin.event.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.event.domain.Event;

@Mapper
public interface EventMapper {

	List<Event> getEventList();
	List<Event> getEventParticipant(String evCd);
	List<Event> getEventDetail(String evCd);
	int addEvent(Event event);
	
}
