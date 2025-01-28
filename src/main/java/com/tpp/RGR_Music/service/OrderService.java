package com.tpp.RGR_Music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpp.RGR_Music.models.Orders;
import com.tpp.RGR_Music.models.repository.OrdersRepository;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository orderRepository;

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> findOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public void saveOrder(Orders order) {
        orderRepository.save(order);
    }

    public void updateOrder(Orders updatedOrder) {
        Optional<Orders> existingOrderOpt = orderRepository.findById(updatedOrder.getOrderId());
    
        if (existingOrderOpt.isPresent()) {
            Orders existingOrder = existingOrderOpt.get();
            existingOrder.setVisitor(updatedOrder.getVisitor());
            existingOrder.setOrderDate(updatedOrder.getOrderDate());
            orderRepository.save(existingOrder);
        }
    }

    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }
}
