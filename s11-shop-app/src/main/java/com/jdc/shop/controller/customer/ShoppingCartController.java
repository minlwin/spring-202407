package com.jdc.shop.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jdc.shop.controller.input.ShoppingCart;
import com.jdc.shop.controller.input.ShoppingCartItem;
import com.jdc.shop.controller.output.ShoppingCartResponse;
import com.jdc.shop.model.master.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("cart")
@SessionAttributes("cart")
public class ShoppingCartController {
	
	private final ProductService productService;
	
	@GetMapping("checkout")
	String navigateToCheckOut() {
		return "checkout/shipping-address";
	}
	
	@PostMapping("checkout/confirm")
	String confirm(
			@Validated
			@ModelAttribute("cart") ShoppingCart shoppingCart, BindingResult result) {
		return "invoice/confirm";
	}
	
	@PostMapping("checkout")
	String checkOut(@ModelAttribute("cart") ShoppingCart shoppingCart) {
		return "redirect:/customer/sale/%s";
	}
	
	@ResponseBody
	@GetMapping("add")
	int addToCart(
			@ModelAttribute("cart") ShoppingCart shoppingCart, 
			@RequestParam int productId) {
		
		var product = productService.findForEdit(productId);
		shoppingCart.addToCart(ShoppingCartItem.from(product));
		
		return shoppingCart.getTotalCount();
	}
	
	@ResponseBody
	@GetMapping("plus-one")
	ShoppingCartResponse plusOne(
			@ModelAttribute("cart") ShoppingCart shoppingCart, 
			@RequestParam int productId) {
		
		shoppingCart.plusOne(productId);
		
		return ShoppingCartResponse.from(shoppingCart);
	}
	
	@ResponseBody
	@GetMapping("minus-one")
	ShoppingCartResponse minusOne(
			@ModelAttribute("cart") ShoppingCart shoppingCart, 
			@RequestParam int productId) {
		
		shoppingCart.removeOne(productId);
		
		return ShoppingCartResponse.from(shoppingCart);
	}

	@ModelAttribute(name = "cart")
	ShoppingCart shoppingCart() {
		return new ShoppingCart();
	}
}
