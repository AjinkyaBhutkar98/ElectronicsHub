package com.ajinkyabhutkar.electronicstore.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //PENDING,DISPATCHED,DELIVERED
    //ENUM
    private String orderStatus;

    //PENDING,NOT_PAID
    //enum
    //boolean false-not paid true -paid
    private String paymentStatus;

    private int orderAmount;

    @Column(length = 1000)
    private String billingAddress;

    private String billingPhone;

    private String billingName;

    private LocalDateTime orderDateTime;

    private LocalDateTime deliveredDateTime;

    //user can have many orders
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList=new ArrayList<>();
}
