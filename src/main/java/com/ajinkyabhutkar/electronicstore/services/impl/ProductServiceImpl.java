package com.ajinkyabhutkar.electronicstore.services.impl;

import com.ajinkyabhutkar.electronicstore.dtos.PageableResponse;
import com.ajinkyabhutkar.electronicstore.dtos.ProductDto;
import com.ajinkyabhutkar.electronicstore.entities.Category;
import com.ajinkyabhutkar.electronicstore.entities.Product;
import com.ajinkyabhutkar.electronicstore.exceptions.ResourceNotFoundException;
import com.ajinkyabhutkar.electronicstore.helper.Helper;
import com.ajinkyabhutkar.electronicstore.repositories.CategoryRepo;
import com.ajinkyabhutkar.electronicstore.repositories.ProductRepo;
import com.ajinkyabhutkar.electronicstore.services.CategoryService;
import com.ajinkyabhutkar.electronicstore.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<ProductDto> searchProduct(String title) {

        List<Product> products=productRepo.findByTitleContaining(title).orElseThrow(()->new ResourceNotFoundException( "there are no products"));

        List<ProductDto> productDtos=products.stream().map(product->modelMapper.map(product,ProductDto.class)).toList();

        return productDtos;

    }

    @Override
    public List<ProductDto> searchByProductStatus( ) {
        List<Product> products=productRepo.findByIsLiveTrue().orElseThrow(()->new ResourceNotFoundException( "there are no products "));

        List<ProductDto> productDtos=products.stream().map(product->modelMapper.map(product,ProductDto.class)).toList();

        return productDtos;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product product=modelMapper.map(productDto,Product.class);

        productRepo.save(product);

        return modelMapper.map(product,ProductDto.class);
    }

    @Override
    public ProductDto getProduct(Long id) {

        Product product=productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("product not found with id "+id));

        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

        Page<Product> allUsers=productRepo.findAll(pageable);

        Page<ProductDto> allProductsDto=allUsers.map(product->modelMapper.map(product,ProductDto.class));
        return PageableResponse.fromPage(allProductsDto);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id) {
        Product product=productRepo.findById(productDto.getId()).orElseThrow(()->new ResourceNotFoundException("product not found with "+id));

        product=productRepo.save(modelMapper.map(productDto,Product.class));

        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public void deleteProduct(Long id) {

        Product product=productRepo.findById(id).orElseThrow(()->new RuntimeException("product not found with id"+id));

        productRepo.delete(product);


    }

    @Override
    public ProductDto createWithCategory(ProductDto productDto, Long categoryId) {
        Product product=modelMapper.map(productDto,Product.class);
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("category not found with id: {}"+categoryId));
        product.setCategory(category);
        Product savedProduct=productRepo.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }


    @Override
    public ProductDto updateWithCategory(Long productId, Long categoryId) {

        Product product=productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found with id:"+productId));
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category Not Found with id:"+categoryId));

        product.setCategory(category);
        productRepo.save(product);
        return modelMapper.map(product,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllProductsWithCategory(Long categoryId,int pageNo,int size,String sortBy,String sortDir) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category id not found"+categoryId));

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo,size,sort);
        Page<Product> page=productRepo.findByCategory(category,pageable);

        return Helper.getPageableResponse(page,ProductDto.class);

    }
}
