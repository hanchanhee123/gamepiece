package gamepiece.user.pointShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import gamepiece.user.pointShop.service.PointShopService;


@Controller
@RequestMapping("/user/point")
public class PointShopController {
	private final PointShopService pointshopService;
	
	public PointShopController(PointShopService pointshopService) {
		this.pointshopService = pointshopService;
	}

	@GetMapping("/shop")
	public String getItemList(Model model) {
		
		
		model.addAttribute("title", "포인트샵");
		model.addAttribute("Shop", pointshopService.findAll());
		
		
		return "user/points/pointshopList";
	}
}
