package com.jdc.shop.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jdc.shop.controller.input.ShoppingCart;
import com.jdc.shop.controller.input.ShoppingCartItem;
import com.jdc.shop.model.master.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("cart")
@SessionAttributes("cart")
public class ShoppingCartController {
	
	private final ProductService productService;
	
	@ResponseBody
	@GetMapping("add")
	int addToCart(
			@ModelAttribute("cart") ShoppingCart shoppingCart, 
			@RequestParam int productId) {
		
		var product = productService.findForEdit(productId);
		shoppingCart.addToCart(ShoppingCartItem.from(product));
		
		return shoppingCart.getTotalCount();
	}

	@ModelAttribute(name = "cart")
	ShoppingCart shoppingCart() {
		return new ShoppingCart();
	}
}
