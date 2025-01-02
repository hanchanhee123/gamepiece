package gamepiece.admin.point.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gamepiece.admin.point.service.PointService;


@Controller
@RequestMapping("/admin/point")
public class PointController {
	
	private final PointService pointService;
	
	public PointController(PointService pointService) {
		this.pointService = pointService;
	}
	
	
	@GetMapping("/list")
	public String pointListView(Model model) {
		
		model.addAttribute("title", "포인트샵 목록");
		model.addAttribute("pointList", pointService.findAll());
		model.addAttribute("cateList", pointService.findCate());
		
		return "admin/points/pointshopList";
	}
	@GetMapping("/detail")
	public String pointDetail(@RequestParam(value = "itemName") String itemName,
							  Model model) {
		
		var itemInfo = pointService.getItemInfoByName(itemName);
		
		model.addAttribute("title", "상세보기");
		model.addAttribute("pointList", pointService.findAll());
		model.addAttribute("cateList", pointService.findCate());
		model.addAttribute("itemInfo", itemInfo);
		
		
		return "admin/points/pointshopdetail";
	}
	@GetMapping("/add")
	public String pointAdd(Model model) {
		
		model.addAttribute("title", "아이템 추가");
		model.addAttribute("cateList", pointService.findCate());
		model.addAttribute("pointList", pointService.findAll());
		/* model.addAttribute("addItem", pointService.addPointShop()); */
		
		return "admin/points/pointshopadd";
	}

}	
