package com.ajinkyabhutkar.electronicstore.services;


import com.ajinkyabhutkar.electronicstore.dtos.CustomPaging;
import com.ajinkyabhutkar.electronicstore.dtos.ProductDto;
import com.ajinkyabhutkar.electronicstore.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> searchProduct(String title);

    List<ProductDto> searchByProductStatus();

    ProductDto createProduct(ProductDto productDto);

    ProductDto getProduct(Long id);

    CustomPaging<ProductDto> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);

    ProductDto updateProduct(ProductDto productDto,Long id);

    void deleteProduct(Long id);



}
