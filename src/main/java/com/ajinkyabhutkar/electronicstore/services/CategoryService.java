package com.ajinkyabhutkar.electronicstore.services;

import com.ajinkyabhutkar.electronicstore.dtos.CategoryDto;
import com.ajinkyabhutkar.electronicstore.dtos.PageableResponse;

import java.util.List;

public interface CategoryService {

    //crate
    CategoryDto createCategory(CategoryDto categoryDto);

    //createMultipleCategories
    List<CategoryDto> createCategories(List<CategoryDto> categoryDtos);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto,Long id);

    //delete
    void deleteCategory(Long id);

    //get all
    PageableResponse<CategoryDto> getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir);

    //get By Id
    CategoryDto getCategoryById(Long id);

    //search
    List<CategoryDto> searchCategoryByTitle(String keyword);

}
