package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.controller.input.CategorySearch;
import com.jdc.shop.model.master.service.CategoryManagementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/category")
public class CategoryController {
	
	private final CategoryManagementService service;

	@GetMapping
	String search(CategorySearch search, ModelMap model) {
		var list = service.search(search);
		model.put("list", list);
		return "category/list";
	}
	
	@PostMapping("upload")
	String upload(@RequestParam MultipartFile file) {
		return "redirect/admin/category";
	}
}
