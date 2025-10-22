package com.ajinkyabhutkar.electronicstore.dtos;

import com.ajinkyabhutkar.electronicstore.entities.Cart;
import com.ajinkyabhutkar.electronicstore.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {


    private Long id;
    private ProductDto product;
    private int quantity;
    private int totalPrice;
//    private CartDto cart;
}
