package com.ajinkyabhutkar.electronicstore.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderRequest {

    //with this dto user will pass cart id and user id into json instead of url

    @NotNull(message = "cart id cannot be blank")
    private Long cartId;

    @NotNull(message = "user id cannot be blank")
    private Long userId;

    private String orderStatus="PENDING";
    private String paymentStatus="NOT_PAID";

    @NotBlank(message = "please fill billing address")
    private String billingAddress;

    @NotBlank(message = "please fill billing phone")
    private String billingPhone;

    @NotBlank(message = "please fill billing name")
    private String billingName;
}
