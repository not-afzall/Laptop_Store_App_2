package com.afz.lapstore.service;

import com.afz.lapstore.dto.*;
import com.afz.lapstore.entity.*;
import com.afz.lapstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LaptopRepository laptopRepository;

    public OrderListResponseDTO getOrdersByBuyer(Long buyerId) {
        List<OrderResponseDTO> orders = orderRepository.findByBuyerId(buyerId)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();

        OrderListResponseDTO responseDTO = new OrderListResponseDTO();
        responseDTO.setOrders(orders);

        return responseDTO;

    }

    @Transactional
    public OrderResponseDTO placeOrder(OrderRequestDTO request) {

        User buyer = userRepository.findById(request.getBuyerId())
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        Laptop laptop = laptopRepository.findById(request.getLaptopId())
                .orElseThrow(() -> new RuntimeException("Laptop not found"));

        if (Boolean.TRUE.equals(laptop.getSold())) {
            throw new RuntimeException("Laptop already sold");
        }

        laptop.setSold(true);
        laptopRepository.save(laptop);

        Order order = new Order();
        order.setBuyer(buyer);
        order.setLaptop(laptop);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        Order savedOrder = orderRepository.save(order);

        return convertToResponseDTO(savedOrder);
    }

    public OrderResponseDTO getOrderById(Long userId, Long orderId) {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {
            return null;
        }

        Order order = optionalOrder.get();

        return convertToResponseDTO(order);
    }

    private OrderResponseDTO convertToResponseDTO(Order order) {

        OrderResponseDTO responseDTO = new OrderResponseDTO();

        responseDTO.setOrderId(order.getId());
        responseDTO.setBuyerName(order.getBuyer().getName());
        responseDTO.setLaptopBrand(order.getLaptop().getBrand());
        responseDTO.setLaptopModel(order.getLaptop().getModel());
        responseDTO.setPrice(order.getLaptop().getPrice());
        responseDTO.setStatus(order.getStatus());

        return responseDTO;
    }
}
