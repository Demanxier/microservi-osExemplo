package com.demanxier.Pedido.service;

import com.demanxier.Pedido.model.OrderRequestDTO;
import com.demanxier.Pedido.model.OrderResponseDTO;
import com.demanxier.Pedido.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {
        // O nome 'product-catalog-service' é resolvido pelo Eureka
        String productUrl = "http://product-catalog-service/products/" + orderRequest.getProductId();

        // 1. Chama o microsserviço de catálogo para buscar o produto
        ProductDTO product = restTemplate.getForObject(productUrl, ProductDTO.class);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        // 2. Verifica se há estoque suficiente
        if (product.getStock() < orderRequest.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estoque insuficiente");
        }

        // 3. Simula a criação do pedido
        double total = product.getPrice() * orderRequest.getQuantity();

        // Aqui você poderia salvar o pedido em um banco de dados
        // e reduzir o estoque no serviço de produtos (via outra chamada HTTP)

        return new OrderResponseDTO(UUID.randomUUID().toString(), "PEDIDO_CRIADO", total);
    }
    }
