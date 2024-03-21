package com.sales.quiz.service;

import com.sales.quiz.converter.ProductDTO;
import com.sales.quiz.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Validated
public interface ProductService {

    @NotNull List<ProductDTO> getAllProducts();

    ProductDTO getProduct(@Min(value = 1L, message = "Invalid product ID.") long id);

    Product add(ProductDTO product);

    void update(ProductDTO product);
}
