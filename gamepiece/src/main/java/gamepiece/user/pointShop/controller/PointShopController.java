package gamepiece.user.pointShop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.pointShop.service.PointShopService;
import gamepiece.util.Pageable;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/point")
public class PointShopController {
	private final PointShopService pointshopService;
	
	public PointShopController(PointShopService pointshopService) {
		this.pointshopService = pointshopService;
	}
	@GetMapping("/modal")
	public ResponseEntity<Point> getitemModal(@RequestParam(value="itemCd") String itemCd,
							Model model) {
		
		var pointInfo = pointshopService.pointInfo(itemCd);
		/* System.out.println("pointinfo 데이터: " + pointInfo); */
		
		
		return ResponseEntity.ok(pointInfo);
	}
	
	
	@GetMapping("/imoticon")
	public String getimoticonList(Pageable pageable,Model model,HttpSession session) {
		var imoticonInfo = pointshopService.findimoticon(pageable);
		
		List<Point> imoticonList = imoticonInfo.getContents();
		int imoticoncurrentPage = imoticonInfo.getCurrentPage();
		int imoticonstartPageNum = imoticonInfo.getStartPageNum();
		int imoticonendPageNum = imoticonInfo.getEndPageNum();
		int imoticonlastPage = imoticonInfo.getLastPage();
		String userId = (String) session.getAttribute("SID");
		
		model.addAttribute("imoticonList", imoticonList);
		model.addAttribute("imoticoncurrentPage", imoticoncurrentPage);
		model.addAttribute("imoticonstartPageNum", imoticonstartPageNum);
		model.addAttribute("imoticonendPageNum", imoticonendPageNum);
		model.addAttribute("imoticonlastPage", imoticonlastPage);
		var userPoint = pointshopService.getPointsHeld(userId);
		model.addAttribute("userPoint", userPoint.getTotalPoint());

		
		return "user/points/imoticonList";
	}
	
	@GetMapping("/avatar")
	public String getavatarList(Pageable pageable,Model model, HttpSession session) {
		var avatarInfo = pointshopService.findavatar(pageable);
		
		List<Point> avatarList = avatarInfo.getContents();
		int currentPage = avatarInfo.getCurrentPage();
		int startPageNum = avatarInfo.getStartPageNum();
		int endPageNum = avatarInfo.getEndPageNum();
		int lastPage = avatarInfo.getLastPage();
		String userId = (String) session.getAttribute("SID");
		
		model.addAttribute("avatarList", avatarList );
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		var userPoint = pointshopService.getPointsHeld(userId);
		model.addAttribute("userPoint", userPoint.getTotalPoint());
		
		
		return "user/points/avatarList";
	}
	
	@GetMapping("/frame")
	public String getframeList(Pageable pageable,Model model, HttpSession session) {
		var frameInfo = pointshopService.findavatarframe(pageable);
		
		List<Point> frameList = frameInfo.getContents();
		int currentPage = frameInfo.getCurrentPage();
		int startPageNum = frameInfo.getStartPageNum();
		int endPageNum = frameInfo.getEndPageNum();
		int lastPage = frameInfo.getLastPage();
		String userId = (String) session.getAttribute("SID");
		
		model.addAttribute("frameList", frameList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		var userPoint = pointshopService.getPointsHeld(userId);
		model.addAttribute("userPoint", userPoint.getTotalPoint());
		
		
		return "user/points/frameList";
	}
	
	@GetMapping("/etc")
	public String getetcList(Pageable pageable,Model model, HttpSession session) {
		var etcInfo = pointshopService.findetc(pageable);
		
		List<Point> etcList = etcInfo.getContents();
		int currentPage = etcInfo.getCurrentPage();
		int startPageNum = etcInfo.getStartPageNum();
		int endPageNum = etcInfo.getEndPageNum();
		int lastPage = etcInfo.getLastPage();
		String userId = (String) session.getAttribute("SID");
		
		model.addAttribute("etcList", etcList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		var userPoint = pointshopService.getPointsHeld(userId);
		model.addAttribute("userPoint", userPoint.getTotalPoint());
		
		
		return "user/points/etcList";
	}
	
	@GetMapping("/background")
	public String getbackgroundList(Pageable pageable,Model model, HttpSession session) {
		var backInfo = pointshopService.findbackground(pageable);
		
		List<Point> backList = backInfo.getContents();
		int currentPage = backInfo.getCurrentPage();
		int startPageNum = backInfo.getStartPageNum();
		int endPageNum = backInfo.getEndPageNum();
		int lastPage = backInfo.getLastPage();
		String userId = (String) session.getAttribute("SID");
		
		model.addAttribute("backList", backList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		var userPoint = pointshopService.getPointsHeld(userId);
		model.addAttribute("userPoint", userPoint.getTotalPoint());
		
		return "user/points/backgroundList";
	}
	
	
	
	@GetMapping("/shop")
	public String getItemList(Pageable pageable, Model model, HttpSession session) {
		
		var imoticonInfo = pointshopService.findimoticon(pageable);
		var avatarInfo = pointshopService.findavatar(pageable);
		var frameInfo = pointshopService.findavatarframe(pageable);
		var etcInfo = pointshopService.findetc(pageable);
		var backInfo = pointshopService.findbackground(pageable);
		
		
		String userId = (String) session.getAttribute("SID");
		List<Point> imoticonList = imoticonInfo.getContents();
		int imoticoncurrentPage = imoticonInfo.getCurrentPage();
		int imoticonstartPageNum = imoticonInfo.getStartPageNum();
		int imoticonendPageNum = imoticonInfo.getEndPageNum();
		int imoticonlastPage = imoticonInfo.getLastPage();
		
		List<Point> avatarList = avatarInfo.getContents();
		int avatarcurrentPage = avatarInfo.getCurrentPage();
		int avatarstartPageNum = avatarInfo.getStartPageNum();
		int avatarendPageNum = avatarInfo.getEndPageNum();
		int avatarlastPage = avatarInfo.getLastPage();
		
		List<Point> frameList = frameInfo.getContents();
		int framecurrentPage = frameInfo.getCurrentPage();
		int framestartPageNum = frameInfo.getStartPageNum();
		int frameendPageNum = frameInfo.getEndPageNum();
		int framelastPage = frameInfo.getLastPage();
		
		List<Point> etcList = etcInfo.getContents();
		int etccurrentPage = etcInfo.getCurrentPage();
		int etcstartPageNum = etcInfo.getStartPageNum();
		int etcendPageNum = etcInfo.getEndPageNum();
		int etclastPage = etcInfo.getLastPage();
		
		List<Point> backList = backInfo.getContents();
		int backcurrentPage = backInfo.getCurrentPage();
		int backstartPageNum = backInfo.getStartPageNum();
		int backendPageNum = backInfo.getEndPageNum();
		int backlastPage = backInfo.getLastPage();
		
		model.addAttribute("title", "포인트샵");
		model.addAttribute("cateList", pointshopService.findCate());
		model.addAttribute("itemInfo", pointshopService.findAll());
		
		
		model.addAttribute("imoticonList", imoticonList);
		model.addAttribute("imoticoncurrentPage", imoticoncurrentPage);
		model.addAttribute("imoticonstartPageNum", imoticonstartPageNum);
		model.addAttribute("imoticonendPageNum", imoticonendPageNum);
		model.addAttribute("imoticonlastPage", imoticonlastPage);
		
		model.addAttribute("avatarList", avatarList );
		model.addAttribute("avatarcurrentPage", avatarcurrentPage);
		model.addAttribute("avatarstartPageNum", avatarstartPageNum);
		model.addAttribute("avatarendPageNum", avatarendPageNum);
		model.addAttribute("avatarlastPage", avatarlastPage);
		
		model.addAttribute("frameList", frameList);
		model.addAttribute("framecurrentPage", framecurrentPage);
		model.addAttribute("framestartPageNum", framestartPageNum);
		model.addAttribute("frameendPageNum", frameendPageNum);
		model.addAttribute("framelastPage", framelastPage);
		
		model.addAttribute("etcList", etcList);
		model.addAttribute("etccurrentPage", etccurrentPage);
		model.addAttribute("etcstartPageNum", etcstartPageNum);
		model.addAttribute("etcendPageNum", etcendPageNum);
		model.addAttribute("etclastPage", etclastPage);
		
		model.addAttribute("backList", backList);
		model.addAttribute("backcurrentPage", backcurrentPage);
		model.addAttribute("backstartPageNum", backstartPageNum);
		model.addAttribute("backendPageNum", backendPageNum);
		model.addAttribute("backlastPage", backlastPage);
		
		var userPoint = pointshopService.getPointsHeld(userId);
		model.addAttribute("userPoint", userPoint.getTotalPoint());
		
		
		return "user/points/pointshopList";
	}
}
