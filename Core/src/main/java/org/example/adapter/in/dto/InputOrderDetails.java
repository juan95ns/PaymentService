package org.example.adapter.in.dto;

import java.util.List;

public record InputOrderDetails(List<InputOrderItem> orderItems,
                                InputOrderAddress billingAddress,
                                InputOrderAddress shippingAddress,
                                InputUserDetails userDetails) {
}
