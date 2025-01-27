package gamepiece.admin.event.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.util.ParameterMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gamepiece.admin.event.domain.Event;
import gamepiece.admin.event.mapper.EventMapper;
import gamepiece.common.mapper.CommonMapper;
import gamepiece.file.dto.FileDto;
import gamepiece.file.mapper.FileMapper;
import gamepiece.file.util.FilesUtils;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

	
	private final EventMapper eventMapper;
	private final FileMapper fileMapper;
	private final CommonMapper commonMapper;
	private final FilesUtils fileUtils;
	
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
	public Event getEventInfoById(String evCd) {

		return eventMapper.getEventInfoById(evCd);
	}

	@Override
	public void modifyEvent(Event event, MultipartFile files) {
	
		if(files != null && !files.isEmpty()) {			
			FileDto newFileInfo = fileUtils.uploadFile(files);
			if(newFileInfo != null) {
				String fileIdx = event.getFileIdx();
				if(fileIdx != null && !"".equals(fileIdx)) {					
					FileDto fileInfo = fileMapper.getFileInfoByIdx(fileIdx);
					boolean isDelete = fileUtils.deleteFileByPath(fileInfo.getFilePath());
					if(isDelete) {
						newFileInfo.setFileIdx(fileIdx);
						fileMapper.modifyfile(newFileInfo);
					}
				}else {
					fileIdx = commonMapper.getPrimaryKey("file_", "files", "file_idx");
					newFileInfo.setFileIdx(fileIdx);
					fileMapper.addfile(newFileInfo);
				}
				
			}
		}
		
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

	@Override
	public void removeEvent(String evCd) {
		
		eventMapper.removeEvent(evCd);
		
	}

	@Override
	public PageInfo<Event> searchList(String searchValue, String searchCate, Pageable pageable) {
		
		searchCate = "e.ev_nm";
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchCate", searchCate);
		searchMap.put("searchValue", searchValue);
		searchMap.put("pageable", pageable);
		int rowCnt = eventMapper.getSearchCntEventList(searchMap);
		List<Event> eventList = eventMapper.getSearchList(searchMap);
		
		return new PageInfo<>(eventList, pageable, rowCnt);
	}

	@Override
	public PageInfo<Event> getEventWinnerList(Pageable pageable) {
	
		int rowCnt = eventMapper.getCntWinnerList();
		List<Event> eventList = eventMapper.getEventWinnerList(pageable); 
		return new PageInfo<>(eventList, pageable, rowCnt);
	}

	@Override
	public List<Event> getEventsList() {
		
		return eventMapper.getEventsList();
	}

	@Override
	public void addEventWinnerList(Event event) {
	
		String ewCd = commonMapper.getPrimaryKey("ew_", "events_winners", "ew_cd");
		
		event.setEwCd(ewCd);
		event.setAdminId("id01");
		eventMapper.addEventWinnerList(event);
		
	}

	@Override
	public List<Event> getWinnerListInfo(String evCd) {
		
		return eventMapper.getWinnerListInfo(evCd);
	}

	@Override
	public List<Event> EventWinnerListDetail(String evCd) {
		
		return eventMapper.EventWinnerListDetail(evCd);
	}

	@Override
	public Event getEventWinnerListInfoInfoById(String evCd) {

		return eventMapper.getEventWinnerListInfoInfoById(evCd);
	}

	@Override
	public void modifyEventWinnerList(Event event) {

		eventMapper.modifyEventWinnerList(event);	
	}

	@Override
	public void removeEventWinnerList(String evCd) {

		eventMapper.removeEventWinnerList(evCd);
	}

	@Override
	public List<Event> selectEventWinners(String evCd, int evWinnersNum) {
		
		return eventMapper.selectEventWinners(evCd, evWinnersNum);
	}

	@Override
	public void updateWinners(Event event) {

		eventMapper.updateWinners(event);
	}

	@Override
	public int countWinner(String evCd, int evWinnersNum) {
		
		Map<String, Object> resultMap = new ParameterMap<String, Object>();
		resultMap.put("evCd", evCd);
		resultMap.put("evWinnersNum", evWinnersNum);		
		
		return eventMapper.countWinner(resultMap);
	}

	@Override
	public PageInfo<Event> searchWinnerList(String searchValue, String searchCate, Pageable pageable) {
		
		searchCate = "ew_nm";
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchCate", searchCate);
		searchMap.put("searchValue", searchValue);
		searchMap.put("pageable", pageable);
		int rowCnt = eventMapper.getSearchCntWinnerList(searchMap);
		List<Event> eventList = eventMapper.getSearchWinnerList(searchMap);
		
		return new PageInfo<>(eventList, pageable, rowCnt);
		
	}

	@Override
	public void addEvent(Event event, MultipartFile files) {
		FileDto fileInfo = fileUtils.uploadFile(files);
        if(fileInfo != null) {
            String fileIdx = commonMapper.getPrimaryKey("file_", "files", "file_idx");
            fileInfo.setFileIdx(fileIdx);
            fileMapper.addfile(fileInfo);
            String eventCd = commonMapper.getPrimaryKey("ev_", "events", "ev_cd");
            event.setAdminId("id01");
            event.setEvCd(eventCd);
            event.setFileIdx(fileIdx);
            eventMapper.addEvent(event);
        }		
	}
}
