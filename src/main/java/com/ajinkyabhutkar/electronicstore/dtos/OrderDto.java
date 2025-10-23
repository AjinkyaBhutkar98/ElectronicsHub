package com.ajinkyabhutkar.electronicstore.dtos;

import com.ajinkyabhutkar.electronicstore.entities.OrderItem;
import com.ajinkyabhutkar.electronicstore.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private String orderStatus="PENDING";

    private String paymentStatus="NOT_PAID";

    private int orderAmount;

    private String billingAddress;

    private String billingPhone;

    private String billingName;

    private LocalDateTime orderDateTime=LocalDateTime.now();

    private LocalDateTime deliveredDateTime;

    private UserDto user;

    private List<OrderItemDto> orderItemList=new ArrayList<>();
}
