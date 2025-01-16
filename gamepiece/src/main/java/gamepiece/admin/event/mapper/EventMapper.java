package gamepiece.admin.event.mapper;

import java.util.List;
import java.util.Map;

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
	List<Event> getSearchList(Map<String, Object> searchMap);
	int getSearchCntEventList(Map<String, Object> searchMap);
	List<Event> getEventWinnerList(Pageable pageable);
	/* int addEventWinnerList(String evCd, Event event); */
	List<Event> getEventsList();
	void addEventWinnerList(Event event);
	List<Event> getWinnerListInfo(String evNm);
	List<Event> EventWinnerListDetail(String evCd);
	Event getEventWinnerListInfoInfoById(String evCd);
	void modifyEventWinnerList(Event event);
	void removeEventWinnerList(String evCd);
	List<Event> selectEventWinners(String evCd, int evWinnersNum);
	void updateWinners(Event event);
	int countWinner(Map<String, Object> resultMap);
}
