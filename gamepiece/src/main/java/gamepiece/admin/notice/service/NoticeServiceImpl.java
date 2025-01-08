package gamepiece.admin.notice.service;

import java.util.List;

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
		
		noticeList.forEach(noticeInfo -> {
			String noticeWriter = noticeInfo.getNoticeWriter(); 
			switch (noticeWriter) {
				case "id01" -> {
					noticeInfo.setNoticeWriter("관리자");
				}
				
				}
		});
		
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

}
