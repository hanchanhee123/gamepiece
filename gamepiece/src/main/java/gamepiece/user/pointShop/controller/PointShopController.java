package gamepiece.user.pointShop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.pointShop.service.PointShopService;
import gamepiece.user.user.service.UserService;
import gamepiece.util.Pageable;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/point")
@RequiredArgsConstructor
public class PointShopController {
	private final PointShopService pointshopService;
	private final UserService userService;
	
	
	@GetMapping("/addlog")
	public String PointShopLog(String itemCd,
							   String itemName,
							   HttpSession session,
							   int itemPrice) {
		String userId = (String) session.getAttribute("SID");
		
		
		pointshopService.addPointLog(userId, itemName, itemPrice);
		pointshopService.addPointShopLog(userId, itemCd, itemPrice);
		
		return "redirect:/point/shop";
	}
	
	@GetMapping("/modal")
	@ResponseBody
	public Point getitemModal(@RequestParam(value="itemCd") String itemCd, HttpSession session
							 ) {
		
		String userId = (String) session.getAttribute("SID");
		
		var pointInfo = pointshopService.pointInfo(itemCd);
	
		
		return pointInfo;
	}
	
	@GetMapping("/history")
	public String gethistory(Pageable pageable, Model model, HttpSession session) {
		String userId = (String) session.getAttribute("SID");
		var historyInfo = pointshopService.findhistory(pageable, userId);
		
		
		List<Point> historyList = historyInfo.getContents();
		
		log.info("historyList {}", historyList);
		
		int currentPage = historyInfo.getCurrentPage();
		int startPageNum = historyInfo.getStartPageNum();
		int endPageNum = historyInfo.getEndPageNum();
		int lastPage = historyInfo.getLastPage();
		
		String avatar = userService.getUserAvatar(userId);
        model.addAttribute("avatar", avatar);
		
		model.addAttribute("historyList", historyList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("userId", userId);
		var userPoint = pointshopService.getPointsHeld(userId);
		
		if(userId != null) {
			model.addAttribute("userPoint", userPoint.getTotalPoint());
		}
		
		return "user/points/pointHistory";
	}
	
	@GetMapping("/emoticon")
	public String getimoticonList(Pageable pageable,Model model,HttpSession session) {
		
		var imoticonInfo = pointshopService.findimoticon(pageable);
		
		List<Point> imoticonList = imoticonInfo.getContents();
		int imoticoncurrentPage = imoticonInfo.getCurrentPage();
		int imoticonstartPageNum = imoticonInfo.getStartPageNum();
		int imoticonendPageNum = imoticonInfo.getEndPageNum();
		int imoticonlastPage = imoticonInfo.getLastPage();
		String userId = (String) session.getAttribute("SID");
		
		String avatar = userService.getUserAvatar(userId);
        model.addAttribute("avatar", avatar);
        
        var purchaseList = pointshopService.purchaseList(userId);
        model.addAttribute("purchaseList", purchaseList);
        
		model.addAttribute("imoticonList", imoticonList);
		model.addAttribute("imoticoncurrentPage", imoticoncurrentPage);
		model.addAttribute("imoticonstartPageNum", imoticonstartPageNum);
		model.addAttribute("imoticonendPageNum", imoticonendPageNum);
		model.addAttribute("imoticonlastPage", imoticonlastPage);
		model.addAttribute("userId", userId);
		var userPoint = pointshopService.getPointsHeld(userId);
		
		if(userId != null) {
			model.addAttribute("userPoint", userPoint.getTotalPoint());
		}

		
		return "user/points/emoticonList";
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
		
		String avatar = userService.getUserAvatar(userId);
        model.addAttribute("avatar", avatar);
        
        var purchaseList = pointshopService.purchaseList(userId);
        model.addAttribute("purchaseList", purchaseList);
		
		model.addAttribute("avatarList", avatarList );
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("userId", userId);
		var userPoint = pointshopService.getPointsHeld(userId);
		
		if(userId != null) {
			model.addAttribute("userPoint", userPoint.getTotalPoint());
		}
		
		
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
		
		String avatar = userService.getUserAvatar(userId);
        model.addAttribute("avatar", avatar);
        
        var purchaseList = pointshopService.purchaseList(userId);
        model.addAttribute("purchaseList", purchaseList);
		
		model.addAttribute("frameList", frameList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("userId", userId);
		var userPoint = pointshopService.getPointsHeld(userId);
		
		if(userId != null) {
			model.addAttribute("userPoint", userPoint.getTotalPoint());
		}
		
		
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
		
		String avatar = userService.getUserAvatar(userId);
        model.addAttribute("avatar", avatar);
        var purchaseList = pointshopService.purchaseList(userId);
        model.addAttribute("purchaseList", purchaseList);
		
		model.addAttribute("etcList", etcList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("userId", userId);
		var userPoint = pointshopService.getPointsHeld(userId);
		
		if(userId != null) {
			model.addAttribute("userPoint", userPoint.getTotalPoint());
		}
		
		
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
		
		String avatar = userService.getUserAvatar(userId);
        model.addAttribute("avatar", avatar);
		
        var purchaseList = pointshopService.purchaseList(userId);
        model.addAttribute("purchaseList", purchaseList);
        
		model.addAttribute("backList", backList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("userId", userId);
		var userPoint = pointshopService.getPointsHeld(userId);
		
		if(userId != null) {
			model.addAttribute("userPoint", userPoint.getTotalPoint());
		}
		
		return "user/points/backgroundList";
	}
	
	
	
	@GetMapping("/shop")
	public String getItemList(Pageable pageable, Model model, HttpSession session) {
		var imoticonInfo = pointshopService.findimoticon(pageable);
		var avatarInfo = pointshopService.findavatar(pageable);
		var backInfo = pointshopService.findbackground(pageable);
		String userId = (String) session.getAttribute("SID");

        String avatar = userService.getUserAvatar(userId);
        model.addAttribute("avatar", avatar);
		
		
		var purchaseList = pointshopService.purchaseList(userId);
		
		List<Point> imoticonList = imoticonInfo.getContents();
		
		List<Point> avatarList = avatarInfo.getContents();
		
		
		List<Point> backList = backInfo.getContents();
		
		model.addAttribute("purchaseList", purchaseList);
		
		model.addAttribute("title", "포인트샵");
		
		model.addAttribute("userId", userId);
		
		model.addAttribute("imoticonList", imoticonList);
		
		model.addAttribute("avatarList", avatarList );
			
		model.addAttribute("backList", backList);
		
		
		
		
		var userPoint = pointshopService.getPointsHeld(userId);
		
		if(userId != null) {
			model.addAttribute("userPoint", userPoint.getTotalPoint());
		}
		
		
		return "user/points/pointshopList";
	}
}
