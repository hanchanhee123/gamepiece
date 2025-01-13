package gamepiece.admin.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.admin.notice.domain.Notice;
import gamepiece.admin.notice.mapper.NoticeMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {
	
	
	private final NoticeMapper noticeMapper;
	
	
	@Override
	public PageInfo<Notice> getNoticeList(Pageable pageable) {
	
		int rowCnt = noticeMapper.getCntNotice();
		List<Notice> noticeList = noticeMapper.getNoticeList(pageable);
		
		
		
		return new PageInfo<>(noticeList, pageable, rowCnt);
	
	}


	@Override
	public Notice getNoticeInfo(int noticeNum) {
		
		return noticeMapper.getNoticeInfo(noticeNum);
	}


	@Override
	public int addNotice(Notice notice) {
		
		int result = noticeMapper.addNotice(notice);
		
		return result;
	}


	@Override
	public int modifyNotice(Notice notice) {
		
		return noticeMapper.modifyNotice(notice);
	}


	@Override
	public int removeNotice(int noticeNum) {

		return noticeMapper.removeNotice(noticeNum);
	}


	@Override
	public PageInfo<Notice> getSearchList(String searchValue, Pageable pageable) {
		 Map<String, Object> searchMap = new HashMap<String, Object>();
		 

		   searchMap.put("searchValue", searchValue);
		   searchMap.put("offset", pageable.getOffset());
		   searchMap.put("rowPerPage", pageable.getRowPerPage());
		   
		   int rowCnt = noticeMapper.getCntSearchNotice(searchMap);
		   List<Notice> noticeList = noticeMapper.getNoticeSearchList(searchMap);
		 
		   return new PageInfo<>(noticeList, pageable, rowCnt);
	}

}
