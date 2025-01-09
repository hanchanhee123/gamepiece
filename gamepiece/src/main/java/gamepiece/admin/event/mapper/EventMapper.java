package gamepiece.admin.event.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.admin.event.domain.Event;
import gamepiece.util.Pageable;

@Mapper
public interface EventMapper {

	List<Event> getEventList(Pageable pageable);
	List<Event> getEventParticipant(String evCd);
	List<Event> getEventDetail(String evCd);
	int addEvent(Event event);
	int modifyEvent(Event event);
	Event getEventInfoById(String evCd);
	String getEventListWithStatus(String evCd);
	List<Event> getEventWinner(String evCd);
	int getCntEventList();
	void removeEvent(String evCd);
}
