package com.ajinkyabhutkar.electronicstore.services.impl;

import com.ajinkyabhutkar.electronicstore.dtos.CategoryDto;
import com.ajinkyabhutkar.electronicstore.dtos.PageableResponse;
import com.ajinkyabhutkar.electronicstore.entities.Category;
import com.ajinkyabhutkar.electronicstore.exceptions.ResourceNotFoundException;
import com.ajinkyabhutkar.electronicstore.repositories.CategoryRepo;
import com.ajinkyabhutkar.electronicstore.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category=modelMapper.map(categoryDto,Category.class);

        Category savedCategory=categoryRepo.save(category);

        return modelMapper.map(savedCategory,CategoryDto.class);

    }

    @Override
    public List<CategoryDto> createCategories(List<CategoryDto> categoryDtos) {

        // Convert DTOs to entities
        List<Category> categories = categoryDtos.stream()
                .map(dto -> modelMapper.map(dto, Category.class))
                .toList(); // Java 16+, use .collect(Collectors.toList()) if Java 8-15

        // Save all in batch
        List<Category> savedCategories = categoryRepo.saveAll(categories);

        // Convert back to DTOs
        return savedCategories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {

        Category category=categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category Not Found id: !!"+id));

        Category updatedCategory=categoryRepo.save(modelMapper.map(categoryDto, Category.class));

        return modelMapper.map(updatedCategory,CategoryDto.class);

    }

    @Override
    public void deleteCategory(Long id) {
        Category category=categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category Not Found id: !!"+id));

        categoryRepo.delete(category);
        System.out.println("category deleted");
    }

    @Override
    public PageableResponse<CategoryDto> getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

        Page<Category> allCategories=categoryRepo.findAll(pageable);

        Page<CategoryDto> cateDtos=allCategories.map(category->modelMapper.map(category,CategoryDto.class));
        return  PageableResponse.fromPage(cateDtos);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {

        Category category=categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category Not Found id: !!"+id));

        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> searchCategoryByTitle(String keyword) {

        List<Category> searchedCategs=categoryRepo.findByTitleContaining(keyword).orElseThrow(()->new ResourceNotFoundException("category not found"));

        return searchedCategs.stream().map(searchedCateg->modelMapper.map(searchedCateg,CategoryDto.class)).toList();
    }
}
