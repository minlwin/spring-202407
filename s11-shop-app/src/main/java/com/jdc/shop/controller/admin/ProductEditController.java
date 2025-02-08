package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.shop.controller.input.ProductForm;
import com.jdc.shop.model.master.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/product/{id}/edit")
public class ProductEditController {
	
	private final ProductService service;

	@GetMapping
	String edit() {
		return "product/edit";
	}
	
	@PostMapping
	String save(@PathVariable int id, 
			@Validated @ModelAttribute(name = "form") ProductForm productForm, BindingResult result) {
		
		if(result.hasErrors()) {
			return "product/edit";
		}
		
		service.save(id, productForm);
		
		return "redirect:/admin/product/%s/details".formatted(id);
	}
	
	@ModelAttribute(name = "form")
	ProductForm form(@PathVariable int id) {
		return service.findForEdit(id);
	}
}
