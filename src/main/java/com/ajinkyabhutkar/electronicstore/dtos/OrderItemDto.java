package com.ajinkyabhutkar.electronicstore.dtos;

import com.ajinkyabhutkar.electronicstore.entities.Order;
import com.ajinkyabhutkar.electronicstore.entities.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {

    private int quantity;

    private int totalPrice;

    private ProductDto product;

    private OrderDto order;
}
