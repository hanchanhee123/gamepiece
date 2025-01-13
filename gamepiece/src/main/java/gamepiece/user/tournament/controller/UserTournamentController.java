package gamepiece.user.tournament.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gamepiece.user.exception.TournamentException;
import gamepiece.user.tournament.domain.MatchGroup;
import gamepiece.user.tournament.domain.ResponseTemplate;
import gamepiece.user.tournament.service.UserTournamentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/tournament")
@RequiredArgsConstructor
@Slf4j
public class UserTournamentController {
	
	private final UserTournamentService tournamentService;
	
	@GetMapping("/list")
	public String getUserTournamentList(Model model, 
										@RequestParam(value = "leageName",defaultValue = "") String tournament,
										String month,
										String year) {
		if(tournament.isEmpty()) {
			tournament = "world_championship";
		}
		
		if(year == null && month == null) {
			LocalDate now = LocalDate.now();
			year = Integer.toString(now.getYear());
			month = now.getMonth().toString();
		}
		
		log.info("month : {}, year : {}",month,year);
		
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("leageName", tournament);
		
		return "user/tournament/tournamentList";
	}
	
	@PostMapping("/util/ajax")
	@ResponseBody
	public ResponseTemplate<List<MatchGroup>> getMetchList(@RequestParam(value = "gameName",required = false, defaultValue = "League_of_Legends") String gameName,
														   @RequestParam(value = "matchCate",required = false, defaultValue = "world_championship") String matchCate,
														   @RequestParam(value = "date",required = false) String date) {
		if(date == null) {
			LocalDate today = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
			date = today.format(formatter);
		}
		
		List<MatchGroup> tournamentList = tournamentService.getTournamentList(gameName, matchCate, date);
		
		String code = "success";
		String message = date + " 경기일정 없음";
		
		if(tournamentList != null) message = " 경기일정 있음";
		
		return new ResponseTemplate<>(code, message, tournamentList);
	}
	
	@ExceptionHandler(TournamentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseTemplate<String> handlerTournamentException(TournamentException ex){
		log.error("error: {}", ex.getMessage());
		return new ResponseTemplate<>("fail", "잘못된 요청", "요청한 데이터 없음");
	}
}