package com.ajinkyabhutkar.electronicstore.controller;

import com.ajinkyabhutkar.electronicstore.dtos.ApiResponse;
import com.ajinkyabhutkar.electronicstore.dtos.CreateOrderRequest;
import com.ajinkyabhutkar.electronicstore.dtos.OrderDto;
import com.ajinkyabhutkar.electronicstore.dtos.PageableResponse;
import com.ajinkyabhutkar.electronicstore.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest){

        return new ResponseEntity<>(orderService.createOrder(createOrderRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long orderId){
        orderService.removeOrder(orderId);

        ApiResponse apiResponse=ApiResponse.builder().messege("order removed succesfully").success(true).httpStatus(HttpStatus.OK).build();

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<OrderDto>> getUserOrders(@PathVariable Long userId){

        List<OrderDto> userOrders=orderService.getOrdersOfUser(userId);

        return new ResponseEntity<>(userOrders,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<OrderDto>> getAllOrders(
            @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "size",defaultValue = "3") int size,
            @RequestParam(name= "sortBy",defaultValue = "orderDateTime") String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "desc") String sortDir
    ){

        PageableResponse<OrderDto> response=orderService.getOrders(pageNo,size,sortBy,sortDir);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
