package com.ajinkyabhutkar.electronicstore.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddItemToCartRequest {

    @NotNull(message = "Please enter product id")
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}
