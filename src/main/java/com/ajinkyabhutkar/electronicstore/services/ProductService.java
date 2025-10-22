package com.ajinkyabhutkar.electronicstore.services;


import com.ajinkyabhutkar.electronicstore.dtos.PageableResponse;
import com.ajinkyabhutkar.electronicstore.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> searchProduct(String title);

    List<ProductDto> searchByProductStatus();

    ProductDto createProduct(ProductDto productDto);

    ProductDto getProduct(Long id);

    PageableResponse<ProductDto> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);

    ProductDto updateProduct(ProductDto productDto,Long id);

    void deleteProduct(Long id);

    //create product with category
    ProductDto createWithCategory(ProductDto productDto,Long categoryId);

    //update product with category
    ProductDto updateWithCategory(Long productId,Long categoryId);

    //get all products with associated category
    PageableResponse<ProductDto> getAllProductsWithCategory(Long CategoryId,int pageNo,int size,String sortBy,String sortDir);



}
