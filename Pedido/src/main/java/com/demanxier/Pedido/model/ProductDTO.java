package com.demanxier.Pedido.model;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private int stock;
}
