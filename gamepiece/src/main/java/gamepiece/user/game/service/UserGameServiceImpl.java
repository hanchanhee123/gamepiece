package gamepiece.user.game.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gamepiece.user.game.domain.UserGame;
import gamepiece.user.game.mapper.UserGameMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;

@Service("userUserGameService")
@RequiredArgsConstructor
@Transactional
public class UserGameServiceImpl implements UserGameService {

	private final UserGameMapper userGameMapper;
	@Override
	public PageInfo<UserGame> getGameList(Pageable pageable) {
		
		int rowCnt = userGameMapper.getCntGameList();
		
		List<UserGame> userGameList = userGameMapper.getGameList(pageable);
		
		return new PageInfo<>(userGameList,pageable, rowCnt);
	}
	
	@Override
	public ArrayList<String> getPlatformList() {
		
		ArrayList<String> platformList = userGameMapper.getPlatformList();
		return platformList;
	}
	
	@Override
	public ArrayList<String> getGenreList() {
		
		ArrayList<String> genreList = userGameMapper.getGenreList();
		
		return genreList;
	}
	
	
	
	@Override
	public PageInfo<UserGame> getGameListWithPlatform(Pageable pageable, String platformCode) {
		int rowCnt = userGameMapper.getCntGameListWithPlatform();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("platformCode", platformCode);
		paramMap.put("pageable", pageable);
		
		List<UserGame> userGameList = userGameMapper.getGameListWithPlatform(paramMap);
		
		return new PageInfo<>(userGameList, pageable, rowCnt);
	}
	
	// steam 페이지 크롤링
		@Override
		public Map<String, Object> getGameListApi(int currentPage) {
			Map<String, Object> resultMap = new HashMap<>();
		    List<Map<String, Object>> resultList = new ArrayList<>();
		    
		    double totalAmount = 0;
		    int lastPage = 0;
		    int startPageNum = 1;
		    
		    
		    
		    

	        String searchResult = "";
		    try {
		        // 1. 수집 대상 URL
		        String url = "https://store.steampowered.com/search/results?page=" + currentPage + "sort_by=_ASC&term=A";
		        // 2. Connection 생성
		        Connection conn = Jsoup.connect(url);
		        
		        // 3. HTML 파싱.
		        Document html = conn.get();
		        
		        // 검색결과 없음 : 0, 검색결과 있음 : 마지막페이지번호
	            Elements searchResultsElement = html.select(".search_results_count");
	            if(searchResultsElement.isEmpty()) {
	            	searchResultsElement = html.select("#search_results_filtered_warning");
	            	searchResult = searchResultsElement.get(0).children().first().text();
	            }else {
	            	searchResult = searchResultsElement.text();
	            }
	            searchResult = searchResult.substring(0, searchResult.indexOf("result")).trim();
	            searchResult = searchResult.replaceAll("[^0-9]","");
	            if(!"0".equals(searchResult)) {            	
	            	totalAmount = Double.parseDouble(searchResult);
	            	lastPage = (int) Math.ceil(totalAmount/25);
	            	searchResult = "" + lastPage;
	            }
	            
	            int endPageNum = lastPage < 10 ? lastPage : 10;
	            if(currentPage > 6 && lastPage > 9) {
	            	startPageNum = currentPage -5;
	            	endPageNum = currentPage + 4;
	            	if(endPageNum >= lastPage) {
	            		startPageNum = lastPage - 9;
	            		endPageNum = lastPage;
	            	}
	            }
	            
	            
	            System.out.println(searchResult);
	            
		        // 4. 게임 정보 추출
		        Elements element = html.select("#search_resultsRows");
		        Elements aElement = element.select("a");
		        
		        aElement.forEach(a -> {
		            Map<String, Object> game = new HashMap<>();
		            String urlSrc = a.select(".search_capsule > img").attr("src");
		            String title = a.select(".search_name > .title").text();
		            String released = a.select(".search_released").text();
		            if (released.isEmpty() || released.length() < 12) {
		                released = "출시예정";
		            }
		            
		            String finalPrice = "0";
		            String disCountPrice = "0";
		            String originalPrice = finalPrice;
		            
		            if (!"출시예정".equals(released)) {
		                try {
		                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM, yyyy", Locale.ENGLISH);
		                    LocalDate dateTime = LocalDate.parse(released, formatter);
		                    released = dateTime.toString();
		                } catch (DateTimeParseException e) {
		                    // 예외 처리 (파싱 실패 시)
		                    released = "출시예정";
		                }
		                
		                finalPrice = a.select(".discount_block").attr("data-price-final");
		                if (finalPrice.length() > 2) {
		                    finalPrice = finalPrice.substring(0, finalPrice.length() - 2);
		                }
		                disCountPrice = a.select(".discount_block").attr("data-discount");
		                originalPrice = finalPrice;
		                
		                if (!"0".equals(disCountPrice)) {
		                    originalPrice = a.select(".discount_original_price").text();
		                    originalPrice = originalPrice.replaceAll("[^0-9]", "");
		                }
		            }
		            
		            game.put("urlSrc", urlSrc);
		            game.put("title", title);
		            game.put("released", released);
		            game.put("finalPrice", finalPrice);
		            game.put("disCountPrice", disCountPrice);
		            game.put("originalPrice", originalPrice);
		            resultList.add(game);
		        });
		        resultMap.put("currentPage", currentPage);
		        resultMap.put("totalAmount", totalAmount);
		        resultMap.put("lastPage", lastPage);
		        resultMap.put("startPageNum", startPageNum);
		        resultMap.put("endPageNum", endPageNum);
		        
		        
			    resultMap.put("searchResult", searchResult);
		        
		    } catch (Exception e) {
		        System.out.println(e);
		    }
		    
		    resultMap.put("games", resultList);
		    
		    return resultMap;
		}
	
}
