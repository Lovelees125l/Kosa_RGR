package com.tpp.RGR_Music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpp.RGR_Music.models.OrderItems;
import com.tpp.RGR_Music.models.repository.OrderItemsRepository;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemsRepository orderItemRepository;

    public List<OrderItems> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItems> findOrderItemById(Integer id) {
        return orderItemRepository.findById(id);
    }

    public void saveOrderItem(OrderItems orderItem) {
        orderItemRepository.save(orderItem);
    }

    public void updateOrderItem(OrderItems updatedOrderItem) {
        Optional<OrderItems> existingOrderItemOpt = orderItemRepository.findById(updatedOrderItem.getOrderItemId());

        if (existingOrderItemOpt.isPresent()) {
            OrderItems existingOrderItem = existingOrderItemOpt.get();
            existingOrderItem.setOrder(updatedOrderItem.getOrder());
            existingOrderItem.setMenuItem(updatedOrderItem.getMenuItem());
            existingOrderItem.setQuantity(updatedOrderItem.getQuantity());
            orderItemRepository.save(existingOrderItem);
        }
    }

    public void deleteOrderItemById(Integer id) {
        orderItemRepository.deleteById(id);
    }
}
