package gamepiece.admin.point.controller;

import java.util.ArrayList;
import java.util.List;

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
import gamepiece.util.PageInfo;
import gamepiece.util.Pageable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/point")
public class PointController {

	private final PointService pointService;

	public PointController(PointService pointService) {
		this.pointService = pointService;
	}

	@PostMapping("/searchList")
	public String searchListView(
			@RequestParam(value = "searchCate", required = false, defaultValue = "id") String searchCate,
			@RequestParam(value = "searchValue") String searchValue, Pageable pageable, Model model) {

		log.info("searchCate: {}, searchValue: {} ", searchCate, searchValue);

		
		 PageInfo<Point> searchList = pointService.searchList(searchCate, searchValue, pageable);

		 List<Point> ItemInfo = searchList.getContents(); 
		 int currentPage = searchList.getCurrentPage(); 
		 int startPageNum = searchList.getStartPageNum();
		 int endPageNum = searchList.getEndPageNum(); 
		 int lastPage = searchList.getLastPage();
		 
		log.info("{}", searchList);

		model.addAttribute("title", "포인트샵 목록");
		model.addAttribute("searchCate", searchCate);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("ItemList", ItemInfo);
		model.addAttribute("pointList", searchList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);

		return "admin/points/pointshopList";
	}

	@GetMapping("/inactiveItem")
	public String inactiveItem(String ps_cd, Model model) {
		pointService.inactiveItem(ps_cd);

		return "redirect:/admin/point/list";
	}

	@GetMapping("/removeItem")
	public String removeItem(Pageable pageable, String ps_cd, Model model) {
		pointService.removeItem(ps_cd);

		var pageInfo = pointService.findAll(pageable);

		model.addAttribute("pointList", pageInfo);
		model.addAttribute("cateList", pointService.findCate());

		return "redirect:/admin/point/list";
	}

	@GetMapping("/list")
	public String pointListView(Pageable pageable, Model model) {

		var pageInfo = pointService.findAll(pageable);

		List<Point> ItemList = pageInfo.getContents();
		int currentPage = pageInfo.getCurrentPage();
		int startPageNum = pageInfo.getStartPageNum();
		int endPageNum = pageInfo.getEndPageNum();
		int lastPage = pageInfo.getLastPage();

		model.addAttribute("title", "포인트샵 목록");
		model.addAttribute("pointList", pointService.findAll(pageable));
		model.addAttribute("cateList", pointService.findCate());
		model.addAttribute("ItemList", ItemList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPage", lastPage);

		return "admin/points/pointshopList";
	}

	@GetMapping("/modify")
	public String modifyMember(Point point, RedirectAttributes reAttr) {
		System.out.println(point);
		pointService.modifyItem(point);

		log.info("Point : {}", point);

		reAttr.addFlashAttribute("ItemName", point.getItemName());

		return "redirect:/admin/point/list";
	}

	@GetMapping("/detail")
	public String pointDetail(@RequestParam(value = "itemCode") String itemCode, String ps_cd, Model model) {

		var ItemInfo = pointService.getItemInfoByItemName(itemCode);
		pointService.inactiveItem(ps_cd);

		System.out.println(itemCode);

		List<PointCategories> test = new ArrayList<>();
		test = pointService.findCate();
		// System.out.println(pointInfo);
		model.addAttribute("title", "상세보기");
		model.addAttribute("ItemInfo", ItemInfo);
		model.addAttribute("cateList", pointService.findCate());

		return "admin/points/pointshopdetail";
	}

	@PostMapping("/add")
	public String addItem(Point point) {

		pointService.addItem(point);

		return "redirect:/admin/point/list";
	}

	@GetMapping("/add")
	public String pointAdd(Model model, Pageable pageable) {

		model.addAttribute("title", "아이템 추가");
		model.addAttribute("cateList", pointService.findCate());
		model.addAttribute("pointList", pointService.findAll(pageable));

		return "admin/points/pointshopadd";
	}
}
