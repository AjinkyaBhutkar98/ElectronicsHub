package com.ajinkyabhutkar.electronicstore.services;

import com.ajinkyabhutkar.electronicstore.dtos.CreateOrderRequest;
import com.ajinkyabhutkar.electronicstore.dtos.OrderDto;
import com.ajinkyabhutkar.electronicstore.dtos.PageableResponse;

import java.util.List;

public interface OrderService {

    //create order
    OrderDto createOrder(CreateOrderRequest orderDto);

    //remove order
    void removeOrder(Long orderId);

    //get orders of user
    List<OrderDto> getOrdersOfUser(Long userId);

    //get orders
    PageableResponse<OrderDto> getOrders(int pageNumber,int pageSize,String sortBy,String sortDir);

    //other methods related to order
}
