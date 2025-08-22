package com.ajinkyabhutkar.electronicstore.controller;

import com.ajinkyabhutkar.electronicstore.dtos.ApiResponse;
import com.ajinkyabhutkar.electronicstore.dtos.CategoryDto;
import com.ajinkyabhutkar.electronicstore.dtos.CustomPaging;
import com.ajinkyabhutkar.electronicstore.dtos.UserDto;
import com.ajinkyabhutkar.electronicstore.services.CategoryService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> CreateCategory(@RequestBody CategoryDto categoryDto){

        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PostMapping("/create/multiplecategories")
    public ResponseEntity<List<CategoryDto>> createCategories(@RequestBody List<CategoryDto> categoryDtos) {
        List<CategoryDto> savedCategories = categoryService.createCategories(categoryDtos);
        return ResponseEntity.ok(savedCategories);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Long id){

        return new ResponseEntity<>(categoryService.updateCategory(categoryDto, id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){

        categoryService.deleteCategory(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessege("category deleted successfully");
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setSuccess(true);

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<CustomPaging<CategoryDto>> getAll(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "size",defaultValue = "3") int size,
            @RequestParam(name= "sortBy",defaultValue = "name") String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "asc") String sortDir

    ){

        CustomPaging<CategoryDto> allCategories=categoryService.getAllCategories(pageNo,size,sortBy,sortDir);
        return new ResponseEntity<>(allCategories,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.getCategoryById(id),HttpStatus.OK);
    }

    @GetMapping("/search/{categoryName}")
    public ResponseEntity<List<CategoryDto>> searchCategory(@PathVariable String categoryName){

        return new ResponseEntity<>(categoryService.searchCategoryByTitle(categoryName),HttpStatus.OK);
    }






}
