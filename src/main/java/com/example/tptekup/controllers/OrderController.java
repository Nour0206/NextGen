package com.example.tptekup.controllers;

import com.example.tptekup.Entities.Order;
import com.example.tptekup.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/addOrder")
    public String addOrder(Model model) {
        Order order = new Order();
        model.addAttribute("OrderForm", order);
        return "new_order";
    }

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveOrder(@ModelAttribute("OrderForm") Order order) {
        orderService.createOrder(order);
        return "redirect:/allOrders";
    }

    @RequestMapping("/allOrders")
    public String listOrders(Model model) {
        List<Order> listOrders = orderService.getAllOrders();
        model.addAttribute("listOrders", listOrders);
        return "list_orders";
    }

    @GetMapping("deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") long id) {
        orderService.deleteOrder(id);
        return "redirect:/allOrders";
    }

    @GetMapping("editOrder/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "update_order";
    }

    @PostMapping("updateOrder/{id}")
    public String updateOrder(@PathVariable("id") long id, Order order) {
        orderService.updateOrder(order);
        return "redirect:/allOrders";
    }
}
