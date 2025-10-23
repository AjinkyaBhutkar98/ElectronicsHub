package com.ajinkyabhutkar.electronicstore.controller;

import com.ajinkyabhutkar.electronicstore.dtos.AddItemToCartRequest;
import com.ajinkyabhutkar.electronicstore.dtos.ApiResponse;
import com.ajinkyabhutkar.electronicstore.dtos.CartDto;
import com.ajinkyabhutkar.electronicstore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addItemsToCart(@PathVariable Long userId,@RequestBody AddItemToCartRequest request){

        CartDto cartDto=cartService.addItemToCart(userId,request);

        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/item/{itemId}")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long userId,@PathVariable Long itemId){
        cartService.removeItemFromCart(userId,itemId);

        ApiResponse apiResponse=new ApiResponse();

        apiResponse.setMessege("Cart item removed successfully ");
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setSuccess(true);

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/deleteCart/{userId}")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long userId){

        ApiResponse apiResponse=new ApiResponse();
        cartService.clearCart(userId);
        apiResponse.setMessege("Cart cleared successfully ");
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setSuccess(true);

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long userId){

        CartDto cartDto=cartService.getCartByUser(userId);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }
}
