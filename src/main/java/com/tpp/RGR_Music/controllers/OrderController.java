package com.tpp.RGR_Music.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tpp.RGR_Music.models.Orders;
import com.tpp.RGR_Music.service.OrderService;
import com.tpp.RGR_Music.service.VisitorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private VisitorService visitorService;

    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders"; // має відповідати імені вашого HTML-файлу (orders.html)
    }

    @GetMapping("/add")
    public String addOrderForm(Model model) {
        model.addAttribute("order", new Orders());
        model.addAttribute("visitors", visitorService.getAllVisitors());
        return "add-order";
    }

    @PostMapping("/add")
    public String addOrder(@Valid @ModelAttribute("order") Orders order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("visitors", visitorService.getAllVisitors());
            return "add-order";
        }
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String editOrderForm(@PathVariable("id") Integer id, Model model) {
        Optional<Orders> orderOpt = orderService.findOrderById(id);
        if (orderOpt.isPresent()) {
            model.addAttribute("order", orderOpt.get());
            model.addAttribute("visitors", visitorService.getAllVisitors());
            return "edit-order";
        }
        return "redirect:/orders";
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable("id") Integer id, @Valid @ModelAttribute("order") Orders order,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("visitors", visitorService.getAllVisitors());
            return "edit-order";
        }
        orderService.updateOrder(order);
        return "redirect:/orders";
    }

    // Видалення замовлення
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id) {
        orderService.deleteOrderById(id);  // Видаляємо замовлення за ID
        return "redirect:/orders";  // Перехід на сторінку зі списком замовлень
    }
}
