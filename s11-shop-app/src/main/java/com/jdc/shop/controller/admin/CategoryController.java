package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.controller.input.CategoryForm;
import com.jdc.shop.controller.input.CategorySearch;
import com.jdc.shop.controller.output.AjaxSaveResult;
import com.jdc.shop.model.master.service.CategoryManagementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/category")
public class CategoryController {
	
	private final CategoryManagementService service;

	@GetMapping
	String search(
			ModelMap model, 
			CategorySearch search, 
			@RequestParam(required = false, defaultValue = "4") int page,
			@RequestParam(required = false, defaultValue = "1") int size) {
		var pageResult = service.search(search, page, size);
		model.put("pageResult", pageResult);
		return "category/list";
	}
	
	@PostMapping("upload")
	String upload(@RequestParam MultipartFile file) {
		service.upload(file);
		return "redirect:/admin/category";
	}
	
	@PostMapping
	@ResponseBody
	AjaxSaveResult save(@Validated CategoryForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return new AjaxSaveResult(result.getFieldErrors().stream().map(a -> a.getDefaultMessage()).toList());
		}
		
		service.save(form);
		
		return new AjaxSaveResult(null);
	}
}
