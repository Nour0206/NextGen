package com.example.tptekup.controllers;

import com.example.tptekup.Entities.CartItem;
import com.example.tptekup.Services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @RequestMapping("/addCartItem")
    public String addCartItem(Model model) {
        CartItem cartItem = new CartItem();
        model.addAttribute("CartItemForm", cartItem);
        return "new_cart_item";
    }

    @RequestMapping(value = "/saveCartItem", method = RequestMethod.POST)
    public String saveCartItem(@ModelAttribute("CartItemForm") CartItem cartItem) {
        cartItemService.createCartItem(cartItem);
        return "redirect:/allCartItems";
    }

    @RequestMapping("/allCartItems")
    public String listCartItems(Model model) {
        List<CartItem> listCartItems = cartItemService.getAllCartItems();
        model.addAttribute("listCartItems", listCartItems);
        return "list_cart_items";
    }

    @GetMapping("deleteCartItem/{id}")
    public String deleteCartItem(@PathVariable("id") long id) {
        cartItemService.deleteCartItem(id);
        return "redirect:/allCartItems";
    }

    @GetMapping("editCartItem/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        CartItem cartItem = cartItemService.getCartItemById(id);
        model.addAttribute("cartItem", cartItem);
        return "update_cart_item";
    }

    @PostMapping("updateCartItem/{id}")
    public String updateCartItem(@PathVariable("id") long id, CartItem cartItem) {
        cartItemService.updateCartItem(cartItem);
        return "redirect:/allCartItems";
    }
}
