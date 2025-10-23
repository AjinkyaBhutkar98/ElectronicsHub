package com.ajinkyabhutkar.electronicstore.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddItemToCartRequest {

    private Long productId;
    private int quantity;
}
