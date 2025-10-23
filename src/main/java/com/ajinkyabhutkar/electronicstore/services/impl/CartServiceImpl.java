package com.ajinkyabhutkar.electronicstore.services.impl;

import com.ajinkyabhutkar.electronicstore.dtos.AddItemToCartRequest;
import com.ajinkyabhutkar.electronicstore.dtos.CartDto;
import com.ajinkyabhutkar.electronicstore.entities.Cart;
import com.ajinkyabhutkar.electronicstore.entities.CartItem;
import com.ajinkyabhutkar.electronicstore.entities.Product;
import com.ajinkyabhutkar.electronicstore.entities.User;
import com.ajinkyabhutkar.electronicstore.exceptions.BadApiRequestException;
import com.ajinkyabhutkar.electronicstore.exceptions.ResourceNotFoundException;
import com.ajinkyabhutkar.electronicstore.repositories.CartItemRepo;
import com.ajinkyabhutkar.electronicstore.repositories.CartRepo;
import com.ajinkyabhutkar.electronicstore.repositories.ProductRepo;
import com.ajinkyabhutkar.electronicstore.repositories.UserRepo;
import com.ajinkyabhutkar.electronicstore.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {


    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Override
    public CartDto addItemToCart(Long userId, AddItemToCartRequest addItemToCartRequest) {

        //take product quantity and id from request
        int productQuantity=addItemToCartRequest.getQuantity();
        Long productId= addItemToCartRequest.getProductId();

        if(productQuantity<=0){
            throw new BadApiRequestException("Please add valid quantity to the cart");
        }

        //fetch  product
        Product product=productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product Not Found!!"));

        //fetch user
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found!!"));

        //declare cart
        Cart cart=null;


        try{
            //if available use cart or throw NoSuchElementException and create a cart
            cart=cartRepo.findByUser(user).get();
        }catch (NoSuchElementException noSuchElementException){
            cart=new Cart();
            cart.setCreatedAt(LocalDateTime.now());
        }

        //we cannot use boolean in stream map so we will use AtomicBoolean
        AtomicBoolean isUpdated= new AtomicBoolean(false);

        //perform cart operations
        List<CartItem> cartItemList=cart.getCartItemList();

        List<CartItem> updatedCartItemList=cartItemList.stream().map(cartItem -> {
            if(Objects.equals(cartItem.getProduct().getId(), product.getId())){
                //item already present in cart update the cart items
                cartItem.setQuantity(productQuantity);
                cartItem.setTotalPrice((int) (productQuantity*product.getDiscountedPrice()));
                isUpdated.set(true);
            }
            return cartItem;
        }).collect(Collectors.toList());

//        cart.setCartItemList(updatedCartItemList);

        //add product in cart item
        if(!isUpdated.get()){
            CartItem cartItem=CartItem.builder().quantity(productQuantity)
                    .totalPrice((int) (productQuantity*product.getDiscountedPrice()))
                    .cart(cart)
                    .product(product)
                    .build();

            cart.getCartItemList().add(cartItem);
        }
        //add cart items in cart

        cart.setUser(user);

        Cart updatedCart=cartRepo.save(cart);

        return modelMapper.map(updatedCart,CartDto.class);
    }

    @Override
    public void removeItemFromCart(Long userId, Long cartItemId) {

        CartItem cartItem=cartItemRepo.findById(cartItemId).orElseThrow(()->new ResourceNotFoundException("Cart item not found "));
        cartItemRepo.delete(cartItem);

    }

    @Override
    public void clearCart(Long userId) {

        //fetch user
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found!!"));

        //fetch cart
        Cart cart=cartRepo.findByUser(user).orElseThrow(()->new ResourceNotFoundException("Cart not found for user"));

        cart.getCartItemList().clear();

        cartRepo.save(cart);
    }

    @Override
    public CartDto getCartByUser(Long userId) {
        //fetch user
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found!!"));

        //fetch cart
        Cart cart=cartRepo.findByUser(user).orElseThrow(()->new ResourceNotFoundException("Cart not found for user"));

        return modelMapper.map(cart,CartDto.class);
    }
}
