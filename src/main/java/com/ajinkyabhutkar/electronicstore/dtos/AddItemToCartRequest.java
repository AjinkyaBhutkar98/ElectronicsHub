package com.ajinkyabhutkar.electronicstore.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddItemToCartRequest {

    @NotBlank(message = "please enter product id")
    private Long productId;

    @NotBlank(message = "please enter product quantity")
    private int quantity;
}
