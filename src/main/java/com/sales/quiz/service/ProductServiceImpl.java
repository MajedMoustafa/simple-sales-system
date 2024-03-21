package com.sales.quiz.service;

import com.sales.quiz.converter.ProductDTO;
import com.sales.quiz.converter.ProductMapper;
import com.sales.quiz.exception.ResourceNotFoundException;
import com.sales.quiz.model.Product;
import com.sales.quiz.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::ProductToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProduct(long id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        ProductDTO productDTO = productMapper.ProductToProductDTO(product);
        return productDTO;
    }

    @Override
    public Product add(ProductDTO product) {
        Product productToSave = productMapper.ProductDTOToProduct(product);
        Product productSaved = productRepository.saveAndFlush(productToSave);
        return productSaved;
    }

    @Override
    public void update(ProductDTO productDTO) {
        Product product = productRepository
                .findById(productDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Product productToSave = productMapper.ProductDTOToProduct(productDTO);
        productRepository.saveAndFlush(productToSave);
    }
}
