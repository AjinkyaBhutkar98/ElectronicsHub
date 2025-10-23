package com.ajinkyabhutkar.electronicstore.dtos;

import com.ajinkyabhutkar.electronicstore.entities.CartItem;
import com.ajinkyabhutkar.electronicstore.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long id;
    private LocalDateTime createdAt;
    private UserDto user;
    private List<CartItemDto> cartItemList=new ArrayList<>();
}
