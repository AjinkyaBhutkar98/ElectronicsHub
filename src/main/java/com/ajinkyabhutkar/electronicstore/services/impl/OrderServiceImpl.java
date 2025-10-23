package com.ajinkyabhutkar.electronicstore.services.impl;

import com.ajinkyabhutkar.electronicstore.dtos.CreateOrderRequest;
import com.ajinkyabhutkar.electronicstore.dtos.OrderDto;
import com.ajinkyabhutkar.electronicstore.dtos.OrderItemDto;
import com.ajinkyabhutkar.electronicstore.dtos.PageableResponse;
import com.ajinkyabhutkar.electronicstore.entities.*;
import com.ajinkyabhutkar.electronicstore.exceptions.BadApiRequestException;
import com.ajinkyabhutkar.electronicstore.exceptions.ResourceNotFoundException;
import com.ajinkyabhutkar.electronicstore.helper.Helper;
import com.ajinkyabhutkar.electronicstore.repositories.CartRepo;
import com.ajinkyabhutkar.electronicstore.repositories.OrderRepo;
import com.ajinkyabhutkar.electronicstore.repositories.UserRepo;
import com.ajinkyabhutkar.electronicstore.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDto createOrder(CreateOrderRequest orderDto) {

        Long userId=orderDto.getUserId();
        Long cartId=orderDto.getCartId();

        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found!!"));

        //fetch Cart
        Cart cart=cartRepo.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart not found!!"));

        //fetch CartItems
        List<CartItem> cartItemsList=cart.getCartItemList();

        if(cartItemsList.size()<=0){
            throw new BadApiRequestException("Your cart is empty. please add items and try again later");
        }


        //create Order
        Order order=Order.builder()
                .billingName(orderDto.getBillingName())
                .billingAddress(orderDto.getBillingAddress())
                .billingPhone(orderDto.getBillingPhone())
                .orderDateTime(LocalDateTime.now())
                .deliveredDateTime(null)
                .orderStatus(orderDto.getOrderStatus())
                .paymentStatus(orderDto.getPaymentStatus())
                .user(user).build();

        AtomicInteger orderAmount=new AtomicInteger(0);

        //add cart items to order
        List<OrderItem> orderItemList=cartItemsList.stream().map(cartItem -> {

            OrderItem orderItem= OrderItem.builder().
                    quantity(cartItem.getQuantity())
                    .product(cartItem.getProduct())
                    .totalPrice((int) (cartItem.getQuantity()*cartItem.getProduct().getDiscountedPrice())).build();
            orderAmount.set(orderAmount.get()+orderItem.getTotalPrice());
            return orderItem;

        }).collect(Collectors.toList());

        order.setOrderItemList(orderItemList);
        order.setOrderAmount(orderAmount.get());

        //clear items from cart
        cart.getCartItemList().clear();
        cartRepo.save(cart);

        Order savedOrder=orderRepo.save(order);

        return modelMapper.map(order,OrderDto.class);
    }

    @Override
    public void removeOrder(Long orderId) {

        Order order=orderRepo.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order not found !!"));

        orderRepo.delete(order);

    }

    @Override
    public List<OrderDto> getOrdersOfUser(Long userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found!!"));
        List<Order> orders=orderRepo.findByUser(user);

        if(orders.isEmpty()){
            throw new ResourceNotFoundException("no order found against user");
        }

        List<OrderDto> orderDto2=orders.stream().map(x->modelMapper.map(x, OrderDto.class)).collect(Collectors.toList());;

        return orderDto2;
    }

    @Override
    public PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);

        Page<Order> ordersList=orderRepo.findAll(pageable);

        Page<OrderDto> ordersList2=ordersList.map(order->modelMapper.map(order, OrderDto.class));

        return Helper.getPageableResponse(ordersList2, OrderDto.class);
    }
}
