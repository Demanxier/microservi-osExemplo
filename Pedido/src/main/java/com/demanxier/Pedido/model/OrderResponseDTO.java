package com.demanxier.Pedido.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponseDTO {
    private String orderId;
    private String status;
    private double totalAmount;
}
