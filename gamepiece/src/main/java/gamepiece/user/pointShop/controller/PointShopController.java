package gamepiece.user.pointShop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gamepiece.user.pointShop.domain.Point;
import gamepiece.user.pointShop.service.PointShopService;
import gamepiece.util.Pageable;


@Controller
@RequestMapping("/user/point")
public class PointShopController {
	private final PointShopService pointshopService;
	
	public PointShopController(PointShopService pointshopService) {
		this.pointshopService = pointshopService;
	}

	@GetMapping("/shop")
	public String getItemList(Pageable pageable,Model model) {
		
		var pageInfo = pointshopService.findAll(pageable);
		
		List<Point> ItemList = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();
		
		
		model.addAttribute("title", "포인트샵");
		model.addAttribute("pointList", pointshopService.findAll(pageable));
		model.addAttribute("cateList", pointshopService.findCate());
		model.addAttribute("ItemList", ItemList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);
		
		
		return "user/points/pointshopList";
	}
}
