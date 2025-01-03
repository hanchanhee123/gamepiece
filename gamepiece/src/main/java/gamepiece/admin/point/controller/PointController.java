package gamepiece.admin.point.controller;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gamepiece.admin.point.domain.Point;
import gamepiece.admin.point.domain.PointCategories;
import gamepiece.admin.point.service.PointService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/admin/point")
public class PointController {
	
	private final PointService pointService;
	
	public PointController(PointService pointService) {
		this.pointService = pointService;
	}
	
	@GetMapping("/removeItem")
	public String removeItem(String ps_cd, Model model) {
		pointService.removeItem(ps_cd);
		
		model.addAttribute("pointList", pointService.findAll());
		model.addAttribute("cateList", pointService.findCate());
		
		return "redirect:/admin/point/list";
	}
	
	@GetMapping("/list")
	public String pointListView(String ps_cd,Model model) {
		
		model.addAttribute("title", "포인트샵 목록");
		model.addAttribute("pointList", pointService.findAll());
		model.addAttribute("cateList", pointService.findCate());
		
		
		return "admin/points/pointshopList";
	}
	
	@GetMapping("/modify")
	public String modifyMember(Point point,
							   RedirectAttributes reAttr) {
		System.out.println(point);
		pointService.modifyItem(point);
		
		log.info("Point : {}", point);
		
		reAttr.addFlashAttribute("ItemName", point.getItemName());
		
		return "redirect:/admin/point/list";
	}
	
	
	@GetMapping("/detail")
	public String pointDetail(@RequestParam(value = "itemName") String itemName,
							  Model model) {
		
		var itemInfo = pointService.getItemInfoByName(itemName);
		
		List<PointCategories> test = new ArrayList<>();
		test = pointService.findCate();
		System.out.println(test);
		model.addAttribute("title", "상세보기");
		model.addAttribute("pointList", pointService.findAll());
		model.addAttribute("cateList", pointService.findCate());
		model.addAttribute("itemInfo", itemInfo);
		
		return "admin/points/pointshopdetail";
	}
	@PostMapping("/add")
	public String addItem(Point point) {
		
		pointService.addItem(point);
		
		return "redirect:/admin/point/list";
	}
	
	
	@GetMapping("/add")
	public String pointAdd(Model model) {
		
		model.addAttribute("title", "아이템 추가");
		model.addAttribute("cateList", pointService.findCate());
		model.addAttribute("pointList", pointService.findAll());
		
		return "admin/points/pointshopadd";
	}
}	
