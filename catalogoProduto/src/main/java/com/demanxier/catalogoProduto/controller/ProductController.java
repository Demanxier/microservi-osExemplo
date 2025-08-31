package com.demanxier.catalogoProduto.controller;

import com.demanxier.catalogoProduto.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final List<Product> productList = new ArrayList<>();

    @PostConstruct
    private void initDb(){
        productList.add(new Product(1L,"Notebook Gamer", 7500.00,10));
        productList.add(new Product(2L,"Mouse sem Fio", 150.00, 50));
        productList.add(new Product(3L,"Monitor 27 4K", 2000.00, 5));
    }

    @GetMapping
    public List<Product> findAll(){
        return productList;
    }

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable Long id){
        return productList.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
