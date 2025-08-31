package com.demanxier.Pedido.controller;

import com.demanxier.Pedido.model.OrderRequestDTO;
import com.demanxier.Pedido.model.OrderResponseDTO;
import com.demanxier.Pedido.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequest){
        OrderResponseDTO response = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(response);
    }
}
