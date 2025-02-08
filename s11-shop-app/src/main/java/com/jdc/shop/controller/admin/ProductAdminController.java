package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.controller.input.ProductSearch;
import com.jdc.shop.controller.output.StockChangeInfo;
import com.jdc.shop.model.PageInfo;
import com.jdc.shop.model.master.service.ProductService;
import com.jdc.shop.model.master.service.StockHistoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/product")
public class ProductAdminController {
	
	private final ProductService productService;
	private final StockHistoryService historyService;

	@GetMapping
	String search(
			ProductSearch form, 
			ModelMap model,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		model.put("result", productService.search(form, page, size));
		return "product/list";
	}
	
	@GetMapping("{id}/details")
	String showDetails(@PathVariable int id, ModelMap model) {
		model.put("details", productService.findById(id));
		return "product/details";
	}
	
	@PostMapping("{id}/photos")
	String uploadPhotos(
			@PathVariable int id, 
			@RequestParam MultipartFile[] photos) {
		productService.uploadPhotos(id, photos);
		return "redirect:/admin/product/%s/details".formatted(id);
	}

	@ResponseBody
	@GetMapping("{id}/stock")
	PageInfo<StockChangeInfo> searchStockChangeHistory(
			@PathVariable int id, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return historyService.search(id, page, size);
	}

}
