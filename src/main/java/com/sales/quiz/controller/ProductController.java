package com.sales.quiz.controller;


import com.sales.quiz.converter.ProductDTO;
import com.sales.quiz.service.ProductService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping(value = { "", "/" })
    public @NotNull List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = {  "/{productId}" })
    public ProductDTO getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void addProduct(@RequestBody ProductDTO productDTO) {
        productService.add(productDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    void updateProduct(@RequestBody ProductDTO productDTO) {
        productService.update(productDTO);
    }
}
