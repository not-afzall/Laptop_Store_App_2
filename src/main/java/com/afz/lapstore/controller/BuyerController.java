package com.afz.lapstore.controller;

import com.afz.lapstore.dto.*;
import com.afz.lapstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/myOrders/{buyerId}")
    public OrderListResponseDTO myOrders(@PathVariable Long buyerId) {

        return orderService.getOrdersByBuyer(buyerId);
    }

    @GetMapping("/{userId}/{orderId}")
    public OrderResponseDTO getOrderById(
            @PathVariable Long userId,
            @PathVariable Long orderId) {

        return orderService.getOrderById(userId, orderId);
    }

    @PostMapping("/buy")
    public OrderResponseDTO buyLaptop(
            @RequestBody OrderRequestDTO requestDTO) {

        return orderService.placeOrder(requestDTO);
    }
}
