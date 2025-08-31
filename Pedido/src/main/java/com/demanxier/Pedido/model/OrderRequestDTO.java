package com.demanxier.Pedido.model;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private Long productId;
    private int quantity;
}
