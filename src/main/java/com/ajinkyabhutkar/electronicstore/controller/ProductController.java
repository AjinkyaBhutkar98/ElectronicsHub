package com.ajinkyabhutkar.electronicstore.controller;

import com.ajinkyabhutkar.electronicstore.dtos.*;
import com.ajinkyabhutkar.electronicstore.services.FileUploadService;
import com.ajinkyabhutkar.electronicstore.services.ProductService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileUploadService fileUploadService;

    @Value("${product.image.path}")
    private String imagePath;

    //create product
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {

        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable Long id) {

        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<ProductDto>> fetchAllProducts(
            @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "size", defaultValue = "3") int size,
            @RequestParam(name = "sortBy", defaultValue = "title") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir

    ) {

        PageableResponse<ProductDto> allProducts = productService.getAllProducts(pageNo, size, sortBy, sortDir);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<ProductDto>> getByTitle(@PathVariable String title) {

        return new ResponseEntity<>(productService.searchProduct(title), HttpStatus.OK);
    }

    @GetMapping("/live")
    public ResponseEntity<List<ProductDto>> getByStatus() {

        return new ResponseEntity<>(productService.searchByProductStatus(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, Long id) {


        return new ResponseEntity<>(productService.updateProduct(productDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id) {

        ProductDto product = productService.getProduct(id);
        productService.deleteProduct(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessege("Product  : " + product.getTitle() + " id: " + product.getId() + " is deleted successfully!");
        apiResponse.setSuccess(true);
        apiResponse.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    //upload image
    @PostMapping("/imageUpload/{productId}")
    public ResponseEntity<FileResponse> uploadProductImage
    (@PathVariable Long productId, @RequestParam("productImage") MultipartFile productImage) throws IOException {

        String fileName = fileUploadService.uploadFile(productImage, imagePath);

        ProductDto productDto = productService.getProduct(productId);
        productDto.setProductImage(fileName);

        ProductDto productDto1 = productService.updateProduct(productDto, productId);

        FileResponse fileResponse = new FileResponse();
        fileResponse.setFileName(productDto1.getProductImage());
        fileResponse.setMessege("Product image successfully uploaded !!!");

        return new ResponseEntity<>(fileResponse, HttpStatus.CREATED);
    }

    //serve image
    @GetMapping(value = "/imageServe/{productId}")
    public void serveProductImage(@PathVariable Long productId, HttpServletResponse response) throws IOException {

        ProductDto productDto = productService.getProduct(productId);

        InputStream resource = fileUploadService.getFile(imagePath, productDto.getProductImage());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<PageableResponse<ProductDto>> getByCategory(@PathVariable Long categoryId,
                                                                      @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                                      @RequestParam(name = "size", defaultValue = "3") int size,
                                                                      @RequestParam(name = "sortBy", defaultValue = "title") String sortBy,
                                                                      @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir
    ) {

        PageableResponse<ProductDto> productDtoPageableResponse = productService.getAllProductsWithCategory(categoryId,pageNo, size, sortBy, sortDir);

        return new ResponseEntity<>(productDtoPageableResponse, HttpStatus.OK);
    }

}
