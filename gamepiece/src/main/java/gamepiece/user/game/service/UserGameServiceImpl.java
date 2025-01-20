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
import gamepiece.user.game.domain.UserReview;
import gamepiece.user.game.mapper.UserGameMapper;
import gamepiece.user.game.mapper.UserReviewMapper;
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("userUserGameService")
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserGameServiceImpl implements UserGameService {

	private final UserGameMapper userGameMapper;
	private final UserReviewMapper userReviewMapper;
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
	public Map<String, Object> getGameListApi(String searchValue, int currentPage) {
		Map<String, Object> resultMap = new HashMap<>();
	    List<Map<String, Object>> resultList = new ArrayList<>();
	    
	    double totalAmount = 0;
	    int lastPage = 0;
	    int startPageNum = 1;
	    
	    
	    
	    

        String searchResult = "";
	    try {
	        // 1. 수집 대상 URL
	        String url = "https://store.steampowered.com/search/results?page=" + currentPage + "sort_by=_ASC&term=" + searchValue;
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
	        	String gameCode = a.attr("data-ds-itemkey");
	        	gameCode = gameCode.substring(4);
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
	            game.put("gameCode", gameCode);
	            resultList.add(game);
	        });
	        resultMap.put("currentPage", currentPage);
	        resultMap.put("totalAmount", totalAmount);
	        resultMap.put("lastPage", lastPage);
	        resultMap.put("startPageNum", startPageNum);
	        resultMap.put("endPageNum", endPageNum);
	        resultMap.put("searchValue", searchValue);
	        
		    resultMap.put("searchResult", searchResult);
	        
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    
	    resultMap.put("games", resultList);
	    
	    return resultMap;
	}
	// 게임 상세 페이지 크롤링
	@Override
	public Map<String, Object> getGameDetailApi(String gameCode, String title) {
		Map<String, Object> game = new HashMap<String,Object>();
		boolean isDetail = false;
		try {
			String url = "https://store.steampowered.com/app/" + gameCode + "/" + title + "/";
			
			Connection conn = Jsoup.connect(url);
			
			Document html = conn.get();
			
			
			
			
			Elements element = html.select("#tabletGrid");
			if(!element.isEmpty()) {
				isDetail = true;
				Elements titleEle = html.select("#appHubAppName");
				title = titleEle.text();
				// System.out.println(title);
				
				Elements info = element.select(".rightcol");
				//System.out.println(info);
				Elements ImgCtn = info.select("#gameHeaderImageCtn");
				//System.out.println(ImgCtn);
				String imgSrc = ImgCtn.select(".game_header_image_full").attr("src");
				// System.out.println(imgSrc);
				
				String lightInfo = info.select(".game_description_snippet").text();
				//System.out.println(lightInfo);
				
				String date = info.select(".glance_ctn_responsive_left").select(".release_date").select(".date").text();
				// System.out.println(date);
				if(!"출시예정".equals(date)) { 
	        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM, yyyy", Locale.ENGLISH);
	        		LocalDate dateTime = LocalDate.parse(date, formatter);
	        		
	        		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일", Locale.KOREAN);
	        	    String formattedDate = dateTime.format(outputFormatter);	
	        		date = formattedDate.toString();
	        	}
				// System.out.println(date);
				String developer = info.select(".glance_ctn_responsive_left").select("#developers_list").select("a").text();
				// System.out.println(developer);
				
				Elements genreEle = info.select("#glanceCtnResponsiveRight").select("a");
				// System.out.println(genreEle);
				String genre = genreEle.first().text();
				Elements videoEle = element.select(".leftcol");
				// System.out.println(videoEle);
				String imgSrcSec = "";
				String videoSrc = videoEle.select(".highlight_movie").attr("data-webm-source");
				if("".equals(videoSrc)) {
					
					Elements aEle = videoEle.select("a");
					imgSrcSec = aEle.attr("href");
					//System.out.println(imgSrcSec);
				}
				
				//System.out.println(videoSrc);
				
				Elements priceEle = element.select("#game_area_purchase");
				//System.out.println(priceEle);
				String finalPrice = priceEle.select(".game_purchase_price").attr("data-price-final");
				if(!("무료".equals(finalPrice) || finalPrice.length() < 6)) {
					finalPrice = finalPrice.substring(0, finalPrice.length() - 2);
				} else {
					finalPrice = priceEle.select(".game_purchase_price").text();
				}
				// System.out.println(finalPrice);
				Elements deepInfo = element.select("#game_area_description");
				String deepInfoText = deepInfo.text();
				
				game.put("gameCode", gameCode);
				game.put("title", title);
				game.put("imgSrc", imgSrc);
				game.put("lightInfo", lightInfo);
				game.put("date", date);
				game.put("developer", developer);
				game.put("genre", genre);
				game.put("videoSrc", videoSrc);
				game.put("finalPrice", finalPrice);
				game.put("imgSrcSec", imgSrcSec);
				game.put("deepInfoText", deepInfoText);
				
			}
			game.put("isDetail", isDetail);
			// System.out.println(element);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return game;
	}
	// 해당 게임의 리뷰 목록 조회
	@Override
	public List<UserReview> getUserReview(String gameCode) {
		
		List<UserReview> userReview = userReviewMapper.getUserReview(gameCode);
		
		return userReview;
	}

	@Override
	public String getLastReviewNo() {
		String lastReviewNo = userReviewMapper.getLastReviewNum();
		return lastReviewNo;
	}
	
	@Override
	public void writeUserReview(UserReview userReview) {
		int result = userReviewMapper.writeUserReview(userReview);
		
	}
}
