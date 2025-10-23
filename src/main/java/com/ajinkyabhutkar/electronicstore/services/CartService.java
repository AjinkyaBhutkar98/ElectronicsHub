package com.ajinkyabhutkar.electronicstore.services;

import com.ajinkyabhutkar.electronicstore.dtos.AddItemToCartRequest;
import com.ajinkyabhutkar.electronicstore.dtos.CartDto;

public interface CartService {

    // add item to cart logic
    //Case 1:If a cart is not available for user in database we will create the cart and then add item
    //Case 2:If cart is available then we will add the items to cart
    public CartDto addItemToCart(Long userId, AddItemToCartRequest addItemToCartRequest);

    //remove item from cart
    void removeItemFromCart(Long userId,Long cartItemId);

    //clear cart for user
    void clearCart(Long userId);

    //get cart by user
    public CartDto getCartByUser(Long userId);
}
