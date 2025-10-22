package com.ajinkyabhutkar.electronicstore.dtos;


import com.ajinkyabhutkar.electronicstore.entities.Category;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    private Long id;

    @NotBlank(message = "please enter title for product!")
    @Size(min = 5)
    private String title;

    private String description;

    @NotBlank(message = "price cannot be empty")
    @Size(min = 1)
    private double price;

    private LocalDateTime createdDate=LocalDateTime.now();

    private boolean isLive;

    private boolean inStock;

    private double discountedPrice;

    private int quantity;

    private String productImage;

    private CategoryDto category;
}
