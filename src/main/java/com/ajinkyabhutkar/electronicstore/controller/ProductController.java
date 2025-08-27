package com.ajinkyabhutkar.electronicstore.controller;

import com.ajinkyabhutkar.electronicstore.dtos.ApiResponse;
import com.ajinkyabhutkar.electronicstore.dtos.CustomPaging;
import com.ajinkyabhutkar.electronicstore.dtos.ProductDto;
import com.ajinkyabhutkar.electronicstore.dtos.UserDto;
import com.ajinkyabhutkar.electronicstore.entities.Product;
import com.ajinkyabhutkar.electronicstore.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //create product
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){

        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable Long id){

        return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CustomPaging<ProductDto>> fetchAllProducts(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "size",defaultValue = "3") int size,
            @RequestParam(name= "sortBy",defaultValue = "title") String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "asc") String sortDir

    ){

        CustomPaging<ProductDto> allProducts=productService.getAllProducts(pageNo,size,sortBy,sortDir);
        return new ResponseEntity<>(allProducts,HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<ProductDto>> getByTitle(@PathVariable String title){

        return new ResponseEntity<>(productService.searchProduct(title),HttpStatus.OK);
    }

    @GetMapping("/live")
    public ResponseEntity<List<ProductDto>> getByStatus(){

        return new ResponseEntity<>(productService.searchByProductStatus(),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,Long id){


        return new ResponseEntity<>(productService.updateProduct(productDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id){

        ProductDto product=productService.getProduct(id);
        productService.deleteProduct(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessege("Product  : "+product.getTitle()+" id: "+product.getId()+" is deleted successfully!");
        apiResponse.setSuccess(true);
        apiResponse.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }


}
