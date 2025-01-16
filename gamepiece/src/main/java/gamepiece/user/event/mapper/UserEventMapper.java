package gamepiece.user.event.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gamepiece.user.event.domain.Event;
import gamepiece.util.Pageable;

@Mapper
public interface UserEventMapper {

	int getCntEventList();

	List<Event> getProgressEvent(Pageable pageable);

	String getEventListWithStatus(String evCd);

	List<Event> getEndEvent(Pageable pageable);

	List<Event> getWinnerList(Pageable pageable, String evCd);

	List<Event> getEventWinner(String evCd);

	List<Event> getWinnerInfoByEvCd(String evCd);

	List<Event> eventDetail(String evCd);

	int getParticipations(Map resultMap);

	void insertParticipant(Event event);


}
