package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jdc.shop.controller.input.PurchaseForm;
import com.jdc.shop.controller.input.SupplierCodeForm;
import com.jdc.shop.controller.input.SupplierForm;

@Controller
@SessionAttributes({
	"purchase", "supplier", "supplierCode"
})
@RequestMapping("admin/purchase/edit")
public class PurchaseEditController {

	@GetMapping
	String edit() {
		return "purchase/edit/supplier/search";
	}
	
	@PostMapping("supplier/select")
	String selectSupplier(
			@Validated @ModelAttribute("supplierCode") SupplierCodeForm form, BindingResult result, ModelMap model) {
		
		if(result.hasErrors()) {
			return "purchase/edit/supplier/search";
		}
		model.put("supplierUrl", "admin/purchase/edit");
		
		return "purchase/edit/purchase";
	}
	

	@GetMapping("supplier/edit")
	String addNewSupplier() {
		return "purchase/edit/supplier/edit";
	}

	@PostMapping("supplier/edit")
	String selectSupplier(
			@Validated @ModelAttribute("supplier") SupplierForm form, BindingResult result, ModelMap model) {
		
		if(result.hasErrors()) {
			return "purchase/edit/supplier/edit";
		}
		
		model.put("supplierUrl", "admin/purchase/edit/supplier/edit");
		
		return "purchase/edit/purchase";
	}

	@PostMapping("confirm")
	String confirm(
			@Validated @ModelAttribute("purchase") PurchaseForm form, BindingResult result) {
		
		
		
		return "purchase/edit/confirm";
	}
	
	@PostMapping
	String save() {
		return "redirect:/admin/purchase/%s";
	}
	
	@ModelAttribute("purchase")
	PurchaseForm purchaseForm() {
		return new PurchaseForm();
	}
	
	@ModelAttribute("supplierCode")
	SupplierCodeForm supplierCodeForm() {
		return new SupplierCodeForm();
	}
	
	@ModelAttribute("supplier")
	SupplierForm supplierForm() {
		return new SupplierForm();
	}
}
