package gamepiece.user.event.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.util.ParameterMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.common.mapper.CommonMapper;
import gamepiece.file.mapper.FileMapper;
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
	/* private final FileMapper fileMapper; */
	private final CommonMapper commonMapper;
	
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

		int rowCnt = userEventMapper.getCntWinnerList();
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

	@Override
	public List<Event> eventDetail(String evCd) {
		
		return userEventMapper.eventDetail(evCd);
	}

	@Override
	public int getParticipations(String evCd, String loginId) {
		Map<String, Object> resultMap = new ParameterMap<String, Object>();
		resultMap.put("id", loginId);
		resultMap.put("evCd", evCd);
		
		return userEventMapper.getParticipations(resultMap);
	}

	@Override
	public void insertParticipant(Event event) {
	
		String evpNo = commonMapper.getPrimaryKey("evp_", "event_participations", "evp_no");
		
		event.setEvpNo(evpNo);
		
		userEventMapper.insertParticipant(event);
	}

	@Override
	public PageInfo<Event> getEventWinnerList(Pageable pageable) {
		int rowCnt = userEventMapper.getCntWinnerList();
		List<Event> eventList = userEventMapper.getEventWinnerList(pageable); 
		return new PageInfo<>(eventList, pageable, rowCnt);
	}

	@Override
	public PageInfo<Event> searchList(String searchValue, Pageable pageable) {
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchValue", searchValue);
		searchMap.put("pageable", pageable);
		int rowCnt = userEventMapper.getSearchCntEventList(searchMap);
		List<Event> eventList = userEventMapper.getSearchList(searchMap);
		
		return new PageInfo<>(eventList, pageable, rowCnt);
	}

	@Override
	public PageInfo<Event> searchWinnerList(String searchValue, Pageable pageable) {

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchValue", searchValue);
		searchMap.put("pageable", pageable);
		int rowCnt = userEventMapper.getSearchCntWinnerList(searchMap);
		List<Event> eventList = userEventMapper.getSearchWinnerList(searchMap);
		
		return new PageInfo<>(eventList, pageable, rowCnt);
	}
}
