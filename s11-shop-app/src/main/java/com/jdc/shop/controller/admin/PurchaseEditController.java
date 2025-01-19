package com.jdc.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jdc.shop.controller.input.PurchaseForm;
import com.jdc.shop.model.transaction.service.PurchaseAdminService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("purchase")
@RequestMapping("admin/purchase/edit")
public class PurchaseEditController {
	
	private final PurchaseAdminService service;
	
	@GetMapping
	String startFlow(HttpSession session, ModelMap model) {
		session.removeAttribute("purchase");
		model.remove("purchase");
		return "redirect:/admin/purchase/edit/supplier";
	}

	@GetMapping("supplier")
	String supplier(ModelMap model) {
		if(model.get("purchase") == null) {
			model.put("purchase", new PurchaseForm());
		}
		return "purchase/edit/supplier";
	}
	
	/**
	 * Form Value will be Supplier Informations
	 * @param form
	 * @return
	 */
	@PostMapping("purchase")
	String purchase(@ModelAttribute("purchase") PurchaseForm form) {
		
		form.validateSupplier();
		
		if(!form.getErrors().isEmpty()) {
			return "purchase/edit/supplier";
		}
		
		if(form.getItems().isEmpty()) {
			form.addItem();
		}
		
		return "purchase/edit/purchase";
	}
	
	@GetMapping("purchase")
	String backToPurchase() {
		return "purchase/edit/purchase";
	}

	@PostMapping("purchase/add")
	String addPurchaseItem(@ModelAttribute("purchase") PurchaseForm form) {
		form.addItem();
		return "purchase/edit/purchase";
	}
	
	@PostMapping("purchase/remove")
	String removePurchaseItem(@RequestParam int index, @ModelAttribute("purchase") PurchaseForm form) {
		form.removeItem(index);
		return "purchase/edit/purchase";
	}

	/**
	 * Form Value will be Purchase Items
	 * 
	 * @param form
	 * @return
	 */
	@PostMapping("confirm")
	String confirm(@ModelAttribute("purchase") PurchaseForm form) {
		return "purchase/edit/confirm";
	}
	
	@PostMapping
	String save(@ModelAttribute("purchase") PurchaseForm form, HttpSession session, ModelMap model) {
		
		var id = service.save(form);
		
		session.removeAttribute("purchase");
		model.remove("purchase");
		
		return "redirect:/admin/purchase/%s".formatted(id.getCode());
	}
}
