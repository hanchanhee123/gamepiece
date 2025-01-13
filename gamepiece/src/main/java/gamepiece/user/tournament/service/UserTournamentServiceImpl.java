package gamepiece.user.tournament.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import gamepiece.user.tournament.domain.MatchGroup;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserTournamentServiceImpl implements UserTournamentService{

	@Override
	public List<MatchGroup> getTournamentList(String gameName, String matchCate, String date) {
		List<MatchGroup> tournamentList = null;
		boolean isProcess = true;
		int request = 0;
		while (isProcess) {
			request++;
			Map<String, Object> resultMap = getContents(gameName, matchCate, date);
			isProcess = (boolean) resultMap.get("isProcess");
			
			if(!isProcess) tournamentList = (List<MatchGroup>) resultMap.get("tournamentList");
			if(request > 5) break;
		}
		
		return tournamentList;
	}

	private Map<String, Object> getContents(String gameName, String matchCate, String date) {
		
		boolean isProcess = true;
		List<MatchGroup> tournamentList = null;
		
		try {
			// 수집대상 url
			String url = "https://game.naver.com/esports/" + gameName + "/schedule/" + matchCate + "?date=" + date;
			log.info("url : {}",url);
			
			// connection 생성
			Connection conn = Jsoup.connect(url);
			
			// html 파싱
			Document html = conn.get();
			
			Elements element = html.select("#__NEXT_DATA__");
			String str = element.html();
			
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(str);
			
			String monthSchedule = jsonNode.get("props")
						    			   .get("initialState")
						    			   .get("schedule")
						    			   .get("monthSchedule").toString();
			
			if(monthSchedule != null && monthSchedule.equals("[]")) {
				isProcess = false;
			}else if(monthSchedule != null && !monthSchedule.equals("[]")) {
				tournamentList = objectMapper.readValue(monthSchedule, new TypeReference<List<MatchGroup>>() {});
				String startDate = tournamentList.get(0).getSchedules().get(0).getStartDate();
				if(startDate != null && startDate.indexOf(date) > -1) isProcess = false;
			}
		} catch (Exception e) {
			log.error("error : {}",e.getMessage());
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("isProcess", isProcess);
		resultMap.put("tournamentList", tournamentList);
		
		return resultMap;
	}
	
}
